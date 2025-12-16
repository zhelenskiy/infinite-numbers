package com.zhelenskiy.infinite.numbers.cache

import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.Segment
import com.zhelenskiy.infinite.numbers.diff
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber

/**
 * A cache interface for observing the evaluation of real numbers.
 *
 * Implementations may memoize intermediate approximations (as [Segment]s)
 * produced while evaluating a [RealNumber]. This allows multiple consumers to
 * reuse already computed segments instead of re‑evaluating the same prefixes
 * of a computation.
 */
interface EvaluationCache {
    /**
     * Observes the evaluation of a [RealNumber] and iterates over its approximations until it
     * finds the first [Segment] whose difference is strictly less than [maxDelta].
     *
     * @param number the [RealNumber] being evaluated.
     * @param maxDelta the exclusive maximum allowed difference; must be positive.
     * @return the first [Segment] with `diff < maxDelta`.
     */
    fun observe(number: RealNumber, maxDelta: RationalNumber): Segment =
        observe(number).iterateUntilDifferenceLessThanDelta(maxDelta)

    /**
     * Observes the evaluation of [number] and returns a lazily produced sequence of
     * approximating [Segment]s.
     *
     * Implementations are free to cache and/or share the produced segments.
     *
     * @param number the [RealNumber] whose approximation sequence is requested.
     * @return a sequence of [Segment]s representing progressively tighter bounds.
     */
    fun observe(number: RealNumber): Sequence<Segment>

    /**
     * An [EvaluationCache] implementation that performs no caching.
     *
     * It simply delegates to the underlying [RealNumber.observe] implementation,
     * which is marked with [CacheInternals] to discourage direct use by clients.
     */
    object None : EvaluationCache {
        @OptIn(CacheInternals::class)
        override fun observe(number: RealNumber): Sequence<Segment> = number.observe().asSequence()
    }
}

/**
 * Iterates over this sequence of [Segment]s and returns the first element with
 * a strictly smaller difference than [maxDelta].
 *
 * @receiver a sequence of [Segment]s, typically produced by [EvaluationCache.observe].
 * @param maxDelta a positive threshold; the method looks for a segment with `diff < maxDelta`.
 * @return the first matching [Segment].
 *
 * @throws IllegalArgumentException if [maxDelta] is not positive.
 * @throws IllegalStateException if the sequence yields no elements satisfying the condition
 * or the sequence is empty.
 */
fun Sequence<Segment>.iterateUntilDifferenceLessThanDelta(maxDelta: RationalNumber): Segment {
    require(maxDelta > zero) { "Max delta must be positive: $maxDelta" }
    firstOrNull { it.diff < maxDelta }?.let { return it }
    throw IllegalStateException("No segment with diff < $maxDelta was produced")
}

/**
 * Marks [RealNumber.observe] to make its direct invocation explicitly opt‑in.
 *
 * Calling it directly bypasses any [EvaluationCache], so it should only be used
 * inside cache implementations (i.e. within [EvaluationCache.observe]).
 */
@RequiresOptIn("This function is internal, use it through EvaluationCache::observe")
annotation class CacheInternals
