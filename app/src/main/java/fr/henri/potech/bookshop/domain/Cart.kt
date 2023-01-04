package fr.henri.potech.bookshop.domain

import fr.henri.potech.bookshop.data.dto.OfferTypeDTO
import fr.henri.potech.bookshop.data.remote.HenriPotierApi
import fr.henri.potech.bookshop.data.remote.ListToStringJoin

data class Cart(
    var books: List<Book>,

    ) {
    fun total(): Double {
        return books.sumOf { it.price }
    }

    suspend fun computeTotalWithOffer(): Double {
        val commercialOffers =
            HenriPotierApi.client.getCommercialOffers(ListToStringJoin(books.map { it.isbn }))
        val availableOffers = commercialOffers.offers.map { offer ->
            when (offer) {
                is OfferTypeDTO.Percentage -> Percentage(offer.value)
                is OfferTypeDTO.Minus -> Minus(offer.value)
                is OfferTypeDTO.Slice -> Slice(offer.sliceValue, offer.value)
            }
        }

        return availableOffers.map { it.apply(total()) }.minOrNull() ?: total()

    }

}
