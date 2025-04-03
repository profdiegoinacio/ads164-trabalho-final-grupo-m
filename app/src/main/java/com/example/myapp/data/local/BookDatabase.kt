package com.example.myapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapp.data.model.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
