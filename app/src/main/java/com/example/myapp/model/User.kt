package com.example.myapp.model
import androidx.annotation.DrawableRes

data class User(
    val name: String,
    val email: String,
    @DrawableRes val imageResId: Int,
   // val favoriteBooks: List<Book>
)