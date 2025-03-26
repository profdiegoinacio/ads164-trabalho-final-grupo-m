package com.example.myapp.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.model.Book
import com.example.myapp.model.DataSource

@Composable
fun DetailsScreen(navController: NavHostController, bookId: String) {
    val book = DataSource.getBookById(bookId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
       IconButton(onClick = {
            navController.popBackStack()
       }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )}

        Image(
            painter = rememberAsyncImagePainter(model = book.imageResId),
            contentDescription = "Book Cover",
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
                if (DataSource.isWantToRead(book)) {
                    DataSource.removeFromWantToRead(book)
                } else {
                    DataSource.addToWantToRead(book)
                }
            }) {
                Icon(
                    imageVector = if (DataSource.isWantToRead(book)) Icons.Outlined.Check else Icons.Outlined.Add ,
                    contentDescription = "Want to Read",
                    tint = if (DataSource.isWantToRead(book)) Color.Blue else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                if (DataSource.isFavorite(book)) {
                    DataSource.removeFromFavorites(book)
                } else {
                    DataSource.addToFavorites(book)
                }
            }) {
                Icon(
                    imageVector = if (DataSource.isFavorite(book)) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Favorite",
                    tint = if (DataSource.isFavorite(book)) Color.Yellow else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
            //
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                if (DataSource.isFinishedReading(book)) {
                    DataSource.removeFinishedReading(book)
                } else {
                    DataSource.addToFinishedReading(book)
                }
            }) {
                Icon(
                    imageVector = if (DataSource.isFinishedReading(book)) Icons.Outlined.CheckBox else Icons.Filled.CheckBoxOutlineBlank ,
                    contentDescription = "Finished Reading",
                    tint = if (DataSource.isFinishedReading(book)) Color.Green else Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = book.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "by ${book.author}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Category: ${book.category}",
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
