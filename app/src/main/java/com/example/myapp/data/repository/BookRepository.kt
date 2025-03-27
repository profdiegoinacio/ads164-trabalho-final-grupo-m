package com.example.myapp.data.repository

import android.util.Log
import com.example.myapp.data.local.BookDao
import com.example.myapp.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()

    fun getBookById(bookId: String): Flow<Book?> = bookDao.getBookById(bookId)

    suspend fun updateBook(book: Book) {
        try {
            bookDao.updateBook(book)
        } catch (e: Exception) {
            Log.e("BookRepository", "Error updating book: ${e.message}", e) // Include the exception for better logging
        }
    }

    fun getFavoriteBooks(): Flow<List<Book>> = bookDao.getFavoriteBooks()
    fun getWantToReadBooks(): Flow<List<Book>> = bookDao.getWantToReadBooks()
    fun getFinishedReadingBooks(): Flow<List<Book>> = bookDao.getFinishedReadingBooks()
}