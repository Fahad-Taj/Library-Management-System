package com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.Create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.library_management_android_app.data_models.BookDataModel.AddBook.add_book_request
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyTextField

@Composable
fun CreateScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val bookViewModel: CreateBook_ViewModel = viewModel()
            val response by bookViewModel.createbookresponse.collectAsState()
            val context = LocalContext.current

            var bookTitle by remember { mutableStateOf("") }
            var authorName by remember { mutableStateOf("") }
            var bookDescription by remember { mutableStateOf("") }

            BookDetailsForm(bookTitle = bookTitle,
                onBookTitleChange = { newValue -> bookTitle = newValue },
                authorName = authorName,
                onAuthorNameChange = { newValue -> authorName = newValue },
                bookDescription = bookDescription,
                onBookDescriptionChange = { newValue -> bookDescription = newValue })

            Spacer(modifier = Modifier.height(20.dp))

            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xff972CB0)),
                modifier = Modifier.width(240.dp),
                shape = RoundedCornerShape(19.dp),
                onClick = {
                    if (bookTitle.isNotEmpty() && authorName.isNotEmpty() && bookDescription.isNotEmpty()) {
                        val newBook = add_book_request(
                            Author = authorName, description = bookDescription, title = bookTitle
                        )
                        bookViewModel.addbook(newBook)
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                Text(text = "Submit Book", color = Color.White)
            }

            // Observe the response and display a toast message
            LaunchedEffect(response) {
                response?.let {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreate() {
    CreateScreen(navController = rememberNavController())
}

@Composable
fun BookDetailsForm(
    bookTitle: String,
    onBookTitleChange: (String) -> Unit,
    authorName: String,
    onAuthorNameChange: (String) -> Unit,
    bookDescription: String,
    onBookDescriptionChange: (String) -> Unit
) {
    MyTextField(
        bookid = bookTitle,
        onValueChange = onBookTitleChange,
        placeholder = "Enter the Book title",
       ImageVector = Icons.Outlined.MenuBook
    )
    Spacer(modifier = Modifier.height(25.dp))

    MyTextField(
        bookid = authorName,
        onValueChange = onAuthorNameChange,
        placeholder = "Enter the Author Name",
        ImageVector = Icons.Outlined.PersonOutline
    )
    Spacer(modifier = Modifier.height(25.dp))

    MyTextField(
        bookid = bookDescription,
        onValueChange = onBookDescriptionChange,
        placeholder = "Enter the Description",
        ImageVector = Icons.Outlined.Description

    )
    Spacer(modifier = Modifier.height(25.dp))

}
