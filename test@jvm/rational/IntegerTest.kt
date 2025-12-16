package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.utils.absolute
import com.zhelenskiy.infinite.numbers.utils.minus
import com.zhelenskiy.infinite.numbers.utils.mod
import com.zhelenskiy.infinite.numbers.utils.modQuotient
import com.zhelenskiy.infinite.numbers.utils.parseString
import com.zhelenskiy.infinite.numbers.utils.plus
import com.zhelenskiy.infinite.numbers.utils.rem
import com.zhelenskiy.infinite.numbers.utils.remQuotient
import com.zhelenskiy.infinite.numbers.utils.sign
import com.zhelenskiy.infinite.numbers.utils.square
import com.zhelenskiy.infinite.numbers.utils.times
import com.zhelenskiy.infinite.numbers.utils.toString
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegerTest {
    @Test
    fun factoriesAndConstants() {
        assertEquals(IntegerNumber.parseString("0"), zero)
        assertEquals(IntegerNumber.parseString("1"), one)
    }

    @Test
    fun parseAndToStringRadix() {
        assertEquals(IntegerNumber.parseString("255"), IntegerNumber.parseString("ff", 16))
        assertEquals("ff", IntegerNumber.parseString("255").toString(16))
        assertEquals("-1010", IntegerNumber.parseString("-10").toString(2))
    }

    @Test
    fun basicArithmetic() {
        val a = IntegerNumber.parseString("20")
        val b = IntegerNumber.parseString("6")

        assertEquals(IntegerNumber.parseString("26"), a + b)
        assertEquals(IntegerNumber.parseString("14"), a - b)
        assertEquals(IntegerNumber.parseString("120"), a * b)
        assertEquals(IntegerNumber.parseString("2"), a % b)
        assertEquals(IntegerNumber.parseString("2"), a mod b)
        assertEquals(IntegerNumber.parseString("3"), a remQuotient b)
        assertEquals(IntegerNumber.parseString("3"), a modQuotient b)
    }

    @Test
    fun unaryIncDecSignAbsSquare() {
        var x = IntegerNumber.parseString("-5")
        assertEquals(-1, x.sign)
        assertEquals(IntegerNumber.parseString("5"), x.absolute())
        assertEquals(IntegerNumber.parseString("25"), x.square())

        assertEquals(IntegerNumber.parseString("-5"), +x)
        assertEquals(IntegerNumber.parseString("5"), -x)

        x++
        assertEquals(IntegerNumber.parseString("-4"), x)
        x--
        assertEquals(IntegerNumber.parseString("-5"), x)
    }

    @Test
    fun mixedWithKotlinPrimitives() {
        val base = IntegerNumber.parseString("10")
        assertEquals(IntegerNumber.parseString("13"), base + 3)
        assertEquals(IntegerNumber.parseString("7"), base - 3)
        assertEquals(IntegerNumber.parseString("30"), base * 3)
        assertEquals(IntegerNumber.parseString("1"), base % 3)
        assertEquals(IntegerNumber.parseString("1"), base mod 3)

        // Commutative overloads
        assertEquals(IntegerNumber.parseString("13"), 3 + base)
        assertEquals(IntegerNumber.parseString("-7"), 3 - base)
        assertEquals(IntegerNumber.parseString("30"), 3 * base)
        assertEquals(IntegerNumber.parseString("3"), 33 % base)
        assertEquals(IntegerNumber.parseString("3"), 33 mod base)

        // Unsigned small primitives
        val ub: UByte = 2u
        val us: UShort = 4u
        assertEquals(IntegerNumber.parseString("12"), base + ub)
        assertEquals(IntegerNumber.parseString("6"), base - us)
        assertEquals(IntegerNumber.parseString("20"), ub * base)
    }
}
