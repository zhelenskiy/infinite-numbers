package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.diff
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber

/**
 * Creates a comparator for real numbers that compares them with a given precision [delta].
 *
 * The comparison works by evaluating the difference `(a - b)` with precision [delta]:
 * - Returns `-1` if `a < b - delta` (a is significantly smaller)
 * - Returns `1` if `a > b + delta` (a is significantly larger)
 * - Returns `0` if the difference is within `[-delta, delta]` (considered approximately equal)
 *
 * @param delta The precision threshold for comparison. Must be positive.
 * @return A comparator that compares real numbers within the specified delta tolerance.
 * @throws IllegalArgumentException if delta is not positive.
 */
context(cache: EvaluationCache)
fun realNumberApproximateComparator(delta: RationalNumber): Comparator<RealNumber> {
    require(delta > zero) { "Delta must be positive" }
    return Comparator { a, b ->
        val (lowerBound, upperBound) = cache.observe(a - b, delta)
        when {
            lowerBound.value <= -delta -> -1
            upperBound.value >= delta -> 1
            else -> 0
        }
    }
}

/**
 * Creates a comparator for real numbers that never returns equality for non-known rationals (always returns -1 or 1).
 *
 * This comparator implements a total order on real numbers by:
 * - For rational numbers: using exact rational comparison
 * - For non-rational numbers: iteratively refining the precision until a non-zero difference is detected.
 *
 * **Warning**: This function may not terminate if the two real numbers are actually equal and their sequences are infinite,
 * as it will keep refining precision indefinitely searching for a difference.
 *
 * @return A comparator for real numbers which never returns 0 for infinite sequences of digits.
 */
@PotentiallyInfiniteRealNumberApi
context(cache: EvaluationCache)
fun nonEqualRealNumberComparator(): Comparator<RealNumber> = Comparator { a, b ->
    when {
        a is RationalNumber && b is RationalNumber -> a.compareTo(b)
        else -> generateSequence<RationalNumber>(one) { it / two }
            .takeWhile { cache.observe(a).first().diff != zero || cache.observe(b).first().diff != zero }
            .map { realNumberApproximateComparator(it).compare(a, b) }
            .firstOrNull { it != 0 } ?: cache.observe(a).first().lowerBound.compareTo(cache.observe(b).first().lowerBound)
    }
}
