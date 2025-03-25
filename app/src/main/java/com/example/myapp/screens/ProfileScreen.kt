package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.model.Book
import com.example.myapp.model.DataSource
import com.example.myapp.ui.theme.accentColor

@Composable
fun ProfileScreen(navController: NavHostController) {
    val user = DataSource.user
    val favoriteBooks = DataSource.favoriteBooks
    val wantToReadBooks = DataSource.wantToReadBooks

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Back to home")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = user.imageResId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "My Favorite Books:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(favoriteBooks) { book ->
                FavoriteBookItem(book = book, onFavoriteClick = {
                    if (DataSource.isFavorite(book)) {
                        DataSource.removeFromFavorites(book)
                    } else {
                        DataSource.addToFavorites(book)
                    }
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Want To Read",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(wantToReadBooks) { book ->
                WantToReadBookItem(book = book, onWantToReadClick = {
                    if (DataSource.isWantToRead(book)) {
                        DataSource.removeFromWantToRead(book)
                    } else {
                        DataSource.addToWantToRead(book)
                    }
                })
            }
        }

    }
}

@Composable
fun FavoriteBookItem(book: Book, onFavoriteClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .width(140.dp)
            .height(250.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.imageResId),
                contentDescription = "Book Cover",
                modifier = Modifier
                    .width(120.dp) // Fixed width for the image
                    .height(140.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = onFavoriteClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    imageVector = if (DataSource.isFavorite(book)) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Favorite",
                    tint = if (DataSource.isFavorite(book)) Color.Yellow else Color.Gray
                )
            }
        }
    }
}

@Composable
fun WantToReadBookItem(book: Book, onWantToReadClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .width(140.dp)
            .height(250.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.imageResId),
                contentDescription = "Book Cover",
                modifier = Modifier
                    .width(120.dp) // Fixed width for the image
                    .height(140.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(
                onClick = onWantToReadClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = if (DataSource.isWantToRead(book)) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUpOffAlt,
                    contentDescription = "Favorite",
                    tint = if (DataSource.isWantToRead(book)) Color.Green else Color.Gray
                )
            }
        }
    }
}