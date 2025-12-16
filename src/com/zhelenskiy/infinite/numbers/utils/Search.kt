package com.zhelenskiy.infinite.numbers.utils

import com.zhelenskiy.infinite.numbers.AnyBounds
import com.zhelenskiy.infinite.numbers.Border
import com.zhelenskiy.infinite.numbers.Border.Finite
import com.zhelenskiy.infinite.numbers.Border.Inclusive
import com.zhelenskiy.infinite.numbers.Border.Infinite
import com.zhelenskiy.infinite.numbers.Border.Infinity
import com.zhelenskiy.infinite.numbers.Bounds
import com.zhelenskiy.infinite.numbers.PotentiallyInfiniteRealNumberApi
import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.Segment
import com.zhelenskiy.infinite.numbers.cache.CacheInternals
import com.zhelenskiy.infinite.numbers.cache.EvaluationCache
import com.zhelenskiy.infinite.numbers.diff
import com.zhelenskiy.infinite.numbers.rangeTo
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.one
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.two
import com.zhelenskiy.infinite.numbers.rational.IntegerNumber.Companion.zero
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import com.zhelenskiy.infinite.numbers.utils.SearchResult.Found
import com.zhelenskiy.infinite.numbers.utils.SearchResult.MustBeAfter
import com.zhelenskiy.infinite.numbers.utils.SearchResult.MustBeBefore
import com.zhelenskiy.infinite.numbers.utils.SearchResult.MustBeSomewhere
import com.zhelenskiy.infinite.numbers.utils.SearchResult.OutOfBounds

/**
 * Represents the result of comparing a real number to two bounds.
 */
internal enum class CompositeComparisonResult() {
    BeforeLowerBound, AfterLowerBound, BeforeUpperBound, AfterUpperBound
}

/**
 * Compares this [RealNumber] to two **different** bounds by progressively refining approximations.
 *
 * The necessity of difference is needed to make the computation finite.
 * If the bounds are the same, the function will get stuck in an infinite loop.
 *
 * @param lowerBound The lower bound for comparison.
 * @param upperBound The upper bound for comparison.
 * @return A [CompositeComparisonResult] indicating the position of this number relative to the bounds.
 */
context(cache: EvaluationCache)
internal fun RealNumber.compareTo(lowerBound: RealNumber, upperBound: RealNumber): CompositeComparisonResult {
    var delta: RationalNumber = one

    fun RealNumber.getBounds() = cache.observe(this, delta)

    while (true) {
        val (lowerL, lowerR) = lowerBound.getBounds()
        val (upperL, upperR) = upperBound.getBounds()
        val bounds = this.getBounds()
        val (currentL, currentR) = bounds
        when {
            currentL > upperR -> return CompositeComparisonResult.AfterUpperBound
            currentR < lowerL -> return CompositeComparisonResult.BeforeLowerBound
            currentL > lowerR -> return CompositeComparisonResult.AfterLowerBound
            currentR < upperL -> return CompositeComparisonResult.BeforeUpperBound
        }

        delta /= 2
    }
}

/**
 * Represents the state of a search operation, tracking the bounds and their results.
 *
 * @property lowerBound The lower bound of the search interval.
 * @property lowerResult The result of the function application at the lower bound.
 * @property upperBound The upper bound of the search interval.
 * @property upperResult The result of the function application at the upper bound.
 */
private data class SearchState(
    val lowerBound: RationalNumber, val lowerResult: RealNumber,
    val upperBound: RationalNumber, val upperResult: RealNumber,
) {
    companion object Companion {
        /**
         * Creates a [SearchState] representing a found solution.
         *
         * @param argument The argument where the solution was found.
         * @param result The result of the function application at that [argument].
         * @return A [SearchState] with both bounds set to the found argument.
         */
        fun found(argument: RationalNumber, result: RealNumber) = SearchState(
            lowerBound = argument, lowerResult = result, upperBound = argument, upperResult = result
        )
    }
}


/**
 * Performs a binary search to find the argument for which the monotonic function [f] produces the [expected] value.
 *
 * If the value is not found and the exceeded border is inclusive, the function returns `null`.
 * If the value is not found and the exceeded border is exclusive, the function does not terminate.
 *
 * @param expected The expected result value to search for.
 * @param bounds The bounds within which to search. Defaults to the entire real line.
 * @param f The monotonic function to invert.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or `null` if not found and the exceeded border is inclusive.
 */
