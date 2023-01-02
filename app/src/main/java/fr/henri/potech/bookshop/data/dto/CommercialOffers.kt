package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory

data class CommercialOffersDto(

    @SerializedName("offers") var offers: ArrayList<OfferDto> = arrayListOf()

)


data class OfferDto(
    @SerializedName("type") var type: String,
)

sealed class OfferType {
    data class Percentage(@SerializedName("value") val value: Double) : OfferType()
    data class Minus(@SerializedName("value") val value: Double) : OfferType()
    data class Slice(
        @SerializedName("value") val value: Double,
        @SerializedName("sliceValue") val sliceValue: Double
    ) : OfferType()

    companion object {
        val runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
            .of(OfferType::class.java, "type")
            .registerSubtype(Percentage::class.java, "percentage")
            .registerSubtype(Minus::class.java, "minus")
            .registerSubtype(Slice::class.java, "slice")
    }

}