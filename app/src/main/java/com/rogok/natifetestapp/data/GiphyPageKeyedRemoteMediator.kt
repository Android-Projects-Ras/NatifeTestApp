/*
package com.rogok.natifetestapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rogok.natifetestapp.api.GiphyApi
import com.rogok.natifetestapp.models.GiphyImage
import com.rogok.natifetestapp.data.entites.GiphyImageRemoteKeys
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GiphyPageKeyedRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val db: GiphyDatabase,
    private val api: GiphyApi,
    private val query: String
) : RemoteMediator<Int, GiphyImage>() {

    val giphyDao = db.giphyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GiphyImage>
    ): MediatorResult {

        // calculate the current page to load depending on the state
        return try {
            // The network load method takes an optional `after=<user.id>` parameter. For every
            // page after the first, we pass the last user ID to let it continue from where it
            // left off. For REFRESH, pass `null` to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, we never need to prepend, since REFRESH will always load the
                // first page in the list. Immediately return, reporting end of pagination.
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    // We must explicitly check if the last item is `null` when appending,
                    // since passing `null` to networkService is only valid for initial load.
                    // If lastItem is `null` it means no items were loaded after the initial
                    // REFRESH and there are no more items to load.

                    lastItem.id
                }
            }

            val response = api.searchImages(query)*/
/*.searchUsers(query = query, after = loadKey)*//*


            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    giphyDao.deleteGiphyItems()*/
/*.deleteByQuery(query)*//*

                }

                // Insert new users into database, which invalidates the current
                // PagingData, allowing Paging to present the updates in the DB.
                giphyDao.insertGiphyList(response.giphyData)
            }

            MediatorResult.Success(endOfPaginationReached = response.nextKey == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}


*/
