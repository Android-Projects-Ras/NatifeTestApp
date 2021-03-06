package com.rogok.natifetestapp.models


import com.google.gson.annotations.SerializedName

data class Meta(
    val msg: String,
    @SerializedName("response_id")
    val responseId: String,
    val status: Int
)