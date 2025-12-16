package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rational.IntStorage
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.text.toIntOrNull
import kotlin.text.toLongOrNull


/**
 * Returns the string representation of this `IntegerNumber` in the specified radix.
 *
 * @param radix The base to use for the string representation. The radix must be between
 * 2 and 36 inclusive, where 10 represents the decimal system, 16 represents hexadecimal, etc.
 * @return The string representation of this number in the given radix.
 */
public fun IntegerNumber.toString(radix: Int): String = storage.toString(radix)

/**
 * Converts this [IntegerNumber] to an [Int] if it can be represented as a valid [Int].
 * Returns `null` if the number is outside the range of [Int] values or if the conversion fails.
 *
 * @return The [Int] value of this [IntegerNumber] if it fits within the [Int] range, or `null` otherwise.
 */
public fun IntegerNumber.toIntOrNull(): Int? = storage.toString().toIntOrNull()

/**
 * Converts this [IntegerNumber] to an [Int] or throws a [NumberFormatException] if the
 * conversion is not possible.
 *
 * @return The [Int] representation of this [IntegerNumber] if the conversion is successful.
 * @throws NumberFormatException If the conversion fails or the value is not within [Int] range.
 */
public fun IntegerNumber.toIntOrThrow(): Int = toIntOrNull() ?: throw NumberFormatException("Not an Int: $this")

/**
 * Converts this [IntegerNumber] to a [Long] if it can be represented as a valid [Long].
 * Returns `null` if the number is outside the range of [Long] values or if the conversion fails.
 *
 * @return The [Long] value of this [IntegerNumber] if it fits within the [Long] range, or `null` otherwise.
 */
public fun IntegerNumber.toLongOrNull(): Long? = storage.toString().toLongOrNull()

/**
 * Converts this [IntegerNumber] to a [Long] or throws a [NumberFormatException] if the
 * conversion is not possible.
 *
 * @return The [Long] representation of this [IntegerNumber] if the conversion is successful.
 * @throws NumberFormatException If the conversion fails or the value is not within [Long] range.
 */
public fun IntegerNumber.toLongOrThrow(): Long = toLongOrNull() ?: throw NumberFormatException("Not a Long: $this")

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: IntegerNumber): IntegerNumber =
    IntegerNumber(storage.abs() % other.storage.abs()) * sign

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: IntegerNumber): IntegerNumber {
    val rem = this % other.absolute()
    return rem + (if (rem != zero && this.sign * other.sign < 0) other else zero)
}

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(storage / other.storage)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: IntegerNumber): IntegerNumber =
    (this - (this mod other)) remQuotient other

/**
 * Parses a string representation of an integer in a specified base and returns an [IntegerNumber] object.
 *
 * @param string The string representation of the integer to parse.
 * @param base The base of the number system used for parsing. It must be from 2 to 36. The default is 10.
 * @return An [IntegerNumber] object representing the parsed integer.
 */
@JvmOverloads
public fun IntegerNumber.Companion.parseString(string: String, base: Int = 10): IntegerNumber =
    IntegerNumber(IntStorage.parseString(string, base))


/**
 * Creates a new [IntegerNumber] instance from a [Long] value.
 *
 * @param value The [Long] value to be converted into an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [Long] value.
 */
@JvmName("fromLong")
public operator fun IntegerNumber.Companion.invoke(value: Long): IntegerNumber =
    IntegerNumber(IntStorage.fromLong(value))

/**
 * Creates an [IntegerNumber] instance from a given [ULong] value.
 *
 * @param value The [ULong] value to be converted into an [IntegerNumber].
 * @return A new [IntegerNumber] instance representing the given [ULong].
 */
@JvmName("fromULong")
public operator fun IntegerNumber.Companion.invoke(value: ULong): IntegerNumber =
    IntegerNumber(IntStorage.fromULong(value))

/**
 * Creates a new [IntegerNumber] instance from a given [Int] value.
 *
 * @param value The [Int] value to be converted into an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [Int].
 */
@JvmName("fromInt")
public operator fun IntegerNumber.Companion.invoke(value: Int): IntegerNumber =
    IntegerNumber(IntStorage.fromInt(value))

/**
 * Creates an [IntegerNumber] from the given [UInt] value.
 *
 * @param value The [UInt] value to be converted to an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [UInt] value.
 */
@JvmName("fromUInt")
public operator fun IntegerNumber.Companion.invoke(value: UInt): IntegerNumber =
    IntegerNumber(IntStorage.fromUInt(value))

