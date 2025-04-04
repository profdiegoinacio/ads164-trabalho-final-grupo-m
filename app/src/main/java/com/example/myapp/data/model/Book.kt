package com.example.myapp.data.model

data class Book(
     val id: String,
     val name: String,
     val author: String,
     val category: String,
     val resume: String,
     val imageResId: Int,
     val isFavorite: Boolean = false,
     val isWantToRead: Boolean = false,
     val isFinished: Boolean = false,
)