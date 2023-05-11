package com.demo.enerjisa.domain

import com.demo.enerjisa.data.model.UserInvoices
import retrofit2.http.GET


interface WebService {

    @GET("userInvoices.json")
    suspend fun getUserInvoices(): UserInvoices
}