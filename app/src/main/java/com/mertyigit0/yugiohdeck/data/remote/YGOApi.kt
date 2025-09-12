package com.mertyigit0.yugiohdeck.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface YGOApi {
    @GET("cardinfo.php")
    suspend fun getAllCards(): CardResponse

    // Belirli kart id'lerine göre filtreli getir (virgülle ayrılmış)
    @GET("cardinfo.php")
    suspend fun getCardsByIds(
        @Query("id") idsCsv: String
    ): CardResponse
}
