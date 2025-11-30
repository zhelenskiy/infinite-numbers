package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RationalNumberFromStringInvokeTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base10BasicAndRepeatingAndComma() {
        val r1 = RationalNumber("3.14")
        assertEquals("3.14", r1.toString(10, FractionFormat.DOT))

        val r2 = RationalNumber("1.2(3)")
        val expected = RationalNumber("1.2(3)")
        assertEquals(expected.toString(10, FractionFormat.DIVISION), r2.toString(10, FractionFormat.DIVISION))

        // comma works the same as dot
        val r3 = RationalNumber("1,2(3)")
        assertEquals(r2.toString(10, FractionFormat.DIVISION), r3.toString(10, FractionFormat.DIVISION))
    }

    @Test
    fun integerOnlyAndLeadingSeparator() {
        assertEquals(Integer.parseString("7").toString(), RationalNumber("7").toString())

        val dotLeading = RationalNumber(".5")
        val commaLeading = RationalNumber(",5")
        val expected = RationalNumber("0.5")
        assertEquals(expected.toString(10, FractionFormat.DIVISION), dotLeading.toString(10, FractionFormat.DIVISION))
        assertEquals(expected.toString(10, FractionFormat.DIVISION), commaLeading.toString(10, FractionFormat.DIVISION))
    }

    @Test
    fun differentRadixFractionAndPeriod() {
        // In base 2: 1.01 = 1 + 1/4 = 5/4
        val b2 = RationalNumber("1.01", 2)
        assertEquals(ratio("5", "4").toString(10, FractionFormat.DIVISION), b2.toString(10, FractionFormat.DIVISION))

        // In base 16: 1.(5) = 1 + 5/15 = 1 + 1/3 = 4/3
        val hexRepeating = RationalNumber("1.(5)", 16)
        assertEquals(ratio("4", "3").toString(10, FractionFormat.DIVISION), hexRepeating.toString(10, FractionFormat.DIVISION))
    }

    @Test
    fun emptyStringParsesToZeroCurrentBehavior() {
        // Current implementation treats empty string as 0
        assertEquals(Integer.zero.toString(), RationalNumber("").toString())
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
        assertEquals("-5/4", RationalNumber("-1.25").toString(10, FractionFormat.DIVISION))
        assertEquals("-1/3", RationalNumber("-0.(3)").toString(10, FractionFormat.DIVISION))

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
        assertEquals(expected.toString(10, FractionFormat.DIVISION), parsed.toString(10, FractionFormat.DIVISION))
    }

    @Test
    fun zeroRepeatingFormsAndEmptyParentheses() {
        // Repeating zero changes nothing
        assertEquals(RationalNumber("1").toString(), RationalNumber("1.(0)").toString())
        // Empty parentheses are treated like repeating zero
        assertEquals(RationalNumber("1").toString(), RationalNumber("1.()").toString())
        // Classic one third
        assertEquals("1/3", RationalNumber("0.(3)").toString(10, FractionFormat.DIVISION))
    }

    @Test
    fun leadingSeparatorWithRepeating() {
        // .(3) → 1/3
        assertEquals("1/3", RationalNumber(".(3)").toString(10, FractionFormat.DIVISION))
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
        assertEquals(ratio("31", "16").toString(10, FractionFormat.DIVISION), r.toString(10, FractionFormat.DIVISION))
    }
}
