package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.utils.parseString
import com.zhelenskiy.infinite.numbers.utils.sign
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RationalNumberOrderingTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun normalizationEqualityLargePairs() {
        // Equal after reduction
        assertEquals(ratio("1", "2"), ratio("2", "4"))
        assertEquals(ratio("-3", "7"), ratio("6", "-14"))

        // Larger reducible pair
        val a = ratio("1234567890", "2469135780")
        val b = ratio("1", "2")
        assertEquals(b, a)
    }

    @Test
    fun transitivityAndAntisymmetry() {
        val a = ratio("1", "3")
        val b = ratio("1", "2")
        val c = ratio("2", "3")

        assertTrue(a < b && b < c)
        assertTrue(a < c) // transitivity
        assertFalse(b < a) // antisymmetry
    }

    @Test
    fun compareSignMatchesDifferenceNumeratorSign() {
        val xs = listOf(ratio("1","4"), ratio("2","5"), ratio("-3","7"), ratio("5","3"))
        for (i in xs.indices) {
            for (j in xs.indices) {
                val a = xs[i]
                val b = xs[j]
                val cmp = a.compareTo(b).coerceIn(-1, 1)
                val diffSign = (a - b).numerator.sign.coerceIn(-1, 1)
                assertEquals(cmp, diffSign)
            }
        }
    }
}
