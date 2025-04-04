package com.example.myapp.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapp.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Update
    suspend fun updateBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    // Favorites
    @Query("SELECT * FROM books WHERE isFavorite = True")
    fun getFavorite(): Flow<List<BookEntity>>

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)

    // Want To Read Books
    @Query("SELECT * FROM books WHERE isWantToRead = True")
    fun getWantToRead(): Flow<List<BookEntity>>

    @Query("UPDATE books SET isWantToRead = :isWantToRead WHERE id = :id")
    suspend fun updateWantToRead(id: Int, isWantToRead: Boolean)

    // Finished Books
    @Query("SELECT * FROM books WHERE isFinished = True")
    fun getFinishedReading(): Flow<List<BookEntity>>

    @Query("UPDATE books SET isFinished = :isFinished WHERE id = :id")
    suspend fun updateFinished(id: Int, isFinished: Boolean)
}