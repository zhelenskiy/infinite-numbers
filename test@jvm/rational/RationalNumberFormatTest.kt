package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.utils.parseString
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun divisionAndMixedBase10Positive() {
        val r = ratio("7", "3")
        assertEquals("7/3", r.toString(fractionFormat = FractionFormat.DIVISION, radix = 10))
        assertEquals("2 1/3", r.toString(fractionFormat = FractionFormat.MIXED, radix = 10))
    }

    @Test
    fun dotAndCommaBase10RepeatingAndTerminating() {
        val repeating = ratio("7", "3")
        assertEquals("2.(3)", repeating.toString(fractionFormat = FractionFormat.DOT, radix = 10))
        assertEquals("2,(3)", repeating.toString(fractionFormat = FractionFormat.COMMA, radix = 10))

        val half = ratio("1", "2")
        assertEquals("0.5", half.toString(FractionFormat.DOT, 10))
        assertEquals("0,5", half.toString(FractionFormat.COMMA, 10))
    }

    @Test
    fun differentRadicesDivisionAndMixed() {
        val r = ratio("5", "8") // 5/8 = 0.101 in base 2
        assertEquals("5/8", r.toString(FractionFormat.DIVISION, 10))
        assertEquals("0.101", r.toString(FractionFormat.DOT, 2))

        val q = ratio("255", "16") // 255/16 = 15 + 15/16 = F.F in base 16
        assertEquals("F F/10", q.toString(FractionFormat.MIXED, 16).uppercase())
        assertEquals("F.F", q.toString(FractionFormat.DOT, 16).uppercase())
    }

    @Test
    fun negativeNumbersMixedAndDot() {
        val r = ratio("-7", "3")
        assertEquals("-2 1/3", r.toString(FractionFormat.MIXED, 10))
        assertEquals("-2.(3)", r.toString(FractionFormat.DOT, 10))
    }
}
