package rational

import com.zhelenskiy.infinite.numbers.rational.*
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatMoreRadicesTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base8ExamplesAndNegatives() {
        val threeEighths = ratio("3", "8")
        assertEquals("0.3", threeEighths.toString(8, FractionFormat.DOT))
        assertEquals("0,3", threeEighths.toString(8, FractionFormat.COMMA))

        val thirteenEighths = ratio("13", "8")
        assertEquals("1 5/10", thirteenEighths.toString(8, FractionFormat.MIXED))
        assertEquals("1.5", thirteenEighths.toString(8, FractionFormat.DOT))

        val negative = ratio("-13", "8")
        assertEquals("-1 5/10", negative.toString(8, FractionFormat.MIXED))
        assertEquals("-1.5", negative.toString(8, FractionFormat.DOT))
    }

    @Test
    fun hexRepeatingAndTerminating() {
        val oneThird = ratio("1", "3")
        // In base 16: 1/3 = 0.(5)
        assertEquals("0.(5)", oneThird.toString(16, FractionFormat.DOT))

        val fifteenSixteenths = ratio("15", "16")
        assertEquals("0.F", fifteenSixteenths.toString(16, FractionFormat.DOT).uppercase())
        assertEquals("0,F", fifteenSixteenths.toString(16, FractionFormat.COMMA).uppercase())
    }
}
