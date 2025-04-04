package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.viewmodel.BookViewModel
import com.example.myapp.data.local.BookDao
import com.example.myapp.data.repository.BookRepository

class BookViewModelFactory(private val repository: BookRepository,    private val bookDao: BookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
