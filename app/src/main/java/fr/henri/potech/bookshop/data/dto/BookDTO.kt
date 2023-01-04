package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName
import fr.henri.potech.bookshop.domain.Book
import java.math.BigDecimal
import java.net.URL


data class BookDTO(
    @SerializedName("isbn") var isbn: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Double,
    @SerializedName("cover") var cover: String,
    @SerializedName("synopsis") var synopsis: ArrayList<String> = arrayListOf()
)

fun Book.from(dto: BookDTO): Book {
    return Book(
        URL(dto.cover),
        dto.title,
        dto.isbn,
        dto.synopsis.joinToString("\n"),
        BigDecimal(dto.price)
    )
}
