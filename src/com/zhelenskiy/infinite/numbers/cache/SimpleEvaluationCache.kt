package com.zhelenskiy.infinite.numbers.cache

import com.zhelenskiy.infinite.numbers.RealNumber
import com.zhelenskiy.infinite.numbers.Segment
import com.zhelenskiy.infinite.numbers.diff
import com.zhelenskiy.infinite.numbers.rational.RationalNumber
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import kotlin.concurrent.atomics.AtomicReference
import kotlin.concurrent.atomics.ExperimentalAtomicApi

/**
 * A simple thread-safe implementation of [EvaluationCache] that caches [RealNumber] observations.
 *
 * This cache stores the observation iterators for [RealNumber]s in a persistent map, allowing
 * multiple consumers to share the same observation sequence. When a [RealNumber] is observed,
 * subsequent observations of the same number will reuse the cached iterator and the last value,
 * avoiding redundant computation.
 */
@OptIn(ExperimentalAtomicApi::class)
open class SimpleEvaluationCache : EvaluationCache {
    // todo use concurrent map instead of atomic reference to persistent map
    private val map = AtomicReference<PersistentMap<RealNumber, CachedIterator<Segment>>>(persistentMapOf())

    override fun observe(number: RealNumber): Sequence<Segment> = observeImpl(number).asSequence()

    @OptIn(CacheInternals::class)
    private tailrec fun observeImpl(number: RealNumber): CachedIterator<Segment> {
        val currentMap = map.load()
        currentMap[number]?.let { return it }
        val iterator = CachedIterator(number.observe())
        val newMap = currentMap.put(number, iterator)
        return if (map.compareAndSet(currentMap, newMap)) iterator else observeImpl(number)
    }

    override fun observe(number: RealNumber, maxDelta: RationalNumber): Segment {
        val iterator = observeImpl(number)
        // checked for any value without locking first
        iterator.lastResult?.takeIf { it.diff < maxDelta }?.let { return it }
        return iterator.lock.withLock { iterator.asSequence().iterateUntilDifferenceLessThanDelta(maxDelta) }
    }
}

@OptIn(ExperimentalAtomicApi::class)
private class CachedIterator<T : Any>(private val originalIterator: Iterator<T>) : Iterator<T> {
    val lock = reentrantLock()
    var lastResult: T? = null

    override fun hasNext(): Boolean = lock.withLock { originalIterator.hasNext() }

    override fun next(): T = lock.withLock {
        val currentElement = originalIterator.next()
        lastResult = currentElement
        currentElement
    }

    fun asSequence(): Sequence<T> = sequence {
        lastResult?.let { yield(it) }
        yieldAll(this@CachedIterator)
    }
}
