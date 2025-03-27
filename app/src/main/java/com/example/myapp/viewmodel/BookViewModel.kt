package com.example.myapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.model.Book
import com.example.myapp.data.model.DataSource // Import DataSource
import com.example.myapp.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BookViewModel(application: Application, private val repository: BookRepository) :
    AndroidViewModel(application) {

    private val _allBooks = MutableStateFlow<List<Book>>(emptyList())
    val allBooks: StateFlow<List<Book>> = _allBooks.asStateFlow()

    private val _favoriteBooks = MutableStateFlow<List<Book>>(emptyList())
    val favoriteBooks: StateFlow<List<Book>> = _favoriteBooks.asStateFlow()

    private val _wantToReadBooks = MutableStateFlow<List<Book>>(emptyList())
    val wantToReadBooks: StateFlow<List<Book>> = _wantToReadBooks.asStateFlow()

    private val _finishedReadingBooks = MutableStateFlow<List<Book>>(emptyList())
    val finishedReadingBooks: StateFlow<List<Book>> = _finishedReadingBooks.asStateFlow()

    init {
        loadInitialData()
        collectBooks()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                val booksInDb = repository.getAllBooks().firstOrNull()
                if (booksInDb.isNullOrEmpty()) {
                    DataSource.books.forEach { book ->
                        repository.updateBook(book) // Use updateBook to insert with default list statuses
                    }
                    _allBooks.value = DataSource.books
                } else {
                    _allBooks.value = booksInDb
                }
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error loading initial data: ${e.message}", e)
                // Handle the error appropriately, e.g., show an error message to the user.
            }
        }
    }

    private fun collectBooks() {
        viewModelScope.launch {
            repository.getAllBooks().collectLatest { _allBooks.value = it }
            repository.getFavoriteBooks().collectLatest { _favoriteBooks.value = it }
            repository.getWantToReadBooks().collectLatest { _wantToReadBooks.value = it }
            repository.getFinishedReadingBooks().collectLatest { _finishedReadingBooks.value = it }
        }
    }

    // Functions to update list memberships
    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            try {
                val updatedBook = book.copy(isFavorite = !book.isFavorite)
                repository.updateBook(updatedBook)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error toggling favorite status: ${e.message}", e)
                // Handle the error
            }
        }
    }

    fun toggleWantToRead(book: Book) {
        viewModelScope.launch {
            try {
                val updatedBook = book.copy(isWantToRead = !book.isWantToRead)
                repository.updateBook(updatedBook)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error toggling want to read status: ${e.message}", e)
            }
        }
    }

    fun toggleFinishedReading(book: Book) {
        viewModelScope.launch {
            try {
                val updatedBook = book.copy(isFinishedReading = !book.isFinishedReading)
                repository.updateBook(updatedBook)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error toggling finished reading status: ${e.message}", e)
                // Handle the error
            }
        }
    }
}