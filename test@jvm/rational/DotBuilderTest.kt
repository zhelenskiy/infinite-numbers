package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DotBuilderTest {
    @Test
    fun base10ExamplesAndErrors() {
        val piLike = RationalNumber("3.14")
        assertEquals("3.14", piLike.toString(10, FractionFormat.DOT))

        val oneTwoRepeatingThree = RationalNumber("1.2(3)")
        assertEquals("1.2(3)", oneTwoRepeatingThree.toString(10, FractionFormat.DOT))

        // Invalid formats should still throw
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.-1") }
        assertFailsWith<IllegalArgumentException> { RationalNumber("1.1", 1) }
    }

    @Test
    fun nonDecimalRadixesAndSigns() {
        // Base 2: 1.dot(1, 2, 0) -> 1.1
        val bin = RationalNumber("1.1", 2)
        assertEquals("1.1", bin.toString(2, FractionFormat.DOT))

        // Base 16: 10.dot(15, 16) -> A.F
        val hex = RationalNumber("A.F", 16)
        assertEquals("A.F", hex.toString(16, FractionFormat.DOT).uppercase())

        // Negative integer part
        val negative = RationalNumber("-2.5")
        assertEquals("-2.5", negative.toString(10, FractionFormat.DOT))
    }
}
