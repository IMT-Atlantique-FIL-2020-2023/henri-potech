package fr.bardon_sassi.bookshop.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.bardon_sassi.bookshop.domain.Book
import fr.bardon_sassi.bookshop.ui.components.BookCover

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: (Book) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick(book) },
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            BookCover(
                url = book.coverUrl,
            )
            Text(
                text = book.title,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${book.price}",
                fontSize = 10.sp,
                lineHeight = 11.sp
            )
        }
    }
}
