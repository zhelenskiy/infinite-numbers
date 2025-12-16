package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber


/**
 * Natural numbers sequence.
 */
val naturalSequence = generateSequence(one) { it + one }

/**
 * Non-negative integer numbers sequence.
 */
val naturalSequenceWithZero = generateSequence(zero) { it + one }

/**
 * Euler's constant.
 */
val e = naturalSequenceWithZero.map { one / factorial(it) }
    .sum { n -> if (n == zero) two else 1 / (n * factorial(n)) }

/**
 * Factorial function.
 */
fun factorial(n: IntegerNumber): IntegerNumber {
    require(n >= zero) { "Factorial of negative number is undefined" }
    var result = one
    var current = two
    while (current <= n) {
        result *= current
        current++
    }
    return result
}

/**
 * Pi constant.
 */
// Machin-like formula
val pi: RealNumber = naturalSequenceWithZero.map { n ->
    val part1 = (-one).pow(n) / (2 * n + one) * (one / 5).pow(2 * n + 1)
    val part2 = (-one).pow(n) / (2 * n + one) * (one / 239).pow(2 * n + 1)
    16 * part1 - 4 * part2
}.sum { n ->
    fun arctanRemainder(x: RationalNumber, n: IntegerNumber): RationalNumber {
        val k = 2 * n + 3
        return x.pow(k) / k
    }

    val r1 = 16 * arctanRemainder(one / 5, n)
    val r2 = 4 * arctanRemainder(one / 239, n)
    r1 + r2
}
