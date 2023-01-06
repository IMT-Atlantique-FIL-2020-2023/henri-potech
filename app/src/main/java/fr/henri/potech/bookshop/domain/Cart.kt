package fr.henri.potech.bookshop.domain

import fr.henri.potech.bookshop.data.dto.OfferTypeDTO
import fr.henri.potech.bookshop.data.remote.HenriPotierApi
import fr.henri.potech.bookshop.data.remote.ListToStringJoin
import java.math.BigDecimal

data class Cart(
    var books: List<Book>,
) {
    private val total: BigDecimal
        get() { return books.sumOf { it.price } }

    suspend fun computeTotalWithOffer(): BigDecimal {
        val commercialOffers =
            HenriPotierApi.client.getCommercialOffers(ListToStringJoin(books.map { it.isbn }))
        val availableOffers = commercialOffers.offers.map { offer ->
            when (offer) {
                is OfferTypeDTO.Percentage -> Percentage(value = offer.value)
                is OfferTypeDTO.Minus -> Minus(value = offer.value)
                is OfferTypeDTO.Slice -> Slice(sliceValue = offer.sliceValue, value = offer.value)
            }
        }

        return availableOffers.minOfOrNull { it.apply(total) } ?: total
    }
}
