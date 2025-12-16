package real

import com.zhelenskiy.infinite.numbers.AnyBounds
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.binarySearch
import com.zhelenskiy.infinite.numbers.utils.pow
import com.zhelenskiy.infinite.numbers.utils.times as trueTimes
import com.zhelenskiy.infinite.numbers.utils.realNumberApproximateComparator

class BinarySearchTest : AbstractSearchTest<RationalNumber>() {
    context(cache: EvaluationCache)
    override fun searchImpl(
        expected: RationalNumber, bounds: AnyBounds, f: (RationalNumber) -> RationalNumber
    ): RealNumber? = binarySearch(expected, bounds, f)

    context(cache: EvaluationCache)
    override fun searchImpl(expected: RationalNumber, f: (RationalNumber) -> RationalNumber): RealNumber? =
        binarySearch(expected, f = f)

    override fun RationalNumber.convert(): RationalNumber = this

    context(cache: EvaluationCache)
    override fun Int.times(x: RationalNumber): RationalNumber = this.trueTimes(x)
}

class BinarySearchInComparatorContextTest : AbstractSearchTest<RationalNumber>() {
    context(cache: EvaluationCache)
    override fun searchImpl(
        expected: RationalNumber, bounds: AnyBounds, f: (RationalNumber) -> RationalNumber
    ): RealNumber? = context(realNumberApproximateComparator(ten.pow(-5))) {
        binarySearch(expected, bounds, f)
    }

    context(cache: EvaluationCache)
    override fun searchImpl(expected: RationalNumber, f: (RationalNumber) -> RationalNumber): RealNumber? =
        context(realNumberApproximateComparator(ten.pow(-5))) {
            binarySearch(expected, f = f)
        }

    override fun RationalNumber.convert(): RationalNumber = this

    context(cache: EvaluationCache)
    override fun Int.times(x: RationalNumber): RationalNumber = this.trueTimes(x)
}
