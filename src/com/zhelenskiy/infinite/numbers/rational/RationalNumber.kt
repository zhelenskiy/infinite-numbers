package com.zhelenskiy.infinite.numbers.rational

import com.ionspin.kotlin.bignum.integer.Sign
import com.zhelenskiy.infinite.numbers.InfiniteNumber
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.COMMA
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.DIVISION
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.DOT
import com.zhelenskiy.infinite.numbers.rational.FractionFormat.MIXED
import com.zhelenskiy.infinite.numbers.rational.Integer.Companion.one
import com.zhelenskiy.infinite.numbers.rational.Integer.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber.Companion.underCommonDivisor
import com.zhelenskiy.infinite.numbers.rational.RegularRationalNumber.Companion.createThisOrAncestor
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads

public interface RationalNumber: InfiniteNumber, Comparable<RationalNumber> {
    public val numerator: Integer
    public val denominator: Integer

    override fun compareTo(other: RationalNumber): Int {
        val thisNumber = this.numerator * other.denominator
        val otherNumber = other.numerator * this.denominator
        return thisNumber.compareTo(otherNumber)
    }

    public fun toString(radix: Int, fractionFormat: FractionFormat): String = when (fractionFormat) {
        DOT -> toCommaFormat(radix, ".")
        COMMA -> toCommaFormat(radix, ",")
        DIVISION -> "${numerator.toString(radix)}/${denominator.toString(radix)}"
        MIXED -> {
            val integerPart = integerPart()
            val integerString = if (integerPart == zero) "" else integerPart.toString(radix) + " "
            val fractionString = fractionalPart().absolute().toString(radix, DIVISION)
            "$integerString$fractionString"
        }
    }

    public companion object {
        public operator fun invoke(numerator: Integer, denominator: Integer): RationalNumber =
            createThisOrAncestor(numerator, denominator)

        public inline fun underCommonDivisor(
            first: RationalNumber, second: RationalNumber, action: (Integer, Integer) -> Integer
        ): RationalNumber {
            val commonDenominator = first commonDenominatorWith second
            val newFirst = first.numerator * (commonDenominator remQuotient first.denominator)
            val newSecond = second.numerator * (commonDenominator remQuotient second.denominator)
            return RationalNumber(action(newFirst, newSecond), commonDenominator)
        }
    }
}

public enum class FractionFormat {
    DIVISION, MIXED, DOT, COMMA
}

public infix fun RationalNumber.commonDenominatorWith(other: RationalNumber): Integer =
    (this.denominator * other.denominator) remQuotient gcd(this.denominator, other.denominator)


private fun gcd(a: Integer, b: Integer): Integer = if (b == zero) a else gcd(b, a % b)

@JvmOverloads
@JvmName("fromString")
public operator fun RationalNumber.Companion.invoke(string: String, radix: Int = 10): RationalNumber {
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

    val integer = Integer.parseString(integerPart.removePrefix("-").ifEmpty { "0" }, radix)
    val fractionalNumeratorOrNull = if (fractionalPartString.isEmpty()) null else Integer.parseString(fractionalPartString, radix)
    val periodNumerator = Integer.parseString(periodString.ifEmpty { "0" }, radix)
    val fractionalDenominator = Integer(value = radix).pow(n = Integer(fractionalPartString.length))
    val fractional = (fractionalNumeratorOrNull ?: zero) / fractionalDenominator
    val periodDenominator = Integer(value = radix).pow(n = Integer(periodString.length)).dec()
    val period = if (periodDenominator == zero) zero else periodNumerator / periodDenominator / fractionalDenominator
    return (integer + fractional + period) * sign
}

@ConsistentCopyVisibility
internal data class RegularRationalNumber private constructor(override val numerator: Integer, override val denominator: Integer) : RationalNumber {
    override fun toString(): String = "$numerator/$denominator"

    companion object {
        internal fun createThisOrAncestor(numerator: Integer, denominator: Integer): RationalNumber {
            require(denominator != zero) { "Denominator must be non-zero" }
            if (denominator.storage.getSign() == Sign.NEGATIVE) return createThisOrAncestor(-numerator, -denominator)
            val gcd = gcd(numerator, denominator).absolute()
            val newNumerator = numerator remQuotient gcd
            val newDenominator = denominator remQuotient gcd
            return if (newDenominator == one) newNumerator else RegularRationalNumber(newNumerator, newDenominator)
        }
    }
}

public operator fun RationalNumber.inc(): RationalNumber = this + one
public operator fun RationalNumber.unaryMinus(): RationalNumber = RationalNumber(-numerator, denominator)
public operator fun RationalNumber.unaryPlus(): RationalNumber = this
public operator fun RationalNumber.times(other: RationalNumber): RationalNumber =
    RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator)
public operator fun RationalNumber.dec(): RationalNumber = this - one
public operator fun RationalNumber.minus(other: RationalNumber): RationalNumber =
    underCommonDivisor(this, other) { a, b -> a - b }
public operator fun RationalNumber.plus(other: RationalNumber): RationalNumber =
    underCommonDivisor(this, other) { a, b -> a + b }
public operator fun RationalNumber.div(other: RationalNumber): RationalNumber {
    require(other != zero) { "Divisor must be non-zero" }
    return RationalNumber(this.numerator * other.denominator, this.denominator * other.numerator)
}

public fun RationalNumber.absolute(): RationalNumber = if (this < zero) -this else this
public fun RationalNumber.integerPart(): Integer = numerator remQuotient denominator
public fun RationalNumber.fractionalPart(): RationalNumber = this - integerPart()

