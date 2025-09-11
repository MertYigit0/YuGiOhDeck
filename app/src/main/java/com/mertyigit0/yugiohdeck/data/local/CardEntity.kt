package com.mertyigit0.yugiohdeck.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardentity")
data class CardEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String
)
