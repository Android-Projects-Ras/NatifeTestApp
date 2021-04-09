package com.rogok.natifetestapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.rogok.natifetestapp.api.GiphyApi
import com.rogok.natifetestapp.data.GiphyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(GiphyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyApi(retrofit: Retrofit): GiphyApi =
        retrofit.create(GiphyApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(/*@ApplicationContext*/ context: Context) =
        Room.databaseBuilder(context, GiphyDatabase::class.java, "giphy_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideGiphyDao(db: GiphyDatabase) = db.giphyDao()

    @Provides
    fun provideGiphyRemoteKeysDao(db: GiphyDatabase) = db.giphyRemoteKeysDao()
}