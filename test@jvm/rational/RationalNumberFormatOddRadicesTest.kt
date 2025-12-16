package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.parseString
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatOddRadicesTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base3TerminatingAndRepeating() {
        // 1/3 terminates in base 3: 0.1
        val oneThird = ratio("1", "3")
        assertEquals("0.1", oneThird.toString(FractionFormat.DOT, 3))
        assertEquals("0,1", oneThird.toString(FractionFormat.COMMA, 3))

        // 1/2 repeats in base 3: 0.(1)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(1)", oneHalf.toString(FractionFormat.DOT, 3))

        // 5/3 = 1 2/3 -> mixed should show base-3 denominator as 10
        val fiveThirds = ratio("5", "3")
        assertEquals("1 2/10", fiveThirds.toString(FractionFormat.MIXED, 3))
        assertEquals("1.2", fiveThirds.toString(FractionFormat.DOT, 3))
    }

    @Test
    fun base5Examples() {
        // 3/5 terminates in base 5: 0.3
        val threeFifths = ratio("3", "5")
        assertEquals("0.3", threeFifths.toString(FractionFormat.DOT, 5))

        // 1/2 repeats in base 5: 0.(2)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(2)", oneHalf.toString(FractionFormat.DOT, 5))
        assertEquals("0,(2)", oneHalf.toString(FractionFormat.COMMA, 5))
    }

    @Test
    fun base7RepeatingExample() {
        // 1/2 in base 7 repeats with digit 3: 0.(3)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(3)", oneHalf.toString(FractionFormat.DOT, 7))
    }

    @Test
    fun base12LettersAndTerminating() {
        // Digits above 9 should use letters; 10 -> A, 11 -> B in radix 12
        val twelvePlus = RationalNumber("A.B", 12)
        assertEquals("A.B", twelvePlus.toString(FractionFormat.DOT, 12).uppercase())

        // 1/3 = 0.4 in base 12 since 4/12 = 1/3
        val oneThird = ratio("1", "3")
        assertEquals("0.4", oneThird.toString(FractionFormat.DOT, 12))

        // 1/6 = 0.2 in base 12 since 2/12 = 1/6
        val oneSixth = ratio("1", "6")
        assertEquals("0.2", oneSixth.toString(FractionFormat.DOT, 12))
    }
}
