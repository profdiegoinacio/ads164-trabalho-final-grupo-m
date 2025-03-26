package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.model.Book
import com.example.myapp.model.DataSource
import com.example.myapp.ui.theme.buttonTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val books = DataSource.books
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val filteredBooks = remember(query) {
        if (query.isBlank()) {
            books
        } else {
            books.filter { book ->
                book.name.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true) ||
                        book.category.contains(query, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding around the entire screen
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = query,
            onQueryChange = { newQuery ->
                query = newQuery
            },
            onSearch = {
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search for books")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        ) {
        }
        // Removed the "Go to profile" button
        Spacer(modifier = Modifier.height(16.dp)) // Spacing before the book list
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp) // Increased spacing between items
        ) {
            items(filteredBooks) { book ->
                BookItem(
                    book = book,
                    onBookClick = {
                        navController.navigate("details/${book.id}")
                    },
                    onFavoriteClick = {
                        if (DataSource.isFavorite(book)) {
                            DataSource.removeFromFavorites(book)
                        } else {
                            DataSource.addToFavorites(book)
                        }
                    },
                    onWantToReadClick = {
                        if (DataSource.isWantToRead(book)) {
                            DataSource.removeFromWantToRead(book)
                        } else {
                            DataSource.addToWantToRead(book)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    onBookClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onWantToReadClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onBookClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp), // Rounded corners for the card
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp) // Padding within the card
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = book.imageResId),
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .width(60.dp)
                        .height(90.dp)
                        .clip(RoundedCornerShape(8.dp)), // Rounded corners for the image
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp)) // Spacing between image and text
                Column {
                    Text(
                        text = book.name,
                        style = MaterialTheme.typography.titleLarge, // Use the right style
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Spacing between name and author
                    Text(
                        text = "by ${book.author}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Spacing between author and category
                    Text(
                        text = book.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Row { // Add this row
                IconButton(onClick = onWantToReadClick) {
                    Icon(
                        imageVector = if (DataSource.isWantToRead(book)) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUpOffAlt,
                        contentDescription = "Want to Read",
                        tint = if (DataSource.isWantToRead(book)) Color.Green else Color.Gray,
                        modifier = Modifier.size(32.dp) // Bigger icon size
                    )
                }
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (DataSource.isFavorite(book)) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = if (DataSource.isFavorite(book)) Color.Yellow else Color.Gray,
                        modifier = Modifier.size(32.dp) // Bigger icon size
                    )
                }
            }
        }
    }
}