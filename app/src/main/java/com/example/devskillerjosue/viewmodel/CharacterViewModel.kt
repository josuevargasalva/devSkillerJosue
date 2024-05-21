package com.example.devskillerjosue.viewmodel

import com.example.devskillerjosue.common.BaseListViewModel
import com.example.devskillerjosue.common.State
import com.example.devskillerjosue.models.MCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterViewModel : BaseListViewModel<MCharacter>() {

    //get character list using Rx data and binding
    override fun getData() {
        state.value = State.LOADING
        disposeBag.add(provider.getCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                currentData.value = it.results
                state.value = State.IDLE
                state.value = State.SUCCESS
            }, { throwable ->
                state.value = State.IDLE
                state.value = State.ERROR
            }
            ))
    }
}