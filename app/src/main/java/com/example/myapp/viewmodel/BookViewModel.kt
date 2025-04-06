package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.model.BookEntity
import com.example.myapp.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.myapp.data.model.DataSource
class BookViewModel(private val repository: BookRepository) : ViewModel() {

    // 🔄 Fluxos de dados do banco
   // val allBooks: StateFlow<List<BookEntity>> = repository.getAllBooks()
     //   .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    private val _allBooks = MutableStateFlow<List<BookEntity>>(emptyList())
    val allBooks: StateFlow<List<BookEntity>> = _allBooks

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _allBooks.value = DataSource.books  // <- Aqui é onde carrega os livros do arquivo
    }

    val favoriteBooks: StateFlow<List<BookEntity>> = repository.getFavorite()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wantToReadBooks: StateFlow<List<BookEntity>> = repository.getWantToRead()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val finishedBooks: StateFlow<List<BookEntity>> = repository.getFinished()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 🔍 Buscar livro por ID
    suspend fun getBookById(id: Int): BookEntity? {
        return repository.getBookById(id)
    }

    // ⭐ Alternar favorito
    fun toggleFavorite(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(bookId, currentStatus)
        }
    }

    // 📖 Alternar "quero ler"
    fun toggleWantToRead(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleWantToRead(bookId, currentStatus)
        }
    }

    // ✅ Alternar finalizado
    fun toggleFinished(bookId: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFinished(bookId, currentStatus)
        }
    }

    // ✏️ Atualizar livro (opcional)
    fun updateBook(book: BookEntity) {
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }

    // 🗑️ Deletar livro (opcional)
    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }
}
