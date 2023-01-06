package fr.bardon_sassi.bookshop.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import fr.bardon_sassi.bookshop.ui.cart.bottom_sheets_scaffold.BottomSheetScaffold
import fr.bardon_sassi.bookshop.ui.cart.bottom_sheets_scaffold.BottomSheetValue
import fr.bardon_sassi.bookshop.ui.cart.bottom_sheets_scaffold.rememberBottomSheetScaffoldState
import fr.bardon_sassi.bookshop.ui.cart.bottom_sheets_scaffold.rememberBottomSheetState
import fr.bardon_sassi.bookshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomCartSheetScaffold(content: @Composable (PaddingValues) -> Unit) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )
    val sheetCornerRadius = dimensionResource(id = R.dimen.cart_sheet_corner_radius)
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = sheetCornerRadius,
            topEnd = sheetCornerRadius,
        ),
        sheetPeekHeight = dimensionResource(id = R.dimen.cart_sheet_peek_height),
        sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        sheetContent = {
            Box {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Surface(
                        modifier = Modifier
                            .height(3.dp)
                            .width(40.dp),
                        color = Color.LightGray,
                        shape = RoundedCornerShape(percent = 50),
                    ) {}
                }
                BottomSheetContent(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.cart_sheet_padding)),
                )
            }
        },
        scaffoldState = bottomSheetScaffoldState
    ) {
        content(it)
    }
}

@Composable
fun BottomSheetContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CartHeader()
        CartItems()
    }
}