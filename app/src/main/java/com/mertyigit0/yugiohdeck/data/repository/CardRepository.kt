package com.mertyigit0.yugiohdeck.data.repository

import android.content.Context
import com.mertyigit0.yugiohdeck.data.local.CardDao
import com.mertyigit0.yugiohdeck.data.local.CardEntity
import com.mertyigit0.yugiohdeck.data.remote.CardDto
import com.mertyigit0.yugiohdeck.data.remote.CardImage
import com.mertyigit0.yugiohdeck.data.remote.YGOApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val api: YGOApi,
    private val cardDao: CardDao,
    @ApplicationContext private val context: Context
) {

    // Yalnızca yerelde görüntüsü bulunan kartları API'den getir ve görüntüyü asset ile değiştir
    suspend fun getAllCards(): List<CardDto> {
        // Assets içindeki dosya adlarından id'leri çıkar
        val assetFiles = context.assets.list("")?.toList().orEmpty()
        val smallPattern = Regex("^(\\d+)_small\\.jpg$")
        val localIds: List<Int> = assetFiles.mapNotNull { name ->
            smallPattern.find(name)?.groupValues?.getOrNull(1)?.toIntOrNull()
        }.distinct().sorted()

        if (localIds.isEmpty()) return emptyList()

        // API: sadece bu id'ler için veriyi çek
        // API bir kerede CSV id kabul ediyor
        val csv = localIds.joinToString(",")
        val remoteCards = api.getCardsByIds(csv).cards

        // Her kartın görselini yerel asset ile override et
        return remoteCards.map { card ->
            val localUrl = "file:///android_asset/${card.id}_small.jpg"
            card.copy(images = listOf(CardImage(imageUrl = localUrl)))
        }
    }

    // MyDeck / favori işlemleri
    fun getMyDeck(): Flow<List<CardEntity>> = cardDao.getAllMyDeck()

    suspend fun addToMyDeck(card: CardEntity) = cardDao.addToMyDeck(card)

    suspend fun removeFromMyDeck(card: CardEntity) = cardDao.removeFromMyDeck(card)
}
