package com.rogok.natifetestapp.api

import com.rogok.natifetestapp.models.GiphyResponse1
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=cheeseburgers&limit=5&offset=0&rating=g&lang=en

interface GiphyApi {

    companion object {
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        const val API_KEY = "j1PuizimiJqzVbW7Ymighwhub2ZMd42X"//"YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL"
    }

    @GET("search")
    suspend fun searchImages(
        @Query("q")
        searchQueryPhrase: String = "cheeseburgers",
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("offset")
        startResultPosition: Int = 0, //Maximum: “4999” Default = 0
        @Query("rating")
        rating: String = "g",
        @Query("limit")
        maxObjectsNumber: Int = 5,  //The maximum number of objects to return. (Default: “25”).
        @Query("lang")
        lang: String = "en"

    ): GiphyResponse1
}