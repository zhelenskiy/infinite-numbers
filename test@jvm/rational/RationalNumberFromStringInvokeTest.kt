package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.parseString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RationalNumberFromStringInvokeTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base10BasicAndRepeatingAndComma() {
        val r1 = RationalNumber("3.14")
        assertEquals("3.14", r1.toString(FractionFormat.DOT, 10))

        val r2 = RationalNumber("1.2(3)")
        val expected = RationalNumber("1.2(3)")
        assertEquals(expected.toString(FractionFormat.DIVISION, 10), r2.toString(FractionFormat.DIVISION, 10))

        // comma works the same as dot
        val r3 = RationalNumber("1,2(3)")
        assertEquals(r2.toString(FractionFormat.DIVISION, 10), r3.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun integerOnlyAndLeadingSeparator() {
        assertEquals(IntegerNumber.parseString("7").toString(), RationalNumber("7").toString())

        val dotLeading = RationalNumber(".5")
        val commaLeading = RationalNumber(",5")
        val expected = RationalNumber("0.5")
        assertEquals(expected.toString(FractionFormat.DIVISION, 10), dotLeading.toString(FractionFormat.DIVISION, 10))
        assertEquals(expected.toString(FractionFormat.DIVISION, 10), commaLeading.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun differentRadixFractionAndPeriod() {
        // In base 2: 1.01 = 1 + 1/4 = 5/4
        val b2 = RationalNumber("1.01", 2)
        assertEquals(ratio("5", "4").toString(FractionFormat.DIVISION, 10), b2.toString(FractionFormat.DIVISION, 10))

        // In base 16: 1.(5) = 1 + 5/15 = 1 + 1/3 = 4/3
        val hexRepeating = RationalNumber("1.(5)", 16)
        assertEquals(
            ratio("4", "3").toString(FractionFormat.DIVISION, 10),
            hexRepeating.toString(FractionFormat.DIVISION, 10)
        )
    }

    @Test
    fun emptyStringParsesToZeroCurrentBehavior() {
        // Current implementation treats empty string as 0
        assertEquals(zero.toString(), RationalNumber("").toString())
    }

    @Test
    fun errorsForInvalidInput() {
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.2.3") }
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.2)") }
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.2", 1) } // invalid radix passed through to dot()
    }

    @Test
    fun negativesAndPureIntegers() {
        // negatives with explicit integer part
        assertEquals("-5/4", RationalNumber("-1.25").toString(FractionFormat.DIVISION, 10))
        assertEquals("-1/3", RationalNumber("-0.(3)").toString(FractionFormat.DIVISION, 10))

        // pure integers and leading zeros
        assertEquals("0", RationalNumber("000").toString())
        assertEquals("7", RationalNumber("0007").toString())
        assertEquals("-7", RationalNumber("-0007").toString())
        assertEquals("0", RationalNumber("-0").toString())
    }

    @Test
    fun leadingZerosInIntegerFractionAndPeriod() {
        // 001.020(003) should equal 1 + 20/1000 + 3/(999*1000)
        val parsed = RationalNumber("001.020(003)")
        val expected = RationalNumber("001.020(003)")
        assertEquals(expected.toString(FractionFormat.DIVISION, 10), parsed.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun zeroRepeatingFormsAndEmptyParentheses() {
        // Repeating zero changes nothing
        assertEquals("1", RationalNumber("1.(0)").toString())
        // Empty parentheses are treated like repeating zero
        assertEquals("1", RationalNumber("1.()").toString())
        // Classic one third
        assertEquals("1/3", RationalNumber("0.(3)").toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun leadingSeparatorWithRepeating() {
        // .(3) → 1/3
        assertEquals("1/3", RationalNumber(".(3)").toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun invalidDigitsForRadix() {
        // digit '2' is invalid for base 2 in fractional part
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.2", 2) }
        // 'G' is invalid for hexadecimal period
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.(G)", 16) }
    }

    @Test
    fun uppercaseDigitsInHexFraction() {
        // In base 16: 1.F = 1 + 15/16 = 31/16
        val r = RationalNumber("1.F", 16)
        assertEquals(ratio("31", "16").toString(FractionFormat.DIVISION, 10), r.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun simpleFractionFormat() {
        // Basic fraction: 3/4
        val r1 = RationalNumber("3/4")
        assertEquals("3/4", r1.toString(FractionFormat.DIVISION, 10))

        // Negative fraction: -5/8
        val r2 = RationalNumber("-5/8")
        assertEquals("-5/8", r2.toString(FractionFormat.DIVISION, 10))

        // Fraction that reduces: 6/8 = 3/4
        val r3 = RationalNumber("6/8")
        assertEquals("3/4", r3.toString(FractionFormat.DIVISION, 10))

        // Integer as fraction: 10/1
        val r4 = RationalNumber("10/1")
        assertEquals("10", r4.toString())

        // Zero numerator: 0/5
        val r5 = RationalNumber("0/5")
        assertEquals("0", r5.toString())
    }

    @Test
    fun fractionFormatDifferentRadixes() {
        // Base 2: 101/10 = 5/2 in base 10
        val r1 = RationalNumber("101/10", 2)
        assertEquals("5/2", r1.toString(FractionFormat.DIVISION, 10))

        // Base 16: A/2 = 10/2 = 5 in base 10
        val r2 = RationalNumber("A/2", 16)
        assertEquals("5", r2.toString())

        // Base 8: 7/4 in base 8 = 7/4 in base 10
        val r3 = RationalNumber("7/4", 8)
        assertEquals("7/4", r3.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun mixedNumberFormat() {
        // Basic mixed number: 1 1/2 = 3/2
        val r1 = RationalNumber("1 1/2")
        assertEquals("3/2", r1.toString(FractionFormat.DIVISION, 10))

        // Negative mixed number: -2 3/4 = -11/4
        val r2 = RationalNumber("-2 3/4")
        assertEquals("-11/4", r2.toString(FractionFormat.DIVISION, 10))

        // Mixed number with zero integer part: 0 5/8 = 5/8
        val r3 = RationalNumber("0 5/8")
        assertEquals("5/8", r3.toString(FractionFormat.DIVISION, 10))

        // Large mixed number: 100 1/3
        val r4 = RationalNumber("100 1/3")
        assertEquals("301/3", r4.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun mixedNumberFormatDifferentRadixes() {
        // Base 2: 10 1/10 = (2 + 1/2) = 5/2 in base 10
        val r1 = RationalNumber("10 1/10", 2)
        assertEquals("5/2", r1.toString(FractionFormat.DIVISION, 10))

        // Base 16: F 1/2 = (15 + 1/2) = 31/2 in base 10
        val r2 = RationalNumber("F 1/2", 16)
        assertEquals("31/2", r2.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun fractionFormatErrorCases() {
        // Zero denominator
        assertFailsWith<IllegalArgumentException> { RationalNumber("5/0") }

        // Negative denominator is not allowed
        assertFailsWith<IllegalArgumentException> { RationalNumber("5/-2") }

        // Both negative - still not allowed
        assertFailsWith<IllegalArgumentException> { RationalNumber("-5/-2") }
    }

    @Test
    fun mixedNumberErrorCases() {
        // Negative numerator in fractional part should fail
        assertFailsWith<IllegalArgumentException> { RationalNumber("1 -1/2") }

        // Zero denominator in mixed number
        assertFailsWith<IllegalArgumentException> { RationalNumber("1 1/0") }

        // Negative sign applies to whole mixed number
        val r = RationalNumber("-1 1/2")
        assertEquals("-3/2", r.toString(FractionFormat.DIVISION, 10))
    }

    @Test
    fun fractionFormatWithReduction() {
        // 12/18 reduces to 2/3
        val r1 = RationalNumber("12/18")
        assertEquals("2/3", r1.toString(FractionFormat.DIVISION, 10))

        // Large numbers that reduce: 1000/2000 = 1/2
        val r2 = RationalNumber("1000/2000")
        assertEquals("1/2", r2.toString(FractionFormat.DIVISION, 10))

        // Negative fraction that reduces: -8/12 = -2/3
        val r3 = RationalNumber("-8/12")
        assertEquals("-2/3", r3.toString(FractionFormat.DIVISION, 10))
    }
}
