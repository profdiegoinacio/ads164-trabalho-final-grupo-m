package com.example.myapp.screens

import android.R.color.white
import android.R.id.primary
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.exemplo.todo.viewmodel.BookViewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.data.model.Book
import com.example.myapp.data.model.BookEntity
import com.example.myapp.data.model.DataSource

@Composable
fun BibliotecaScreen(navController: NavHostController, bookViewModel: BookViewModel) {
    //val favoriteBooks = bookViewModel.favoriteBooks.collectAsState(initial = emptyList()).value
    //val wantToReadBooks = bookViewModel.wantToReadBooks.collectAsState(initial = emptyList()).value
    //val finishedReadingBooks = bookViewModel.finishedReadingBooks.collectAsState(initial = emptyList()).value
    val favoriteBooks = bookViewModel.favoriteBooks.collectAsState(initial = emptyList()).value
    val wantToReadBooks = bookViewModel.wantToReadBooks.collectAsState(initial = emptyList()).value
    val finishedReadingBooks = bookViewModel.finishedReadingBooks.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        BookSection(title = "Meus livros favoritos:", books = favoriteBooks, navController)
        Spacer(modifier = Modifier.height(24.dp))
        BookSection(title = "Para ler:", books = wantToReadBooks, navController)
        Spacer(modifier = Modifier.height(24.dp))
        BookSection(title = "Finalizados:", books = finishedReadingBooks, navController)
    }
}

@Composable
fun BookSection(title: String, books: List<BookEntity>, navController: NavHostController) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        items(books) { book ->
            BookItem(book = book, navController = navController)
        }
    }
}

@Composable
fun BookItem(book: BookEntity, navController: NavHostController) {
    ElevatedCard(
        modifier = Modifier
            .width(140.dp)
            .height(230.dp)
            .clickable {
                navController.navigate("details/${book.id}")
            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.imageResId), // Alterado para imageUrl do Room
                contentDescription = "Book Cover",
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title, // Alterado para title do Room
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
