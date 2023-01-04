package fr.henri.potech.bookshop.data.dto

import com.google.gson.annotations.SerializedName
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import java.math.BigDecimal

data class CommercialOffersDTO(
    @SerializedName("offers") var offers: ArrayList<OfferTypeDTO> = arrayListOf()
)


sealed class OfferTypeDTO {
    data class Percentage(@SerializedName("value") val value: BigDecimal) : OfferTypeDTO()
    data class Minus(@SerializedName("value") val value: BigDecimal) : OfferTypeDTO()
    data class Slice(
        @SerializedName("value") val value: BigDecimal,
        @SerializedName("sliceValue") val sliceValue: BigDecimal
    ) : OfferTypeDTO()

    companion object {
        val runtimeTypeAdapterFactory: RuntimeTypeAdapterFactory<OfferTypeDTO> =
            RuntimeTypeAdapterFactory
                .of(OfferTypeDTO::class.java, "type")
                .registerSubtype(Percentage::class.java, "percentage")
                .registerSubtype(Minus::class.java, "minus")
                .registerSubtype(Slice::class.java, "slice")
    }

}