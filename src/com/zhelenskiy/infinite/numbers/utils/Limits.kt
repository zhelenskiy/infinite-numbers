package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.cache.CacheInternals
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import kotlin.jvm.JvmName

/**
 * Computes the result of applying the given operation on the limits of the given operands.
 *
 * The operation must be non-strictly monotonic on each operand.
 * The direction of the monotonicity can be different for each operand.
 *
 * @param operand1 the first real number operand
 * @param operand2 the second real number operand
 * @param operation the operation to be applied to the operands, represented as a function
 * that takes two rational numbers and returns a real number
 * @return a real number representing the result of the computed operation, determined
 * by iteratively narrowing bounds
 */
@OptIn(CacheInternals::class)
fun limitMonotonic(
    operand1: RealNumber, operand2: RealNumber, operation: (RationalNumber, RationalNumber) -> RealNumber,
): RealNumber = RealNumber {
    val cache = contextOf<EvaluationCache>()
    iterator {
        var delta: RationalNumber = one
        while (true) {
            val (lowerBound1, upperBound1) = cache.observe(operand1, delta)
            val (lowerBound2, upperBound2) = cache.observe(operand2, delta)
            val results = listOf(
                cache.observe(operation(lowerBound1.value, lowerBound2.value), delta),
                cache.observe(operation(upperBound1.value, upperBound2.value), delta),
                cache.observe(operation(lowerBound1.value, upperBound2.value), delta),
                cache.observe(operation(upperBound1.value, lowerBound2.value), delta),
            ).flatMap { (a, b) -> listOf(a, b) }
            val lowerBound = results.min()
            val upperBound = results.max()
            yield(lowerBound..upperBound)
            if (lowerBound == upperBound) break
            delta /= two
        }
    }
}

/**
 * Computes the result of applying the given operation on the limits of the given operand.
 *
 * @param operand the real number operand
 * @param operation the operation to be applied to the operand, represented as a function
 * that takes a rational number and returns a real number
 * @return a real number representing the result of the computed operation, determined
 * by iteratively narrowing bounds
 */
@OptIn(CacheInternals::class)
fun limit(operand: RealNumber, operation: (RationalNumber) -> RealNumber): RealNumber = RealNumber {
    val cache = contextOf<EvaluationCache>()
    iterator {
        var delta: RationalNumber = one
        while (true) {
            val (lowerBound1, upperBound1) = cache.observe(operand, delta)
            val results = listOf(
                cache.observe(operation(lowerBound1.value), delta),
                cache.observe(operation(upperBound1.value), delta),
            ).flatMap { (a, b) -> listOf(a, b) }
            val lowerBound = results.min()
            val upperBound = results.max()
            yield(lowerBound..upperBound)
            if (lowerBound == upperBound) break
            delta /= two
        }
    }
}

/**
 * Computes the limit of a real number sequence using the function [getEpsilon] for residual error estimation.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return A [RealNumber] representing the computed limit of the sequence.
 */
@OptIn(CacheInternals::class)
fun Sequence<RealNumber>.limit(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber): RealNumber =
    RealNumber {
        iterator {
            val cache = contextOf<EvaluationCache>()
            var index = zero
            val maxDeltas = generateSequence<RationalNumber>(one) { it / 2 }
            for ((element, maxDelta) in this@limit.zip(maxDeltas)) {
                val epsilon = getEpsilon(index)
                val (lowerBound, upperBound) = cache.observe(element, maxDelta)
                val newBounds = Inclusive(lowerBound.value - epsilon)..Inclusive(upperBound.value + epsilon)
                yield(newBounds)
                index++
            }
        }
    }


/**
 * Computes the sum of the possibly infinite given rational number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the sum limit of the given sequence
 */
@JvmName("rationalNumberSum")
fun Sequence<RationalNumber>.sum(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    runningReduce(RationalNumber::plus).limit(getEpsilon)

/**
 * Computes the product of the possibly infinite given rational number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the product limit of the given sequence
 */
@JvmName("rationalNumberProduct")
fun Sequence<RationalNumber>.product(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    runningReduce(RationalNumber::times).limit(getEpsilon)

private fun Sequence<RationalNumber>.sum1(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    sum(getEpsilon)

private fun Sequence<RationalNumber>.product1(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    product(getEpsilon)

/**
 * Computes the sum of the possibly infinite given rational number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the sum limit of the given sequence
 */
@JvmName("rationalNumberSum")
context(cache: EvaluationCache)
fun Sequence<RationalNumber>.sum(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) = sum1(getEpsilon)

/**
 * Computes the product of the possibly infinite given rational number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the product limit of the given sequence
 */
context(cache: EvaluationCache)
@JvmName("rationalNumberProduct")
fun Sequence<RationalNumber>.product(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    product1(getEpsilon)

/**
 * Computes the sum of the possibly infinite given real number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the sum limit of the given sequence
 */
@JvmName("realNumberSum")
context(cache: EvaluationCache)
fun Sequence<RealNumber>.sum(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    runningReduce { number, other -> number.plus(other) }.limit(getEpsilon)

/**
 * Computes the product of the possibly infinite given real number sequence.
 *
 * @param getEpsilon function that takes sequence index (starting with 0) and returns the residual epsilon value after computing the value
 * @return the product limit of the given sequence
 */
@JvmName("realNumberProduct")
context(cache: EvaluationCache)
fun Sequence<RealNumber>.product(getEpsilon: Sequence<RealNumber>.(IntegerNumber) -> RationalNumber) =
    runningReduce { number, other -> number.times(other) }.limit(getEpsilon)
