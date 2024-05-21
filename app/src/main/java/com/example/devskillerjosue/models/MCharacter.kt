package com.example.devskillerjosue.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Character model
class MCharacter: Serializable {

    @SerializedName("name")
    var name: String = "";

    @SerializedName("image")
    var image: String = "";

    @SerializedName("status")
    var status: String = "";

    @SerializedName("species")
    var species: String = "";

}