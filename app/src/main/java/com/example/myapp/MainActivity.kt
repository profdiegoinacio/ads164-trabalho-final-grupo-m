package com.example.myapp

import com.example.myapp.ui.theme.MyAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import br.com.exemplo.todo.viewmodel.BookViewModel
import com.example.myapp.data.local.BookDatabase
import com.example.myapp.data.repository.BookRepository
import com.example.myapp.navigation.NavGraph
import com.example.myapp.viewmodel.BookViewModelFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private lateinit var database: BookDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bookViewModel: BookViewModel = ViewModelProvider(this, BookViewModelFactory(repository)).get(BookViewModel::class.java)

        val bookDao = BooksApplication.database.bookDao()
        val repository = BookRepository(bookDao) // Cria o repositório
       // val bookViewModel: BookViewModel by viewModels {
         //  BookViewModelFactory(repository)
        //}


        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(bookViewModel)
                }
            }
        }
    }
}