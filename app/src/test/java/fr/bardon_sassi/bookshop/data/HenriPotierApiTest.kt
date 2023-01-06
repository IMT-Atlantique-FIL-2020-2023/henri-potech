package fr.bardon_sassi.bookshop.data

import fr.bardon_sassi.bookshop.data.remote.HenriPotierApi
import fr.bardon_sassi.bookshop.data.remote.ListToStringJoin
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class HenriPotierApiTest {

    @ExperimentalCoroutinesApi
    @Test
    fun listBooks() = runTest {
        val books = HenriPotierApi.client.getBooks()
        Assert.assertEquals(7, books.size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun listCommercialOffers() = runTest {
        val commercialOffers =
            HenriPotierApi.client.getCommercialOffers(
                ListToStringJoin(
                    listOf(
                        "a460afed-e5e7-4e39-a39d-c885c05db861",
                        "c8fabf68-8374-48fe-a7ea-a00ccd07afff"
                    )
                )
            )
        Assert.assertEquals(3, commercialOffers.offers.size)
    }
}