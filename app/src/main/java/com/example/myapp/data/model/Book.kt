package com.example.myapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "resume") val resume: String,
    @ColumnInfo(name = "imageResId") val imageResId: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false,
    @ColumnInfo(name = "is_want_to_read") val isWantToRead: Boolean = false,
    @ColumnInfo(name = "is_finished_reading") val isFinishedReading: Boolean = false
)