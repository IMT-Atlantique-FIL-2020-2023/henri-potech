package fr.henri.potech.bookshop.data.remote

import com.google.gson.GsonBuilder
import fr.henri.potech.bookshop.data.remote.dto.BookDTO
import fr.henri.potech.bookshop.data.remote.dto.CommercialOffersDTO
import fr.henri.potech.bookshop.data.remote.dto.OfferTypeDTO.Companion.runtimeTypeAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://henri-potier.techx.fr/"

interface HenriPotierApi {
    companion object {
        val client: HenriPotierApi = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create()
            )
        ).baseUrl(BASE_URL).build().create(HenriPotierApi::class.java)
    }

    @GET("books")
    suspend fun getBooks(): List<BookDTO>

    @GET("/books/{isbns}/commercialOffers")
    suspend fun getCommercialOffers(
        @Path("isbns", encoded = true) isbns: ListToStringJoin
    ): CommercialOffersDTO
}

/**
 * This class is used to join a list of strings with a comma
 * It is used to pass a list of isbns to the API
 * toString will be called by retrofit
 */
class ListToStringJoin(val list: List<String>) {
    override fun toString() = list.joinToString(",")
}