package com.zhelenskiy.infinite.numbers.rational

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.zhelenskiy.infinite.numbers.rational.Integer.Companion.zero
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

internal typealias IntStorage = BigInteger

/**
 * Arbitrary‑precision integer type
 */
@ConsistentCopyVisibility
public data class Integer internal constructor(val storage: IntStorage) : RationalNumber {
    override val numerator: Integer get() = this
    override val denominator: Integer get() = one

    override fun toString(): String = storage.toString()

    override fun compareTo(other: RationalNumber): Int =
        if (other is Integer) storage.compareTo(other.storage) else super.compareTo(other)

    override fun toString(radix: Int, fractionFormat: FractionFormat): String = toString(radix)


    public companion object {
        @JvmStatic
        public val one: Integer = Integer(1)
        @JvmStatic
        public val zero: Integer = Integer(0)
    }
}

public fun Integer.toString(radix: Int): String = storage.toString(radix)

public operator fun Integer.inc(): Integer = Integer(storage + 1)
public operator fun Integer.unaryMinus(): Integer = Integer(-storage)
public operator fun Integer.unaryPlus(): Integer = this
public operator fun Integer.rem(other: Integer): Integer = Integer(storage.abs() % other.storage.abs()) * sign
public infix fun Integer.mod(other: Integer): Integer {
    val rem = this % other.absolute()
    return rem + (if (rem != zero && this.sign * other.sign < 0) other else zero)
}

public operator fun Integer.times(other: Integer): Integer = Integer(storage * other.storage)
public operator fun Integer.dec(): Integer = Integer(storage - 1)
public operator fun Integer.minus(other: Integer): Integer = Integer(storage - other.storage)
public operator fun Integer.plus(other: Integer): Integer = Integer(storage + other.storage)
public infix fun Integer.remQuotient(other: Integer): Integer = Integer(storage / other.storage)
public infix fun Integer.modQuotient(other: Integer): Integer = (this - (this mod other)) remQuotient other

@JvmOverloads
public fun Integer.Companion.parseString(string: String, base: Int = 10): Integer =
    Integer(IntStorage.parseString(string, base))

@JvmName("fromLong")
public operator fun Integer.Companion.invoke(value: Long): Integer = Integer(IntStorage.fromLong(value))
@JvmName("fromULong")
public operator fun Integer.Companion.invoke(value: ULong): Integer = Integer(IntStorage.fromULong(value))
@JvmName("fromInt")
public operator fun Integer.Companion.invoke(value: Int): Integer = Integer(IntStorage.fromInt(value))
@JvmName("fromUInt")
public operator fun Integer.Companion.invoke(value: UInt): Integer = Integer(IntStorage.fromUInt(value))
@JvmName("fromShort")
public operator fun Integer.Companion.invoke(value: Short): Integer = Integer(IntStorage.fromShort(value))
@JvmName("fromUShort")
public operator fun Integer.Companion.invoke(value: UShort): Integer = Integer(IntStorage.fromUShort(value))
@JvmName("fromByte")
public operator fun Integer.Companion.invoke(value: Byte): Integer = Integer(IntStorage.fromByte(value))
@JvmName("fromUByte")
public operator fun Integer.Companion.invoke(value: UByte): Integer = Integer(IntStorage.fromUByte(value))

// -- Mixed arithmetic with Kotlin primitives ----------------------------------------------------
// Each overload delegates to the corresponding `Integer(<primitive>)` factory.
public operator fun Integer.plus(other: Byte): Integer = this + Integer(other)
public operator fun Integer.plus(other: UByte): Integer = this + Integer(other)
public operator fun Integer.plus(other: Short): Integer = this + Integer(other)
public operator fun Integer.plus(other: UShort): Integer = this + Integer(other)
public operator fun Integer.plus(other: Int): Integer = this + Integer(other)
public operator fun Integer.plus(other: Long): Integer = this + Integer(other)

public operator fun Byte.plus(other: Integer): Integer = Integer(this) + other
public operator fun UByte.plus(other: Integer): Integer = Integer(this) + other
public operator fun Short.plus(other: Integer): Integer = Integer(this) + other
public operator fun UShort.plus(other: Integer): Integer = Integer(this) + other
public operator fun Int.plus(other: Integer): Integer = Integer(this) + other
public operator fun Long.plus(other: Integer): Integer = Integer(this) + other

public operator fun Integer.minus(other: Byte): Integer = this - Integer(other)
public operator fun Integer.minus(other: UByte): Integer = this - Integer(other)
public operator fun Integer.minus(other: Short): Integer = this - Integer(other)
public operator fun Integer.minus(other: UShort): Integer = this - Integer(other)
public operator fun Integer.minus(other: Int): Integer = this - Integer(other)
public operator fun Integer.minus(other: Long): Integer = this - Integer(other)

public operator fun Byte.minus(other: Integer): Integer = Integer(this) - other
public operator fun UByte.minus(other: Integer): Integer = Integer(this) - other
public operator fun Short.minus(other: Integer): Integer = Integer(this) - other
public operator fun UShort.minus(other: Integer): Integer = Integer(this) - other
public operator fun Int.minus(other: Integer): Integer = Integer(this) - other
public operator fun Long.minus(other: Integer): Integer = Integer(this) - other

