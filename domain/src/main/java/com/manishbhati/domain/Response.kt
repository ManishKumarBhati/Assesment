package com.manishbhati.domain

data class Response(
    val id: Long,
    val gender: String,
    val age: Int,
    val name: String,
    val location: String,
    val mob: String,
    val email: String,
    val img: String
)