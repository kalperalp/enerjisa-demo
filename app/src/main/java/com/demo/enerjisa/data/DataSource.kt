package com.demo.enerjisa.data

import com.demo.enerjisa.data.model.UserInvoices
import com.demo.enerjisa.vo.Resource
import com.demo.enerjisa.vo.RetrofitClient

class DataSource {

    suspend fun getUserInvoices(): Resource.Success<UserInvoices>{
        return Resource.Success(RetrofitClient.webService.getUserInvoices())
    }
}