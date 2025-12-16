package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.AnyBounds
import com.zhelenskiy.infinite.numbers.Border
import com.zhelenskiy.infinite.numbers.Border.Infinity
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import kotlin.math.sign
import kotlin.require

/**
 * Adds another [Byte] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Byte] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: Byte): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [UByte] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UByte] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: UByte): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [Short] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Short] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: Short): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [UShort] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UShort] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: UShort): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [Int] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Int] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: Int): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [UInt] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UInt] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: UInt): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [Long] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Long] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: Long): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [ULong] to this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [ULong] to be added to this [RealNumber].
 * @return A new [RealNumber] representing the sum of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.plus(other: ULong): RealNumber = this + IntegerNumber(other)

/**
 * Adds another [RealNumber] to this [Byte] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [Byte].
 * @return A new [RealNumber] representing the sum of this [Byte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [UByte] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [UByte].
 * @return A new [RealNumber] representing the sum of this [UByte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [Short] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [Short].
 * @return A new [RealNumber] representing the sum of this [Short] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [UShort] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [UShort].
 * @return A new [RealNumber] representing the sum of this [UShort] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [Int] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [Int].
 * @return A new [RealNumber] representing the sum of this [Int] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [UInt] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [UInt].
 * @return A new [RealNumber] representing the sum of this [UInt] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [Long] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [Long].
 * @return A new [RealNumber] representing the sum of this [Long] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Adds another [RealNumber] to this [ULong] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be added to this [ULong].
 * @return A new [RealNumber] representing the sum of this [ULong] and the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.plus(other: RealNumber): RealNumber = IntegerNumber(this) + other

/**
 * Subtracts another [Byte] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Byte] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: Byte): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UByte] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UByte] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: UByte): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Short] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Short] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: Short): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UShort] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UShort] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: UShort): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Int] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Int] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: Int): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UInt] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [UInt] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: UInt): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Long] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [Long] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: Long): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [ULong] from this [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [ULong] to be subtracted from this [RealNumber].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RealNumber.minus(other: ULong): RealNumber = this - IntegerNumber(other)

/**
 * Subtracts another [RealNumber] from this [Byte] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [Byte].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Byte.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [UByte] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [UByte].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UByte.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [Short] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [Short].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Short.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [UShort] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [UShort].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UShort.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [Int] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [Int].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Int.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [UInt] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [UInt].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UInt.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [Long] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [Long].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Long.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RealNumber] from this [ULong] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be subtracted from this [ULong].
 * @return A new [RealNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun ULong.minus(other: RealNumber): RealNumber = IntegerNumber(this) - other

/**
 * Multiplies this [RealNumber] by another [Byte] and returns the result as a new [RealNumber].
 *
 * @param other The [Byte] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: Byte): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [UByte] and returns the result as a new [RealNumber].
 *
 * @param other The [UByte] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: UByte): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [Short] and returns the result as a new [RealNumber].
 *
 * @param other The [Short] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: Short): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [UShort] and returns the result as a new [RealNumber].
 *
 * @param other The [UShort] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: UShort): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [Int] and returns the result as a new [RealNumber].
 *
 * @param other The [Int] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: Int): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [UInt] and returns the result as a new [RealNumber].
 *
 * @param other The [UInt] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: UInt): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [Long] and returns the result as a new [RealNumber].
 *
 * @param other The [Long] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: Long): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RealNumber] by another [ULong] and returns the result as a new [RealNumber].
 *
 * @param other The [ULong] to be multiplied with this [RealNumber].
 * @return A new [RealNumber] representing the product of this [RealNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.times(other: ULong): RealNumber = this * IntegerNumber(other)

/**
 * Multiplies this [Byte] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [Byte].
 * @return A new [RealNumber] representing the product of this [Byte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UByte] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [UByte].
 * @return A new [RealNumber] representing the product of this [UByte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Short] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [Short].
 * @return A new [RealNumber] representing the product of this [Short] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UShort] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [UShort].
 * @return A new [RealNumber] representing the product of this [UShort] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Int] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [Int].
 * @return A new [RealNumber] representing the product of this [Int] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UInt] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [UInt].
 * @return A new [RealNumber] representing the product of this [UInt] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Long] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [Long].
 * @return A new [RealNumber] representing the product of this [Long] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Multiplies this [ULong] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] to be multiplied with this [ULong].
 * @return A new [RealNumber] representing the product of this [ULong] and the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.times(other: RealNumber): RealNumber = IntegerNumber(this) * other

/**
 * Divides this [RealNumber] by another [Byte] and returns the result as a new [RealNumber].
 *
 * @param other The [Byte] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: Byte): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [UByte] and returns the result as a new [RealNumber].
 *
 * @param other The [UByte] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: UByte): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [Short] and returns the result as a new [RealNumber].
 *
 * @param other The [Short] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: Short): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [UShort] and returns the result as a new [RealNumber].
 *
 * @param other The [UShort] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: UShort): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [Int] and returns the result as a new [RealNumber].
 *
 * @param other The [Int] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: Int): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [UInt] and returns the result as a new [RealNumber].
 *
 * @param other The [UInt] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: UInt): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [Long] and returns the result as a new [RealNumber].
 *
 * @param other The [Long] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: Long): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [RealNumber] by another [ULong] and returns the result as a new [RealNumber].
 *
 * @param other The [ULong] divisor.
 * @return A new [RealNumber] representing the quotient of this [RealNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RealNumber.div(other: ULong): RealNumber = this / IntegerNumber(other)

/**
 * Divides this [Byte] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [Byte] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [UByte] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [UByte] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [Short] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [Short] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [UShort] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [UShort] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [Int] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [Int] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [UInt] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [UInt] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [Long] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [Long] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * Divides this [ULong] by another [RealNumber] and returns the result as a new [RealNumber].
 *
 * @param other The [RealNumber] divisor.
 * @return A new [RealNumber] representing the quotient of this [ULong] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.div(other: RealNumber): RealNumber = IntegerNumber(this) / other

/**
 * The sign of this [RealNumber].
 *
 * @return `-1` for negative numbers, `0` for zero, and `1` for positive numbers.
 */
