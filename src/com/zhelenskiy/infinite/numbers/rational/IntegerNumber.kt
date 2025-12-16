package com.zhelenskiy.infinite.numbers.rational

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.utils.absolute
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.rem
import com.zhelenskiy.infinite.numbers.utils.remQuotient
import com.zhelenskiy.infinite.numbers.utils.sign
import com.zhelenskiy.infinite.numbers.utils.toString
import kotlin.jvm.JvmStatic

internal typealias IntStorage = BigInteger

/**
 * Arbitrary‑precision integer type
 */
@ConsistentCopyVisibility
public data class IntegerNumber internal constructor(val storage: IntStorage) : RationalNumber {
    /**
     * The numerator of this [IntegerNumber] (which is itself).
     */
    override val numerator: IntegerNumber get() = this

    /**
     * The denominator of this [IntegerNumber] (which is always 1).
     */
    override val denominator: IntegerNumber get() = one

    override fun toString(): String = storage.toString()

    override fun compareTo(other: RationalNumber): Int =
        if (other is IntegerNumber) storage.compareTo(other.storage) else super.compareTo(other)

    override fun toString(fractionFormat: FractionFormat, radix: Int): String = toString(radix)

    /**
     * Multiplies this [IntegerNumber] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be multiplied with this [IntegerNumber].
     * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
     */
    context(cache: EvaluationCache)
    public operator fun times(other: IntegerNumber): IntegerNumber = times1(other)

    /**
     * Multiplies this [IntegerNumber] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be multiplied with this [IntegerNumber].
     * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
     */
    public operator fun times(other: IntegerNumber): IntegerNumber = IntegerNumber(storage * other.storage)

    /**
     * Decrements the value of this [IntegerNumber] by 1 and returns the result.
     *
     * @return A new [IntegerNumber] representing the decremented value of this [IntegerNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun dec(): IntegerNumber = dec1()

    /**
     * Decrements the value of this [IntegerNumber] by 1 and returns the result.
     *
     * @return A new [IntegerNumber] representing the decremented value of this [IntegerNumber].
     */
    public override operator fun dec(): IntegerNumber = IntegerNumber(storage - 1)

    /**
     * Increments the value of this [IntegerNumber] by 1 and returns the result.
     *
     * @return A new [IntegerNumber] representing the incremented value of this [IntegerNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun inc(): IntegerNumber = inc1()

    /**
     * Increments the value of this [IntegerNumber] by 1 and returns the result as a new [IntegerNumber].
     *
     * @return A new [IntegerNumber] representing the incremented value of this [IntegerNumber].
     */
    public override operator fun inc(): IntegerNumber = IntegerNumber(storage + 1)

    /**
     * Subtracts another [IntegerNumber] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be subtracted from this [IntegerNumber].
     * @return A new [IntegerNumber] representing the result of the subtraction.
     */
    context(cache: EvaluationCache)
    public operator fun minus(other: IntegerNumber): IntegerNumber = minus1(other)

    /**
     * Subtracts another [IntegerNumber] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be subtracted from this [IntegerNumber].
     * @return A new [IntegerNumber] representing the result of the subtraction.
     */
    public operator fun minus(other: IntegerNumber): IntegerNumber = IntegerNumber(storage - other.storage)

    /**
     * Adds another [IntegerNumber] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be added to this [IntegerNumber].
     * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
     */
    context(cache: EvaluationCache)
    public operator fun plus(other: IntegerNumber): IntegerNumber = plus1(other)

    /**
     * Adds another [IntegerNumber] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
     *
     * @param other The [IntegerNumber] to be added to this [IntegerNumber].
     * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
     */
    public operator fun plus(other: IntegerNumber): IntegerNumber = IntegerNumber(storage + other.storage)

    /**
     * Returns the negation of this [IntegerNumber].
     *
     * @return A new [IntegerNumber] representing the negated value of this [IntegerNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun unaryMinus(): IntegerNumber = unaryMinus1()

    /**
     * Returns the negation of this [IntegerNumber].
     *
     * @return A new [IntegerNumber] representing the negated value of this [IntegerNumber].
     */
    public override operator fun unaryMinus(): IntegerNumber = IntegerNumber(-storage)

    /**
     * Returns this [IntegerNumber].
     *
     * @return this [IntegerNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun unaryPlus(): IntegerNumber = unaryPlus1()

    /**
     * Returns this [IntegerNumber].
     *
     * @return This [IntegerNumber].
     */
    public override operator fun unaryPlus(): IntegerNumber = this

    context(cache: EvaluationCache)
    public override operator fun times(other: RealNumber): RealNumber = when {
        other is IntegerNumber -> this * other
        this == zero -> zero
        this == one -> other
        this == -one -> -other
        else -> super.times(other)
    }

    context(cache: EvaluationCache)
    public override fun plus(other: RealNumber): RealNumber = when {
        other is IntegerNumber -> this + other
        this == zero -> other
        else -> super.plus(other)
    }

    context(cache: EvaluationCache)
    public override fun minus(other: RealNumber): RealNumber = when (other) {
        is IntegerNumber -> this - other
        else -> super.minus(other)
    }

    /**
     * Returns the digit sequence representation of this [IntegerNumber] in the specified radix.
     *
     * @param radix The radix (base) for the digit sequence. Must be greater than 1.
     * @return A [RationalFlatInfiniteFormatter] representing the digit sequence.
     */
    override fun digitSequence(radix: IntegerNumber): RationalFlatInfiniteFormatter {
        require(radix > one) { "Radix must be positive" }
        val integerPart = buildList {
            var value = this@IntegerNumber
            if (value == zero) add(zero)
            value = value.absolute()
            while (value != zero) {
                add(value % radix)
                value = value remQuotient radix
            }
        }
        return RationalFlatInfiniteFormatter(sign, integerPart, emptySequence())
    }

    public companion object {
        /**
         * The value of 1.
         */
        @JvmStatic
        public val one: IntegerNumber = IntegerNumber(1)

        /**
         * The value of 2.
         */
        @JvmStatic
        public val two: IntegerNumber = IntegerNumber(2)

        /**
         * The value of 0.
         */
        @JvmStatic
        public val zero: IntegerNumber = IntegerNumber(0)

        /**
         * The value of 10.
         */
        @JvmStatic
        public val ten: IntegerNumber = IntegerNumber(10)
    }
}

private fun IntegerNumber.dec1(): IntegerNumber = dec()
private fun IntegerNumber.inc1(): IntegerNumber = inc()
private fun IntegerNumber.unaryPlus1(): IntegerNumber = unaryPlus()
private fun IntegerNumber.unaryMinus1(): IntegerNumber = unaryMinus()
private fun IntegerNumber.plus1(other: IntegerNumber): IntegerNumber = plus(other)
private fun IntegerNumber.minus1(other: IntegerNumber): IntegerNumber = minus(other)
private fun IntegerNumber.times1(other: IntegerNumber): IntegerNumber = times(other)
