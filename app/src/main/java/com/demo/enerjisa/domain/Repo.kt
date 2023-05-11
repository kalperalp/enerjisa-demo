package com.demo.enerjisa.domain

import com.demo.enerjisa.data.model.UserInvoices
import com.demo.enerjisa.vo.Resource

interface Repo {

    suspend fun getUserInvoices(): Resource<UserInvoices>

}