context(comp: Comparator<RealNumber>)
public val RealNumber.sign: Int
    get() = comp.compare(this, zero).sign

/**
 * Returns the absolute value of this [RealNumber].
 *
 * @return A new [RealNumber] representing the absolute value of this number.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
public fun RealNumber.absolute(): RealNumber = when (sign) {
    1 -> this
    -1 -> -this
    else -> zero
}

/**
 * Computes the [exponent]-th root of this [RationalNumber].
 *
 * @param exponent The root exponent. Must be positive.
 * @return A [RealNumber] representing the root of this number.
 * @throws IllegalArgumentException if [exponent] is not positive or if the root of a negative number with even exponent is attempted.
 */
context(cache: EvaluationCache)
fun RationalNumber.root(exponent: IntegerNumber): RealNumber = root(
    exponent = exponent,
    sign = { sign },
    search = { expected, bounds, f -> binarySearch(expected, bounds, f) },
    power = { pow(it) }
)

/**
 * Raises this [RationalNumber] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: RationalNumber): RealNumber = pow(exponent.numerator).root(exponent.denominator)

/**
 * Computes the square of this [RealNumber].
 *
 * @return A new [RealNumber] representing the square of this number.
 */
context(cache: EvaluationCache)
fun RealNumber.square(): RealNumber = this * this

/**
 * Computes the [exponent]-th root of this [RealNumber].
 *
 * @param exponent The root exponent. Must be positive.
 * @return A [RealNumber] representing the root of this number.
 * @throws IllegalArgumentException if [exponent] is not positive or if the root of a negative number with even exponent is attempted.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
fun RealNumber.root(exponent: IntegerNumber): RealNumber = root(
    exponent = exponent,
    sign = { sign },
    search = { expected, bounds, f -> reverseValue(expected, bounds, f) },
    power = { pow(it) }
)

private fun <T: RealNumber> T.root(
    exponent: IntegerNumber, sign: T.() -> Int,
    search: (expected: T, bounds: AnyBounds, f: (T) -> T) -> RealNumber?, power: T.(IntegerNumber) -> T
): RealNumber {
    require(exponent > zero) { "Power must be positive" }
    if (exponent == one) return this
    val isPowerEven = exponent % 2 == zero
    val sign = sign()
    if (isPowerEven) require(sign >= 0) { "Root of negative number is not defined" }
    val bounds = (if (isPowerEven) Border.Inclusive(zero) else -Infinity)..+Infinity
    return search(this, bounds) { it.power(exponent) } ?: error("Root not found")
}

/**
 * Raises this [RealNumber] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
fun RealNumber.pow(exponent: RationalNumber): RealNumber = pow(exponent.numerator).root(exponent.denominator)

/**
 * Raises this [RealNumber] to a real power [exponent].
 *
 * @param exponent The real exponent. Must be non-negative.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 * @throws IllegalArgumentException if [exponent] is negative or if this number is negative.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
fun RealNumber.pow(exponent: RealNumber): RealNumber {
    if (exponent is RationalNumber) return pow(exponent)
    require(comp.compare(exponent, zero) >= 0) { "Power must be non-negative" }
    require(comp.compare(this, zero) >= 0) { "Base must be non-negative"}
    return limitMonotonic(this, exponent) { a, b -> a.pow(b) }
}

/**
 * Computes the natural logarithm (base e) of a [RealNumber].
 *
 * @param x The real number. Must be positive.
 * @return A [RealNumber] representing the natural logarithm of [x].
 * @throws IllegalArgumentException if [x] is not positive.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
public fun ln(x: RealNumber): RealNumber = log(x, e)

/**
 * Computes the logarithm of [x] with the specified [base].
 *
 * @param x The real number. Must be positive.
 * @param base The logarithm base.
 * @return A [RealNumber] representing the logarithm of [x] in the given [base].
 * @throws IllegalArgumentException if [x] is not positive.
 */
