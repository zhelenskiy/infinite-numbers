package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.Segment
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.rational.integerPart
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * Returns the absolute value of this [RationalNumber].
 *
 * @return A new [RationalNumber] representing the absolute value of this number.
 */
context(cache: EvaluationCache)
public fun RationalNumber.absolute(): RationalNumber = if (this < zero) -this else this

/**
 * Returns the absolute value of this [RationalNumber].
 *
 * @return A new [RationalNumber] representing the absolute value of this number.
 */
public fun RationalNumber.absolute(): RationalNumber = if (this < zero) -this else this

/**
 * The sign of this [RationalNumber].
 *
 * @return `-1` for negative numbers, `0` for zero, and `1` for positive numbers.
 */
context(cache: EvaluationCache)
public val RationalNumber.sign: Int
    get() = numerator.sign1
private val RationalNumber.sign1: Int get() = sign

/**
 * The sign of this [RationalNumber].
 *
 * @return `-1` for negative numbers, `0` for zero, and `1` for positive numbers.
 */
public val RationalNumber.sign: Int
    get() = numerator.sign

/**
 * Computes the square of this [RationalNumber].
 *
 * @return A new [RationalNumber] representing the square of this number.
 */
context(cache: EvaluationCache)
public fun RationalNumber.square(): RationalNumber = square1()
private fun RationalNumber.square1(): RationalNumber = square()

/**
 * Computes the square of this [RationalNumber].
 *
 * @return A new [RationalNumber] representing the square of this number.
 */
public fun RationalNumber.square(): RationalNumber = this.timesUnsafe(this)


/**
 * Adds another [Byte] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: Byte): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Byte] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: Byte): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UByte] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: UByte): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UByte] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: UByte): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Short] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: Short): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Short] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: Short): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UShort] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: UShort): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UShort] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: UShort): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Int] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: Int): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Int] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: Int): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UInt] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: UInt): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [UInt] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: UInt): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Long] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: Long): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [Long] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: Long): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [ULong] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.plus(other: ULong): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [ULong] to this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be added to this [RationalNumber].
 * @return A new [RationalNumber] representing the sum of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.plus(other: ULong): RationalNumber = this + IntegerNumber(other)

/**
 * Adds another [RationalNumber] to this [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Byte].
 * @return A new [RationalNumber] representing the sum of this [Byte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Byte].
 * @return A new [RationalNumber] representing the sum of this [Byte] and the [other].
 */
public operator fun Byte.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UByte].
 * @return A new [RationalNumber] representing the sum of this [UByte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UByte].
 * @return A new [RationalNumber] representing the sum of this [UByte] and the [other].
 */
public operator fun UByte.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Short].
 * @return A new [RationalNumber] representing the sum of this [Short] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Short].
 * @return A new [RationalNumber] representing the sum of this [Short] and the [other].
 */
public operator fun Short.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UShort].
 * @return A new [RationalNumber] representing the sum of this [UShort] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UShort].
 * @return A new [RationalNumber] representing the sum of this [UShort] and the [other].
 */
public operator fun UShort.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Int].
 * @return A new [RationalNumber] representing the sum of this [Int] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Int].
 * @return A new [RationalNumber] representing the sum of this [Int] and the [other].
 */
public operator fun Int.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UInt].
 * @return A new [RationalNumber] representing the sum of this [UInt] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [UInt].
 * @return A new [RationalNumber] representing the sum of this [UInt] and the [other].
 */
public operator fun UInt.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Long].
 * @return A new [RationalNumber] representing the sum of this [Long] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [Long].
 * @return A new [RationalNumber] representing the sum of this [Long] and the [other].
 */
