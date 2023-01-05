package fr.henri.potech.bookshop.data.remote.dto

import com.google.gson.annotations.SerializedName
import fr.henri.potech.bookshop.domain.Book
import java.math.BigDecimal
import java.net.URL


data class BookDTO(
    @SerializedName("isbn") val isbn: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: BigDecimal,
    @SerializedName("cover") val cover: String,
    @SerializedName("synopsis") val synopsis: ArrayList<String> = arrayListOf()
)

fun Book.Companion.from(dto: BookDTO): Book {
    return Book(
        URL(dto.cover),
        dto.title,
        dto.isbn,
        dto.synopsis.joinToString("\n"),
        dto.price
    )
}
