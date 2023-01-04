package fr.henri.potech.bookshop.domain

import java.math.BigDecimal

sealed interface Offer {
    fun apply(total: BigDecimal): BigDecimal
}

/**
 * Reduces the total price by a fixed percentage.
 */
data class Percentage(
    val value: Double
): Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return total * BigDecimal(1 - this.value)
    }
}

/**
 * Reduces the total price by a fixed amount.
 */
data class Minus(
    val value: Double
): Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return total - BigDecimal(this.value)
    }
}

/**
 * Reduces the total price when the total exceeds `sliceValue`.
 */
data class Slice(
    val sliceValue: Double,
    val value: Double
): Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        return if (total > BigDecimal(this.sliceValue)) {
            total - BigDecimal(this.value)
        } else {
            total
        }
    }
}
