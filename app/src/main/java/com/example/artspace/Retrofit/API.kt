package com.example.artspace.Retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}