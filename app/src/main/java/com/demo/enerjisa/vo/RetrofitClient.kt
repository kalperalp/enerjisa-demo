package com.demo.enerjisa.vo

import com.google.gson.GsonBuilder
import com.demo.enerjisa.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/bydevelopertr/enerjisa/main/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}