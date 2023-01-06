package fr.henri.potech.bookshop.ui.cart

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Cart
import fr.henri.potech.bookshop.ui.BookCover
import kotlinx.coroutines.launch
import java.math.BigDecimal


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CartTotal(cartModel: CartViewModel = CartViewModel.getInstance()) {
    val cart = cartModel.cart.collectAsState(initial = Cart()).value
    AnimatedContent(
        targetState = cart.books.size,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }
    ) {
        Column {
            Text(
                text = cart.total.toRidePrice(), style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "${cart.books.size} items", style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun CartHeader(cartModel: CartViewModel = CartViewModel.getInstance()) {
    val coroutineScope = rememberCoroutineScope()
    val (isLoading, setIsLoading) = remember { mutableStateOf(false) }
    val (bestPricePair, setBestPricePair) = remember {
        mutableStateOf<Pair<BigDecimal, String>?>(
            null
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val cart = cartModel.cart.collectAsState(initial = Cart()).value
        CartTotal()
        Button(
            enabled = cart.books.isNotEmpty() && !isLoading,
            onClick = {
                coroutineScope.launch {
                    setIsLoading(true)
                    try {
                        val bestPrice = cart.computeTotalWithOffer()
                        setBestPricePair(bestPrice)
                    } finally {
                        setIsLoading(false)
                    }
                }
            }) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp,
                )
            } else {
                Text(text = "Compute Best Price")
            }
        }
    }

    if (bestPricePair != null) {
        AlertDialog(onDismissRequest = { setBestPricePair(null) }, text = {
            Text(text = "Best price is ${bestPricePair.first.toRidePrice()} with offer ${bestPricePair.second}")
        }, confirmButton = {
            TextButton(onClick = {
                setBestPricePair(null)
            }) {
                Text("Oki-doki")
            }
        })
    }
}

@Composable
fun CartItems(cartModel: CartViewModel = CartViewModel.getInstance()) {
    val cart = cartModel.cart.collectAsState(initial = Cart())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
    ) {
        item {
            Divider()
        }
        itemsIndexed(items = cart.value.books, key = { _, book -> book.uuid }) { index, book ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)
            ) {
                CartItem(book)
            }
            if (index != cart.value.books.size - 1) {
                Divider()
            }
        }
    }
}

@Composable
fun CartItem(book: Book) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            BookCover(url = book.coverUrl, modifier = Modifier.height(40.dp))
            Text(
                text = book.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1.8f)
            )
            Text(
                text = book.price.toRidePrice(),
                textAlign = TextAlign.End,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1F)

            )
        }
    }
}