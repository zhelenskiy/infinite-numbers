package real

import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.floorToRationalNumber
import com.zhelenskiy.infinite.numbers.utils.roundToRationalNumber
import com.zhelenskiy.infinite.numbers.utils.ceilingToRationalNumber
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.div
import com.zhelenskiy.infinite.numbers.utils.limit
import com.zhelenskiy.infinite.numbers.utils.minus
import com.zhelenskiy.infinite.numbers.utils.naturalSequence
import com.zhelenskiy.infinite.numbers.utils.pi
import com.zhelenskiy.infinite.numbers.utils.plus
import kotlin.test.Test

@OptIn(PotentiallyInfiniteRealNumberApi::class)
class RoundFloorCeilTest : AbstractRealNumberTest() {

    private val fluctuatingSequence = naturalSequence
        .map { (-one).pow(it) / ten.pow(it) }
        .limit { one / ten.pow(it) }

    @Test
    fun integersFloorRoundCeilToInteger() = testImpl {
        // positive integer
        val five = IntegerNumber(5)
        assertEquals(five, five.floorToRationalNumber(ten, zero))
        assertEquals(five, five.roundToRationalNumber(ten, zero))
        assertEquals(five, five.ceilingToRationalNumber(ten, zero))

        // negative integer
        val minusThree = IntegerNumber(-3)
        assertEquals(minusThree, minusThree.floorToRationalNumber(ten, zero))
        assertEquals(minusThree, minusThree.roundToRationalNumber(ten, zero))
        assertEquals(minusThree, minusThree.ceilingToRationalNumber(ten, zero))
    }

    @Test
    fun rationalsFloorRoundCeilToInteger() = testImpl {
        // 3/2 = 1.5
        val threeOverTwo = IntegerNumber(3) / two
        assertEquals(one, threeOverTwo.floorToRationalNumber(ten, zero))
        assertEquals(two, threeOverTwo.roundToRationalNumber(ten, zero))
        assertEquals(two, threeOverTwo.ceilingToRationalNumber(ten, zero))

        // -3/2 = -1.5
        val minusThreeOverTwo = RationalNumber(IntegerNumber(-3), two)
        assertEquals(IntegerNumber(-2), minusThreeOverTwo.floorToRationalNumber(ten, zero))
        // Rounding rule in RealNumber.roundToRationalNumber: ties (digit*2 >= radix) go to upperBound.
        // For negatives upperBound is toward zero, so -1.5 rounds to -1.
        assertEquals(-one, minusThreeOverTwo.roundToRationalNumber(ten, zero))
        assertEquals(-one, minusThreeOverTwo.ceilingToRationalNumber(ten, zero))
    }

    @Test
    fun realsFloorRoundCeilToInteger() = testImpl {
        // Using built-in pi real number
        assertEquals(IntegerNumber(3), pi.floorToRationalNumber(ten, zero))
        assertEquals(IntegerNumber(3), pi.roundToRationalNumber(ten, zero))
        assertEquals(IntegerNumber(4), pi.ceilingToRationalNumber(ten, zero))
    }

    @Test
    fun realsRoundFloorCeilWithFractionDigits() = testImpl(precision = 2) {
        // With comparator precision set to 2, pi should be ~ 3.14
        val expected = RationalNumber("3.14")
        // roundToRationalNumber() with comp context
        assertEquals(expected, pi.roundToRationalNumber())
        // floor/ceil with explicit digits = 2
        val twoDigits = two
        assertEquals(expected, pi.floorToRationalNumber(ten, twoDigits))
        assertEquals(RationalNumber("3.15"), pi.ceilingToRationalNumber(ten, twoDigits))
    }

    @Test
    fun fluctuatingSequence() = testImpl {
        for (n in listOf(-one, zero, one)) {
            assertEquals(n, (n + fluctuatingSequence).roundToRationalNumber(fractionDigitsCount = zero))
            val half = one / 2
            assertEquals(n, (n + fluctuatingSequence + half).floorToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n, (n - (fluctuatingSequence + half)).ceilingToRationalNumber(fractionDigitsCount = zero))

            assertEquals(n, n.roundToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n, n.floorToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n, n.ceilingToRationalNumber(fractionDigitsCount = zero))

            assertEquals(n + 1, (n + half).roundToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n, (n + half).floorToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n + 1, (n + half).ceilingToRationalNumber(fractionDigitsCount = zero))

            assertEquals(n, (n - half).roundToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n - 1, (n - half).floorToRationalNumber(fractionDigitsCount = zero))
            assertEquals(n, (n - half).ceilingToRationalNumber(fractionDigitsCount = zero))
        }
    }
}
