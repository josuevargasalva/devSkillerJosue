package com.example.devskillerjosue.common

import androidx.lifecycle.MutableLiveData

//baseViewModel used for list requests
open class BaseListViewModel<T> : BaseViewModel(), ViewModelList<T> {

    val currentData = MutableLiveData<List<T>>()

    override fun getData() { }

    override fun objectAt(index: Int): T {
        return currentData.value!![index]
    }
}