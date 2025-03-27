package com.example.myapp.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapp.data.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}