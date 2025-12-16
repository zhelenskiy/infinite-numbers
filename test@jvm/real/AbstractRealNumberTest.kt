package real

import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.cache.SimpleEvaluationCache
import com.zhelenskiy.infinite.numbers.rational.FractionFormat
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.ceilingToRationalNumber
import com.zhelenskiy.infinite.numbers.utils.floorToRationalNumber
import com.zhelenskiy.infinite.numbers.utils.roundToRationalNumber
import com.zhelenskiy.infinite.numbers.utils.invoke
import com.zhelenskiy.infinite.numbers.utils.pow
import com.zhelenskiy.infinite.numbers.utils.realNumberApproximateComparator
import java.util.Comparator.nullsFirst
import kotlin.test.assertEquals

abstract class AbstractRealNumberTest {
    val defaultFractionDigitsCount = 5
    val defaultRadix = 10

    @PotentiallyInfiniteRealNumberApi
    context(cache: EvaluationCache)
    fun RealNumber.roundToRationalNumber() = roundToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    @PotentiallyInfiniteRealNumberApi
    context(cache: EvaluationCache)
    fun RealNumber.floorToRationalNumber() = floorToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    @PotentiallyInfiniteRealNumberApi
    context(cache: EvaluationCache)
    fun RealNumber.ceilingToRationalNumber() = ceilingToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    context(cache: EvaluationCache)
    fun RationalNumber.roundToRationalNumber() = roundToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    context(cache: EvaluationCache)
    fun RationalNumber.floorToRationalNumber() = floorToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    context(cache: EvaluationCache)
    fun RationalNumber.ceilingToRationalNumber() = ceilingToRationalNumber(
        radix = IntegerNumber(defaultRadix),
        fractionDigitsCount = IntegerNumber(defaultFractionDigitsCount),
    )

    protected fun testImpl(
        precision: Int = defaultFractionDigitsCount,
        body: context(Comparator<RealNumber>, EvaluationCache) () -> Unit
    ) = context(SimpleEvaluationCache()) {
        val numberApproximateComparator = realNumberApproximateComparator(
            IntegerNumber(defaultRadix).pow(-precision)
        )
        context(numberApproximateComparator) { body() }
    }

    @PotentiallyInfiniteRealNumberApi
    context(comp: Comparator<RealNumber>, _: EvaluationCache)
    protected fun assertEquals(expected: RealNumber?, actual: RealNumber?) {
        val expectedRational = expected?.roundToRationalNumber()?.toString(fractionFormat = FractionFormat.DOT)
        val actualRational = actual?.roundToRationalNumber()?.toString(fractionFormat = FractionFormat.DOT)
        assertEquals(nullsFirst(comp).compare(expected, actual), 0, "$expectedRational != $actualRational")
    }

    context(comp: Comparator<RealNumber>, _: EvaluationCache)
    protected fun assertEquals(expected: RationalNumber?, actual: RationalNumber?) {
        val expectedRational = expected?.roundToRationalNumber()?.toString(fractionFormat = FractionFormat.DOT)
        val actualRational = actual?.roundToRationalNumber()?.toString(fractionFormat = FractionFormat.DOT)
        assertEquals(nullsFirst(comp).compare(expected, actual), 0, "$expectedRational != $actualRational")
    }
}
