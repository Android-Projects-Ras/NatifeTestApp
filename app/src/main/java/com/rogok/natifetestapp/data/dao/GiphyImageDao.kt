package com.rogok.natifetestapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rogok.natifetestapp.models.GiphyImage

@Dao
interface GiphyImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGiphyImageList(giphyImageList: List<GiphyImage>)

    @Query("SELECT * FROM giphy_image_table")
    fun getAllGiphyImages(): PagingSource<Int, GiphyImage>

    @Query("DELETE FROM giphy_image_table")
    fun deleteGiphyImages(): Int
}