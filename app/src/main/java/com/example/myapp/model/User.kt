package com.example.myapp.model
import androidx.annotation.DrawableRes

data class User(
    val name: String,
    @DrawableRes val imageResId: Int, // URL for the user's profile picture
    val favoriteBooks: List<Book>
)