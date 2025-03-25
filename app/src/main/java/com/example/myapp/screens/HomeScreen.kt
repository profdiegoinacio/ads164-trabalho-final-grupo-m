package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapp.model.Book
import com.example.myapp.model.DataSource
import com.example.myapp.ui.theme.accentColor
import com.example.myapp.ui.theme.backgroundColor
import com.example.myapp.ui.theme.buttonTextColor
import com.example.myapp.ui.theme.primaryColor
import com.example.myapp.ui.theme.textColor

@Composable
fun HomeScreen(navController: NavHostController) {
    val books = DataSource.books
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("profile") },
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text(text = "Go to profile", color = buttonTextColor)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(books) { book ->
                BookListItem(
                    book = book,
                    navController = navController,
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
fun BookListItem(
    book: Book,
    navController: NavHostController,
    onFavoriteClick: () -> Unit,
    onWantToReadClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            //.background(accentColor)
            .clickable {
                navController.navigate("details/${book.id}")
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = book.imageResId),
                contentDescription = "Book Cover",
                modifier = Modifier
                    .width(100.dp) // Fixed width for the image
                    .aspectRatio(0.66f) // Aspect ratio of a typical book cover
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Row { // Add this row
                IconButton(onClick = onWantToReadClick) {
                    Icon(
                        imageVector = if (DataSource.isWantToRead(book)) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUpOffAlt,
                        contentDescription = "Want to Read",
                        tint = if (DataSource.isWantToRead(book)) Color.Green else Color.Gray
                    )
                }
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (DataSource.isFavorite(book)) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favorite",
                        tint = if (DataSource.isFavorite(book)) Color.Yellow else Color.Gray
                    )
                }

            }
        }
    }
}