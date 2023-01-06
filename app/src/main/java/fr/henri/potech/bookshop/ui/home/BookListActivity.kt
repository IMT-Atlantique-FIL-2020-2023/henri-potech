package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun BookList() {
    val books = (1..50).toList()
        .map { i ->
            BookCardState(
                isbn = "$i",
                coverUrl = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
                title = "Title $i",
                price = BigDecimal(10 * i % 40 + 2),
            )
        }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BookGrid(books)
    }
}
