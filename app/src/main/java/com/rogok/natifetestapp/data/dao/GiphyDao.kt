package com.rogok.natifetestapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rogok.natifetestapp.models.GiphyImage

@Dao
interface GiphyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGiphyList(news: List<GiphyImage>)
    @Query("SELECT * FROM giphy_image_table")
    fun observeGiphyPaginated(): PagingSource<Int, GiphyImage>
    @Query("DELETE FROM giphy_image_table")
    fun deleteGiphyItems(): Int
}