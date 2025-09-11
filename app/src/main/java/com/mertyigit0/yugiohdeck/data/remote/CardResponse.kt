package com.mertyigit0.yugiohdeck.data.remote

import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("data")
    val cards: List<CardDto>
)