package com.zhelenskiy.infinite.numbers.rational

import com.ionspin.kotlin.bignum.integer.Sign
import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.Segment
import com.zhelenskiy.infinite.numbers.cache.CacheInternals
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.COMMA
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.DIVISION
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.DOT
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.MIXED
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RegularRationalNumber.Companion.createThisOrAncestor
import com.zhelenskiy.infinite.numbers.utils.absolute
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.parseString
import com.zhelenskiy.infinite.numbers.utils.rem
import com.zhelenskiy.infinite.numbers.utils.remQuotient
import com.zhelenskiy.infinite.numbers.utils.sign
import com.zhelenskiy.infinite.numbers.utils.square
import com.zhelenskiy.infinite.numbers.utils.toIntOrNull
import com.zhelenskiy.infinite.numbers.utils.toString
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads


/**
 * Arbitrary-precision rational number type represented as an irreducible fraction of integer numerator and natural denominator.
 */
public interface RationalNumber: RealNumber, Comparable<RationalNumber> {
    /**
     * The numerator of this rational number.
     */
    public val numerator: IntegerNumber

    /**
     * The denominator of this rational number.
     *
     * The value is greater than 0.
     */
    public val denominator: IntegerNumber

    context(cache: EvaluationCache)
    override operator fun plus(other: RealNumber): RealNumber = when (other) {
        is RationalNumber -> this + other
        else -> super.plus(other)
    }

    context(cache: EvaluationCache)
    override fun minus(other: RealNumber): RealNumber = when (other) {
        is RationalNumber -> this - other
        else -> super.minus(other)
    }

    context(cache: EvaluationCache)
    override fun times(other: RealNumber): RealNumber = when (other) {
        is RationalNumber -> this * other
        else -> super.times(other)
    }

    context(cache: EvaluationCache)
    override fun div(other: RealNumber): RealNumber = when (other) {
        is RationalNumber -> this / other
        else -> super.div(other)
    }

    /**
     * Increments the value of this [RationalNumber] by 1 and returns the result.
     *
     * @return A new [RationalNumber] representing the incremented value of this [RationalNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun inc(): RationalNumber = inc1()

    /**
     * Increments the value of this [RationalNumber] by 1 and returns the result.
     *
     * @return A new [RationalNumber] representing the incremented value of this [RationalNumber].
     */
    public operator fun inc(): RationalNumber = this + one

    /**
     * Returns the negation of this [RationalNumber].
     *
     * @return A new [RationalNumber] representing the negated value of this [RationalNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun unaryMinus(): RationalNumber = unaryMinus1()

    /**
     * Returns the negation of this [RationalNumber].
     *
     * @return A new [RationalNumber] representing the negated value of this [RationalNumber].
     */
    public operator fun unaryMinus(): RationalNumber = RationalNumber(-numerator, denominator, calculateGcd = false)

    /**
     * Returns this [RationalNumber].
     *
     * @return This [RationalNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun unaryPlus(): RationalNumber = unaryPlus1()

    /**
     * Returns this [RationalNumber].
     *
     * @return This [RationalNumber].
     */
    public operator fun unaryPlus(): RationalNumber = this


    /**
     * Multiplies this [RationalNumber] by another [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be multiplied with this [RationalNumber].
     * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
     */
    context(cache: EvaluationCache)
    public operator fun times(other: RationalNumber): RationalNumber = times1(other)

