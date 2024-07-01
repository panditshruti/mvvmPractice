package com.rajnish.mydairy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val  BASE_URL = "http://localhost:8001/v1/"


    val instance:ApiService by lazy {
     val retrofit = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()

        retrofit.create(ApiService::class.java)
    }
}