/**
 * Creates an [IntegerNumber] from the given [Short] value.
 *
 * @param value The [Short] value to be converted to an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [Short] value.
 */
@JvmName("fromShort")
public operator fun IntegerNumber.Companion.invoke(value: Short): IntegerNumber =
    IntegerNumber(IntStorage.fromShort(value))

/**
 * Creates an [IntegerNumber] from the given [UShort] value.
 *
 * @param value The [UShort] value to be converted to an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [Uhort] value.
 */
@JvmName("fromUShort")
public operator fun IntegerNumber.Companion.invoke(value: UShort): IntegerNumber =
    IntegerNumber(IntStorage.fromUShort(value))

/**
 * Creates an [IntegerNumber] from the given [Byte] value.
 *
 * @param value The [Byte] value to be converted to an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [Byte] value.
 */
@JvmName("fromByte")
public operator fun IntegerNumber.Companion.invoke(value: Byte): IntegerNumber =
    IntegerNumber(IntStorage.fromByte(value))

/**
 * Creates an [IntegerNumber] from the given [UByte] value.
 *
 * @param value The [UByte] value to be converted to an [IntegerNumber].
 * @return A new [IntegerNumber] representing the given [UByte] value.
 */
@JvmName("fromUByte")
public operator fun IntegerNumber.Companion.invoke(value: UByte): IntegerNumber =
    IntegerNumber(IntStorage.fromUByte(value))


/**
 * Adds another [Byte] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: Byte): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Byte] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: Byte): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UByte] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: UByte): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UByte] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: UByte): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Short] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: Short): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Short] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: Short): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UShort] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: UShort): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UShort] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: UShort): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Int] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: Int): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Int] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: Int): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UInt] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: UInt): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [UInt] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: UInt): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Long] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: Long): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [Long] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: Long): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [ULong] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.plus(other: ULong): IntegerNumber = this + IntegerNumber(other)

/**
 * Adds another [ULong] to this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be added to this [IntegerNumber].
 * @return A new [IntegerNumber] representing the sum of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.plus(other: ULong): IntegerNumber = this + IntegerNumber(other)


/**
 * Adds another [IntegerNumber] to this [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Byte].
 * @return A new [IntegerNumber] representing the sum of this [Byte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Byte].
 * @return A new [IntegerNumber] representing the sum of this [Byte] and the [other].
 */
public operator fun Byte.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [UByte].
 * @return A new [IntegerNumber] representing the sum of this [UByte] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [UByte].
 * @return A new [IntegerNumber] representing the sum of this [UByte] and the [other].
 */
public operator fun UByte.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Short].
 * @return A new [IntegerNumber] representing the sum of this [Short] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Short].
 * @return A new [IntegerNumber] representing the sum of this [Short] and the [other].
 */
public operator fun Short.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [UShort].
 * @return A new [IntegerNumber] representing the sum of this [UShort] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [UShort].
 * @return A new [IntegerNumber] representing the sum of this [UShort] and the [other].
 */
public operator fun UShort.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Int].
 * @return A new [IntegerNumber] representing the sum of this [Int] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Int].
 * @return A new [IntegerNumber] representing the sum of this [Int] and the [other].
 */
public operator fun Int.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Long].
 * @return A new [IntegerNumber] representing the sum of this [Long] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other

/**
 * Adds another [IntegerNumber] to this [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be added to this [Long].
 * @return A new [IntegerNumber] representing the sum of this [Long] and the [other].
 */
public operator fun Long.plus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) + other


/**
 * Subtracts another [Byte] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: Byte): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Byte] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: Byte): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UByte] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: UByte): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UByte] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: UByte): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Short] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: Short): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Short] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: Short): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UShort] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: UShort): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UShort] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: UShort): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Int] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: Int): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Int] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: Int): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UInt] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: UInt): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [UInt] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: UInt): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Long] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: Long): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [Long] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: Long): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [ULong] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.minus(other: ULong): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [ULong] from this [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be subtracted from this [IntegerNumber].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun IntegerNumber.minus(other: ULong): IntegerNumber = this - IntegerNumber(other)

/**
 * Subtracts another [IntegerNumber] from this [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Byte].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Byte.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Byte].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun Byte.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UByte].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UByte.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UByte].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun UByte.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Short].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Short.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Short].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun Short.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UShort].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UShort.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UShort].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun UShort.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Int].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Int.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Int].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun Int.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UInt] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UInt].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun UInt.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [UInt] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [UInt].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun UInt.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Long].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun Long.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [Long].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun Long.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [ULong] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [ULong].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
context(cache: EvaluationCache)
public operator fun ULong.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other

/**
 * Subtracts another [IntegerNumber] from this [ULong] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be subtracted from this [ULong].
 * @return A new [IntegerNumber] representing the result of the subtraction.
 */
