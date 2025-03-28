package com.example.myapp.data.repository

import android.util.Log
import com.example.myapp.data.local.BookDao
import com.example.myapp.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()

    fun getBookById(bookId: String): Flow<Book?> = bookDao.getBookById(bookId)

    suspend fun updateBook(book: Book) {
        Log.d("BookRepository", "Updating book: ${book.id}, favorite=${book.isFavorite}, wantToRead=${book.isWantToRead}")
        bookDao.updateBook(book)
        Log.d("BookRepository", "Book updated successfully: ${book.id}")

    }

    fun getFavoriteBooks(): Flow<List<Book>> = bookDao.getFavoriteBooks()
    fun getWantToReadBooks(): Flow<List<Book>> = bookDao.getWantToReadBooks()
    fun getFinishedReadingBooks(): Flow<List<Book>> = bookDao.getFinishedReadingBooks()
}