package com.mertyigit0.yugiohdeck.data.remote

import com.google.gson.annotations.SerializedName

data class CardDto(
    val id: Int,
    val name: String,
    val type: String?,
    val desc: String?,
    val atk: Int?,
    val def: Int?,
    val level: Int?,
    @SerializedName("card_images")
    val images: List<CardImage>
)

