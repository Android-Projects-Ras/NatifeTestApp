package com.rogok.natifetestapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(
    val msg: String,
    @SerializedName("response_id")
    val responseId: String,
    val status: Int
): Parcelable