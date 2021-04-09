package com.rogok.natifetestapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rogok.natifetestapp.data.entites.GiphyImageRemoteKeys

@Dao
interface GiphyRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<GiphyImageRemoteKeys>)

    @Query("SELECT * FROM keys_table WHERE giphyId = :id")
    fun remoteKeysGiphyId(id: Long): GiphyImageRemoteKeys?

    @Query("DELETE FROM keys_table")
    fun clearRemoteKeys()
}