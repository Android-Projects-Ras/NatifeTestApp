package com.rogok.natifetestapp.models

import com.google.gson.annotations.SerializedName


data class GiphyResponse(
    @SerializedName("data")
    val giphyData: List<GiphyImage>,
    val meta: Meta,
    val pagination: Pagination
)