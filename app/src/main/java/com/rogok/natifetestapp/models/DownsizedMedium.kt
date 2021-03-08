package com.rogok.natifetestapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DownsizedMedium(
    val height: String,
    val size: String,
    val url: String,
    val width: String
): Parcelable