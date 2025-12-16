package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.mod
import com.zhelenskiy.infinite.numbers.utils.modQuotient
import com.zhelenskiy.infinite.numbers.utils.parseString
import com.zhelenskiy.infinite.numbers.utils.rem
import com.zhelenskiy.infinite.numbers.utils.remQuotient
import com.zhelenskiy.infinite.numbers.utils.times
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegerAllMethodsTest {
    private fun int(s: String) = IntegerNumber.parseString(s)

    @Test
    fun constructorsForAllPrimitives() {
        // Signed
        assertEquals(int("-128"), IntegerNumber((-128).toByte()))
        assertEquals(int("32767"), IntegerNumber(32767.toShort()))
        assertEquals(int("2147483647"), IntegerNumber(Int.MAX_VALUE))
        assertEquals(int("-9223372036854775808"), IntegerNumber(Long.MIN_VALUE))

        // Unsigned
        val ub: UByte = 255u
        val us: UShort = 65535u
        val ui: UInt = 4000000000u
        val ul: ULong = 18446744073709551615u

        assertEquals(int("255"), IntegerNumber(ub))
        assertEquals(int("65535"), IntegerNumber(us))
        assertEquals(int("4000000000"), IntegerNumber(ui))
        assertEquals(int("18446744073709551615"), IntegerNumber(ul))
    }

    @Test
    fun unaryPlusAndCompareToWithRational() {
        val a = int("42")
        val b = +a
        assertEquals(int("42"), b)

        // Compare Integer with non-integer RationalNumber
        val r = RationalNumber(int("21"), int("1"))
        assertEquals(1, a.compareTo(r)) // 42 > 21
        val eq = RationalNumber(int("84"), int("2")) // equals 42
        assertEquals(0, a.compareTo(eq))
    }

    @Test
    fun remainderAndModWithPrimitiveQuotients() {
        val a = int("27")

        // rem/mod with primitives on the right
        assertEquals(int("1"), a % 13)
        assertEquals(int("1"), a mod 13)

        // remQuotient/modQuotient with primitive U* and signed
        val q1 = a remQuotient 4
        val q2 = a remQuotient 4u
        assertEquals(int("6"), q1)
        assertEquals(int("6"), q2)

        val mq1 = a modQuotient 4
        val mq2 = a modQuotient 4u
        assertEquals(int("6"), mq1)
        assertEquals(int("6"), mq2)

        // Left primitives
        assertEquals(int("1"), 27 % int("13"))
        assertEquals(int("1"), 27 mod int("13"))
    }

    @Test
    fun primitiveMultiplicationAndMixedSignRemainders() {
        val x = int("-20")
        val y = int("6")
        assertEquals(int("-120"), x * 6)
        assertEquals(int("-120"), 6 * x)

        // Check identities already covered elsewhere, but ensure mod and rem exist with negatives
        val r1 = x mod y
        val r2 = x % y
        val q1 = x modQuotient y
        val q2 = x remQuotient y
        assertEquals(x, q1 * y + r1)
        assertEquals(x, q2 * y + r2)
    }
}
