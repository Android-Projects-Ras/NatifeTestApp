package com.rogok.natifetestapp.models


import com.google.gson.annotations.SerializedName

data class Pagination(
    val count: Int,
    val offset: Int,
    @SerializedName("total_count")
    val totalCount: Int
)