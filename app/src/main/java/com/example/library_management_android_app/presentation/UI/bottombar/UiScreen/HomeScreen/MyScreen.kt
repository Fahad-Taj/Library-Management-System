package com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen() {

    //insert update getallbooks getbookbyid delete
    Box(
        modifier = Modifier.fillMaxSize().background( Color.White)
    ) {
        val bookViewModel: MyScreenViewModel = viewModel()
        var bookid by remember { mutableStateOf("") }

        val singleBook by bookViewModel.bookByIdResponse.collectAsState()
        val allBooks by bookViewModel.allBooksResponse.collectAsState()
        var showDeleteAlert by remember {
            mutableStateOf(false)
        }
        var bookIdToDelete by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, shape = RectangleShape)
            , verticalArrangement =Arrangement.Center
        ) {
            MyTextField(
                bookid,
                onValueChange = { newValue -> bookid = newValue },
                placeholder = "Enter the Book Id",
                ImageVector=Icons.Default.Search
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(450.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    when {
                        singleBook != null -> {
                            singleBook.let {
                                it?.body().let {
                                    if (it != null) {
                                        BookCard(
                                            it,
                                            onDeleteClick = {
                                                showDeleteAlert = true
                                                bookIdToDelete = it._id  // Store the ID of the book to be deleted
                                            }
                                        )
                                    } else {
                                        Text(
                                            text = "No Data found with this book id",
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                        allBooks != null -> {
                            allBooks?.let { response ->
                                LazyColumn {
                                    response.body()?.let { books ->
                                        items(books) { book ->
                                            BookCard(
                                               book,
                                                onDeleteClick = {
                                                    showDeleteAlert = true
                                                    bookIdToDelete = book._id  // Store the ID of the book to be deleted
                                                }
                                            )
                                        }

                                    } ?: run {
                                        item {
                                            Text(
                                                text = "nothhing to show",
                                                color = Color.White
                                            )
                                        }

                                    }
                                }
                            }
                        }

                        else -> {
                            // Display loading or default message
                            Text("Empty record", modifier = Modifier, color = Color.White)
                        }

                    }
                    if (showDeleteAlert) {
                        DeleteAlertDialog(
                            onDismiss = { showDeleteAlert = false },
                            id = bookIdToDelete,
                            DeleteSuccess = {
                                bookViewModel.getAllBooks()
                                bookViewModel.getBookById(bookid)  // Refresh single book view if needed
                            }
                        )
                    }
                }
            }
            //buttons for get all books or get by book id
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top=10.dp)
                    .height(45.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff972CB0)
                    ), modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    onClick = {
                        bookViewModel.getAllBooks()
                    }
                ) {
                    Text(text = "Get All Books")
                }
                Spacer(modifier = Modifier.widthIn(40.dp))
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff972CB0)
                ),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    onClick = {
                        if (bookid.isNotEmpty()) {
                            bookViewModel.getBookById(bookid)
                        }
                    }) {
                    Text(text = "Get Book by id")
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    bookid: String, onValueChange: (String) -> Unit, placeholder: String,ImageVector:ImageVector
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = bookid,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF333333),
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 14.sp, color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),

            )
    }

}

@Preview(showBackground = true)
@Composable
fun ExamplePreview() {
    MyScreen()
//    BookCard(
//        id = "66b4b588253ac78d15741814",
//        title = "bodkkdkkdok2",
//        author = "Dr abc",
//        description = "This is all about self-growth"
//    )
}




