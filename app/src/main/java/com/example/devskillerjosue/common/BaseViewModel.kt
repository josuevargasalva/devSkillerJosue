package com.example.devskillerjosue.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devskillerjosue.api.Api
import com.example.devskillerjosue.api.ApiProvider
import io.reactivex.disposables.CompositeDisposable

//baseViewModel used for object requests
open class BaseViewModel (
    var provider: Api = ApiProvider.provider()
) : ViewModel() {
    val disposeBag = CompositeDisposable()
    val state = MutableLiveData<State>()
}