public operator fun Integer.times(other: Byte): Integer = this * Integer(other)
public operator fun Integer.times(other: UByte): Integer = this * Integer(other)
public operator fun Integer.times(other: Short): Integer = this * Integer(other)
public operator fun Integer.times(other: UShort): Integer = this * Integer(other)
public operator fun Integer.times(other: Int): Integer = this * Integer(other)
public operator fun Integer.times(other: Long): Integer = this * Integer(other)

public operator fun Byte.times(other: Integer): Integer = Integer(this) * other
public operator fun UByte.times(other: Integer): Integer = Integer(this) * other
public operator fun Short.times(other: Integer): Integer = Integer(this) * other
public operator fun UShort.times(other: Integer): Integer = Integer(this) * other
public operator fun Int.times(other: Integer): Integer = Integer(this) * other
public operator fun Long.times(other: Integer): Integer = Integer(this) * other

public operator fun Integer.rem(other: Byte): Integer = this % Integer(other)
public operator fun Integer.rem(other: UByte): Integer = this % Integer(other)
public operator fun Integer.rem(other: Short): Integer = this % Integer(other)
public operator fun Integer.rem(other: UShort): Integer = this % Integer(other)
public operator fun Integer.rem(other: Int): Integer = this % Integer(other)
public operator fun Integer.rem(other: Long): Integer = this % Integer(other)

public operator fun Byte.rem(other: Integer): Integer = Integer(this) % other
public operator fun UByte.rem(other: Integer): Integer = Integer(this) % other
public operator fun Short.rem(other: Integer): Integer = Integer(this) % other
public operator fun UShort.rem(other: Integer): Integer = Integer(this) % other
public operator fun Int.rem(other: Integer): Integer = Integer(this) % other
public operator fun Long.rem(other: Integer): Integer = Integer(this) % other

public infix fun Integer.mod(other: Byte): Integer = this mod Integer(other)
public infix fun Integer.mod(other: UByte): Integer = this mod Integer(other)
public infix fun Integer.mod(other: Short): Integer = this mod Integer(other)
public infix fun Integer.mod(other: UShort): Integer = this mod Integer(other)
public infix fun Integer.mod(other: Int): Integer = this mod Integer(other)
public infix fun Integer.mod(other: Long): Integer = this mod Integer(other)

public infix fun Byte.mod(other: Integer): Integer = Integer(this) mod other
public infix fun UByte.mod(other: Integer): Integer = Integer(this) mod other
public infix fun Short.mod(other: Integer): Integer = Integer(this) mod other
public infix fun UShort.mod(other: Integer): Integer = Integer(this) mod other
public infix fun Int.mod(other: Integer): Integer = Integer(this) mod other
public infix fun Long.mod(other: Integer): Integer = Integer(this) mod other

public infix fun Integer.remQuotient(other: Byte): Integer = this remQuotient Integer(other)
public infix fun Integer.remQuotient(other: UByte): Integer = this remQuotient Integer(other)
public infix fun Integer.remQuotient(other: Short): Integer = this remQuotient Integer(other)
public infix fun Integer.remQuotient(other: UShort): Integer = this remQuotient Integer(other)
public infix fun Integer.remQuotient(other: Int): Integer = this remQuotient Integer(other)
public infix fun Integer.remQuotient(other: UInt): Integer = this remQuotient Integer(other)

public infix fun Byte.remQuotient(other: Integer): Integer = Integer(this) remQuotient other
public infix fun UByte.remQuotient(other: Integer): Integer = Integer(this) remQuotient other
public infix fun Short.remQuotient(other: Integer): Integer = Integer(this) remQuotient other
public infix fun UShort.remQuotient(other: Integer): Integer = Integer(this) remQuotient other
public infix fun Int.remQuotient(other: Integer): Integer = Integer(this) remQuotient other
public infix fun UInt.remQuotient(other: Integer): Integer = Integer(this) remQuotient other

public infix fun Integer.modQuotient(other: Byte): Integer = this modQuotient Integer(other)
public infix fun Integer.modQuotient(other: UByte): Integer = this modQuotient Integer(other)
public infix fun Integer.modQuotient(other: Short): Integer = this modQuotient Integer(other)
public infix fun Integer.modQuotient(other: UShort): Integer = this modQuotient Integer(other)
public infix fun Integer.modQuotient(other: Int): Integer = this modQuotient Integer(other)
public infix fun Integer.modQuotient(other: UInt): Integer = this modQuotient Integer(other)

public infix fun Byte.modQuotient(other: Integer): Integer = Integer(this) modQuotient other
public infix fun UByte.modQuotient(other: Integer): Integer = Integer(this) modQuotient other
public infix fun Short.modQuotient(other: Integer): Integer = Integer(this) modQuotient other
public infix fun UShort.modQuotient(other: Integer): Integer = Integer(this) modQuotient other
public infix fun Int.modQuotient(other: Integer): Integer = Integer(this) modQuotient other
public infix fun UInt.modQuotient(other: Integer): Integer = Integer(this) modQuotient other

public val Integer.sign: Int
    get() = storage.signum()

public fun Integer.square(): Integer = this * this
public fun Integer.absolute(): Integer = Integer(storage.abs())
