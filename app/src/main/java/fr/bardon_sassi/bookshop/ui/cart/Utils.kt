package fr.bardon_sassi.bookshop.ui.cart

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*


fun BigDecimal.toRidePrice(): String {
    val format: java.text.NumberFormat = DecimalFormat.getCurrencyInstance(Locale.FRANCE)
    format.minimumFractionDigits = 2
    format.maximumFractionDigits = 2
    return format.format(this.toDouble())
}