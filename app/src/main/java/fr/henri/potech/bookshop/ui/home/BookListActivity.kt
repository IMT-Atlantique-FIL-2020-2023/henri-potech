package fr.henri.potech.bookshop.ui.home

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.ui.bookdetail.BookDetailActivity
import java.math.BigDecimal
import java.net.URL

//class BookListActivity : ComponentActivity() {
//    private lateinit var viewModel: BookListViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        this.viewModel = ViewModelProvider(this)[BookListViewModel::class.java]
//        val books = this.viewModel.books
//
//        setContent {
//            // A surface container using the 'background' color from the theme
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colorScheme.background
//            ) {
//                BookGrid(books)
//            }
//        }
//    }
//}

@Composable
fun BookList(
    viewModel: BookListViewModel = viewModel()
) {
    val context = LocalContext.current

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val books = viewModel.books.observeAsState().value
        if (viewModel.isLoading.observeAsState().value == true) {
            Text(text = "Loading")
        } else {
            if (books != null) {
                BookGrid(books.getOrDefault(emptyList()),
                    onClick = { book ->
                        val intent = Intent(context, BookDetailActivity::class.java)
                        intent.putExtra("book", book)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}
