package com.rogok.natifetestapp.data

import androidx.paging.PagingSource
import com.rogok.natifetestapp.api.GiphyApi
import com.rogok.natifetestapp.models.GiphyImage
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val GIPHY_START_PAGE_INDEX = 1

/*PagingSource: It is a generic abstract class
that is responsible for loading the paging data from the network*/
class GiphyPagingSource @Inject constructor(
    private val giphyApi: GiphyApi,
    private val query: String
) : PagingSource<Int, GiphyImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val position = params.key ?: GIPHY_START_PAGE_INDEX
        //val loadSize = params.loadSize

        return try {

            val response = giphyApi.searchImages(
                searchQueryPhrase = query, pageNumber = position
,
                maxObjectsNumber = params.loadSize

            )
            val images = response.giphyData
            LoadResult.Page(  // make Page
                data = images,
                prevKey = if (position == GIPHY_START_PAGE_INDEX) null else position - 1,
                nextKey = if (images.isEmpty()) null else position +1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}