public fun RationalNumber.square(): RationalNumber = this * this
public fun Integer.pow(n: Integer): RationalNumber = when {
    n == zero -> one
    n == one -> this
    n < zero -> one / this.pow(-n)
    n % 2 == zero -> this.square().pow(n remQuotient 2)
    else -> this * this.pow(n - one)
}



public operator fun RationalNumber.plus(other: Byte): RationalNumber = this + Integer(other)
public operator fun RationalNumber.plus(other: UByte): RationalNumber = this + Integer(other)
public operator fun RationalNumber.plus(other: Short): RationalNumber = this + Integer(other)
public operator fun RationalNumber.plus(other: UShort): RationalNumber = this + Integer(other)
public operator fun RationalNumber.plus(other: Int): RationalNumber = this + Integer(other)
public operator fun RationalNumber.plus(other: Long): RationalNumber = this + Integer(other)

public operator fun Byte.plus(other: RationalNumber): RationalNumber = Integer(this) + other
public operator fun UByte.plus(other: RationalNumber): RationalNumber = Integer(this) + other
public operator fun Short.plus(other: RationalNumber): RationalNumber = Integer(this) + other
public operator fun UShort.plus(other: RationalNumber): RationalNumber = Integer(this) + other
public operator fun Int.plus(other: RationalNumber): RationalNumber = Integer(this) + other
public operator fun Long.plus(other: RationalNumber): RationalNumber = Integer(this) + other

public operator fun RationalNumber.minus(other: Byte): RationalNumber = this - Integer(other)
public operator fun RationalNumber.minus(other: UByte): RationalNumber = this - Integer(other)
public operator fun RationalNumber.minus(other: Short): RationalNumber = this - Integer(other)
public operator fun RationalNumber.minus(other: UShort): RationalNumber = this - Integer(other)
public operator fun RationalNumber.minus(other: Int): RationalNumber = this - Integer(other)
public operator fun RationalNumber.minus(other: Long): RationalNumber = this - Integer(other)

public operator fun Byte.minus(other: RationalNumber): RationalNumber = Integer(this) - other
public operator fun UByte.minus(other: RationalNumber): RationalNumber = Integer(this) - other
public operator fun Short.minus(other: RationalNumber): RationalNumber = Integer(this) - other
public operator fun UShort.minus(other: RationalNumber): RationalNumber = Integer(this) - other
public operator fun Int.minus(other: RationalNumber): RationalNumber = Integer(this) - other
public operator fun Long.minus(other: RationalNumber): RationalNumber = Integer(this) - other

public operator fun RationalNumber.times(other: Byte): RationalNumber = this * Integer(other)
public operator fun RationalNumber.times(other: UByte): RationalNumber = this * Integer(other)
public operator fun RationalNumber.times(other: Short): RationalNumber = this * Integer(other)
public operator fun RationalNumber.times(other: UShort): RationalNumber = this * Integer(other)
public operator fun RationalNumber.times(other: Int): RationalNumber = this * Integer(other)
public operator fun RationalNumber.times(other: Long): RationalNumber = this * Integer(other)

public operator fun Byte.times(other: RationalNumber): RationalNumber = Integer(this) * other
public operator fun UByte.times(other: RationalNumber): RationalNumber = Integer(this) * other
public operator fun Short.times(other: RationalNumber): RationalNumber = Integer(this) * other
public operator fun UShort.times(other: RationalNumber): RationalNumber = Integer(this) * other
public operator fun Int.times(other: RationalNumber): RationalNumber = Integer(this) * other
public operator fun Long.times(other: RationalNumber): RationalNumber = Integer(this) * other

public operator fun RationalNumber.div(other: Byte): RationalNumber = this / Integer(other)
public operator fun RationalNumber.div(other: UByte): RationalNumber = this / Integer(other)
public operator fun RationalNumber.div(other: Short): RationalNumber = this / Integer(other)
public operator fun RationalNumber.div(other: UShort): RationalNumber = this / Integer(other)
public operator fun RationalNumber.div(other: Int): RationalNumber = this / Integer(other)
public operator fun RationalNumber.div(other: Long): RationalNumber = this / Integer(other)

public operator fun Byte.div(other: RationalNumber): RationalNumber = Integer(this) / other
public operator fun UByte.div(other: RationalNumber): RationalNumber = Integer(this) / other
public operator fun Short.div(other: RationalNumber): RationalNumber = Integer(this) / other
public operator fun UShort.div(other: RationalNumber): RationalNumber = Integer(this) / other
public operator fun Int.div(other: RationalNumber): RationalNumber = Integer(this) / other
public operator fun Long.div(other: RationalNumber): RationalNumber = Integer(this) / other

private fun RationalNumber.toCommaFormat(radix: Int, separator: String): String {
    if (this < zero) return "-${(-this).toCommaFormat(radix, separator)}"
    if (this is Integer) return toString(radix)
    require(radix > 1) { "Radix must be greater than 1" }

    val intStr = (numerator remQuotient denominator).toString(radix)

    var remainder = numerator % denominator
    val remainders = LinkedHashSet<Integer>()
    val digits = mutableListOf<String>()
    while (true) {
        val digit = (remainder * radix).remQuotient(denominator).absolute()

        if (!remainders.add(remainder)) {
            val periodStartIndex: Int = remainders.indexOf(remainder)
            val digitsBeforePeriod = digits.subList(0, periodStartIndex).joinToString("")
            val digitsAfterPeriod = digits.subList(periodStartIndex, digits.size).joinToString("")

            return "$intStr$separator${digitsBeforePeriod}(${digitsAfterPeriod})"
        }

        digits.add(digit.toString(radix))

        remainder = (remainder * radix) % denominator

        if (remainder == zero) {
            return "$intStr$separator${digits.joinToString("")}"
        }
    }
}
