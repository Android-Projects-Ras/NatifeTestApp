package com.rogok.natifetestapp.models

import com.google.gson.annotations.SerializedName


data class GiphyResponse1(
    @SerializedName("data")
    val giphyData: List<GiphyImage1>,
    val meta: Meta,
    val pagination: Pagination
)