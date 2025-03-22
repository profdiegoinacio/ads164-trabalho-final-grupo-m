package com.example.myapp.model
import com.example.myapp.R

object DataSource {

    val books = listOf(
        Book(
            id = "1",
            name = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            resume = "A high fantasy novel about a hobbit's quest to destroy a powerful ring.",
            imageResId = R.drawable.lord_of_the_rings // Use the resource ID
        ),
        Book(
            id = "2",
            name = "Pride and Prejudice",
            author = "Jane Austen",
            resume = "A romantic novel that explores the social conventions of early 19th-century England.",
            imageResId = R.drawable.pride_and_prejudice         ),
        Book(
            id = "3",
            name = "The Hitchhiker's Guide to the Galaxy",
            author = "Douglas Adams",
            resume = "A humorous science fiction series that follows the misadventures of an unsuspecting Englishman.",
            imageResId = R.drawable.hitchhikers_guide         ),
        Book(
            id = "4",
            name = "Harry Potter and the Philosopher's Stone",
            author = "J. K. Rowling",
            resume = "A fantasy novel about an orphaned boy who discovers he is a wizard and is invited to study at Hogwarts School of Witchcraft and Wizardry.",
            imageResId = R.drawable.harry_potter         ),
    )

    val user = User(
        name = "John Doe",
        imageResId = R.drawable.harry_potter,
                favoriteBooks = listOf(books[0], books[2])
    )
}