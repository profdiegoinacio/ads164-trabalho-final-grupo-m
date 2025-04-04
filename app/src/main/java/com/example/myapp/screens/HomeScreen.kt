package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.data.model.BookEntity
import com.example.myapp.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(bookViewModel: BookViewModel, navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    // ViewModel state
    val allBooks by bookViewModel.allBooks.collectAsState(initial = emptyList())
    val favoriteBooks by bookViewModel.favoriteBooks.collectAsState(initial = emptyList())
    val wantToReadBooks by bookViewModel.wantToReadBooks.collectAsState(initial = emptyList())

    // Filtrar os livros com base na busca
    val filteredBooks = allBooks.filter { book ->
        book.title.contains(query, ignoreCase = true) || book.author.contains(query, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = { newQuery -> query = newQuery },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Pesquisar por livros") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredBooks) { book ->
                val isFavorite = favoriteBooks.any { it.id == book.id }
                val isWantToRead = wantToReadBooks.any { it.id == book.id }

                BookItem(
                    book = book.copy(isFavorite = isFavorite, isWantToRead = isWantToRead),
                    onBookClick = { navController.navigate("details/${book.id}") },
                    onFavoriteClick = { bookViewModel.toggleFavorite(book.id, isFavorite) },
                    onWantToReadClick = { bookViewModel.toggleWantToRead(book.id, isWantToRead) }
                )
            }
        }
    }
}

@Composable
fun BookItem(
    book: BookEntity,
    onBookClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onWantToReadClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onBookClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Image(
                    painter = rememberAsyncImagePainter(model = book.imageResId),
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .width(90.dp)
                        .height(130.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "de ${book.author}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = book.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Row {
                IconButton(onClick = onWantToReadClick) {
                    Icon(
                        imageVector = if (book.isWantToRead) Icons.Outlined.Check else Icons.Outlined.Add,
                        contentDescription = "Want to Read",
                        tint = if (book.isWantToRead) MaterialTheme.colorScheme.secondary else Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (book.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = if (book.isFavorite) MaterialTheme.colorScheme.primary else Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
