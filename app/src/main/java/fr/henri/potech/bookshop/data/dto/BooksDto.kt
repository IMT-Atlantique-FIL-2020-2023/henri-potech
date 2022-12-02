package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName


data class BooksDto(

    @SerializedName("isbn") var isbn: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Int,
    @SerializedName("cover") var cover: String,
    @SerializedName("synopsis") var synopsis: ArrayList<String> = arrayListOf()

)