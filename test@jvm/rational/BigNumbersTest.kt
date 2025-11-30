package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BigNumbersTest {
    private fun int(s: String) = Integer.parseString(s)

    @Test
    fun bigIntegerRoundTripAndArithmetic() {
        val bigAStr = "9".repeat(200) // 200-digit number
        val bigBStr = "1" + "0".repeat(150) // 10^150
        val bigA = int(bigAStr)
        val bigB = int(bigBStr)

        // Round-trip
        assertEquals(bigAStr, bigA.toString())
        assertEquals(bigBStr, bigB.toString())

        // Addition smoke
        val sum = bigA + bigB
        assertTrue(sum > bigA && sum > bigB)

        // Multiplication + division identity: (A * 10^150) / 10^150 == A
        val prod = bigA * bigB
        val back = prod remQuotient bigB
        assertEquals(bigA, back)
    }

    @Test
    fun largeRationalReductionAndOps() {
        val g = int("1000")
        val num = int("123456789012345678901234567890") * g
        val den = int("987654321098765432109876543210") * g

        val r = RationalNumber(num, den)
        // After reduction by gcd=g, the result should equal num/g over den/g
        val expected = RationalNumber(num remQuotient g, den remQuotient g)
        assertEquals(expected, r)

        // Simple arithmetic remains consistent
        val doubled = r + r
        assertEquals(expected * Integer(2), doubled)

        // Formatting smoke in base 10
        val s = r.toString(10, FractionFormat.DIVISION)
        assertTrue('/' in s)
    }
}
