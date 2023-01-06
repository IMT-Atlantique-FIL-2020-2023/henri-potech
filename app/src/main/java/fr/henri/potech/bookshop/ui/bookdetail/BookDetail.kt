package fr.henri.potech.bookshop.ui.bookdetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.henri.potech.bookshop.R
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.ui.cart.BottomCartSheetScaffold
import fr.henri.potech.bookshop.ui.cart.CartViewModel
import fr.henri.potech.bookshop.ui.components.BookCover
import fr.henri.potech.bookshop.ui.theme.BookShopTheme
import java.math.BigDecimal
import java.net.URL

class BookDetailActivity(
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            this.intent?.extras?.getParcelable<Book>("book")?.let { book ->
                BookShopTheme {
                    BookDetailScreen(book)
                }
            }
        }
    }
}


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun BookDetailScreen(book: Book, cartModel: CartViewModel = CartViewModel.getInstance()) {
    BottomCartSheetScaffold {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.title_activity_book_detail)) },
//                actions = {
//                    IconButton(
//                        onClick = {},
//                    ) {
//                        Icon(
//                            imageVector = Icons.TwoTone.ShoppingCart,
//                            contentDescription = "Add to cart"
//                        )
//                    }
//                },
                )
            },
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                BookDetail(
                    book
                ) { cartModel.addToCart(book) }
            }
        }
    }

}

@Composable
fun BookDetail(
    book: Book,
    addToCart: (isbn: String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BookCover(
                url = book.coverUrl,
                modifier = Modifier
                    .height(256.dp),
            )
            Text(
                text = book.title,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "ISBN : ${book.isbn}",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                color = Color.Gray,
            )
            Text(
                text = "${book.price} €",
                fontSize = 14.sp,
                lineHeight = 16.sp,
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { addToCart(book.isbn) },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Add to cart",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Add to cart")
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Synopsis",
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = book.synopsis,
                fontSize = 12.sp,
                lineHeight = 14.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val state = Book(
        isbn = "a460afed-e5e7-4e39-a39d-c885c05db861",
        coverUrl = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
        title = "Henri Potier et la Chambre des secrets",
        price = BigDecimal(30),
        synopsis = "Henri Potier passe l'été chez les Dursley et reçoit la visite de Dobby, un elfe de maison. Celui-ci vient l'avertir que des évènements étranges vont bientôt se produire à Poudlard et lui conseille donc vivement de ne pas y retourner. Henri choisit d'ignorer cet avertissement. Le jour de son départ pour l'école, il se retrouve bloqué avec Ron Weasley à la gare de King's Cross, sans pouvoir se rendre sur le quai 9 ¾ où les attend le Poudlard Express. En dernier recours, les garçons se rendent donc à Poudlard à l'aide de la voiture volante de Monsieur Weasley et manquent de peu de se faire renvoyer dès leur arrivée à l'école pour avoir été aperçus au cours de leur voyage par plusieurs moldus.\nLe nouveau professeur de défense contre les forces du mal, Gilderoy Lockhart, se montre particulièrement narcissique et inefficace. Pendant ce temps, Henri entend une voix étrange en parcourant les couloirs du château, systématiquement associée à la pétrification immédiate d'un élève moldu de l'école. Dès la première attaque, un message sanglant apparaît sur l'un des murs, informant que la Chambre des secrets a été ouverte. Dumbledore et les autres professeurs (ainsi que Henri, Ron et Hermione) doivent prendre les mesures nécessaires pour trouver l'identité du coupable et protéger les élèves contre de nouvelles agressions.",
    )
    BookShopTheme {
        BookDetailScreen(state)
    }
}
