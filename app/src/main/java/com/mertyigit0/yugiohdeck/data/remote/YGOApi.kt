package com.mertyigit0.yugiohdeck.data.remote

import retrofit2.http.GET

interface YGOApi {
    @GET("cardinfo.php")
    suspend fun getAllCards(): CardResponse
}
