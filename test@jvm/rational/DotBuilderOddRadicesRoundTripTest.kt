package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class DotBuilderOddRadicesRoundTripTest {
    @Test
    fun base3RoundTripWithLeadingZeroAndPeriod() {
        // 1.0(1) in base 3 = 1 + 0/3 + (1)/(3^1 - 1) = 3/2
        val tri = RationalNumber("1.0(1)", 3)
        assertEquals("1.0(1)", tri.toString(3, FractionFormat.DOT))
        assertEquals("1,0(1)", tri.toString(3, FractionFormat.COMMA))
    }

    @Test
    fun base12LettersAndRoundTrip() {
        val dozenal = RationalNumber("5.A(3)", 12)
        // Ensure we preserve digits and period when formatting back in radix 12
        assertEquals("5.A(3)", dozenal.toString(12, FractionFormat.DOT).uppercase())
    }
}
