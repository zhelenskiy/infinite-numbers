package real

import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.*
import com.zhelenskiy.infinite.numbers.utils.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RealNumberExtensionsTest : AbstractRealNumberTest() {

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun arithmeticWithPrimitiveInterop() = testImpl {
        val a: RealNumber = two

        assertEquals(RationalNumber("5"), (a + 3))
        assertEquals(-one, (a - 3))
        assertEquals(RationalNumber("6"), (a * 3))
        assertEquals(one, (a / 2))

        assertEquals(RationalNumber("5"), (3 + a))
        assertEquals(one, (3 - a))
        assertEquals(RationalNumber("6"), (3 * a))
        assertEquals(RationalNumber("3"), (6 / a))
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun signAndAbsolute() = testImpl {
        val neg: RealNumber = RationalNumber("-5")
        val pos: RealNumber = RationalNumber("7")
        val z: RealNumber = zero

        assertEquals(-1, neg.sign)
        assertEquals(1, pos.sign)
        assertEquals(0, z.sign)

        assertEquals(RationalNumber("5"), neg.absolute())
        assertEquals(RationalNumber("7"), pos.absolute())
        assertEquals(zero, z.absolute())
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun rationalRootAndPow() = testImpl {
        assertEquals(RationalNumber("3"), RationalNumber("9").root(two))
        assertEquals(RationalNumber("3"), RationalNumber("27").root(IntegerNumber(3)))
        assertEquals(one, one.root(IntegerNumber(3)))
        assertEquals(one, one.root(one))
        assertEquals(RationalNumber("0.5"), RationalNumber("0.25").root(two))

        assertEquals(RationalNumber("3"), RationalNumber("9").pow(RationalNumber("1/2")))
        assertEquals(RationalNumber("4"), RationalNumber("8").pow(RationalNumber("2/3")))
        assertEquals(RationalNumber("2.08008"), RationalNumber("3").pow(RationalNumber("2/3")))

        assertEquals(RationalNumber("1.25992"), two.root(IntegerNumber(3)))
        assertEquals(RationalNumber("-1.25992"), (-two).root(IntegerNumber(3)))

        assertFailsWith<IllegalArgumentException> { one.root(-one) }
        assertFailsWith<IllegalArgumentException> { (-one).root(two) }
        assertFailsWith<IllegalArgumentException> { RationalNumber("4").root(zero) }
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun realPowerNonNegativeConstraintsAndResult() = testImpl {
        val base: RealNumber = two
        val exponent: RealNumber = RationalNumber("3")
        val half = RationalNumber("0.5")
        assertEquals(RationalNumber("8"), base.pow(exponent))

        assertEquals(half, base.pow(-one))
        assertEquals(half, base.pow(-one as RealNumber))
        assertEquals(IntegerNumber(4), (-base).pow(two))
        assertEquals(IntegerNumber(4), (-base).pow(two as RealNumber))

        assertFailsWith<IllegalArgumentException> { pi.pow(-pi) }
        assertFailsWith<IllegalArgumentException> { (-pi).pow(pi) }
        assertFailsWith<IllegalArgumentException> { (-pi).pow(-pi) }
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun naturalLogAndLog() = testImpl(precision = 2) {
        val hundred = RationalNumber("100")
        assertEquals(two / 3, log(hundred, IntegerNumber(1000)).roundToRationalNumber(fractionDigitsCount = IntegerNumber(3)))
        assertEquals(two, log(hundred, ten).roundToRationalNumber(fractionDigitsCount = IntegerNumber(3)))
        assertEquals(-two, log(1 / hundred, ten).roundToRationalNumber(fractionDigitsCount = two))

        assertEquals(one, ln(e).roundToRationalNumber(fractionDigitsCount = one))
        assertEquals(two, ln(e * e).roundToRationalNumber(fractionDigitsCount = one))
        assertEquals(-one, ln(1 / e).roundToRationalNumber(fractionDigitsCount = one))
    }
}
