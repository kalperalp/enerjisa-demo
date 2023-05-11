package com.demo.enerjisa.domain

import com.demo.enerjisa.data.DataSource
import com.demo.enerjisa.data.model.UserInvoices
import com.demo.enerjisa.vo.Resource

class RepoImpl(val dataSource: DataSource): Repo {

    override suspend fun getUserInvoices(): Resource<UserInvoices> {
        return dataSource.getUserInvoices()
    }

}