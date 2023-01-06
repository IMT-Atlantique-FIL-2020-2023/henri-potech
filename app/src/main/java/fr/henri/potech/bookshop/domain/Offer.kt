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
        val reducedTotal = total * (BigDecimal(1) - this.value)
        println("$this on $total = $reducedTotal")
        return reducedTotal
    }
}

/**
 * Reduces the total price by a fixed amount.
 */
data class Minus(
    val value: BigDecimal
) : Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        val reducedTotal = total - this.value
        println("$this on $total = $reducedTotal")
        return reducedTotal
    }
}

/**
 * Reduces the total price when the total exceeds `sliceValue`.
 */
data class Slice(
    val sliceValue: BigDecimal, val value: BigDecimal
) : Offer {
    override fun apply(total: BigDecimal): BigDecimal {
        val reducedTotal = if (total > this.sliceValue) {
            total - this.value
        } else {
            total
        }
        println("$this on $total = $reducedTotal")
        return reducedTotal
    }
}
