package real

import com.zhelenskiy.infinite.numbers.AnyBounds
import com.zhelenskiy.infinite.numbers.Border.Exclusive
import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.Border.Infinity
import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.times
import kotlin.test.Test

abstract class AbstractSearchTest<T : RealNumber> : AbstractRealNumberTest() {
    context(cache: EvaluationCache)
    abstract fun searchImpl(expected: T, bounds: AnyBounds, f: (RationalNumber) -> T): RealNumber?

    context(cache: EvaluationCache)
    abstract fun searchImpl(expected: T, f: (RationalNumber) -> T): RealNumber?

    context(cache: EvaluationCache)
    abstract operator fun Int.times(x: T): T

    abstract fun RationalNumber.convert(): T

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun searchSegment() = testImpl {
        for (sign in listOf(1, -1)) {
            val five = RationalNumber("5")

            val f: (RationalNumber) -> T = { x -> (sign * x * x).convert() }
            val bounds = Inclusive(zero)..Inclusive(ten)

            assertEquals(
                expected = zero,
                actual = searchImpl(zero.convert(), bounds, f)!!
            )
            assertEquals(
                expected = five,
                actual = searchImpl((sign * RationalNumber("25")).convert(), bounds, f)!!
            )
            assertEquals(
                expected = ten,
                actual = searchImpl((sign * RationalNumber("100")).convert(), bounds, f)!!
            )
            assertEquals(
                expected = ten,
                actual = searchImpl((sign * RationalNumber("100")).convert(), bounds, f)!!
            )
            assertEquals(
                expected = RationalNumber("1.73205"),
                actual = searchImpl((sign * RationalNumber("3")).convert(), bounds, f)
            )

            // not found within bounds -> null
            val otherBounds = Inclusive(two)..Inclusive(ten)
            assertEquals(
                expected = null,
                actual = searchImpl((sign * one).convert(), otherBounds, f)
            )
            assertEquals(
                expected = null,
                actual = searchImpl((sign * IntegerNumber(1000)).convert(), otherBounds, f)
            )
        }
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun searchInfiniteBounds() = testImpl {
        val bounds = -Infinity..+Infinity
        val root2 = RationalNumber("1.25992")
        val samples = listOf(
            zero to zero,
            RationalNumber("0.1") to RationalNumber("0.001"),
            one to one,
            root2 to two,
            two to RationalNumber("8"),
            RationalNumber("10") to RationalNumber("1000"),
        )
        val cube = { x: RationalNumber -> (x * x * x).convert() }
        val minusCube = { x: RationalNumber -> (-x * x * x).convert() }
        for ((argument, result) in samples) {
            assertEquals(
                expected = argument,
                actual = searchImpl(result.convert(), bounds, cube)
            )
            assertEquals(
                expected = -argument,
                actual = searchImpl((-result).convert(), bounds, cube)
            )
            assertEquals(
                expected = -argument,
                actual = searchImpl(result.convert(), bounds, minusCube)
            )
            assertEquals(
                expected = argument,
                actual = searchImpl((-result).convert(), bounds, minusCube)
            )
            assertEquals(
                expected = argument,
                actual = searchImpl(result.convert(), cube)
            )
            assertEquals(
                expected = -argument,
                actual = searchImpl((-result).convert(), cube)
            )
            assertEquals(
                expected = -argument,
                actual = searchImpl(result.convert(), minusCube)
            )
            assertEquals(
                expected = argument,
                actual = searchImpl((-result).convert(), minusCube)
            )
        }
    }


    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun searchInclusiveToInfiniteBounds() = testImpl {
        for (isInclusive in listOf(true, false)) {
            val samples = listOfNotNull(
                RationalNumber("1.41421") to two,
                (zero to zero).takeIf { isInclusive },
                one to one,
                RationalNumber("10") to RationalNumber("100"),
                RationalNumber("0.1") to RationalNumber("0.01"),
                (null to -one).takeIf { isInclusive },
            )
            for (resultSign in listOf(1, -1)) {
                for (argumentSign in listOf(1, -1)) {
                    val makeBorder = if (isInclusive) ::Inclusive else Exclusive::Regular
                    val bounds =
                        if (argumentSign == 1) makeBorder(zero)..+Infinity
                        else -Infinity..makeBorder(zero)
                    for ((argument, result) in samples) {
                        assertEquals(
                            expected = if (argument != null) argumentSign * argument else null,
                            actual = searchImpl(
                                expected = (resultSign * result).convert(),
                                bounds = bounds,
                                f = { x: RationalNumber -> (resultSign * x * x).convert() }
                            )
                        )
                    }
                }
            }
        }
    }
}
