package fr.henri.potech.bookshop.ui.home

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.henri.potech.bookshop.ui.bookdetail.BookDetailActivity

@Composable
fun BookList(
    viewModel: BookListViewModel = viewModel(),
    contentPadding: PaddingValues = PaddingValues(),
) {
    val context = LocalContext.current

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val books = viewModel.books.observeAsState().value
        if (viewModel.isLoading.observeAsState().value == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 2.dp,
                )
            }
        } else {
            if (books != null) {
                BookGrid(
                    books = books.getOrDefault(emptyList()),
                    onClick = { book ->
                        val intent = Intent(context, BookDetailActivity::class.java)
                        intent.putExtra("book", book)
                        context.startActivity(intent)
                    },
                    contentPadding = contentPadding,
                )
            }
        }
    }
}
