package com.rogok.natifetestapp.models


import com.google.gson.annotations.SerializedName

data class Preview(
    val height: String,
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    val width: String
)