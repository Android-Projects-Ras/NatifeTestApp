package com.rogok.natifetestapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pagination(
    val count: Int,
    val offset: Int,
    @SerializedName("total_count")
    val totalCount: Int
): Parcelable