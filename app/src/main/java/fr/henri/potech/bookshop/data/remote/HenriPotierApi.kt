package fr.henri.potech.bookshop.data.remote

import com.google.gson.GsonBuilder
import fr.henri.potech.bookshop.data.dto.BookDto
import fr.henri.potech.bookshop.data.dto.CommercialOffersDto
import fr.henri.potech.bookshop.data.dto.OfferType.Companion.runtimeTypeAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://henri-potier.techx.fr/"

interface HenriPotierApi {
    companion object {
        val client: HenriPotierApi = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                        .create()
                )
            )
            .baseUrl(BASE_URL)
            .build()
            .create(HenriPotierApi::class.java)
    }

    @GET("books")
    suspend fun getBooks(): List<BookDto>

    @GET("/books/{isbns}/commercialOffers")
    suspend fun getCommercialOffers(
        @Path("isbns", encoded = true) isbns: ListToStringJoin
    ): CommercialOffersDto
}

class ListToStringJoin(val list: List<String>) {
    override fun toString() = list.joinToString(",")

}