context(cache: EvaluationCache)
fun binarySearch(
    expected: RationalNumber, bounds: AnyBounds = -Infinity..+Infinity, f: (RationalNumber) -> RationalNumber
): RealNumber? = context(naturalOrder<RationalNumber>()) {
    if (bounds.lowerBound is Inclusive && f(bounds.lowerBound.value) == expected) {
        return@context bounds.lowerBound.value
    }
    if (bounds.upperBound is Inclusive && f(bounds.upperBound.value) == expected) {
        return@context bounds.upperBound.value
    }
    searchImpl(bounds, expected, f) { state, isAscending ->
        val (lowerBound, _, upperBound, _) = state
        val mid = (lowerBound + upperBound) / 2
        val midValue = f(mid)
        val compare = midValue.compareTo(expected)
        when (isAscending) {
            (compare < 0) -> state.copy(lowerBound = mid, lowerResult = midValue)
            (compare > 0) -> state.copy(upperBound = mid, upperResult = midValue)
            else -> SearchState.found(mid, midValue)
        }
    }
}

/**
 * Performs a search to find the argument for which the monotonic [RealNumber] function [f] produces the [expected] value.
 *
 * If the value is not found and the exceeded border is inclusive, the function returns `null`.
 * If the value is not found and the exceeded border is exclusive, the function does not terminate.
 *
 * @param expected The expected result value to search for.
 * @param bounds The bounds within which to search. Defaults to the entire real line.
 * @param f The monotonic function to invert.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or `null` if not found and the exceeded border is inclusive.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
fun search(
    expected: RealNumber,
    bounds: AnyBounds = -Infinity..+Infinity,
    f: (RationalNumber) -> RealNumber
): RealNumber? = searchImpl(bounds, expected, f)

/**
 * Performs a search to find the argument for which the **strictly** monotonic [RealNumber] function [f] produces the [expected] value.
 *
 * If the value is not found and the exceeded border is inclusive, the function returns `null`.
 * If the value is not found and the exceeded border is exclusive, the function does not terminate.
 *
 * Unlike [search], it does not require `Comparator<RealNumber>` to be provided in the context, but it requires strict monotonicity of [f].
 *
 * @param expected The expected result value to search for.
 * @param bounds The bounds within which to search. Defaults to the entire real line.
 * @param f The monotonic function to invert.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or `null` if not found and the exceeded border is inclusive.
 */
@OptIn(PotentiallyInfiniteRealNumberApi::class)
context(cache: EvaluationCache)
fun reverseValue(
    expected: RealNumber,
    bounds: AnyBounds = -Infinity..+Infinity,
    f: (RationalNumber) -> RealNumber
): RealNumber? = with(nonEqualRealNumberComparator()) { searchImpl(bounds, expected, f) }

/**
 * Creates a reverse function that finds arguments producing given values.
 *
 * @receiver A reversible monotonic function that produces values to search for.
 * @param bounds The bounds within which to search. Defaults to the entire real line.
 * @return A function that takes an expected value and returns the argument producing that value, or null if not found within the given bounds if the exceeded border is inclusive.
 */
@OptIn(PotentiallyInfiniteRealNumberApi::class)
context(cache: EvaluationCache)
fun ((RationalNumber) -> RealNumber).reverse(
    bounds: AnyBounds = -Infinity..+Infinity
): (RealNumber) -> RealNumber? = with(nonEqualRealNumberComparator()) {
    { expected: RealNumber -> searchImpl(bounds, expected, this@reverse) }
}

/**
 * Implementation of the search algorithm using a ternary search strategy.
 *
 * This function evaluates the function at two midpoints and narrows down the search interval
 * based on where the expected value lies. It validates monotonicity at each step.
 *
 * @param bounds The bounds within which to search.
 * @param expected The expected result value to search for.
 * @param f The monotonic function to invert.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or null if not found.
 * @throws IllegalArgumentException if the function is not monotonic.
 */