public operator fun Long.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [ULong].
 * @return A new [RationalNumber] representing the sum of this [ULong] and the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Adds another [RationalNumber] to this [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be added to this [ULong].
 * @return A new [RationalNumber] representing the sum of this [ULong] and the [other].
 */
public operator fun ULong.plus(other: RationalNumber): RationalNumber = IntegerNumber(this) + other

/**
 * Subtracts another [Byte] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: Byte): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Byte] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: Byte): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UByte] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: UByte): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UByte] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: UByte): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Short] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: Short): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Short] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: Short): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UShort] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: UShort): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UShort] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: UShort): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Int] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: Int): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Int] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: Int): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UInt] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: UInt): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UInt] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: UInt): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Long] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: Long): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Long] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: Long): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [ULong] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.minus(other: ULong): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [ULong] from this [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be subtracted from this [RationalNumber].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun RationalNumber.minus(other: ULong): RationalNumber = this - IntegerNumber(other)

/**
 * Subtracts another [RationalNumber] from this [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Byte].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Byte.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Byte].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun Byte.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UByte].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UByte.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UByte].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun UByte.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Short].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Short.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Short].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun Short.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UShort].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UShort.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UShort].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun UShort.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Int].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Int.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Int].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun Int.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UInt].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UInt.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [UInt].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun UInt.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Long].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Long.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [Long].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun Long.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [ULong].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun ULong.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Subtracts another [RationalNumber] from this [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be subtracted from this [ULong].
 * @return A new [RationalNumber] representing the result of the subtraction.
 */
public operator fun ULong.minus(other: RationalNumber): RationalNumber = IntegerNumber(this) - other

/**
 * Multiplies this [RationalNumber] by another [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: Byte): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: Byte): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: UByte): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: UByte): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: Short): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: Short): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: UShort): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: UShort): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: Int): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: Int): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: UInt): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: UInt): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: Long): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: Long): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.times(other: ULong): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [RationalNumber] by another [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] to be multiplied with this [RationalNumber].
 * @return A new [RationalNumber] representing the product of this [RationalNumber] and the [other].
 */
public operator fun RationalNumber.times(other: ULong): RationalNumber = this * IntegerNumber(other)

/**
 * Multiplies this [Byte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Byte].
 * @return A new [RationalNumber] representing the product of this [Byte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Byte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Byte].
 * @return A new [RationalNumber] representing the product of this [Byte] and the [other].
 */
public operator fun Byte.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UByte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UByte].
 * @return A new [RationalNumber] representing the product of this [UByte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UByte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UByte].
 * @return A new [RationalNumber] representing the product of this [UByte] and the [other].
 */
public operator fun UByte.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Short] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Short].
 * @return A new [RationalNumber] representing the product of this [Short] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Short] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Short].
 * @return A new [RationalNumber] representing the product of this [Short] and the [other].
 */
public operator fun Short.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UShort] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UShort].
 * @return A new [RationalNumber] representing the product of this [UShort] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UShort] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UShort].
 * @return A new [RationalNumber] representing the product of this [UShort] and the [other].
 */
public operator fun UShort.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Int] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Int].
 * @return A new [RationalNumber] representing the product of this [Int] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Int] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Int].
 * @return A new [RationalNumber] representing the product of this [Int] and the [other].
 */
public operator fun Int.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UInt] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UInt].
 * @return A new [RationalNumber] representing the product of this [UInt] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UInt] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [UInt].
 * @return A new [RationalNumber] representing the product of this [UInt] and the [other].
 */
public operator fun UInt.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Long] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Long].
 * @return A new [RationalNumber] representing the product of this [Long] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Long] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [Long].
 * @return A new [RationalNumber] representing the product of this [Long] and the [other].
 */
public operator fun Long.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [ULong] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [ULong].
 * @return A new [RationalNumber] representing the product of this [ULong] and the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Multiplies this [ULong] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] to be multiplied with this [ULong].
 * @return A new [RationalNumber] representing the product of this [ULong] and the [other].
 */
public operator fun ULong.times(other: RationalNumber): RationalNumber = IntegerNumber(this) * other

/**
 * Divides this [RationalNumber] by another [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: Byte): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Byte] and returns the result as a new [RationalNumber].
 *
 * @param other The [Byte] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: Byte): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: UByte): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UByte] and returns the result as a new [RationalNumber].
 *
 * @param other The [UByte] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: UByte): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: Short): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Short] and returns the result as a new [RationalNumber].
 *
 * @param other The [Short] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: Short): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: UShort): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UShort] and returns the result as a new [RationalNumber].
 *
 * @param other The [UShort] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: UShort): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: Int): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Int] and returns the result as a new [RationalNumber].
 *
 * @param other The [Int] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: Int): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: UInt): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [UInt] and returns the result as a new [RationalNumber].
 *
 * @param other The [UInt] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: UInt): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: Long): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [Long] and returns the result as a new [RationalNumber].
 *
 * @param other The [Long] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: Long): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun RationalNumber.div(other: ULong): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [RationalNumber] by another [ULong] and returns the result as a new [RationalNumber].
 *
 * @param other The [ULong] divisor.
 * @return A new [RationalNumber] representing the quotient of this [RationalNumber] divided by the [other].
 */
