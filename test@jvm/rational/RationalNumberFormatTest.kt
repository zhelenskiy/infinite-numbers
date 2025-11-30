package rational

import com.zhelenskiy.infinite.numbers.rational.*
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun divisionAndMixedBase10Positive() {
        val r = ratio("7", "3")
        assertEquals("7/3", r.toString(radix = 10, fractionFormat = FractionFormat.DIVISION))
        assertEquals("2 1/3", r.toString(radix = 10, fractionFormat = FractionFormat.MIXED))
    }

    @Test
    fun dotAndCommaBase10RepeatingAndTerminating() {
        val repeating = ratio("7", "3")
        assertEquals("2.(3)", repeating.toString(radix = 10, fractionFormat = FractionFormat.DOT))
        assertEquals("2,(3)", repeating.toString(radix = 10, fractionFormat = FractionFormat.COMMA))

        val half = ratio("1", "2")
        assertEquals("0.5", half.toString(10, FractionFormat.DOT))
        assertEquals("0,5", half.toString(10, FractionFormat.COMMA))
    }

    @Test
    fun differentRadicesDivisionAndMixed() {
        val r = ratio("5", "8") // 5/8 = 0.101 in base 2
        assertEquals("5/8", r.toString(10, FractionFormat.DIVISION))
        assertEquals("0.101", r.toString(2, FractionFormat.DOT))

        val q = ratio("255", "16") // 255/16 = 15 + 15/16 = F.F in base 16
        assertEquals("F F/10", q.toString(16, FractionFormat.MIXED).uppercase())
        assertEquals("F.F", q.toString(16, FractionFormat.DOT).uppercase())
    }

    @Test
    fun negativeNumbersMixedAndDot() {
        val r = ratio("-7", "3")
        assertEquals("-2 1/3", r.toString(10, FractionFormat.MIXED))
        assertEquals("-2.(3)", r.toString(10, FractionFormat.DOT))
    }
}
