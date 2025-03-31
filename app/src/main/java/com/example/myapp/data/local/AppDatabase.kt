package com.example.myapp.data.local


import androidx.activity.result.launch
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapp.data.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        private class PopulateDbCallback(
            private val bookDao: BookDao,
            private val books: List<Book>
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                books.forEach { book ->
                    CoroutineScope(Dispatchers.IO).launch {
                        bookDao.updateBook(book)
                    }
                }
            }
        }
        fun getPopulateDbCallback(bookDao: BookDao, books: List<Book>): Callback {
            return PopulateDbCallback(bookDao, books)
        }
    }
}