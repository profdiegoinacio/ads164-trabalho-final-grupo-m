package com.example.myapp.data.repository

import com.example.myapp.data.local.BookDao
import com.example.myapp.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    // 📚 Todos os livros
    fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    // 🔍 Livro por ID
    suspend fun getBookById(id: Int): BookEntity? = bookDao.getBookById(id)

    // ⭐ Favoritos
    fun getFavorite(): Flow<List<BookEntity>> = bookDao.getFavorite()
    suspend fun toggleFavorite(bookId: Int, currentStatus: Boolean) {
        bookDao.updateFavorite(bookId, !currentStatus)
    }

    // 📖 Quero ler
    fun getWantToRead(): Flow<List<BookEntity>> = bookDao.getWantToRead()
    suspend fun toggleWantToRead(bookId: Int, currentStatus: Boolean) {
        bookDao.updateWantToRead(bookId, !currentStatus)
    }

    // ✅ Lidos
    fun getFinished(): Flow<List<BookEntity>> = bookDao.getFinishedReading()
    suspend fun toggleFinished(bookId: Int, currentStatus: Boolean) {
        bookDao.updateFinished(bookId, !currentStatus)
    }

    // ✏️ Atualizar livro
    suspend fun updateBook(book: BookEntity) = bookDao.updateBook(book)

    // 🗑️ Deletar livro
    suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)
}
