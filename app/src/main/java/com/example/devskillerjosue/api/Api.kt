package com.example.devskillerjosue.api

import com.example.devskillerjosue.models.MResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    //Request get used for get list of characters
    @GET("api/character")
    fun getCharacters(
    ): Observable<MResponse>

}