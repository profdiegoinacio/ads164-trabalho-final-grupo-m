package com.example.myapp

import com.example.myapp.ui.theme.MyAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myapp.data.repository.BookRepository
import com.example.myapp.navigation.NavGraph
import com.example.myapp.viewmodel.BookViewModel
import com.example.myapp.viewmodel.BookViewModelFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val bookDao = BooksApplication.database.bookDao()

       val repository = BookRepository(bookDao)
       val bookViewModel: BookViewModel by viewModels {
           BookViewModelFactory(repository, bookDao)
       }

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