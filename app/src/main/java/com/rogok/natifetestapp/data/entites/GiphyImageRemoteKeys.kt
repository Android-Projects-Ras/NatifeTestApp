package com.rogok.natifetestapp.data.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

//create an Entity to store the paging information for offline paging
@Entity(tableName = "keys_table")
data class GiphyImageRemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val giphyId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