context(comp: Comparator<RealNumber>, cache: EvaluationCache)
public fun log(x: RealNumber, base: RealNumber): RealNumber {
    require(comp.compare(x, zero) > 0) { "Logarithm argument must be positive" }
    return search(x) { base.pow(it) }!!
}

/**
 * Raises this [Byte] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun Byte.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UByte] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun UByte.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Short] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun Short.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UShort] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun UShort.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Int] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun Int.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UInt] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun UInt.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Long] to a real power [exponent].
 *
 * @param exponent The real exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun Long.pow(exponent: RealNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Byte] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Byte.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UByte] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UByte.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Short] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Short.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UShort] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UShort.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Int] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Int.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UInt] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UInt.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Long] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Long.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [ULong] to a rational power [exponent].
 *
 * @param exponent The rational exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun ULong.pow(exponent: RationalNumber): RealNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Byte] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Byte.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [Byte] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun Byte.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UByte] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UByte.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [UByte] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun UByte.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Short] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Short.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [Short] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun Short.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UShort] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UShort.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [UShort] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun UShort.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Int] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Int.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [Int] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun Int.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [UInt] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun UInt.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [UInt] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun UInt.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [Long] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun Long.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [Long] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun Long.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [ULong] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun ULong.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)
/**
 * Raises this [ULong] to an integer power [exponent].
 *
 * @param exponent The integer exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun ULong.pow(exponent: IntegerNumber): RationalNumber = IntegerNumber(this).pow(exponent)

/**
 * Raises this [RealNumber] to a [Byte] power [exponent].
 *
 * @param exponent The byte exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: Byte): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [UByte] power [exponent].
 *
 * @param exponent The unsigned byte exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: UByte): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [Short] power [exponent].
 *
 * @param exponent The short exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: Short): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [UShort] power [exponent].
 *
 * @param exponent The unsigned short exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: UShort): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to an [Int] power [exponent].
 *
 * @param exponent The int exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: Int): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [UInt] power [exponent].
 *
 * @param exponent The unsigned int exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: UInt): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [Long] power [exponent].
 *
 * @param exponent The long exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: Long): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RealNumber] to a [ULong] power [exponent].
 *
 * @param exponent The unsigned long exponent.
 * @return A [RealNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RealNumber.pow(exponent: ULong): RealNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Byte] power [exponent].
 *
 * @param exponent The byte exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: Byte): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UByte] power [exponent].
 *
 * @param exponent The unsigned byte exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: UByte): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Short] power [exponent].
 *
 * @param exponent The short exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: Short): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UShort] power [exponent].
 *
 * @param exponent The unsigned short exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: UShort): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to an [Int] power [exponent].
 *
 * @param exponent The int exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: Int): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UInt] power [exponent].
 *
 * @param exponent The unsigned int exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: UInt): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Long] power [exponent].
 *
 * @param exponent The long exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: Long): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [ULong] power [exponent].
 *
 * @param exponent The unsigned long exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
context(cache: EvaluationCache)
fun RationalNumber.pow(exponent: ULong): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Byte] power [exponent].
 *
 * @param exponent The byte exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: Byte): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UByte] power [exponent].
 *
 * @param exponent The unsigned byte exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: UByte): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Short] power [exponent].
 *
 * @param exponent The short exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: Short): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UShort] power [exponent].
 *
 * @param exponent The unsigned short exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: UShort): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to an [Int] power [exponent].
 *
 * @param exponent The int exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: Int): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [UInt] power [exponent].
 *
 * @param exponent The unsigned int exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: UInt): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [Long] power [exponent].
 *
 * @param exponent The long exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: Long): RationalNumber = pow(IntegerNumber(exponent))

/**
 * Raises this [RationalNumber] to a [ULong] power [exponent].
 *
 * @param exponent The unsigned long exponent.
 * @return A [RationalNumber] representing this number raised to the [exponent] power.
 */
fun RationalNumber.pow(exponent: ULong): RationalNumber = pow(IntegerNumber(exponent))
