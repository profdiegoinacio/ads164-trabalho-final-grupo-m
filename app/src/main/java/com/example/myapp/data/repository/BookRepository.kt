package com.example.myapp.data.repository

import com.example.myapp.data.local.BookDao
import com.example.myapp.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val allBooks = bookDao.getAllBooks()
    val favoriteBooks = bookDao.getFavoriteBooks()
    val wantToReadBooks: Flow<List<BookEntity>> = bookDao.getWantToReadBooks()
    val finishedReadingBooks: Flow<List<BookEntity>> = bookDao.getFinishedReadingBooks()
    suspend fun insertBook(book: BookEntity) {
       bookDao.insertBook(book)
    }

    suspend fun deleteBook(book: BookEntity) {
        bookDao.deleteBook(book)
    }

    suspend fun getBookById(id: Int):BookEntity? {
        return bookDao.getBookById(id)
    }
}