    /**
     * Multiplies this [RationalNumber] by another [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be multiplied with this [RationalNumber].
     * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
     */
    public operator fun times(other: RationalNumber): RationalNumber =
        RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator)

    /**
     * Decrements the value of this [RationalNumber] by 1 and returns the result.
     *
     * @return A new [RationalNumber] representing the decremented value of this [RationalNumber].
     */
    context(cache: EvaluationCache)
    public override operator fun dec(): RationalNumber = dec1()

    /**
     * Decrements the value of this [RationalNumber] by 1 and returns the result.
     *
     * @return A new [RationalNumber] representing the decremented value of this [RationalNumber].
     */
    public operator fun dec(): RationalNumber = this - one

    /**
     * Subtracts another [RationalNumber] from this [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be subtracted from this [RationalNumber].
     * @return A new [RationalNumber] representing the result of the subtraction.
     */
    context(cache: EvaluationCache)
    public operator fun minus(other: RationalNumber): RationalNumber = minus1(other)

    /**
     * Subtracts another [RationalNumber] from this [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be subtracted from this [RationalNumber].
     * @return A new [RationalNumber] representing the result of the subtraction.
     */
    public operator fun minus(other: RationalNumber): RationalNumber =
        underCommonDivisor(this, other) { a, b -> a - b }

    /**
     * Adds another [RationalNumber] to this [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be added to this [RationalNumber].
     * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
     */
    context(cache: EvaluationCache)
    public operator fun plus(other: RationalNumber): RationalNumber = plus1(other)

    /**
     * Adds another [RationalNumber] to this [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to be added to this [RationalNumber].
     * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
     */
    public operator fun plus(other: RationalNumber): RationalNumber =
        underCommonDivisor(this, other) { a, b -> a + b }

    /**
     * Divides this [RationalNumber] by another [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to divide by.
     * @return A new [RationalNumber] representing the result of the division.
     * @throws IllegalArgumentException if the divisor is zero.
     */
    context(cache: EvaluationCache)
    public operator fun div(other: RationalNumber): RationalNumber = div1(other)

    /**
     * Divides this [RationalNumber] by another [RationalNumber] and returns the result as a new [RationalNumber].
     *
     * @param other The [RationalNumber] to divide by.
     * @return A new [RationalNumber] representing the result of the division.
     * @throws IllegalArgumentException if the divisor is zero.
     */
    public operator fun div(other: RationalNumber): RationalNumber {
        require(other != zero) { "Divisor must be non-zero" }

        return RationalNumber(this.numerator * other.denominator, this.denominator * other.numerator)
    }

    /**
     * Multiplies this [RationalNumber] by another [RationalNumber] without calculating the GCD for the result.
     * This is faster but may result in a non-reduced fraction and break the invariants.
     *
     * It must be used with caution, only in performance-critical situations
     * where irreducibility is guaranteed by the contract, e.g., in the power implementation.
     *
     * @param other The [RationalNumber] to be multiplied with this [RationalNumber].
     * @return A new [RationalNumber] representing the product, possibly not in the reduced form.
     */
    public fun timesUnsafe(other: RationalNumber): RationalNumber =
        RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator, calculateGcd = false)

    /**
     * Divides this [RationalNumber] by another [RationalNumber] without calculating the GCD for the result.
     * This is faster but may result in a non-reduced fraction and break the invariants.
     *
     * It must be used with caution, only in performance-critical situations
     * where irreducibility is guaranteed by the contract, e.g., in the power implementation.
     *
     * @param other The [RationalNumber] to divide by.
     * @return A new [RationalNumber] representing the result of the division, possibly not in the reduced form.
     */
    public fun divUnsafe(other: RationalNumber): RationalNumber =
        RationalNumber(this.numerator * other.denominator, this.denominator * other.numerator, calculateGcd = false)

    override fun compareTo(other: RationalNumber): Int {
        if (this.denominator == other.denominator) return this.numerator.compareTo(other.numerator)
        val thisNumber = this.numerator * other.denominator
        val otherNumber = other.numerator * this.denominator
        return thisNumber.compareTo(otherNumber)
    }

    /**
     * Converts this [RationalNumber] to a string representation in the specified format and radix.
     *
     * @param fractionFormat The format to use for the string representation.
     * @param radix The radix (base) for the representation, default is 10. It must be from 2 to 36.
     * @return A string representation of this [RationalNumber].
     */
    public fun toString(fractionFormat: FractionFormat, radix: Int = 10): String = when (fractionFormat) {
        DOT -> toFlatPeriodicFormat(radix).toString(separator = ".")
        COMMA -> toFlatPeriodicFormat(radix).toString(separator = ",")
        DIVISION -> "${numerator.toString(radix)}/${denominator.toString(radix)}"
        MIXED -> {
            val integerPart = integerPart()
            val integerString = if (integerPart == zero) "" else integerPart.toString(radix) + " "
            val fractionString = fractionalPart().absolute().toString(DIVISION, radix)
            "$integerString$fractionString"
        }
    }


    /**
     * Raises this [RationalNumber] to the power of [n] and returns the result as a new [RationalNumber].
     *
     * @param n The exponent.
     * @return A new [RationalNumber] representing this [RationalNumber] raised to the power of [n].
     */
    context(cache: EvaluationCache)
    public override fun pow(n: IntegerNumber): RationalNumber = pow1(n)

    /**
     * Raises this [RationalNumber] to the power of [n] and returns the result as a new [RationalNumber].
     *
     * @param n The exponent.
     * @return A new [RationalNumber] representing this [RationalNumber] raised to the power of [n].
     */
    public fun pow(n: IntegerNumber): RationalNumber = when {
        n == zero -> one
        n == one -> this
        n < zero -> one.divUnsafe(pow(-n))
        this == one -> one
        this == zero -> zero
        this == -one -> if (n % 2 == zero) one else -one
        n % 2 == zero -> square().pow(n remQuotient 2)
        else -> this.timesUnsafe(pow(n - one))
    }

    @CacheInternals
    context(cache: EvaluationCache)
    override fun observe(): Iterator<Segment> = iterator {
        val bound = Inclusive(this@RationalNumber)
        yield(bound..bound)
    }

    /**
     * Returns the digit sequence representation of this [RationalNumber] in the specified radix.
     *
     * @param radix The radix (base) for the digit sequence. Must be greater than 1.
     * @return A [RationalFlatInfiniteFormatter] representing the digit sequence.
     */
    fun digitSequence(radix: IntegerNumber): RationalFlatInfiniteFormatter

    public companion object {
        /**
         * Creates a [RationalNumber] from the given numerator and denominator.
         *
         * The resulting fraction will be reduced by the GCD, unless [calculateGcd] is set to false.
         * If it is set to false, construction of [RationalNumber] is faster but may result in a non-reduced fraction and break the invariants.
         * The `false` value should be used only in performance-critical situations with irreducibility guaranteed by the contract,
         * e.g., in the power implementation.
         *
         * @param numerator The numerator of the rational number.
         * @param denominator The denominator of the rational number.
         * @param calculateGcd Whether to calculate and reduce by the GCD, the default is true.
         * @return A new [RationalNumber] instance.
         */
        public operator fun invoke(numerator: IntegerNumber, denominator: IntegerNumber, calculateGcd: Boolean = true): RationalNumber =
            createThisOrAncestor(numerator, denominator, calculateGcd)

        /**
         * Performs an operation on two [RationalNumber]s under a common denominator.
         *
         * @param first The first [RationalNumber].
         * @param second The second [RationalNumber].
         * @param action The action to perform on the numerators under the common denominator.
         * @return A new [RationalNumber] representing the result of the action.
         */
        public inline fun underCommonDivisor(
            first: RationalNumber, second: RationalNumber, action: (IntegerNumber, IntegerNumber) -> IntegerNumber
        ): RationalNumber {
            val commonDenominator = first commonDenominatorWith second
            val newFirst =
                if (first.denominator == commonDenominator) first.numerator
                else first.numerator * (commonDenominator remQuotient first.denominator)
            val newSecond =
                if (second.denominator == commonDenominator) second.numerator
                else second.numerator * (commonDenominator remQuotient second.denominator)
            return RationalNumber(action(newFirst, newSecond), commonDenominator)
        }
    }
}

