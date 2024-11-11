package com.example.library_management_android_app.presentation.UI.bottombar.UiScreen.HomeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.library_management_android_app.data_models.BookDataModel.GetBookById.getbookbyid_response
import com.example.library_management_system.presentation.UI.bottombar.UiScreen.Delete_ViewModel
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

@Composable
fun BookCard(
    book: getbookbyid_response?, onDeleteClick: () -> Unit
) {
    SelectionContainer {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BookDetails(book!!._id, book.title, book.Author, book.description, book.imagePath)
                DeleteButton(
                    onClick = onDeleteClick, modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun BookDetails(
    id: String, title: String, author: String, description: String, imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
        ) {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White, fontWeight = FontWeight.Bold
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "by $author",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "ID: $id",
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier.aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current
            Log.e("RKl", imageUrl)
            val imageUrl = imageUrl.toHttpUrlOrNull()
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context)
                    .data(imageUrl)
                    .listener(
                        onError = { request, result ->
                            Log.e(
                                "BookDetails",
                                "Error loading image: ${result.throwable.message}",
                                result.throwable
                            )
                        },
                        onSuccess = { _, _ ->
                            Log.d("BookDetails", "Image loaded successfully")
                        }
                    )
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = "Book cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator()
                }

                is AsyncImagePainter.State.Error -> {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error loading image",
                        tint = Color.Gray
                    )
                }

                else -> {}
            }

        }
    }
}

@Composable
fun DeleteButton(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick, modifier = modifier
            .padding(4.dp)
            .size(32.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Delete Book",
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun DeleteAlertDialog(onDismiss: () -> Unit, id: String, DeleteSuccess: () -> Unit) {

    val deleteViewmodel: Delete_ViewModel = viewModel()
    val bookViewModel: MyScreenViewModel = viewModel()

    val singleBook by bookViewModel.bookByIdResponse.collectAsState()
    val allBooks by bookViewModel.allBooksResponse.collectAsState()

    AlertDialog(
        modifier = Modifier
            .height(200.dp)
            .width(280.dp),
        containerColor = Color.Black,
        onDismissRequest = onDismiss,
        shape = AlertDialogDefaults.shape,
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true),
        dismissButton = {
            Button(
                modifier = Modifier.border(1.dp, Color.White, shape = ButtonDefaults.shape),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                onClick = {
                    onDismiss()
                },
            ) {
                Text("Cancel", color = Color.White)
            }

        },
        confirmButton = {
            Button(
                modifier = Modifier.border(1.dp, Color.White, shape = ButtonDefaults.shape),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                    deleteViewmodel.deletebookbyid(id)
                    DeleteSuccess()
                    onDismiss()
                },
            ) {
                Text("Delete", color = Color.White)
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Confirm Delete", color = Color.White)
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
            }
        },
        text = {
            Text(
                "Are you sure you want to delete this book?", color = Color.White,
            )
        },
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBookcard() {
    var showDeleteAlert by remember {
        mutableStateOf(true)
    }
    DeleteAlertDialog(
        onDismiss = { showDeleteAlert = false },
        id = 111.toString(),
        DeleteSuccess = {

        }
    )

}