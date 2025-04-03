package com.example.myapp.data.local


import androidx.room.*
import com.example.myapp.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getAllBooks(): kotlinx.coroutines.flow.Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    @Query("SELECT * FROM books WHERE category = :category")
    fun getBooksByCategory(category: String): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isFavorite = True")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isWantToRead = True")
    fun getWantToReadBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isFinished = True")
    fun getFinishedReadingBooks(): Flow<List<BookEntity>>

}