context(cache: EvaluationCache, comp: Comparator<RealNumber>)
private fun searchImpl(
    bounds: AnyBounds, expected: RealNumber,
    f: (RationalNumber) -> RealNumber,
): RealNumber? = searchImpl(bounds, expected, f) { state, isAscending ->
    val (lowerBound, lowerResult, upperBound, upperResult) = state
    val mid1 = (2 * lowerBound + upperBound) / 3
    val mid1Result = f(mid1)
    val mid2 = (lowerBound + 2 * upperBound) / 3
    val mid2Result = f(mid2)
    listOf(lowerResult, mid1Result, mid2Result, upperResult).zipWithNext().forEach { (a, b) ->
        if (isAscending) require(comp.compare(a, b) <= 0) { "Values must be monotonically increasing" }
        else require(comp.compare(a, b) >= 0) { "Values must be monotonically decreasing" }
    }
    if (isAscending) {
        when (expected.compareTo(mid1Result, mid2Result)) {
            CompositeComparisonResult.BeforeLowerBound -> state.copy(upperBound = mid1, upperResult = mid1Result)
            CompositeComparisonResult.AfterLowerBound -> state.copy(lowerBound = mid1, lowerResult = mid1Result)
            CompositeComparisonResult.BeforeUpperBound -> state.copy(upperBound = mid2, upperResult = mid2Result)
            CompositeComparisonResult.AfterUpperBound -> state.copy(lowerBound = mid2, lowerResult = mid2Result)
        }
    } else {
        when (expected.compareTo(mid2Result, mid1Result)) {
            CompositeComparisonResult.BeforeLowerBound -> state.copy(lowerBound = mid2, lowerResult = mid2Result)
            CompositeComparisonResult.AfterLowerBound -> state.copy(upperBound = mid2, upperResult = mid2Result)
            CompositeComparisonResult.BeforeUpperBound -> state.copy(lowerBound = mid1, lowerResult = mid1Result)
            CompositeComparisonResult.AfterUpperBound -> state.copy(upperBound = mid1, upperResult = mid1Result)
        }
    }
}

/**
 * Divides the bounded finite region into segments and allows processing them via a consumer function.
 *
 * @receiver Finite bounds defined by a lower and upper bound.
 * @param monotonousSequenceConsumer A function that consumes a sequence of the following segments and a handler for out-of-bounds cases,
 * and returns a [SearchResult] based on the computations. The function accepts a sequence of segments and a lambda that
 * handles [OutOfBounds] instances to adjust or bypass them during processing.
 * @return The [SearchResult] returned by the consumer function, indicating the result of processing the segments.
 */
private fun <R> Bounds<Finite, Finite>.finiteToSegments(monotonousSequenceConsumer: (Sequence<Segment>, (OutOfBounds) -> OutOfBounds?) -> SearchResult<R>): SearchResult<R> {
    val commonSeries = generateSequence(diff) { it / two }
    return when (lowerBound) {
        is Border.Exclusive.Regular -> when (upperBound) {
            is Border.Exclusive.Regular -> {
                val mid = Inclusive((upperBound.value + lowerBound.value) / 2)
                copy(upperBound = mid).finiteToSegments(monotonousSequenceConsumer).map(
                    onFound = ::Found,
                    onMustBeSomewhere = { null },
                    onMustBeBefore = { MustBeBefore },
                    onMustBeAfter = { null },
                ) ?: copy(lowerBound = mid).finiteToSegments(monotonousSequenceConsumer).map(
                    onFound = ::Found,
                    onMustBeSomewhere = { MustBeSomewhere },
                    onMustBeBefore = { error("Non monotonic function") },
                    onMustBeAfter = { MustBeAfter },
                )
            }

            is Inclusive -> commonSeries
                .map { Inclusive(lowerBound.value + it) }
                .zipWithNext { upperBound, lowerBound -> lowerBound..upperBound }
                .let { seq -> monotonousSequenceConsumer(seq) { if (it == MustBeBefore) null else it } }
        }

        is Inclusive -> when (upperBound) {
            is Inclusive -> monotonousSequenceConsumer(sequenceOf(lowerBound..upperBound)) { it }
            is Border.Exclusive.Regular -> commonSeries
                .map { Inclusive(upperBound.value - it) }
                .zipWithNext { lowerBound, upperBound -> lowerBound..upperBound }
                .let { seq -> monotonousSequenceConsumer(seq) { if (it == MustBeAfter) null else it } }
        }
    }
}

/**
 * Divides the bounded region into segments and allows processing them via a consumer function.
 *
 * @receiver Bounds defined by a lower and upper bound.
 * @param monotonousSequenceConsumer A function that consumes a sequence of the following segments and a handler for out-of-bounds cases,
 * and returns a [SearchResult] based on the computations. The function accepts a sequence of segments and a lambda that
 * handles [OutOfBounds] instances to adjust or bypass them during processing.
 * @return The [SearchResult] returned by the consumer function, indicating the result of processing the segments.
 */
