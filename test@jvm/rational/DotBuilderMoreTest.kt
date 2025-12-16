package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DotBuilderMoreTest {
    @Test
    fun repeatingInNonDecimalBasesAndNegatives() {
        // Base 2: 1.dot(1, 2, 1) -> 10
        val binRepeating = RationalNumber("1.1(1)", 2)
        assertEquals("10", binRepeating.toString(FractionFormat.DOT, 2))

        // Base 16: A.dot(B, 16, C) -> A.B(C)
        val hexRepeating = RationalNumber("A.B(C)", 16)
        assertEquals("A.B(C)", hexRepeating.toString(FractionFormat.DOT, 16).uppercase())

        // Negative integer part with a non-zero period
        val negativeRepeat = RationalNumber("-1.6(3)")
        assertEquals("-1.6(3)", negativeRepeat.toString(FractionFormat.DOT, 10))
    }

    @Test
    fun additionalInvalidInputs() {
        assertFailsWith<IllegalArgumentException> { RationalNumber("0.0", 0) } // radix <= 1
        assertFailsWith<IllegalArgumentException> { RationalNumber("2.-5") } // negative fractional
        assertFailsWith<IllegalArgumentException> { RationalNumber("2.5(-1)") } // negative period
    }
}