private fun RationalNumber.pow1(n: IntegerNumber): RationalNumber = pow(n)
private fun RationalNumber.dec1(): RationalNumber = dec()
private fun RationalNumber.inc1(): RationalNumber = inc()
private fun RationalNumber.unaryPlus1(): RationalNumber = unaryPlus()
private fun RationalNumber.unaryMinus1(): RationalNumber = unaryMinus()
private fun RationalNumber.plus1(other: RationalNumber): RationalNumber = plus(other)
private fun RationalNumber.minus1(other: RationalNumber): RationalNumber = minus(other)
private fun RationalNumber.times1(other: RationalNumber): RationalNumber = times(other)
private fun RationalNumber.div1(other: RationalNumber): RationalNumber = div(other)

/**
 * Enumeration of formats for representing rational numbers as strings.
 */
public enum class FractionFormat {
    /** Represents the fraction as numerator/denominator (e.g., "3/4"). */
    DIVISION,

    /** Represents the fraction as a mixed number (e.g., "1 1/2"). */
    MIXED,

    /** Represents the fraction as a decimal with a dot separator (e.g., "0.5"). */
    DOT,

    /** Represents the fraction as a decimal with a comma separator (e.g., "0,5"). */
    COMMA
}

/**
 * Calculates the common denominator between this [RationalNumber] and another [RationalNumber].
 *
 * @param other The other [RationalNumber].
 * @return The common denominator as an [IntegerNumber].
 */
public infix fun RationalNumber.commonDenominatorWith(other: RationalNumber): IntegerNumber = when {
    // hot paths first
    this is IntegerNumber -> other.denominator
    other is IntegerNumber -> this.denominator
    this.denominator == other.denominator -> denominator
    else -> (this.denominator * other.denominator) remQuotient gcd(this.denominator, other.denominator)
}


private tailrec fun gcd(a: IntegerNumber, b: IntegerNumber): IntegerNumber = if (b == zero) a else gcd(b, a % b)

