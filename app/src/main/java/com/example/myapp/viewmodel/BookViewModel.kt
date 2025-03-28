package com.example.myapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.model.Book
import com.example.myapp.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookViewModel(val repository: BookRepository) : ViewModel() {

    private val _allBooks = MutableStateFlow<List<Book>>(emptyList())
    val allBooks: StateFlow<List<Book>> = _allBooks.asStateFlow()

    private val _favoriteBooks = MutableStateFlow<List<Book>>(emptyList())
    val favoriteBooks: StateFlow<List<Book>> = _favoriteBooks.asStateFlow()

    private val _wantToReadBooks = MutableStateFlow<List<Book>>(emptyList())
    val wantToReadBooks: StateFlow<List<Book>> = _wantToReadBooks.asStateFlow()

    private val _finishedReadingBooks = MutableStateFlow<List<Book>>(emptyList())
    val finishedReadingBooks: StateFlow<List<Book>> = _finishedReadingBooks.asStateFlow()

    init {
        collectBooks()
    }

    private fun collectBooks() {
        viewModelScope.launch {
            repository.getAllBooks().collectLatest {
                _allBooks.value = it
                Log.d("BookViewModel", "Collected books: ${it.size}")
            }
            repository.getFavoriteBooks().collectLatest { _favoriteBooks.value = it }
            repository.getWantToReadBooks().collectLatest { _wantToReadBooks.value = it }
            repository.getFinishedReadingBooks().collectLatest { _finishedReadingBooks.value = it }
        }
    }

    // Functions to update list memberships
    fun toggleFavorite(book: Book) {
        viewModelScope.launch {
            val currentBooks = _allBooks.value.toMutableList()
            val bookToUpdate = currentBooks.find { it.id == book.id }
            if (bookToUpdate != null) {
                val updatedBook = bookToUpdate.copy(isFavorite = !bookToUpdate.isFavorite)
                val index = currentBooks.indexOfFirst { it.id == updatedBook.id }
                if (index != -1) {
                    currentBooks[index] = updatedBook
                    _allBooks.value = currentBooks
                }
                repository.updateBook(updatedBook)
            }
        }
    }

    fun toggleWantToRead(book: Book) {
        viewModelScope.launch {
            val currentBooks = _allBooks.value.toMutableList()
            val bookToUpdate = currentBooks.find { it.id == book.id }
            if (bookToUpdate != null) {
                val updatedBook = bookToUpdate.copy(isWantToRead = !bookToUpdate.isWantToRead)
                val index = currentBooks.indexOfFirst { it.id == updatedBook.id }
                if (index != -1) {
                    currentBooks[index] = updatedBook
                    _allBooks.value = currentBooks
                }
                repository.updateBook(updatedBook)
            }
        }
    }

    fun toggleFinishedReading(book: Book) {
        viewModelScope.launch {
            val currentBooks = _allBooks.value.toMutableList()
            val bookToUpdate = currentBooks.find { it.id == book.id }
            if (bookToUpdate != null) {
                val updatedBook = bookToUpdate.copy(isFinishedReading = !bookToUpdate.isFinishedReading)
                val index = currentBooks.indexOfFirst { it.id == updatedBook.id }
                if (index != -1) {
                    currentBooks[index] = updatedBook
                    _allBooks.value = currentBooks
                }
                repository.updateBook(updatedBook)
            }
        }
    }

    class Factory(private val repository: BookRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BookViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}