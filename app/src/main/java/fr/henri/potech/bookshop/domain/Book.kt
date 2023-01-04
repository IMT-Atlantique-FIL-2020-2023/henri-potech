package fr.henri.potech.bookshop.domain

import java.math.BigDecimal
import java.net.URL

data class Book(
    val coverUrl: URL,
    val title: String,
    val isbn: String,
    val synopsis: String,
    val price: BigDecimal
)
