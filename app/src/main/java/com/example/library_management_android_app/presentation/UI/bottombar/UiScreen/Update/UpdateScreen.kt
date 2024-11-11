package com.example.library_management_system.presentation.UI.bottombar.UiScreen.Update

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
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
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.Create.BookDetailsForm
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyScreenViewModel
import com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen.MyTextField

@Composable
fun UpdateScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var bookid by remember { mutableStateOf("") }
            val context = LocalContext.current

            val bookViewModel: MyScreenViewModel = viewModel()
            val singleBook by bookViewModel.bookByIdResponse.collectAsState()

            val updatescreenViewmodel: UpdateScreen_ViewModel = viewModel()
            val response by updatescreenViewmodel.updatebookresponse.collectAsState()

            var bookTitle by remember { mutableStateOf("") }
            var authorName by remember { mutableStateOf("") }
            var bookDescription by remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MyTextField(
                    bookid,
                    onValueChange = { newValue -> bookid = newValue },
                    placeholder = "Enter the Book Id",
                    ImageVector = Icons.Outlined.Search
                )

            }

            LaunchedEffect(singleBook) {
                singleBook?.let {
                    it.body()?.let { book ->
                        bookid = book._id.toString()
                        bookTitle = book.title
                        authorName = book.Author
                        bookDescription = book.description
                    }
                }
            }


            Spacer(modifier = Modifier.height(70.dp))

            BookDetailsForm(bookTitle = bookTitle,
                onBookTitleChange = { newValue -> bookTitle = newValue },
                authorName = authorName,
                onAuthorNameChange = { newValue -> authorName = newValue },
                bookDescription = bookDescription,
                onBookDescriptionChange = { newValue -> bookDescription = newValue })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff972CB0),
                ), modifier = Modifier.weight(1f),
                     onClick = {
                        if (bookid.isNotEmpty()) {
                            bookViewModel.getBookById(bookid)
                        } else {
                            Toast.makeText(
                                context, "Enter Book id to get details", Toast.LENGTH_SHORT
                            ).show()
                        }

                    }) {

                        Text(text = "Get Book details", color = Color.White)

                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff972CB0)
                ), modifier = Modifier,
                    onClick = {
                        if (bookTitle.isNotEmpty() && authorName.isNotEmpty() && bookDescription.isNotEmpty()) {
                            val updatedbook = add_book_request(
                                Author = authorName,
                                description = bookDescription,
                                title = bookTitle
                            )
                            updatescreenViewmodel.updatebook(bookid, updatedbook)
                        } else {
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text(text = "Modify The Details", color = Color.White)
                }
            }

            LaunchedEffect(response) {
                response?.let {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Data Updation is succesfull", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Data Updation is unsuccesfull", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewUpdate() {
    UpdateScreen(navController = rememberNavController())
}