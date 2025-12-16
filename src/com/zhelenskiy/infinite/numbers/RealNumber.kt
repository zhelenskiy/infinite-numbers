package com.zhelenskiy.infinite.numbers

import com.zhelenskiy.infinite.numbers.Border.CanBeLeftBorder
import com.zhelenskiy.infinite.numbers.Border.CanBeRightBorder
import com.zhelenskiy.infinite.numbers.Border.ExclusiveLeftBorder
import com.zhelenskiy.infinite.numbers.Border.ExclusiveRightBorder
import com.zhelenskiy.infinite.numbers.Border.Finite
import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.Border.Infinite
import com.zhelenskiy.infinite.numbers.cache.CacheInternals
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rational.FractionFormat
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.limitMonotonic
import com.zhelenskiy.infinite.numbers.utils.rem
import com.zhelenskiy.infinite.numbers.utils.remQuotient
import com.zhelenskiy.infinite.numbers.utils.square
import kotlin.jvm.JvmName

/**
 * Represents a boundary value in an interval, semi-interval, or segment.
 */
sealed interface Border {
    /**
     * Marker interface for borders that can be used as a left (lower) boundary.
     */
    sealed interface CanBeLeftBorder : Border

    /**
     * Marker interface for borders that can be used as a right (upper) boundary.
     */
    sealed interface CanBeRightBorder : Border
    /**
     * A finite boundary with a specific rational value.
     */
    sealed interface Finite : Border, CanBeLeftBorder, CanBeRightBorder {
        /**
         * The rational value of this finite boundary.
         */
        val value: RationalNumber

        abstract override fun toString(): String

        /**
         * Converts this boundary to a string representation in the specified format and radix.
         *
         * @param fractionFormat The format to use for the string representation.
         * @param radix The radix (base) for the representation, default is 10.
         * @return A string representation of this boundary.
         */
        fun toString(fractionFormat: FractionFormat, radix: Int = 10): String = value.toString(fractionFormat, radix)
    }

    /**
     * An infinite boundary (positive or negative infinity).
     */
    sealed interface Infinite : Border

    /**
     * An inclusive boundary.
     *
     * @property value The rational value at this boundary.
     */
    data class Inclusive(override val value: RationalNumber) : Border, Finite, Comparable<Inclusive> {
        override fun toString(): String = value.toString()
        override fun compareTo(other: Inclusive): Int = value.compareTo(other.value)
    }

    /**
     * An exclusive border that can be used as a left (lower) boundary.
     */
    sealed interface ExclusiveLeftBorder : Border, CanBeLeftBorder, Exclusive

    /**
     * An exclusive border that can be used as a right (upper) boundary.
     */
    sealed interface ExclusiveRightBorder : Border, CanBeRightBorder, Exclusive

    /**
     * An exclusive boundary.
     */
    sealed interface Exclusive : Border, Comparable<Exclusive> {
        /**
         * A regular exclusive boundary with a finite value.
         *
         * @property value The rational value at this boundary.
         */
        data class Regular(override val value: RationalNumber) : Finite, ExclusiveLeftBorder, ExclusiveRightBorder {
            override fun toString(): String = value.toString()
            override fun compareTo(other: Exclusive): Int = when (other) {
                is Regular -> value.compareTo(other.value)
                MinusInfinity -> 1
                PlusInfinity -> -1
            }
        }
    }

    /**
     * Represents negative infinity as an exclusive left boundary.
     */
    data object MinusInfinity : ExclusiveLeftBorder, Infinite {
        override fun toString(): String = "-${Infinity}"
        override fun compareTo(other: Exclusive): Int = when (other) {
            MinusInfinity -> 0
            is Exclusive.Regular, is PlusInfinity -> -1
        }
    }

    /**
     * Represents positive infinity as an exclusive right boundary.
     */
    data object PlusInfinity : ExclusiveRightBorder, Infinite {
        override fun toString(): String = "+${Infinity}"
        override fun compareTo(other: Exclusive): Int = when (other) {
            PlusInfinity -> 0
            is Exclusive.Regular, is MinusInfinity -> 1
        }
    }