public operator fun RationalNumber.div(other: ULong): RationalNumber = this / IntegerNumber(other)

/**
 * Divides this [Byte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Byte] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Byte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Byte] divided by the [other].
 */
public operator fun Byte.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UByte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UByte] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UByte] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UByte] divided by the [other].
 */
public operator fun UByte.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Short] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Short] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Short] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Short] divided by the [other].
 */
public operator fun Short.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UShort] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UShort] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UShort] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UShort] divided by the [other].
 */
public operator fun UShort.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Int] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Int] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Int] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Int] divided by the [other].
 */
public operator fun Int.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UInt] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UInt] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [UInt] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [UInt] divided by the [other].
 */
public operator fun UInt.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Long] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Long] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [Long] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [Long] divided by the [other].
 */
public operator fun Long.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [ULong] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [ULong] divided by the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other

/**
 * Divides this [ULong] by another [RationalNumber] and returns the result as a new [RationalNumber].
 *
 * @param other The [RationalNumber] divisor.
 * @return A new [RationalNumber] representing the quotient of this [ULong] divided by the [other].
 */
public operator fun ULong.div(other: RationalNumber): RationalNumber = IntegerNumber(this) / other


/**
 * Processes the first [fractionDigitsCount] fractional digits of this [RationalNumber] in the specified [radix].
 *
 * This function computes the first [fractionDigitsCount] fractional digits of the number and provides them to the [proceed] callback
 * in the view of rational bounds, which represent in the specified [radix] two finite fractions with [fractionDigitsCount] common digits,
 * and the next digit after the requested count.
 *
 * @param R The return type of the [proceed] callback.
 * @param radix The numerical base to use for digit extraction (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to extract. Must be non-negative.
 * @param proceed A callback function that receives the bound segment and the next digit and returns a result.
 * @return The result returned by the [proceed] callback.
 * @throws IllegalArgumentException if [fractionDigitsCount] is negative or if an invalid digit is encountered.
 */
@OptIn(ExperimentalContracts::class)
public fun <R: Any> RationalNumber.proceedWithFirstDigits(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber,
    proceed: (bounds: Segment, nextDigit: IntegerNumber) -> R
): R {
    contract { callsInPlace(proceed, InvocationKind.EXACTLY_ONCE) }
    require(fractionDigitsCount >= zero) { "Digits after comma must be non-negative: $fractionDigitsCount" }

    var result: RationalNumber = integerPart()
    val fractionDigits = digitSequence(radix).digits
    val sign = sign

    var count = zero
    val digitsToMultipliers = (fractionDigits zip generateSequence(one / radix) { it / radix }).iterator()
    while (count < fractionDigitsCount && digitsToMultipliers.hasNext()) {
        val (digit, multiplier) = digitsToMultipliers.next()
        require(digit in zero..<radix) { "Invalid digit: $digit" }
        result += digit * multiplier * sign
        count++
    }
    return if (digitsToMultipliers.hasNext()) {
        val (extraDigit, _) = digitsToMultipliers.next()
        val nextResult = result + sign * radix.pow(-fractionDigitsCount)
        val bounds = listOf(result, nextResult)
            .sorted().map(::Inclusive).let { (first, second) -> first..second }
        proceed(bounds, extraDigit)
    } else {
        proceed(Inclusive(result)..Inclusive(result), zero)
    }
}

@OptIn(ExperimentalContracts::class)
private fun <R: Any> RationalNumber.proceedWithFirstDigits1(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber,
    proceed: (bounds: Segment, nextDigit: IntegerNumber) -> R
): R {
    contract { callsInPlace(proceed, InvocationKind.EXACTLY_ONCE) }
    return proceedWithFirstDigits(radix, fractionDigitsCount, proceed)
}