private fun <R : Any> AnyBounds.toSegments(monotonousSequenceConsumer: (Sequence<Segment>, (OutOfBounds) -> OutOfBounds?) -> SearchResult<R>): SearchResult<R> {
    val commonSeries = generateSequence(one) { it * two }
    return when (lowerBound) {
        is Finite -> when (upperBound) {
            is Finite -> (lowerBound..upperBound).finiteToSegments(monotonousSequenceConsumer)
            is Infinite -> (sequenceOf(lowerBound) + commonSeries.map { Inclusive(lowerBound.value + it) })
                .zipWithNext { lowerBound, upperBound -> lowerBound..upperBound }
                .map { it.finiteToSegments(monotonousSequenceConsumer) }
                .filterNot { it is MustBeSomewhere || it is MustBeAfter }.firstOrNull() ?: MustBeSomewhere
        }

        is Infinite -> when (upperBound) {
            is Finite -> (sequenceOf(upperBound) + commonSeries.map { Inclusive(upperBound.value - it) })
                .zipWithNext { upperBound, lowerBound -> lowerBound..upperBound }
                .map { it.finiteToSegments(monotonousSequenceConsumer) }
                .filterNot { it is MustBeSomewhere || it is MustBeBefore }.firstOrNull() ?: MustBeSomewhere

            is Infinite -> copy(upperBound = Inclusive(zero)).toSegments(monotonousSequenceConsumer).map(
                onFound = ::Found,
                onMustBeSomewhere = { null },
                onMustBeBefore = { MustBeBefore },
                onMustBeAfter = { null },
            ) ?: copy(lowerBound = Inclusive(zero)).toSegments(monotonousSequenceConsumer).map(
                onFound = ::Found,
                onMustBeSomewhere = { MustBeSomewhere },
                onMustBeBefore = { error("Non monotonic function") },
                onMustBeAfter = { MustBeAfter },
            )
        }
    }
}

/**
 * Represents the result of a search operation.
 *
 * @param T The type of the result if found.
 */
private sealed class SearchResult<out T> {
    /**
     * Indicates that the search found a result.
     *
     * @param result The found result value.
     */
    data class Found<T>(val result: T) : SearchResult<T>()

    /**
     * Base class for search results where no value was found.
     */
    sealed class NotFound : SearchResult<Nothing>()

    /**
     * Indicates that the value is not found, but there is no hint where it should be located.
     */
    data object MustBeSomewhere : NotFound()

    /**
     * Base class for search results that are outside the search bounds.
     */
    sealed class OutOfBounds : NotFound()

    /**
     * Indicates that the value must be before (less than) the search bounds.
     */
    data object MustBeBefore : OutOfBounds()

    /**
     * Indicates that the value must be after (greater than) the search bounds.
     */
    data object MustBeAfter : OutOfBounds()

    /**
     * Maps the search result to a value using the provided handlers for each case.
     *
     * @param R The return type.
     * @param onFound Handler for the [Found] case.
     * @param onMustBeSomewhere Handler for the [MustBeSomewhere] case.
     * @param onMustBeBefore Handler for the [MustBeBefore] case.
     * @param onMustBeAfter Handler for the [MustBeAfter] case.
     * @return The result of applying the appropriate handler.
     */
    inline fun <R> map(
        onFound: (T) -> R,
        onMustBeSomewhere: () -> R,
        onMustBeBefore: () -> R,
        onMustBeAfter: () -> R
    ): R = when (this) {
        is Found -> onFound(result)
        MustBeSomewhere -> onMustBeSomewhere()
        MustBeBefore -> onMustBeBefore()
        MustBeAfter -> onMustBeAfter()
    }

    /**
     * Maps the search result to a value using handlers for found and not-found cases.
     *
     * @param R The return type.
     * @param onFound Handler for the [Found] case.
     * @param onNotFound Handler for all [NotFound] cases.
     * @return The result of applying the appropriate handler.
     */
    inline fun <R> map(
        onFound: (T) -> R,
        onNotFound: () -> R,
    ): R = map(
        onFound = onFound,
        onMustBeSomewhere = onNotFound,
        onMustBeBefore = onNotFound,
        onMustBeAfter = onNotFound
    )
}

/**
 * Wraps the search operation, dividing the bounds into segments and searching within each segment.
 *
 * This function handles the segmentation of potentially infinite bounds into finite segments,
 * validates monotonicity, and determines whether the expected value is within the searchable range.
 *
 * @param T The type of values being searched.
 * @param bounds The bounds within which to search.
 * @param expected The expected value to find.
 * @param f The function to invert.
 * @param search The search algorithm to use within each segment.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or null if not found.
 * @throws IllegalArgumentException if the function is not monotonic.
 */
