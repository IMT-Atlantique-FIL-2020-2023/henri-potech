package fr.henri.potech.bookshop.domain

import java.math.BigDecimal

sealed interface Offer {
    fun apply(total: BigDecimal): BigDecimal
}

/**
 * Reduces the total price by a fixed percentage.
 */
data class Percentage(
    val value: BigDecimal
) : Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return total * (BigDecimal(1) - this.value)
    }
}

/**
 * Reduces the total price by a fixed amount.
 */
data class Minus(
    val value: BigDecimal
) : Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return total - this.value
    }
}

/**
 * Reduces the total price when the total exceeds `sliceValue`.
 */
data class Slice(
    val sliceValue: BigDecimal, val value: BigDecimal
) : Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return if (total > this.sliceValue) {
            total - this.value
        } else {
            total
        }
    }
}