/**
 * Processes the first [fractionDigitsCount] fractional digits of this [RationalNumber] in the specified [radix].
 *
 * This function computes the first [fractionDigitsCount] fractional digits of the number and provides them to the [proceed] callback
 * in the view of rational bounds, which represent in the specified [radix] two finite fractions with [fractionDigitsCount] common digits,
 * and the next digit after the requested count.
 *
 * @param R The return type of the [proceed] callback.
 * @param radix The numerical base to use for digit extraction (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to extract. Must be non-negative.
 * @param proceed A callback function that receives the bound segment and the next digit and returns a result.
 * @return The result returned by the [proceed] callback.
 * @throws IllegalArgumentException if [fractionDigitsCount] is negative or if an invalid digit is encountered.
 */
@OptIn(ExperimentalContracts::class)
context(cache: EvaluationCache)
public fun <R: Any> RationalNumber.proceedWithFirstDigits(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber,
    proceed: (bounds: Segment, nextDigit: IntegerNumber) -> R
): R {
    contract { callsInPlace(proceed, InvocationKind.EXACTLY_ONCE) }
    return proceedWithFirstDigits1(radix, fractionDigitsCount, proceed)
}

/**
 * Rounds this [RationalNumber] to a specified number of fractional digits in the given radix.
 *
 * Uses standard rounding rules: rounds up if the next digit is >= radix/2, otherwise rounds down.
 * For negative numbers, the rounding direction is reversed.
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing this number rounded to the specified precision.
 */
public fun RationalNumber.roundToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = proceedWithFirstDigits(radix, fractionDigitsCount) { (lowerBound, upperBound), nextDigit ->
    if (sign >= 0) if (nextDigit * 2 >= radix) upperBound.value else lowerBound.value
    else if (nextDigit * 2 <= radix) upperBound.value else lowerBound.value
}

/**
 * Rounds this [RationalNumber] up to a specified number of fractional digits in the given radix.
 *
 * Always rounds toward positive infinity (ceiling function).
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing the ceiling of this number at the specified precision.
 */
public fun RationalNumber.ceilingToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = proceedWithFirstDigits(radix, fractionDigitsCount) { (_, upperBound), _ -> upperBound.value }

/**
 * Rounds this [RationalNumber] down to a specified number of fractional digits in the given radix.
 *
 * Always rounds toward negative infinity (floor function).
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing the floor of this number at the specified precision.
 */
public fun RationalNumber.floorToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = proceedWithFirstDigits(radix, fractionDigitsCount) { (lowerBound, _), _ -> lowerBound.value }

private fun RationalNumber.roundToRationalNumber1(
    radix: IntegerNumber, fractionDigitsCount: IntegerNumber
): RationalNumber = roundToRationalNumber(radix, fractionDigitsCount)

private fun RationalNumber.ceilingToRationalNumber1(
    radix: IntegerNumber, fractionDigitsCount: IntegerNumber
): RationalNumber = ceilingToRationalNumber(radix, fractionDigitsCount)

private fun RationalNumber.floorToRationalNumber1(
    radix: IntegerNumber, fractionDigitsCount: IntegerNumber
): RationalNumber = floorToRationalNumber(radix, fractionDigitsCount)

/**
 * Rounds this [RationalNumber] to a specified number of fractional digits in the given radix.
 *
 * Uses standard rounding rules: rounds up if the next digit is >= radix/2, otherwise rounds down.
 * For negative numbers, the rounding direction is reversed.
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing this number rounded to the specified precision.
 */
context(cache: EvaluationCache)
public fun RationalNumber.roundToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = roundToRationalNumber1(radix, fractionDigitsCount)

/**
 * Rounds this [RationalNumber] up to a specified number of fractional digits in the given radix.
 *
 * Always rounds toward positive infinity (ceiling function).
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing the ceiling of this number at the specified precision.
 */
context(cache: EvaluationCache)
public fun RationalNumber.ceilingToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = ceilingToRationalNumber1(radix, fractionDigitsCount)