context(comp: Comparator<T>)
private fun <T> wrap(
    bounds: AnyBounds,
    expected: T,
    f: (RationalNumber) -> T,
    search: (bounds: Segment, expected: T, f: (RationalNumber) -> T) -> RealNumber?,
): RealNumber? = bounds.toSegments { sequence, onNotFound ->
    var isIncreasing: Boolean? = null
    for (segment in sequence) {
        val lowerResult = f(segment.lowerBound.value)
        val upperResult = f(segment.upperBound.value)
        when (isIncreasing) {
            true -> require(comp.compare(upperResult, lowerResult) >= 0) { "Values must be monotonically increasing" }
            false -> require(comp.compare(upperResult, lowerResult) <= 0) { "Values must be monotonically decreasing" }
            null -> {
                isIncreasing = comp.compare(lowerResult, upperResult).let { if (it == 0) null else it < 0 }
                val compareExpectedToLowerResult = comp.compare(expected, lowerResult)
                val compareExpectedToUpperResult = comp.compare(expected, upperResult)
                when (isIncreasing) {
                    true if compareExpectedToLowerResult < 0 -> onNotFound(MustBeBefore)?.let { return@toSegments it }
                    true if compareExpectedToUpperResult > 0 -> onNotFound(MustBeAfter)?.let { return@toSegments it }
                    false if compareExpectedToLowerResult > 0 -> onNotFound(MustBeBefore)?.let { return@toSegments it }
                    false if compareExpectedToUpperResult < 0 -> onNotFound(MustBeAfter)?.let { return@toSegments it }
                    else -> {}
                }
            }
        }
        search(segment, expected, f)?.let { return@toSegments Found(it) }
    }
    MustBeSomewhere
}.map(onFound = { it }, onNotFound = { null })

/**
 * Core search implementation that finds an argument producing the expected value.
 *
 * This function iteratively narrows down the search interval using the provided [nextElement] strategy.
 * It handles edge cases such as equal bounds, equal results, and out-of-bounds values.
 * The result is a [RealNumber] that lazily refines its bounds as needed.
 *
 * @param R The type of real number being searched (subtype of [RealNumber]).
 * @param bounds The bounds within which to search.
 * @param expected The expected value to find.
 * @param f The monotonic function to invert.
 * @param nextElement Strategy for computing the next search state given the current state and monotonicity direction.
 * @return A [RealNumber] representing the argument that produces the [expected] value, or `null` if not found.
 * @throws IllegalArgumentException if the lower bound is greater than the upper bound.
 * @throws IllegalStateException from returned value if the comparator lacks sufficient precision.
 */
@OptIn(CacheInternals::class)
context(cache: EvaluationCache, comp: Comparator<R>)
private fun <R : RealNumber> searchImpl(
    bounds: AnyBounds,
    expected: R,
    f: (RationalNumber) -> R,
    nextElement: (SearchState, isAscending: Boolean) -> SearchState,
): RealNumber? = wrap(bounds, expected, f) { localBounds, expected, f ->
    val lowerBound = localBounds.lowerBound.value
    val upperBound = localBounds.upperBound.value
    require(lowerBound <= upperBound) { "Lower bound must be less or equal to upper bound" }

    val lowerResult = f(lowerBound)
    val upperResult = f(upperBound)

    fun onEqualBoundResults() = when {
        comp.compare(lowerResult, expected) != 0 -> null
        lowerBound == upperBound -> lowerBound
        lowerResult is RationalNumber && upperResult is RationalNumber && lowerResult == upperResult -> lowerBound
        else -> RealNumber {
            iterator {
                yield(Inclusive(lowerBound)..Inclusive(upperBound))
                throw IllegalStateException("Your comparator does not have enough precision, try increasing it")
            }
        }
    }

    if (lowerBound == upperBound) return@wrap onEqualBoundResults()

    val isAscending = run {
        val comparisonResult = comp.compare(lowerResult, upperResult)
        if (comparisonResult == 0) return@wrap onEqualBoundResults()
        comparisonResult < 0
    }
    val minBound = if (isAscending) lowerResult else upperResult
    val maxBound = if (isAscending) upperResult else lowerResult
    when (expected.compareTo(minBound, maxBound)) {
        CompositeComparisonResult.BeforeLowerBound, CompositeComparisonResult.AfterUpperBound -> return@wrap null
        else -> {}
    }
    RealNumber {
        iterator {
            val bounds = generateSequence(
                SearchState(lowerBound, lowerResult, upperBound, upperResult)
            ) { state ->
                if (state.lowerBound == state.upperBound) null else nextElement(state, isAscending)
            }
            yieldAll(bounds.map { (lowerBound, _, upperBound, _) ->
                Inclusive(lowerBound)..Inclusive(upperBound)
            })
        }
    }
}
