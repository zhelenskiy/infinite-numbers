package real

import com.zhelenskiy.infinite.numbers.AnyBounds
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.ten
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.pow
import com.zhelenskiy.infinite.numbers.utils.realNumberApproximateComparator
import com.zhelenskiy.infinite.numbers.utils.reverse
import com.zhelenskiy.infinite.numbers.utils.times as trueTimes


class ReverseFunctionSearchTest : AbstractSearchTest<RealNumber>() {

    context(cache: EvaluationCache)
    private val numberApproximateComparator
        get() = realNumberApproximateComparator(ten.pow(-20))

    context(cache: EvaluationCache)
    override fun searchImpl(
        expected: RealNumber, bounds: AnyBounds, f: (RationalNumber) -> RealNumber
    ): RealNumber? = context(numberApproximateComparator) { f.reverse(bounds)(expected) }

    context(cache: EvaluationCache)
    override fun searchImpl(expected: RealNumber, f: (RationalNumber) -> RealNumber): RealNumber? =
        context(numberApproximateComparator) { f.reverse()(expected) }

    override fun RationalNumber.convert(): RationalNumber = this

    context(cache: EvaluationCache)
    override fun Int.times(x: RealNumber): RealNumber = this.trueTimes(x)
}
