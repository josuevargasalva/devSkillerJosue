package com.example.devskillerjosue.common

interface ViewModelList<T> {

    fun getData()

    /**
     * Return the object at index Int.
     */
    fun objectAt(index: Int): T
}