package com.mertyigit0.yugiohdeck.data.repository

import com.mertyigit0.yugiohdeck.data.local.CardDao
import com.mertyigit0.yugiohdeck.data.local.CardEntity
import com.mertyigit0.yugiohdeck.data.remote.CardDto
import com.mertyigit0.yugiohdeck.data.remote.YGOApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val api: YGOApi,
    private val cardDao: CardDao
) {

    // API’den tüm kartları çek
    suspend fun getAllCards(): List<CardDto> {
        return api.getAllCards().cards
    }

    // MyDeck / favori işlemleri
    fun getMyDeck(): Flow<List<CardEntity>> = cardDao.getAllMyDeck()

    suspend fun addToMyDeck(card: CardEntity) = cardDao.addToMyDeck(card)

    suspend fun removeFromMyDeck(card: CardEntity) = cardDao.removeFromMyDeck(card)
}
