package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.model.BookEntity
import com.example.myapp.data.repository.BookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.myapp.data.model.DataSource
class BookViewModel(private val repository: BookRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            if (repository.getAllBooksOnce().isEmpty()) {
                repository.insertAll(DataSource.books)
            }
        }
    }

    val allBooks: StateFlow<List<BookEntity>> = repository.getAllBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteBooks: StateFlow<List<BookEntity>> = repository.getFavorite()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wantToReadBooks: StateFlow<List<BookEntity>> = repository.getWantToRead()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val finishedBooks: StateFlow<List<BookEntity>> = repository.getFinished()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavorite(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(bookId, currentStatus)
        }
    }

    fun toggleWantToRead(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleWantToRead(bookId, currentStatus)
        }
    }

    fun toggleFinished(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFinished(bookId, currentStatus)
        }
    }
}
