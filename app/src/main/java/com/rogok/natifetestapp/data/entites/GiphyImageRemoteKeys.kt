package com.rogok.natifetestapp.data.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GiphyImageRemoteKeys(
    @PrimaryKey
    val giphyId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
