package fr.henri.potech.bookshop.ui.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.ui.cart.bottom_sheets_scaffold.BottomSheetScaffold
import fr.henri.potech.bookshop.ui.cart.bottom_sheets_scaffold.BottomSheetValue
import fr.henri.potech.bookshop.ui.cart.bottom_sheets_scaffold.rememberBottomSheetScaffoldState
import fr.henri.potech.bookshop.ui.cart.bottom_sheets_scaffold.rememberBottomSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomCartSheetScaffold(content: @Composable (PaddingValues) -> Unit) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = 28.0.dp, topEnd = 28.0.dp, bottomEnd = 0.0.dp, bottomStart = 0.0.dp
        ),
        sheetPeekHeight = 80.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        sheetContent = {
            // The content you want to show in your bottom sheet
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 5.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp, 3.dp)
                        .border(
                            width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp)
                        )
                )
                BottomSheetContent()
            }

        },

        scaffoldState = bottomSheetScaffoldState
    ) {
        content(it)
    }
}

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        CartHeader()
        CartItems()
    }
}