public operator fun ULong.minus(other: IntegerNumber): IntegerNumber = IntegerNumber(this) - other


/**
 * Multiplies this [IntegerNumber] by another [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: Byte): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Byte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Byte] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: Byte): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: UByte): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UByte] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UByte] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: UByte): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: Short): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Short] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Short] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: Short): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: UShort): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UShort] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UShort] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: UShort): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: Int): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Int] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Int] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: Int): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UInt] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: UInt): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [UInt] and returns the result as a new [IntegerNumber].
 *
 * @param other The [UInt] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: UInt): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: Long): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [Long] and returns the result as a new [IntegerNumber].
 *
 * @param other The [Long] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: Long): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [ULong] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun IntegerNumber.times(other: ULong): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [IntegerNumber] by another [ULong] and returns the result as a new [IntegerNumber].
 *
 * @param other The [ULong] to be multiplied with this [IntegerNumber].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun IntegerNumber.times(other: ULong): IntegerNumber = this * IntegerNumber(other)

/**
 * Multiplies this [Byte] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Byte].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Byte.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Byte] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Byte].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun Byte.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UByte] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UByte].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UByte.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UByte] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UByte].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun UByte.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Short] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Short].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Short.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Short] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Short].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun Short.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UShort] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UShort].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UShort.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UShort] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UShort].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun UShort.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Int] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Int].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Int.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Int] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Int].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun Int.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UInt] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UInt].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun UInt.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [UInt] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [UInt].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun UInt.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Long] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Long].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun Long.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [Long] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [Long].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun Long.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [ULong] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [ULong].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
context(cache: EvaluationCache)
public operator fun ULong.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Multiplies this [ULong] by another [IntegerNumber] and returns the result as a new [IntegerNumber].
 *
 * @param other The [IntegerNumber] to be multiplied with this [ULong].
 * @return A new [IntegerNumber] representing the product of this [IntegerNumber] and the [other].
 */
public operator fun ULong.times(other: IntegerNumber): IntegerNumber = IntegerNumber(this) * other

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [Byte].
 *
 * @param other the divisor as a [Byte].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: Byte): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [UByte].
 *
 * @param other the divisor as a [UByte].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: UByte): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [Short].
 *
 * @param other the divisor as a [Short].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: Short): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [UShort].
 *
 * @param other the divisor as a [UShort].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: UShort): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [Int].
 *
 * @param other the divisor as an [Int].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: Int): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [UInt].
 *
 * @param other the divisor as an [UInt].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: UInt): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [Long].
 *
 * @param other the divisor as a [Long].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: Long): IntegerNumber = this % IntegerNumber(other)

/**
 * Computes the remainder of dividing this [IntegerNumber] by the given `other` [ULong].
 *
 * @param other the divisor as a [ULong].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun IntegerNumber.rem(other: ULong): IntegerNumber = this % IntegerNumber(other)


/**
 * Computes the remainder of dividing this [Byte] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun Byte.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [UByte] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun UByte.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [Short] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun Short.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [UShort] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun UShort.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [Int] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun Int.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [UInt] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun UInt.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [Long] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun Long.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other

/**
 * Computes the remainder of dividing this [ULong] by the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the remainder of the division as an [IntegerNumber].
 */
public operator fun ULong.rem(other: IntegerNumber): IntegerNumber = IntegerNumber(this) % other


/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [Byte].
 *
 * @param other the divisor as a [Byte].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: Byte): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [UByte].
 *
 * @param other the divisor as a [UByte].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: UByte): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [Short].
 *
 * @param other the divisor as a [Short].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: Short): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [UShort].
 *
 * @param other the divisor as a [UShort].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: UShort): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [Int].
 *
 * @param other the divisor as an [Int].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: Int): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [UInt].
 *
 * @param other the divisor as a [UInt].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: UInt): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [Long].
 *
 * @param other the divisor as a [Long].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: Long): IntegerNumber = this mod IntegerNumber(other)