    /**
     * Companion object for infinity operations.
     */
    data object Infinity {
        override fun toString(): String = "∞"

        /**
         * Returns negative infinity.
         *
         * @return [MinusInfinity].
         */
        operator fun unaryMinus() = MinusInfinity

        /**
         * Returns positive infinity.
         *
         * @return [PlusInfinity].
         */
        operator fun unaryPlus() = PlusInfinity
    }
}

/**
 * Creates a [Bounds] from this border to another border.
 *
 * @param L The type of the left border.
 * @param R The type of the right border.
 * @param other The upper bound.
 * @return A [Bounds] instance representing the range from this to [other].
 */
operator fun <L: CanBeLeftBorder, R: CanBeRightBorder> L.rangeTo(other: R): Bounds<L, R> =
    Bounds(this, other)

/**
 * Represents a bounded region with lower and upper bounds.
 *
 * @param L The type of the left border (inclusive, exclusive, or infinite).
 * @param R The type of the right border (inclusive, exclusive, or infinite).
 * @property lowerBound The lower boundary of this region.
 * @property upperBound The upper boundary of this region.
 */
data class Bounds<out L: CanBeLeftBorder, out R: CanBeRightBorder>(val lowerBound: L, val upperBound: R) {
    init {
        when (lowerBound) {
            is Inclusive -> when (upperBound) {
                is Inclusive -> require(lowerBound.value <= upperBound.value) { "Lower bound must be less or equal to upper bound: $this" }
                is Border.Exclusive.Regular -> require(lowerBound.value < upperBound.value) { "Lower bound must be less than upper bound: $this" }
                is Border.PlusInfinity -> {}
            }
            is Border.Exclusive.Regular -> when (upperBound) {
                is Finite -> require(lowerBound.value < upperBound.value) { "Lower bound must be less than upper bound: $this" }
                is Border.PlusInfinity -> {}
            }
            is Border.MinusInfinity -> {}
        }
    }

    override fun toString(): String = toString { it.toString() }

    /**
     * Converts these bounds to a string representation in the specified format and radix.
     *
     * @param fractionFormat The format to use for the string representation.
     * @param radix The radix (base) for the representation, default is 10. It must be from 2 to 36.
     * @return A string representation of these bounds.
     */
    fun toString(fractionFormat: FractionFormat, radix: Int = 10): String =
        toString { it.toString(fractionFormat, radix) }

    private fun toString(rationalToString: (Finite) -> String): String = buildString {
        when (lowerBound) {
            is Border.Exclusive -> append('(')
            is Inclusive -> append('[')
        }
        when (lowerBound) {
            is Infinite -> append(lowerBound)
            is Finite -> append(rationalToString(lowerBound))
        }
        append(", ")
        when (upperBound) {
            is Infinite -> append(upperBound)
            is Finite -> append(rationalToString(upperBound))
        }
        when (upperBound) {
            is Border.Exclusive -> append(')')
            is Inclusive -> append(']')
        }
    }

}

/**
 * Returns the difference between upper and lower bounds if both are finite, null otherwise.
 */
@get:JvmName("diffOrNull")
val Bounds<*, *>.diff get() = if (lowerBound is Finite && upperBound is Finite) upperBound.value - lowerBound.value else null

/**
 * Returns the difference between upper and lower bounds (both are guaranteed to be finite).
 */
val Bounds<Finite, Finite>.diff get() = (this as Bounds<*, *>).diff!!

/**
 * An interval with exclusive bounds.
 */
typealias Interval = Bounds<ExclusiveLeftBorder, ExclusiveRightBorder>

/**
 * A segment with inclusive bounds.
 */
typealias Segment = Bounds<Inclusive, Inclusive>

/**
 * Bounds with any valid combination of left and right borders.
 */
typealias AnyBounds = Bounds<CanBeLeftBorder, CanBeRightBorder>

/**
 * Represents a real number as a sequence of increasingly precise rational approximating bounds.
 *
 * Real numbers are computed lazily through observation, yielding segments that converge to the actual value.
 */
