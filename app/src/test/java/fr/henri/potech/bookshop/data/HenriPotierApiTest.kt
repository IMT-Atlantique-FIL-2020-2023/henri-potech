package fr.henri.potech.bookshop.data

import fr.henri.potech.bookshop.data.remote.HenriPotierApi
import fr.henri.potech.bookshop.data.remote.ListToStringJoin
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HenriPotierApiTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()

    }

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