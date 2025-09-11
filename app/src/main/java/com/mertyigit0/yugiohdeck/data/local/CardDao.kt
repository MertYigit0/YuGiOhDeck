package com.mertyigit0.yugiohdeck.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM cardentity")
    fun getAllMyDeck(): Flow<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToMyDeck(card: CardEntity)

    @Delete
    suspend fun removeFromMyDeck(card: CardEntity)
}
