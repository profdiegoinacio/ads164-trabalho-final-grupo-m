package com.example.myapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
//import coil.compose.rememberAsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.myapp.model.Book
import com.example.myapp.model.DataSource
import com.example.myapp.ui.theme.accentColor
import com.example.myapp.ui.theme.backgroundColor
import com.example.myapp.ui.theme.buttonTextColor
import com.example.myapp.ui.theme.primaryColor
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.myapp.ui.theme.textColor

@Composable
fun DetailsScreen(navController: NavHostController, bookId: String) {
    val book = DataSource.books.find { it.id == bookId }
    book?.let {
        BookDetails(navController = navController, book = it)
    }
}

@Composable
fun BookDetails(navController: NavHostController, book: Book) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("home") },
            modifier = Modifier.padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor))
        { // Add back button
            Text(text = "Back to home", color = buttonTextColor)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCard(
            modifier = Modifier.background(accentColor)
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = rememberAsyncImagePainter(model = book.imageResId),
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(top = 50.dp)
                        .size(200.dp)
                        .aspectRatio(0.66f)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier
                    .height(16.dp))
                Text( modifier = Modifier
                    .padding(top = 40.dp),
                    text = book.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = textColor)
                Spacer(modifier = Modifier
                    .height(8.dp))
                Text(
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Italic,
                    color = textColor)
                Spacer(modifier = Modifier.height(16.dp))
                Text(modifier = Modifier
                    .background(buttonTextColor)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),// Make the Text scrollable,
                    text = book.resume,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    color = textColor)

            }
        }



    }
}