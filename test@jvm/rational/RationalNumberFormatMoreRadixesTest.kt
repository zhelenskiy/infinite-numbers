package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.utils.parseString
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatMoreRadixesTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base8ExamplesAndNegatives() {
        val threeEighths = ratio("3", "8")
        assertEquals("0.3", threeEighths.toString(FractionFormat.DOT, 8))
        assertEquals("0,3", threeEighths.toString(FractionFormat.COMMA, 8))

        val thirteenEighths = ratio("13", "8")
        assertEquals("1 5/10", thirteenEighths.toString(FractionFormat.MIXED, 8))
        assertEquals("1.5", thirteenEighths.toString(FractionFormat.DOT, 8))

        val negative = ratio("-13", "8")
        assertEquals("-1 5/10", negative.toString(FractionFormat.MIXED, 8))
        assertEquals("-1.5", negative.toString(FractionFormat.DOT, 8))
    }

    @Test
    fun hexRepeatingAndTerminating() {
        val oneThird = ratio("1", "3")
        // In base 16: 1/3 = 0.(5)
        assertEquals("0.(5)", oneThird.toString(FractionFormat.DOT, 16))

        val fifteenSixteenths = ratio("15", "16")
        assertEquals("0.F", fifteenSixteenths.toString(FractionFormat.DOT, 16).uppercase())
        assertEquals("0,F", fifteenSixteenths.toString(FractionFormat.COMMA, 16).uppercase())
    }
}
