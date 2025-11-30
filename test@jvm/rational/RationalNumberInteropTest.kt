package rational

import com.zhelenskiy.infinite.numbers.rational.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RationalNumberInteropTest {
    private fun int(s: String) = Integer.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun mixedWithIntegersLeftAndRight() {
        val r = ratio("3", "4")

        // Right primitives
        assertEquals("7/4", (r + 1).toString())
        assertEquals("-1/4", (r - 1).toString())
        assertEquals("3/2", (r * 2).toString())
        assertEquals("3/8", (r / 2).toString())

        // Left primitives
        assertEquals("7/4", (1 + r).toString())
        assertEquals("1/4", (1 - r).toString())
        assertEquals("3/2", (2 * r).toString())
        assertEquals("4/3", (1 / r).toString())
    }

    @Test
    fun mixedWithAllSmallPrimitives() {
        val r = ratio("5", "6")

        val ub: UByte = 2u
        val us: UShort = 3u
        val b: Byte = 2
        val s: Short = 3
        val i: Int = 4
        val l: Long = 5

        assertEquals("17/6", (r + ub).toString())
        assertEquals("17/6", (r + b).toString())
        assertEquals("23/6", (r + us).toString())
        assertEquals("23/6", (r + s).toString())
        assertEquals("29/6", (r + i).toString())
        assertEquals("35/6", (r + l).toString())

        assertEquals("7/6", (ub - r).toString())
        assertEquals("7/6", (b - r).toString())
        assertEquals("13/6", (us - r).toString())
        assertEquals("13/6", (s - r).toString())
        assertEquals("19/6", (i - r).toString())
        assertEquals("25/6", (l - r).toString())
    }

    @Test
    fun divisionIdentitiesAndErrors() {
        val r = ratio("-7", "5")
        assertEquals(r.toString(), (r / Integer.one).toString())
        assertEquals((-r).toString(), (r / int("-1")).toString())

        assertFailsWith<IllegalArgumentException> { r / Integer.zero }
    }
}
