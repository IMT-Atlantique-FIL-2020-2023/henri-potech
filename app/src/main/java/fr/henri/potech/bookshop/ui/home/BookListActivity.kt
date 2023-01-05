package fr.henri.potech.bookshop.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider

//class BookListActivity : ComponentActivity() {
//    private lateinit var viewModel: HomeViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        this.viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
//        val uiState: HomeUiState = this.viewModel.uiState.value
//
//        setContent {
//            // A surface container using the 'background' color from the theme
////            Surface(
////                modifier = Modifier.fillMaxSize(),
////                color = MaterialTheme.colorScheme.background
////            ) {
////                BookGrid(uiState)
////            }
//
//            Text("Hello")
//
//            LoadingContent(
//                empty = when (uiState) {
//                    is HomeUiState.HasBooks -> false
//                    is HomeUiState.NoBook -> uiState.isLoading
//                },
//                emptyContent = { FullScreenLoading() },
//                content = {
//                    when (uiState) {
//                        is HomeUiState.HasBooks -> {
//                            // A surface container using the 'background' color from the theme
//                            Surface(
//                                modifier = Modifier.fillMaxSize(),
//                                color = Color.Red // MaterialTheme.colorScheme.background
//                            ) {
//                                Text(text = "test")
//                                BookGrid(uiState.library.books)
//                            }
//                        }
//                        is HomeUiState.NoBook -> {
////                            if (uiState.errorMessages.isEmpty()) {
////                                // if there are no posts, and no error, let the user refresh manually
////                                TextButton(
////                                    onClick = onRefreshBooks,
////                                    modifier.fillMaxSize()
////                                ) {
////                                    Text(
////                                        stringResource(id = R.string.home_tap_to_load_content),
////                                        textAlign = TextAlign.Center
////                                    )
////                                }
//                            Text(text = "dsfsdfsdf")
////                            } else {
////                                // there's currently an error showing, don't show any content
////                                Box(contentModifier.fillMaxSize()) { /* empty screen */ }
////                            }
//                        }
//                    }
//                }
//            )
//        }
//    }
//}

@Composable
fun Home() {
    // A surface container using the 'background' color from the theme
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colorScheme.background
//            ) {
//                BookGrid(uiState)
//            }

    LoadingContent(
        empty = when (uiState) {
            is HomeUiState.HasBooks -> false
            is HomeUiState.NoBook -> uiState.isLoading
        },
        emptyContent = { FullScreenLoading() },
        content = {
            when (uiState) {
                is HomeUiState.HasBooks -> {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Red // MaterialTheme.colorScheme.background
                    ) {
                        Text(text = "test")
                        BookGrid(uiState.library.books)
                    }
                }
                is HomeUiState.NoBook -> {
//                            if (uiState.errorMessages.isEmpty()) {
//                                // if there are no posts, and no error, let the user refresh manually
//                                TextButton(
//                                    onClick = onRefreshBooks,
//                                    modifier.fillMaxSize()
//                                ) {
//                                    Text(
//                                        stringResource(id = R.string.home_tap_to_load_content),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
                    Text(text = "dsfsdfsdf")
//                            } else {
//                                // there's currently an error showing, don't show any content
//                                Box(contentModifier.fillMaxSize()) { /* empty screen */ }
//                            }
                }
            }
        }
    )
}

/**
 * Display an initial empty state or content.
 *
 * @param empty (state) when true, display [emptyContent]
 * @param emptyContent (slot) the content to display for the empty state
 * @param content (slot) the main content to show
 */
@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        content()
    }
}

/**
 * Full screen circular progress indicator
 */
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Loading")
        CircularProgressIndicator()
    }
}