public fun interface RealNumber {
    /**
     * Observes this real number as a sequence of increasingly precise segments.
     *
     * Should not be called directly outside of [EvaluationCache] implementations.
     * Use [EvaluationCache.observe] instead.
     *
     * @return An iterator of segments that converge to this real number.
     */
    @CacheInternals
    context(cache: EvaluationCache)
    fun observe(): Iterator<Segment>

    /**
     * Adds another [RealNumber] to this [RealNumber] and returns the result as a new [RealNumber].
     *
     * @param other The [RealNumber] to be added to this [RealNumber].
     * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
     */
    context(cache: EvaluationCache)
    operator fun plus(other: RealNumber): RealNumber = when (other) {
        zero -> this
        else -> limitMonotonic(this, other, RationalNumber::plus)
    }

    /**
     * Subtracts another [RealNumber] from this [RealNumber] and returns the result as a new [RealNumber].
     *
     * @param other The [RealNumber] to be subtracted from this [RealNumber].
     * @return A new [RealNumber] representing the result of the subtraction.
     */
    context(cache: EvaluationCache)
    operator fun minus(other: RealNumber): RealNumber = when (other) {
        zero -> this
        else -> limitMonotonic(this, other, RationalNumber::minus)
    }

    /**
     * Multiplies this [RealNumber] by another [RealNumber] and returns the result as a new [RealNumber].
     *
     * @param other The [RealNumber] to be multiplied with this [RealNumber].
     * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
     */
    context(cache: EvaluationCache)
    operator fun times(other: RealNumber): RealNumber = when (other) {
        zero -> zero
        one -> this
        -one -> -this
        else -> limitMonotonic(this, other, RationalNumber::times)
    }

    /**
     * Divides this [RealNumber] by another [RealNumber] and returns the result as a new [RealNumber].
     *
     * @param other The [RealNumber] to divide by.
     * @return A new [RealNumber] representing the result of the division.
     * @throws ArithmeticException if the divisor is zero.
     */
    context(cache: EvaluationCache)
    operator fun div(other: RealNumber): RealNumber = when (other) {
        zero -> throw ArithmeticException("Division by zero")
        one -> this
        -one -> -this
        else ->  limitMonotonic(this, other, RationalNumber::div)
    }

    /**
     * Increments the value of this [RealNumber] by 1 and returns the result.
     *
     * @return A new [RealNumber] representing the incremented value of this [RealNumber].
     */
    context(cache: EvaluationCache)
    operator fun inc(): RealNumber = this + one

    /**
     * Decrements the value of this [RealNumber] by 1 and returns the result.
     *
     * @return A new [RealNumber] representing the decremented value of this [RealNumber].
     */
    context(cache: EvaluationCache)
    operator fun dec(): RealNumber = this + one

    /**
     * Returns the negation of this [RealNumber].
     *
     * @return A new [RealNumber] representing the negated value of this [RealNumber].
     */
    context(cache: EvaluationCache)
    operator fun unaryMinus(): RealNumber = zero - this

    /**
     * Returns this [RealNumber].
     *
     * @return This [RealNumber].
     */
    context(cache: EvaluationCache)
    operator fun unaryPlus(): RealNumber = this

    /**
     * Raises this [RealNumber] to the power of [n] and returns the result as a new [RealNumber].
     *
     * @param n The exponent.
     * @return A new [RealNumber] representing this [RealNumber] raised to the power of [n].
     */
    context(cache: EvaluationCache)
    fun pow(n: IntegerNumber): RealNumber = when {
        n == zero -> one
        n == one -> this
        n < zero -> one / this.pow(-n)
        n % 2 == zero -> this.square().pow(n remQuotient 2)
        else -> this * this.pow(n - one)
    }
}


/**
 * Marks APIs that may produce infinite or divergent real number sequences.
 *
 * These operations require careful use as they may not terminate or converge.
 */
@RequiresOptIn
annotation class PotentiallyInfiniteRealNumberApi
