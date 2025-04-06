package com.example.myapp.data.repository

import com.example.myapp.data.local.BookDao
import com.example.myapp.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    suspend fun getAllBooksOnce(): List<BookEntity> = bookDao.getAllBooksOnce()

    suspend fun insertAll(books: List<BookEntity>) {
        bookDao.insertAll(books)
    }
    fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    suspend fun getBookById(id: Int): BookEntity? = bookDao.getBookById(id)

    fun getFavorite(): Flow<List<BookEntity>> = bookDao.getFavorite()
    suspend fun toggleFavorite(bookId: Int, currentStatus: Boolean) {
        bookDao.updateFavorite(bookId, !currentStatus)
    }

    fun getWantToRead(): Flow<List<BookEntity>> = bookDao.getWantToRead()
    suspend fun toggleWantToRead(bookId: Int, currentStatus: Boolean) {
        bookDao.updateWantToRead(bookId, !currentStatus)
    }

    fun getFinished(): Flow<List<BookEntity>> = bookDao.getFinishedReading()
    suspend fun toggleFinished(bookId: Int, currentStatus: Boolean) {
        bookDao.updateFinished(bookId, !currentStatus)
    }

    suspend fun updateBook(book: BookEntity) = bookDao.updateBook(book)

    suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)
}
