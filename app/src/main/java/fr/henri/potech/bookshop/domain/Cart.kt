package fr.henri.potech.bookshop.domain

data class Cart(
    var books: List<Book>,
    var availableOffers: List<Offer>,
    var bestOffer: Offer?
)
