package com.example.librarymanagementsystem.presentation.inner_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagementsystem.ui.theme.lightBlue
import com.example.librarymanagementsystem.ui.theme.myTextStyle
import com.example.librarymanagementsystem.ui.theme.topBarColor

@Composable
fun CreateBookComposable(){
    val createBookViewModel: CreateBookViewModel = viewModel()
    val context = LocalContext.current

    // Main Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Id textfield
        TextField(
            modifier = Modifier
                .width(260.dp)
                .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
            value = createBookViewModel.id.value,
            onValueChange = {
                createBookViewModel.updateId(it)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = { Text(text = "Book id") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        
        // UpdateBook button
        Button(
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = topBarColor
            ),
            onClick = { createBookViewModel.updateBook() }) {
            Text(
                text = "Update Book",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Form for creating book
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .border(1.dp, topBarColor, RoundedCornerShape(18.dp))
                .padding(10.dp)
        ){
            // First Column
            Column(
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(
                    modifier = Modifier.height(60.dp),
                    text = "Title",
                    style = myTextStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.height(60.dp),
                    text = "Author",
                    style = myTextStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.height(60.dp),
                    text = "Rating",
                    style = myTextStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.height(60.dp),
                    text = "Times Loaned",
                    style = myTextStyle
                )
                Spacer(modifier = Modifier.height(10.dp))


            }
            // Second Column
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
                    value = createBookViewModel.title.value,
                    onValueChange = { newTitle ->
                        createBookViewModel.title.value = newTitle
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    label = { Text(text = "Title") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
                    value = createBookViewModel.author.value,
                    onValueChange = { newAuthor ->
                        createBookViewModel.author.value = newAuthor
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    label = { Text(text = "Author") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
                    value = createBookViewModel.rating.value.toString(),
                    onValueChange = { newRating ->
                        // Create a new BookModel instance with the updated title
                        createBookViewModel.rating.value = newRating
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    label = { Text(text = "Rating") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .height(60.dp)
                        .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
                    value = createBookViewModel.timesLoaned.value,
                    onValueChange = { newTimesLoaned ->
                        createBookViewModel.timesLoaned.value = newTimesLoaned
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    label = { Text(text = "Times Loaned") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Create Book button
        Button(
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = topBarColor
            ),
            onClick = { createBookViewModel.createBook(context) }) {
            Text(
                text = "Create Book",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }


    }

}