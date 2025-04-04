package com.example.myapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.viewmodel.BookViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsScreen(bookViewModel: BookViewModel, navController: NavHostController, bookId: Int) {
    val allBooks = bookViewModel.allBooks.collectAsState(initial = emptyList()).value
    val book = allBooks.find { it.id == bookId } ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(29.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar"
            )
        }

        Image(
            painter = rememberAsyncImagePainter(model = book.imageResId),
            contentDescription = "Capa do Livro",
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                bookViewModel.toggleWantToRead(book.id, book.isWantToRead)
            }) {
                Icon(
                    imageVector = if (book.isWantToRead) Icons.Outlined.Check else Icons.Outlined.Add,
                    contentDescription = "Quero ler",
                    tint = if (book.isWantToRead) Color.Blue else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                bookViewModel.toggleFavorite(book.id, book.isFavorite)
            }) {
                Icon(
                    imageVector = if (book.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Favorito",
                    tint = if (book.isFavorite) Color.Yellow else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                bookViewModel.toggleFinished(book.id, book.isFinished)
            }) {
                Icon(
                    imageVector = if (book.isFinished) Icons.Outlined.CheckBox else Icons.Filled.CheckBoxOutlineBlank,
                    contentDescription = "Finalizado",
                    tint = if (book.isFinished) Color.Green else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = book.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "de ${book.author}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categoria: ${book.category}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = book.resume,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}
