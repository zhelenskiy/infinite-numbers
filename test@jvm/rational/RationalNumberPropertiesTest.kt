package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.utils.parseString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RationalNumberPropertiesTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun commutativityAndAssociativity() {
        val a = ratio("1", "2")
        val b = ratio("2", "3")
        val c = ratio("-5", "7")

        // Commutativity
        assertEquals(a + b, b + a)
        assertEquals(a * b, b * a)

        // Associativity
        assertEquals((a + b) + c, a + (b + c))
        assertEquals((a * b) * c, a * (b * c))
    }

    @Test
    fun distributivityAndIdentities() {
        val a = ratio("3", "4")
        val b = ratio("5", "6")
        val c = ratio("7", "8")

        // Distributivity
        assertEquals(a * (b + c), a * b + a * c)

        // Identities and zero
        assertEquals(a, a + zero)
        assertEquals(a, a * one)
        assertEquals(ratio("0", "1"), a * zero)
    }

    @Test
    fun inversesReciprocalsAndCancellation() {
        val a = ratio("-7", "9")
        val b = ratio("10", "11")
        val c = ratio("5", "12")

        // Additive inverse
        assertEquals(ratio("0", "1"), a + (-a))

        // Division by self (a != 0)
        assertEquals(one, b / b)

        // Reciprocal identity 1/a * a == 1
        assertEquals(one, (one / b) * b)

        // Cancellation (c != 0)
        assertEquals(a / b, (a * c) / (b * c))
    }

    @Test
    fun equalityNormalizationAndComparisonTransitivity() {
        // Normalization equality
        assertEquals(ratio("1", "2"), ratio("2", "4"))
        assertEquals(ratio("-1", "2"), ratio("1", "-2"))

        // Comparison transitivity: a < b < c => a < c
        val a = ratio("1", "3")
        val b = ratio("1", "2")
        val c = ratio("2", "3")
        assertTrue(a < b && b < c && a < c)
    }
}
