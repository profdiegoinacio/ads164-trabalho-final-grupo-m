package com.example.myapp.model
import androidx.annotation.DrawableRes

data class Book(
    val id: String,
    val name: String,
    val author: String,
    val resume: String,
    val category: String,
    @DrawableRes val imageResId: Int // URL for the book's cover image
)