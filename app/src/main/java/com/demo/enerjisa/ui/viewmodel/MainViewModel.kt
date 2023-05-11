package com.demo.enerjisa.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.enerjisa.domain.Repo
import com.demo.enerjisa.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo: Repo): ViewModel() {

    val fetchUserInvoices = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getUserInvoices())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }


}