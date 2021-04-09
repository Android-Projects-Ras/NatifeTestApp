package com.rogok.natifetestapp.models


import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    /*@Embedded
    val downsized: Downsized,
    @Embedded
    @SerializedName("downsized_large")
    val downsizedLarge: DownsizedLarge,
    @Embedded
    @SerializedName("downsized_medium")
    val downsizedMedium: DownsizedMedium,
    @Embedded
    @SerializedName("downsized_small")
    val downsizedSmall: DownsizedSmall,
    @Embedded
    @SerializedName("downsized_still")
    val downsizedStill: DownsizedStill,
    @Embedded
    @SerializedName("fixed_height")
    val fixedHeight: FixedHeight,
    @Embedded
    @SerializedName("fixed_height_downsampled")
    val fixedHeightDownsampled: FixedHeightDownsampled,*/
    @Embedded(prefix = "small_")
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: FixedHeightSmall,
    /*@Embedded
    @SerializedName("fixed_height_small_still")
    val fixedHeightSmallStill: FixedHeightSmallStill,
    @Embedded
    @SerializedName("fixed_height_still")
    val fixedHeightStill: FixedHeightStill,
    @Embedded
    @SerializedName("fixed_width")
    val fixedWidth: FixedWidth,
    @Embedded
    @SerializedName("fixed_width_downsampled")
    val fixedWidthDownsampled: FixedWidthDownsampled,
    @Embedded
    @SerializedName("fixed_width_small")
    val fixedWidthSmall: FixedWidthSmall,
    @Embedded
    @SerializedName("fixed_width_small_still")
    val fixedWidthSmallStill: FixedWidthSmallStill,
    @Embedded
    @SerializedName("fixed_width_still")
    val fixedWidthStill: FixedWidthStill,
    @Embedded
    val looping: Looping,*/
    @Embedded(prefix = "original")
    val original: Original,
    /*@Embedded
    @SerializedName("original_mp4")
    val originalMp4: OriginalMp4,
    @Embedded
    @SerializedName("original_still")
    val originalStill: OriginalStill,
    @Embedded
    val preview: Preview,
    @Embedded
    @SerializedName("preview_gif")
    val previewGif: PreviewGif,
    @Embedded
    @SerializedName("preview_webp")
    val previewWebp: PreviewWebp,
    @Embedded
    @SerializedName("480w_still")
    val wStill: WStill*/
): Parcelable