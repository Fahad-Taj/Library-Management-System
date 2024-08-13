package com.example.librarymanagementsystem.presentation.inner_composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymanagementsystem.models.BookModel
import com.example.librarymanagementsystem.ui.theme.bottomBarSelected
import com.example.librarymanagementsystem.ui.theme.lightBlue
import com.example.librarymanagementsystem.ui.theme.topBarColor

@Composable
fun HomeComposable(homeViewModel: HomeViewModel){

    //val homeViewModel: HomeViewModel = viewModel()
    val listOfBooks by homeViewModel.booksFlow.collectAsState()

    LaunchedEffect(Unit) {
        if(listOfBooks.isEmpty()){
            homeViewModel.GetAllBooks()
        }
    }

    // Main column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Row, it will contain the TextField and the GetBook button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Id textfield
            TextField(
                modifier = Modifier
                    .width(260.dp)
                    .border(1.dp, topBarColor, RoundedCornerShape(12.dp)),
                value = homeViewModel.id.value,
                onValueChange = {
                    homeViewModel.updateId(it)
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
            Spacer(modifier = Modifier.width(20.dp))

            // GetBook button
            Button(
                modifier = Modifier.height(50.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = topBarColor
                ),
                onClick = { homeViewModel.getBookById() }) {
                Text(
                    text = "Get Book",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.height(20.dp))
        // Row Over

        // View Area
        Column(
            modifier = Modifier
                .height(470.dp)
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(12.dp))
                .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            listOfBooks.forEach {book ->
                singleBook(book = book, homeViewModel = homeViewModel)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Get All book button
        Button(
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = topBarColor
            ),
            onClick = { homeViewModel.GetAllBooks() }) {
            Text(
                text = "Get All Books",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
    
}

@Composable
fun singleBook(book: BookModel, homeViewModel: HomeViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(lightBlue)
            .padding(8.dp)
    ) {

        var expanded by remember { mutableStateOf(false) }
        val context = LocalContext.current

        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = book.title,
                color = topBarColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = book.author,
                color = topBarColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = book.rating.toString().substring(0, 3) + "/5",
                color = topBarColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = book.timesLoaned.toString(),
                color = topBarColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Third Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectionContainer {
                Text(
                    text = book._id,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
            }


            // Drop down
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Delete Book") },
                        onClick = { homeViewModel.deleteBook(book._id.toString(), context) }
                    )
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}