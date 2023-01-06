package fr.bardon_sassi.bookshop.data.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


data class BookDTO(
    @SerializedName("isbn") val isbn: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: BigDecimal,
    @SerializedName("cover") val cover: String,
    @SerializedName("synopsis") val synopsis: ArrayList<String> = arrayListOf()
)

