package fr.henri.potech.bookshop.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.R
import java.net.URL

@Composable
fun BookCover(
    url: URL,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .widthIn(max = 200.dp)
            .aspectRatio(21F/29.7F),
        shape = RoundedCornerShape(2.dp),
        color = Color(150, 29, 29)
    ) {}
}
