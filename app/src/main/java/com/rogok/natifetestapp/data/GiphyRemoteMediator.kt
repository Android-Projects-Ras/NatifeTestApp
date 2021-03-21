package com.rogok.natifetestapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rogok.natifetestapp.api.GiphyApi
import com.rogok.natifetestapp.data.entites.GiphyImageRemoteKeys
import com.rogok.natifetestapp.models.GiphyImage
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

const val DEFAULT_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class GiphyRemoteMediator @Inject constructor(
    private val db: GiphyDatabase,
    private val api: GiphyApi,
    //private val query: String
) : RemoteMediator<Int, GiphyImage>() {

    override suspend fun load(
        loadType: LoadType,  //This tells us where we need to append the data in the page
        state: PagingState<Int, GiphyImage>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = api.searchImages() //getDoggoImages(page, state.config.pageSize)
            val images = response.giphyData
            val isEndOfList = images.isEmpty()
            db.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    db.giphyRemoteKeysDao().clearRemoteKeys()
                    db.giphyDao().deleteGiphyImages()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = images.map {
                    GiphyImageRemoteKeys(giphyId = it.idForRoom, prevKey = prevKey, nextKey = nextKey)
                }
                db.giphyRemoteKeysDao().insertAll(keys)
                db.giphyDao().insertGiphyImageList(images)//.insertAll(response)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GiphyImage>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, GiphyImage>): GiphyImageRemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { giphyImage -> db.giphyRemoteKeysDao().remoteKeysGiphyId(giphyImage.idForRoom) }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, GiphyImage>): GiphyImageRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { giphyImage -> db.giphyRemoteKeysDao().remoteKeysGiphyId(giphyImage.idForRoom) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, GiphyImage>): GiphyImageRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.idForRoom?.let {
                db.giphyRemoteKeysDao().remoteKeysGiphyId(it)
            }
        }
    }

}


