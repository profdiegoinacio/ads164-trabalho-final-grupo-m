package com.example.myapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.myapp.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookById(bookId: String): Flow<Book?>

    @Update
    suspend fun updateBook(book: Book)

    @Query("SELECT * FROM books WHERE is_favorite = 1")
    fun getFavoriteBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE is_want_to_read = 1")
    fun getWantToReadBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE is_finished_reading = 1")
    fun getFinishedReadingBooks(): Flow<List<Book>>
}