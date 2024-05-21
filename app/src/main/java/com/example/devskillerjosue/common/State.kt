package com.example.devskillerjosue.common

//status used for loading and error
enum class State(var error: String = "", var code: Int = 200) {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}