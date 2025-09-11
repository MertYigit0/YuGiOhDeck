package com.mertyigit0.yugiohdeck.data.remote

import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("image_url")
    val imageUrl: String
)