package com.example.myapp.model


data class Book(
    val id: String,
    val name: String,
    val author: String,
    val resume: String,
    val imageResId: Int // URL for the book's cover image
)