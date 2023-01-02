package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory

data class CommercialOffersDto(
    @SerializedName("offers") var offers: ArrayList<OfferTypeDto> = arrayListOf()
)


sealed class OfferTypeDto {
    data class Percentage(@SerializedName("value") val value: Double) : OfferTypeDto()
    data class Minus(@SerializedName("value") val value: Double) : OfferTypeDto()
    data class Slice(
        @SerializedName("value") val value: Double,
        @SerializedName("sliceValue") val sliceValue: Double
    ) : OfferTypeDto()

    companion object {
        val runtimeTypeAdapterFactory: RuntimeTypeAdapterFactory<OfferTypeDto> =
            RuntimeTypeAdapterFactory
                .of(OfferTypeDto::class.java, "type")
                .registerSubtype(Percentage::class.java, "percentage")
                .registerSubtype(Minus::class.java, "minus")
                .registerSubtype(Slice::class.java, "slice")
    }

}