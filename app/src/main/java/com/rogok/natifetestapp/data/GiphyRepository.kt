package com.rogok.natifetestapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rogok.natifetestapp.api.GiphyApi
import com.rogok.natifetestapp.models.GiphyImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyRepository @Inject constructor(
    private val giphyApi: GiphyApi,
    private val db: GiphyDatabase
) {

    /*Pager: This API consumes whatever the RemoteMediator or PagingSource returns
     as a data source to it and returns a reactive stream of PagingData.
     It can be returned as a Flow, Observable, LiveData*/
    /*fun getSearchResult(query: String) =  // : LiveData<PagingData<GiphyImage>>
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GiphyPagingSource(giphyApi, query)}
        ).flow*//*liveData*/

    fun getGiphyImagesFlowDb(): Flow<PagingData<GiphyImage>> {
        if (db == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { db.giphyDao().getAllGiphyImages() }
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = GiphyRemoteMediator(db, giphyApi)
        ).flow
    }
}