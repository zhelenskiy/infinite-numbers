package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.utils.div
import com.zhelenskiy.infinite.numbers.utils.minus
import com.zhelenskiy.infinite.numbers.utils.parseString
import com.zhelenskiy.infinite.numbers.utils.plus
import com.zhelenskiy.infinite.numbers.utils.square
import com.zhelenskiy.infinite.numbers.utils.times
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RationalNumberAllMethodsTest {
    private fun int(s: String) = IntegerNumber.parseString(s)
    private fun ratio(n: String, d: String): RationalNumber = RationalNumber(int(n), int(d))

    @Test
    fun mixedFormatMixedZeroIntegerPartAndOtherRadices() {
        val half = ratio("1", "2")
        // In MIXED format when integer part is zero, no leading zero should be printed
        assertEquals("1/2", half.toString(FractionFormat.MIXED, 10))

        // Base 2 repeating and terminating examples
        assertEquals("0.(01)", ratio("1","3").toString(FractionFormat.DOT, 2))
        assertEquals("1.1", ratio("3","2").toString(FractionFormat.DOT, 2))
    }

    @Test
    fun partsForVariousRanges() {
        val a = ratio("7","3")
        assertEquals(int("2"), a.integerPart())
        assertEquals("1/3", a.fractionalPart().toString())

        val b = ratio("1","4")
        assertEquals(int("0"), b.integerPart())
        assertEquals("1/4", b.fractionalPart().toString())

        val c = ratio("-7","3")
        assertEquals(int("-2"), c.integerPart())
        assertEquals("-1/3", c.fractionalPart().toString())
    }

    @Test
    fun primitiveOverloadsAllOpsBothSides() {
        val r = ratio("5","6")
        val ub: UByte = 2u
        val us: UShort = 3u
        val b: Byte = 2
        val s: Short = 3
        val i: Int = 4
        val l: Long = 5

        // plus
        assertEquals("17/6", (r + ub).toString())
        assertEquals("17/6", (ub + r).toString())
        assertEquals("23/6", (r + s).toString())
        assertEquals("29/6", (i + r).toString())
        assertEquals("35/6", (l + r).toString())

        // minus (both sides)
        assertEquals("-7/6", (r - b).toString())
        assertEquals("7/6", (b - r).toString())
        assertEquals("-13/6", (r - s).toString())
        assertEquals("19/6", (i - r).toString())

        // times
        assertEquals("5/3", (r * ub).toString())
        assertEquals("5/3", (ub * r).toString())
        assertEquals("5/2", (r * s).toString())
        assertEquals("10/3", (i * r).toString())

        // div
        assertEquals("5/12", (r / ub).toString())
        assertEquals("12/5", (ub / r).toString())
        assertEquals("5/18", (r / us).toString())
        assertEquals("24/5", (i / r).toString())
    }

    @Test
    fun compareToAndCommonDenominatorVariants() {
        val a = ratio("1","4")
        val b = ratio("1","6")
        assertTrue(a > b)
        assertEquals(int("12"), a commonDenominatorWith b)

        val c = ratio("2","3")
        val d = ratio("4","6")
        assertEquals(0, c.compareTo(d))
    }

    @Test
    fun squarePowAndAbsoluteNegativeExponent() {
        val r = ratio("-5","2")
        assertEquals("25/4", r.square().toString())

        // Integer.pow with negative exponent returns a RationalNumber (1 / pow(abs(n)))
        assertEquals(ratio("1","8"), IntegerNumber.parseString("2").pow(IntegerNumber.parseString("-3")))
        assertEquals(ratio("1","1"), one.pow(zero))
    }

    @Test
    fun divisionByZeroThrows() {
        val a = ratio("1","2")
        assertFailsWith<IllegalArgumentException> { a / zero }
    }
}
