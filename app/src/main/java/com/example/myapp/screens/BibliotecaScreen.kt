package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.data.model.BookEntity
import com.example.myapp.viewmodel.BookViewModel

@Composable
fun BibliotecaScreen(bookViewModel: BookViewModel, navController: NavHostController) {
    val favoriteBooks = bookViewModel.favoriteBooks.collectAsState(initial = emptyList()).value
    val wantToReadBooks = bookViewModel.wantToReadBooks.collectAsState(initial = emptyList()).value
    val finishedBooks = bookViewModel.finishedBooks.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BookSection(title = "📌 Meus livros favoritos:", books = favoriteBooks, navController = navController)
        Spacer(modifier = Modifier.height(24.dp))

        BookSection(title = "📖 Para ler:", books = wantToReadBooks, navController = navController)
        Spacer(modifier = Modifier.height(24.dp))

        BookSection(title = "✅ Finalizados:", books = finishedBooks, navController = navController)
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
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.imageResId),
                contentDescription = "Capa do Livro",
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
