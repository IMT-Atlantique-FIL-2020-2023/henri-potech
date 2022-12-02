package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName

data class CommercialOffersDto(

    @SerializedName("offers") var offers: ArrayList<OffersDto> = arrayListOf()

)

data class OffersDto(
    @SerializedName("type") var type: String,
    @SerializedName("value") var value: Int
)