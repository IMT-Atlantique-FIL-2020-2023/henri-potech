package fr.bardon_sassi.bookshop.domain

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal
import java.net.URL

data class Book(
    val coverUrl: URL,
    val title: String,
    val isbn: String,
    val synopsis: String,
    val price: BigDecimal,
    /**
     * Unique ID used by recycler views.
     */
    val uuid: String = isbn + Math.random().toString(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        URL(parcel.readString()),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble().toBigDecimal(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(coverUrl.toString())
        parcel.writeString(title)
        parcel.writeString(isbn)
        parcel.writeString(synopsis)
        parcel.writeDouble(price.toDouble())
        parcel.writeString(uuid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