/**
 * Parses a string representation of a rational number in the specified radix.
 * Supports formats: "3/4", "0.5", "0.5(3)", "0,5", "0,5(3)".
 *
 * @param string The string to parse.
 * @param radix The radix (base) for parsing, default is 10. It must be from 2 to 36.
 * @return A new [RationalNumber] parsed from the string.
 * @throws IllegalArgumentException if the string format is invalid.
 */
@JvmOverloads
@JvmName("fromString")
public operator fun RationalNumber.Companion.invoke(string: String, radix: Int = 10): RationalNumber {
    if (string.contains('/')) {
        val (numeratorString, denominatorString) = string.split('/', limit = 2)
        return RationalNumber(
            numerator = IntegerNumber.parseString(numeratorString, radix),
            denominator = IntegerNumber.parseString(denominatorString, radix)
        )
    }
    val parts = string.split('.', ',')
    require(parts.size <= 2) { "Too many parts in string: $string" }
    require(parts.isNotEmpty()) { "String is empty" }
    val integerPart = parts[0].ifEmpty { "0" }
    fun verify(part: String) =
        require(part.all { it in '0'..'9' || it.isLetter() }) { "Invalid character: $string" }
    verify(integerPart.removePrefix("-"))
    val sign = if (string.startsWith("-")) -one else one
    val afterComma = parts.getOrNull(1) ?: ""
    val (fractionalPartString, periodString) =
        if (afterComma.endsWith(')')) afterComma.substringBefore('(') to afterComma.substringAfter('(').dropLast(1)
        else afterComma to ""
    verify(fractionalPartString)
    verify(periodString)

    val integer = IntegerNumber.parseString(integerPart.removePrefix("-").ifEmpty { "0" }, radix)
    val fractionalNumeratorOrNull = if (fractionalPartString.isEmpty()) null else IntegerNumber.parseString(fractionalPartString, radix)
    val periodNumerator = IntegerNumber.parseString(periodString.ifEmpty { "0" }, radix)
    val fractionalDenominator = IntegerNumber(value = radix).pow(n = IntegerNumber(fractionalPartString.length))
    val fractional = (fractionalNumeratorOrNull ?: zero) / fractionalDenominator
    val periodDenominator = IntegerNumber(value = radix).pow(n = IntegerNumber(periodString.length)).dec()
    val period = if (periodDenominator == zero) zero else periodNumerator / periodDenominator / fractionalDenominator
    return (integer + fractional + period) * sign
}

@ConsistentCopyVisibility
internal data class RegularRationalNumber private constructor(override val numerator: IntegerNumber, override val denominator: IntegerNumber) : RationalNumber {
    override fun toString(): String = "$numerator/$denominator"

    override fun digitSequence(radix: IntegerNumber): RationalFlatInfiniteFormatter {
        require(radix > one) { "Radix must be greater than 1" }
        val number = this@RegularRationalNumber
        val sign = number.sign
        val quotient = numerator remQuotient denominator
        var remainder = numerator.absolute() % denominator
        val rest = sequence {
            while (true) {
                val digit = (remainder * radix).remQuotient(denominator)
                yield(digit)
                if (remainder == zero) break
                remainder = (remainder * radix) % denominator
            }
        }
        return RationalFlatInfiniteFormatter(sign, quotient.digitSequence(radix).integerPart, rest)
    }

    companion object {
        internal fun createThisOrAncestor(
            numerator: IntegerNumber, denominator: IntegerNumber, calculateGcd: Boolean
        ): RationalNumber {
            require(denominator != zero) { "Denominator must be non-zero" }
            if (denominator.storage.getSign() == Sign.NEGATIVE) return createThisOrAncestor(-numerator, -denominator, calculateGcd)
            val gcd = if (calculateGcd) gcd(numerator, denominator).absolute() else one
            val newNumerator = if (calculateGcd) numerator remQuotient gcd else numerator
            val newDenominator = if (calculateGcd) denominator remQuotient gcd else denominator
            return if (newDenominator == one) newNumerator else RegularRationalNumber(newNumerator, newDenominator)
        }
    }
}

/**
 * Formatter for representing rational numbers in flat periodic notation (e.g., "0.(3)" for 1/3).
 *
 * @property hasMinus Whether the number is negative.
 * @property integerPart The integer part of the number.
 * @property fractionBeforePeriod The fractional digits before the repeating period.
 * @property period The repeating period digits.
 * @property radix The radix (base) used for the representation.
 */
