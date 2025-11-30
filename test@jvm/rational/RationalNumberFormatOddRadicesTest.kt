package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class RationalNumberFormatOddRadicesTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun base3TerminatingAndRepeating() {
        // 1/3 terminates in base 3: 0.1
        val oneThird = ratio("1", "3")
        assertEquals("0.1", oneThird.toString(3, FractionFormat.DOT))
        assertEquals("0,1", oneThird.toString(3, FractionFormat.COMMA))

        // 1/2 repeats in base 3: 0.(1)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(1)", oneHalf.toString(3, FractionFormat.DOT))

        // 5/3 = 1 2/3 -> mixed should show base-3 denominator as 10
        val fiveThirds = ratio("5", "3")
        assertEquals("1 2/10", fiveThirds.toString(3, FractionFormat.MIXED))
        assertEquals("1.2", fiveThirds.toString(3, FractionFormat.DOT))
    }

    @Test
    fun base5Examples() {
        // 3/5 terminates in base 5: 0.3
        val threeFifths = ratio("3", "5")
        assertEquals("0.3", threeFifths.toString(5, FractionFormat.DOT))

        // 1/2 repeats in base 5: 0.(2)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(2)", oneHalf.toString(5, FractionFormat.DOT))
        assertEquals("0,(2)", oneHalf.toString(5, FractionFormat.COMMA))
    }

    @Test
    fun base7RepeatingExample() {
        // 1/2 in base 7 repeats with digit 3: 0.(3)
        val oneHalf = ratio("1", "2")
        assertEquals("0.(3)", oneHalf.toString(7, FractionFormat.DOT))
    }

    @Test
    fun base12LettersAndTerminating() {
        // Digits above 9 should use letters; 10 -> A, 11 -> B in radix 12
        val twelvePlus = RationalNumber("A.B", 12)
        assertEquals("A.B", twelvePlus.toString(12, FractionFormat.DOT).uppercase())

        // 1/3 = 0.4 in base 12 since 4/12 = 1/3
        val oneThird = ratio("1", "3")
        assertEquals("0.4", oneThird.toString(12, FractionFormat.DOT))

        // 1/6 = 0.2 in base 12 since 2/12 = 1/6
        val oneSixth = ratio("1", "6")
        assertEquals("0.2", oneSixth.toString(12, FractionFormat.DOT))
    }
}
