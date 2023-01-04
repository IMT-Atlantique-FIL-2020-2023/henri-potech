package fr.henri.potech.bookshop.domain

sealed interface Offer {
    fun apply(total: Double): Double
}

/**
 * Reduces the total price by a fixed percentage.
 */
data class Percentage(
    val value: Double
) : Offer {
    override fun apply(total: Double): Double {
        return total * (1 - this.value)
    }
}

/**
 * Reduces the total price by a fixed amount.
 */
data class Minus(
    val value: Double
) : Offer {
    override fun apply(total: Double): Double {
        return total - this.value
    }
}

/**
 * Reduces the total price when the total exceeds `sliceValue`.
 */
data class Slice(
    val sliceValue: Double, val value: Double
) : Offer {
    override fun apply(total: Double): Double {
        return if (total > this.sliceValue) {
            total - this.value
        } else {
            total
        }
    }
}
