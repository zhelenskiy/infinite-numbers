package real

import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.rational.invoke
import com.zhelenskiy.infinite.numbers.utils.*
import java.io.File
import kotlin.io.path.absolute
import kotlin.io.path.div
import kotlin.io.path.readText
import kotlin.test.Test

public class SeriesTest : AbstractRealNumberTest() {

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun finiteSequence() = testImpl {
        val series = sequenceOf(one)
        assertEquals(series.limit { zero }, one)
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun constantSequence() = testImpl {
        val series = generateSequence<RationalNumber>(one) { it }
        assertEquals(one, series.limit { zero })
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun sequence() = testImpl {
        val series = generateSequence<RationalNumber>(one) { it / 2 }
        assertEquals(zero, series.limit { two.pow(-it) })
        assertEquals(two, series.sum { two.pow(-it) })
        assertEquals(zero, series.product { two.pow(-it) })
        val piSeries = series.map { it * pi }
        assertEquals(zero, piSeries.limit { two.pow(-it) * 4 })
        assertEquals(2 * pi, piSeries.sum { two.pow(-it) * 4 })
        assertEquals(zero, piSeries.product { two.pow(-it) * 4 })
    }

    val resourcePath = File("").toPath().absolute() / "testResources"
    val realPi by lazy { (resourcePath / "piDigits.txt").readText() }
    val realE by lazy { (resourcePath / "eDigits.txt").readText() }


    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun pi() = testImpl {
        assertEquals(RationalNumber("3.14159"), pi)
        testPositiveConstant(realPi, pi)
    }

    @PotentiallyInfiniteRealNumberApi
    context(_: Comparator<RealNumber>, _: EvaluationCache)
    fun testPositiveConstant(expectedDecimalString: String, number: RealNumber) {
        val fractionalDigits = expectedDecimalString.substringAfter('.', missingDelimiterValue = "")
        val otherDigits = expectedDecimalString.dropLast(fractionalDigits.length)
        for (digitNumber in 0..fractionalDigits.length) {
            val expectedRational = RationalNumber("$otherDigits${fractionalDigits.take(digitNumber)}")
            val actualRational = number.floorToRationalNumber(fractionDigitsCount = IntegerNumber(digitNumber))
            assertEquals(expectedRational, actualRational)
        }
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun e() = testImpl {
        assertEquals(RationalNumber("2.71828"), e)
        testPositiveConstant(realE, e)
    }

    @OptIn(PotentiallyInfiniteRealNumberApi::class)
    @Test
    fun ePowE() = testImpl {
        val ePowE = limit(e) { it.pow(it) }
        assertEquals(RationalNumber("15.15"), ePowE.roundToRationalNumber(fractionDigitsCount = two))
    }
}
