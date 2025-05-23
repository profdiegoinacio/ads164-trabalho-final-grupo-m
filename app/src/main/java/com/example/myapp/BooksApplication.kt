package com.example.myapp

import android.app.Application
import androidx.room.Room
import com.example.myapp.data.local.BookDatabase

class BooksApplication : Application() {
    companion object {
        val database: BookDatabase by lazy {
            Room.databaseBuilder(
                instance!!.applicationContext,
                BookDatabase::class.java, "books_database"
            ).build()
        }
        var instance: BooksApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}