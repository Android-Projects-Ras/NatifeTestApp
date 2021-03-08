package com.rogok.natifetestapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Analytics(
    val onclick: Onclick,
    val onload: Onload,
    val onsent: Onsent
): Parcelable