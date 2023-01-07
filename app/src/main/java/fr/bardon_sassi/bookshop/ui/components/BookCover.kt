package fr.bardon_sassi.bookshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.net.URL

@Composable
fun BookCover(
    url: URL,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(percent = 5)
    AsyncImage(
        model = url.toString(),
        modifier = modifier
            .aspectRatio(21F / 29.7F)
            .background(Color.Gray, shape)
            .clip(shape),
        contentDescription = "Book cover"
    )
}

@Preview(name = "64", showBackground = true)
@Composable
fun Preview() {
    BookCover(
        url = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
        modifier = Modifier
            .height(64.dp)
            .padding(8.dp),
    )
}

@Preview(name = "200", showBackground = true)
@Composable
fun Preview2() {
    BookCover(
        url = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp),
    )
}