/**
 * Rounds this [RationalNumber] down to a specified number of fractional digits in the given radix.
 *
 * Always rounds toward negative infinity (floor function).
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A new [RationalNumber] representing the floor of this number at the specified precision.
 */
context(cache: EvaluationCache)
public fun RationalNumber.floorToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = floorToRationalNumber1(radix, fractionDigitsCount)

/**
 * Processes the digits of this [RealNumber] by applying the [proceed] function to rational approximations.
 *
 * This function iteratively refines the bounds of the real number and applies the [proceed] function to
 * both bounds. When the [combine] function can produce a consistent result from both bounds, that result
 * is returned. This is useful for computing properties that converge as the bounds tighten.
 *
 * @param R The return type of the [proceed] callback.
 * @param combine A function that attempts to combine results from lower and upper bounds. Returns a value
 *        if the results are consistent (e.g., equal), or null to continue refining.
 * @param proceed A callback function applied to rational approximations of this real number.
 * @return The combined result when bounds produce consistent values.
 * @throws IllegalArgumentException if the digit sequence does not converge to a consistent result.
 */
@OptIn(ExperimentalContracts::class)
@PotentiallyInfiniteRealNumberApi
context(cache: EvaluationCache)
public fun <R: Any> RealNumber.proceedWithFirstDigits(
    combine: (R, R) -> R? = { first, second -> if (first == second) first else null },
    proceed: (RationalNumber) -> R,
): R {
    contract { callsInPlace(proceed, InvocationKind.AT_LEAST_ONCE) }
    for ((lowerBound, upperBound) in cache.observe(this)) {
        combine(proceed(lowerBound.value), proceed(upperBound.value))?.let { return it }
    }
    throw IllegalArgumentException("The digit sequence did not converge")
}

/**
 * Rounds this [RealNumber] to a [RationalNumber] with a specified number of fractional digits.
 *
 * Uses standard rounding rules applied to the rational approximations of this real number.
 *
 * The function is marked as potentially infinite because if the limit of the number is the changing point (radix / 5),
 * it might be impossible to determine the exact digit.
 * For instance, 0.5 + lim_{n -> infinity}((-1)^n / 5^n) is converging to 0.5 but from both sides of the limit.
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A [RationalNumber] representing this real number rounded to the specified precision.
 */
@PotentiallyInfiniteRealNumberApi
context(cache: EvaluationCache)
public fun RealNumber.roundToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber,
): RationalNumber = proceedWithFirstDigits { it.roundToRationalNumber(radix, fractionDigitsCount) }

/**
 * Rounds this [RealNumber] up to a [RationalNumber] with a specified number of fractional digits.
 *
 * Always rounds toward positive infinity (ceiling function).
 *
 * The function is marked as potentially infinite because if the limit of the number is the changing point (0),
 * it might be impossible to determine the sign or the next digit.
 * For instance, lim_{n -> infinity}((-1)^n / 5^n) is converging to 0 but from both sides of 0.
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A [RationalNumber] representing the ceiling of this real number at the specified precision.
 */
@PotentiallyInfiniteRealNumberApi
context(cache: EvaluationCache)
public fun RealNumber.ceilingToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = proceedWithFirstDigits { it.ceilingToRationalNumber(radix, fractionDigitsCount) }

/**
 * Rounds this [RealNumber] down to a [RationalNumber] with a specified number of fractional digits.
 *
 * Always rounds toward negative infinity (floor function).
 *
 * The function is marked as potentially infinite because if the limit of the number is the changing point (0),
 * it might be impossible to determine the sign or the next digit.
 * For instance, lim_{n -> infinity}((-1)^n / 5^n) is converging to 0 but from both sides of 0.
 *
 * @param radix The numerical base to use for rounding (default is 10 for decimal). Must be greater than 1.
 * @param fractionDigitsCount The number of fractional digits to round to.
 * @return A [RationalNumber] representing the floor of this real number at the specified precision.
 */
@PotentiallyInfiniteRealNumberApi
context(cache: EvaluationCache)
public fun RealNumber.floorToRationalNumber(
    radix: IntegerNumber = ten, fractionDigitsCount: IntegerNumber
): RationalNumber = proceedWithFirstDigits { it.floorToRationalNumber(radix, fractionDigitsCount) }
