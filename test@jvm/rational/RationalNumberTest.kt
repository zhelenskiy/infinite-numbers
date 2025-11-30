package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RationalNumberTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun constructionAndNormalization() {
        val r = ratio("2", "4")
        assertEquals("1/2", r.toString())

        val negDen = ratio("1", "-2")
        assertEquals("-1/2", negDen.toString())

        assertFailsWith<IllegalArgumentException> { ratio("1", "0") }
    }

    @Test
    fun comparisonAndCommonDenominator() {
        val a = ratio("1", "2")
        val b = ratio("2", "3")
        assertTrue(a < b)
        assertEquals(int("6"), a commonDenominatorWith b)
    }

    @Test
    fun arithmeticPlusMinusTimesDiv() {
        val a = ratio("1", "2")
        val b = ratio("2", "3")

        assertEquals("7/6", (a + b).toString())
        assertEquals("-1/6", (a - b).toString())
        assertEquals("1/3", (a * b).toString())
        assertEquals("3/4", (a / b).toString())

        assertFailsWith<IllegalArgumentException> { a / Integer.zero }
    }

    @Test
    fun incDecAndUnary() {
        var r = ratio("1", "2")
        r++
        assertEquals("3/2", r.toString())
        r--
        assertEquals("1/2", r.toString())
        assertEquals("-1/2", (-r).toString())
        assertEquals("1/2", (+r).toString())
    }

    @Test
    fun partsAndAbsAndSquarePow() {
        val r = ratio("-7", "3")
        assertEquals(int("-2"), r.integerPart())
        assertEquals("-1/3", r.fractionalPart().toString())
        assertEquals("7/3", r.absolute().toString())

        val q = ratio("5", "2")
        assertEquals("25/4", q.square().toString())

        assertEquals(int("8"), int("2").pow(int("3")))
        assertEquals(int("1"), int("2").pow(int("0")))
    }

    @Test
    fun formattingDivisionMixedDotComma() {
        val r = ratio("7", "3")
        assertEquals("7/3", r.toString(radix = 10, fractionFormat = FractionFormat.DIVISION))
        assertEquals("2 1/3", r.toString(radix = 10, fractionFormat = FractionFormat.MIXED))
        assertEquals("2.(3)", r.toString(radix = 10, fractionFormat = FractionFormat.DOT))
        assertEquals("2,(3)", r.toString(radix = 10, fractionFormat = FractionFormat.COMMA))

        val half = ratio("1", "2")
        assertEquals("0.5", half.toString(10, FractionFormat.DOT))
        assertEquals("0,5", half.toString(10, FractionFormat.COMMA))
    }

    @Test
    fun dotBuilder() {
        val piLike = RationalNumber("3.14")
        assertEquals("3.14", piLike.toString(10, FractionFormat.DOT))

        val oneTwoRepeatingThree = RationalNumber("1.2(3)")
        assertEquals("1.2(3)", oneTwoRepeatingThree.toString(10, FractionFormat.DOT))

        assertFailsWith<IllegalArgumentException> { RationalNumber("1.-1") }
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.1", 1) }
    }
}
