package com.example.myapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val category: String,
    val resume: String,
    val imageResId: Int,
    var isFavorite: Boolean = false,
    var isWantToRead: Boolean = false,
    var isFinished: Boolean = false
)