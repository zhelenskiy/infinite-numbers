package rational

import com.zhelenskiy.infinite.numbers.rational.*
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegerTest {
    @Test
    fun factoriesAndConstants() {
        assertEquals(Integer.parseString("0"), Integer.zero)
        assertEquals(Integer.parseString("1"), Integer.one)
    }

    @Test
    fun parseAndToStringRadix() {
        assertEquals(Integer.parseString("255"), Integer.parseString("ff", 16))
        assertEquals("ff", Integer.parseString("255").toString(16))
        assertEquals("-1010", Integer.parseString("-10").toString(2))
    }

    @Test
    fun basicArithmetic() {
        val a = Integer.parseString("20")
        val b = Integer.parseString("6")

        assertEquals(Integer.parseString("26"), a + b)
        assertEquals(Integer.parseString("14"), a - b)
        assertEquals(Integer.parseString("120"), a * b)
        assertEquals(Integer.parseString("2"), a % b)
        assertEquals(Integer.parseString("2"), a mod b)
        assertEquals(Integer.parseString("3"), a remQuotient b)
        assertEquals(Integer.parseString("3"), a modQuotient b)
    }

    @Test
    fun unaryIncDecSignAbsSquare() {
        var x = Integer.parseString("-5")
        assertEquals(-1, x.sign)
        assertEquals(Integer.parseString("5"), x.absolute())
        assertEquals(Integer.parseString("25"), x.square())

        assertEquals(Integer.parseString("-5"), +x)
        assertEquals(Integer.parseString("5"), -x)

        x++
        assertEquals(Integer.parseString("-4"), x)
        x--
        assertEquals(Integer.parseString("-5"), x)
    }

    @Test
    fun mixedWithKotlinPrimitives() {
        val base = Integer.parseString("10")
        assertEquals(Integer.parseString("13"), base + 3)
        assertEquals(Integer.parseString("7"), base - 3)
        assertEquals(Integer.parseString("30"), base * 3)
        assertEquals(Integer.parseString("1"), base % 3)
        assertEquals(Integer.parseString("1"), base mod 3)

        // Commutative overloads
        assertEquals(Integer.parseString("13"), 3 + base)
        assertEquals(Integer.parseString("-7"), 3 - base)
        assertEquals(Integer.parseString("30"), 3 * base)
        assertEquals(Integer.parseString("3"), 33 % base)
        assertEquals(Integer.parseString("3"), 33 mod base)

        // Unsigned small primitives
        val ub: UByte = 2u
        val us: UShort = 4u
        assertEquals(Integer.parseString("12"), base + ub)
        assertEquals(Integer.parseString("6"), base - us)
        assertEquals(Integer.parseString("20"), ub * base)
    }
}
