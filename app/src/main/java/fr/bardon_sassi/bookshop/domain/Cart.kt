package fr.bardon_sassi.bookshop.domain

import fr.bardon_sassi.bookshop.data.dto.OfferTypeDTO
import fr.bardon_sassi.bookshop.data.remote.HenriPotierApi
import fr.bardon_sassi.bookshop.data.remote.ListToStringJoin
import java.math.BigDecimal

data class Cart(
    val books: List<Book> = listOf(),
) {
    val total: BigDecimal
        get() {
            return books.sumOf { it.price }
        }

    fun add(book: Book): Cart {
        return copy(books = books + book)
    }

    suspend fun computeTotalWithOffer(): Pair<BigDecimal, String> {
        val commercialOffers =
            HenriPotierApi.client.getCommercialOffers(ListToStringJoin(books.map { it.isbn }))
        val availableOffers = commercialOffers.offers.map { offer ->
            when (offer) {
                is OfferTypeDTO.Percentage -> Percentage(offer.value.divide(BigDecimal(100)))
                is OfferTypeDTO.Minus -> Minus(offer.value)
                is OfferTypeDTO.Slice -> Slice(offer.sliceValue, offer.value)
            }
        }
        return availableOffers.map { Pair(it.apply(total), it.toString()) }
            .minByOrNull { it.first }!!
    }
}
