package com.example.androiddevchallenge.domain.models

import com.example.androiddevchallenge.R

data class Dog(
    val id: Int = -1,
    val doggoName: String = "",
    val sex: String = "",
    val imageRes: Int = R.drawable.icy,
    val age: String = ""
)