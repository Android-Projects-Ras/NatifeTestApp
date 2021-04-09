package com.rogok.natifetestapp.models


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//TODO: change package to data.entities.GiphyImage
@Parcelize
@Entity(tableName = "giphy_image_table")
data class GiphyImage(
    @PrimaryKey(autoGenerate = true)
    val idForRoom: Long = 0,
    /*@Embedded(prefix = "analytics_")
    val analytics: Analytics,*/
    @SerializedName("analytics_response_payload")
    val analyticsResponsePayload: String,
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    @SerializedName("content_url")
    val contentUrl: String,
    @SerializedName("embed_url")
    val embedUrl: String,
    //@Ignore
    val id: String,                            //id
    @Embedded(prefix = "images_")
    val images: Images,
    @SerializedName("import_datetime")
    val importDatetime: String,
    @SerializedName("is_sticker")
    val isSticker: Int,
    val rating: String,
    val slug: String,            //The unique slug used in this GIF's URL
    val source: String,
    @SerializedName("source_post_url")
    val sourcePostUrl: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    val title: String,                          //title
    @SerializedName("trending_datetime")
    val trendingDatetime: String,
    val type: String,
    val url: String,
    @SerializedName("username")
    val userName: String                        //username
) : Parcelable