/**
 * Computes the value of this [IntegerNumber] modulo the given `other` [ULong].
 *
 * @param other the divisor as a [ULong].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun IntegerNumber.mod(other: ULong): IntegerNumber = this mod IntegerNumber(other)


/**
 * Computes the value of this [Byte] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun Byte.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [UByte] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun UByte.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [Short] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun Short.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [UShort] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun UShort.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [Int] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun Int.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [UInt] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun UInt.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [Long] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun Long.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other

/**
 * Computes the value of this [ULong] modulo the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the non-negative result of the modulo operation as an [IntegerNumber].
 */
public infix fun ULong.mod(other: IntegerNumber): IntegerNumber = IntegerNumber(this) mod other


/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [Byte].
 *
 * @param other the divisor [Byte]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: Byte): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [UByte].
 *
 * @param other the divisor [UByte]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: UByte): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [Short].
 *
 * @param other the divisor [Short]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: Short): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [UShort].
 *
 * @param other the divisor [UShort]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: UShort): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [Int].
 *
 * @param other the divisor [Int]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: Int): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [UInt].
 *
 * @param other the divisor [UInt]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: UInt): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [Long].
 *
 * @param other the divisor [Long]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: Long): IntegerNumber = this remQuotient IntegerNumber(other)

/**
 * Computes the quotient when performing division on `this` [IntegerNumber] by the given `other` [ULong].
 *
 * @param other the divisor [ULong]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun IntegerNumber.remQuotient(other: ULong): IntegerNumber = this remQuotient IntegerNumber(other)


/**
 * Computes the quotient when performing division on `this` [Byte] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun Byte.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [UByte] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun UByte.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [Short] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun Short.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [UShort] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun UShort.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [Int] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun Int.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [UInt] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun UInt.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [Long] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun Long.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other

/**
 * Computes the quotient when performing division on `this` [ULong] by the given `other` [IntegerNumber].
 *
 * @param other the divisor [IntegerNumber]
 * @return the quotient as an [IntegerNumber] resulting from dividing `this` by `other`.
 */
public infix fun ULong.remQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) remQuotient other


/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [Byte].
 *
 * @param other the divisor as a [Byte].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: Byte): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [UByte].
 *
 * @param other the divisor as a [UByte].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: UByte): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [Short].
 *
 * @param other the divisor as a [Short].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: Short): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [UShort].
 *
 * @param other the divisor as a [UShort].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: UShort): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [Int].
 *
 * @param other the divisor as an [Int].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: Int): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [UInt].
 *
 * @param other the divisor as a [UInt].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: UInt): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [Long].
 *
 * @param other the divisor as a [Long].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: Long): IntegerNumber = this modQuotient IntegerNumber(other)

/**
 * Computes the quotient of the modulus operation for `this` [IntegerNumber] and the given `other` [ULong].
 *
 * @param other the divisor as a [ULong].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun IntegerNumber.modQuotient(other: ULong): IntegerNumber = this modQuotient IntegerNumber(other)


/**
 * Computes the quotient of the modulus operation for `this` [Byte] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun Byte.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [UByte] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun UByte.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [Short] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun Short.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [UShort] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun UShort.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [Int] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun Int.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [UInt] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun UInt.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [Long] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun Long.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * Computes the quotient of the modulus operation for `this` [ULong] and the given `other` [IntegerNumber].
 *
 * @param other the divisor as an [IntegerNumber].
 * @return the quotient as an [IntegerNumber] after performing the modulus operation.
 */
public infix fun ULong.modQuotient(other: IntegerNumber): IntegerNumber = IntegerNumber(this) modQuotient other

/**
 * The sign of this [IntegerNumber].
 *
 * @return `-1` for negative numbers, `0` for zero, and `1` for positive numbers.
 */
public val IntegerNumber.sign: Int
    get() = storage.signum()

/**
 * Computes the square of this [IntegerNumber].
 *
 * @return A new [IntegerNumber] representing the square of this number.
 */
context(cache: EvaluationCache)
public fun IntegerNumber.square(): IntegerNumber = this * this

/**
 * Computes the square of this [IntegerNumber].
 *
 * @return A new [IntegerNumber] representing the square of this number.
 */
public fun IntegerNumber.square(): IntegerNumber = this * this

/**
 * Returns the absolute value of this [IntegerNumber].
 *
 * @return A new [IntegerNumber] representing the absolute value of this number.
 */
public fun IntegerNumber.absolute(): IntegerNumber = IntegerNumber(storage.abs())

/**
 * Returns the absolute value of this [IntegerNumber].
 *
 * @return A new [IntegerNumber] representing the absolute value of this number.
 */
context(cache: EvaluationCache)
public fun IntegerNumber.absolute(): IntegerNumber = IntegerNumber(storage.abs())
