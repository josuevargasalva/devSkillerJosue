package com.example.devskillerjosue.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//character response in character list request
class MResponse {
    @SerializedName("results") @Expose
    var results: List<MCharacter> = listOf();
}