@ConsistentCopyVisibility
public data class RationalFlatPeriodicFormatter internal constructor(
    val hasMinus: Boolean,
    val integerPart: IntegerNumber,
    val fractionBeforePeriod: List<IntegerNumber>,
    val period: List<IntegerNumber>,
    val radix: IntegerNumber,
) {
    override fun toString(): String = toString(".")

    /**
     * Converts this formatter to a string representation with the specified separator.
     *
     * @param separator The separator to use between integer and fractional parts.
     * @param showLeadingZeros Whether to show leading zeros in the integer part.
     * @return A string representation of the rational number in periodic notation.
     */
    fun toString(separator: String, showLeadingZeros: Boolean = true): String = buildString {
        val radix = radix.toIntOrNull() ?: throw IllegalArgumentException("Radix $radix does not support toString")
        fun List<IntegerNumber>.joinDigitsToString() = joinToString("") { it.toString(radix) }

        if (hasMinus) append('-')
        if (showLeadingZeros || integerPart != zero) append(integerPart.toString(radix))
        if (fractionBeforePeriod.any { it != zero } || period.any { it != zero }) {
            append(separator).append(fractionBeforePeriod.joinDigitsToString())
            if (period.any { it != zero }) {
                append('(').append(period.joinDigitsToString()).append(')')
            }
        }
    }
}

/**
 * Returns the integer part of this [RationalNumber].
 *
 * It is negative if the number is negative.
 *
 * @return The integer part as an [IntegerNumber].
 */
public fun RationalNumber.integerPart(): IntegerNumber = numerator remQuotient denominator

/**
 * Returns the fractional part of this [RationalNumber].
 *
 * It is negative if the number is negative.
 *
 * @return The fractional part as a [RationalNumber].
 */
public fun RationalNumber.fractionalPart(): RationalNumber = this - integerPart()

/**
 * Converts this [RationalNumber] to a flat periodic format representation in the specified radix.
 *
 * @param radix The radix (base) for the representation. The default is 10. It must be from 2 to 36.
 * @return A [RationalFlatPeriodicFormatter] representing the number in periodic notation.
 */
public fun RationalNumber.toFlatPeriodicFormat(radix: Int = 10): RationalFlatPeriodicFormatter = toFlatPeriodicFormat(IntegerNumber(radix))

/**
 * Converts this [RationalNumber] to a flat periodic format representation in the specified radix.
 *
 * @param radix The radix (base) for the representation. The default is 10. It must be from 2 to 36.
 * @return A [RationalFlatPeriodicFormatter] representing the number in periodic notation.
 */
public fun RationalNumber.toFlatPeriodicFormat(radix: IntegerNumber = ten): RationalFlatPeriodicFormatter {
    if (this < zero) return (-this).toFlatPeriodicFormat(radix).copy(hasMinus = true)
    if (this is IntegerNumber) {
        return RationalFlatPeriodicFormatter(
            hasMinus = false,
            integerPart = this,
            fractionBeforePeriod = emptyList(),
            period = emptyList(),
            radix = radix,
        )
    }
    require(radix > one) { "Radix must be greater than 1" }

    val intStr = (numerator remQuotient denominator)

    var remainder = numerator % denominator
    val remainders = LinkedHashSet<IntegerNumber>()
    val digits = mutableListOf<IntegerNumber>()
    while (true) {
        val digit = (remainder * radix).remQuotient(denominator).absolute()

        if (!remainders.add(remainder)) {
            val periodStartIndex: Int = remainders.indexOf(remainder)
            val digitsBeforePeriod = digits.subList(0, periodStartIndex)
            val period = digits.subList(periodStartIndex, digits.size)
            return RationalFlatPeriodicFormatter(
                hasMinus = false,
                integerPart = intStr,
                fractionBeforePeriod = digitsBeforePeriod,
                period = period,
                radix = radix,
            )
        }

        digits.add(digit)

        remainder = (remainder * radix) % denominator

        if (remainder == zero) {
            return RationalFlatPeriodicFormatter(
                hasMinus = false,
                integerPart = intStr,
                fractionBeforePeriod = digits,
                period = emptyList(),
                radix = radix,
            )
        }
    }
}

/**
 * Formatter for representing rational numbers as an infinite sequence of digits.
 *
 * @property sign The sign of the number (1 for positive, 0 for 0, -1 for negative).
 * @property integerPart The digits of the integer part.
 * @property digits The sequence of fractional digits (potentially infinite).
 */
@ConsistentCopyVisibility
public data class RationalFlatInfiniteFormatter internal constructor(
    val sign: Int,
    val integerPart: List<IntegerNumber>,
    val digits: Sequence<IntegerNumber>,
)
