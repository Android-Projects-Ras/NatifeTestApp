package com.rogok.natifetestapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rogok.natifetestapp.data.dao.GiphyImageDao
import com.rogok.natifetestapp.data.dao.GiphyRemoteKeyDao
import com.rogok.natifetestapp.data.entites.GiphyImageRemoteKeys
import com.rogok.natifetestapp.models.GiphyImage

@Database(entities = [GiphyImage::class, GiphyImageRemoteKeys::class], version = 1)
abstract class GiphyDatabase: RoomDatabase() {

    abstract fun giphyDao(): GiphyImageDao
    abstract fun giphyRemoteKeysDao(): GiphyRemoteKeyDao

}
