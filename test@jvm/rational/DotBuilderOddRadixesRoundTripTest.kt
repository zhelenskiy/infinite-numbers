package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class DotBuilderOddRadixesRoundTripTest {
    @Test
    fun base3RoundTripWithLeadingZeroAndPeriod() {
        // 1.0(1) in base 3 = 1 + 0/3 + (1)/(3^1 - 1) = 3/2
        val tri = RationalNumber("1.0(1)", 3)
        assertEquals("1.0(1)", tri.toString(FractionFormat.DOT, 3))
        assertEquals("1,0(1)", tri.toString(FractionFormat.COMMA, 3))
    }

    @Test
    fun base12LettersAndRoundTrip() {
        val dozenal = RationalNumber("5.A(3)", 12)
        // Ensure we preserve digits and period when formatting back in radix 12
        assertEquals("5.A(3)", dozenal.toString(FractionFormat.DOT, 12).uppercase())
    }
}
