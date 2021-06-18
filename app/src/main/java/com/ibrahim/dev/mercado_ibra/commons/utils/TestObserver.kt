package com.ibrahim.dev.mercado_ibra.commons.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.Collections
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class TestObserver<T> private constructor() : Observer<T> {
    private val valueHistory: MutableList<T> = ArrayList()
    private val onChanged: MutableCollection<Consumer<T>> = ArrayList()
    private var valueLatch = CountDownLatch(1)

    override fun onChanged(value: T) {
        valueHistory.add(value)
        valueLatch.countDown()
        for (consumer in onChanged) {
            consumer.accept(value)
        }
    }

    /**
     * Returns a last received value. Fails if no value was received yet.
     *
     * @return a last received value
     */
    fun value(): T {
        return valueAt(valueHistory.size.dec())
    }

    /**
     * Returns the element in the selected position. Fails if no value was received yet
     * or index out of bounds
     *
     * @return a last received value
     */
    private fun valueAt(index: Int): T {
        assertHasValue()
        if (index < 0 && index >= valueHistory.size) {
            throw fail("Invalid index: $index")
        }
        return valueHistory[index]
    }

    /**
     * Returns a unmodifiable list of received values.
     *
     * @return a list of received values
     */
    fun valueHistory(): List<T> {
        return Collections.unmodifiableList(valueHistory)
    }

    /**
     * Assert that this TestObserver received at least one value.
     *
     * @return this
     */
    fun assertHasValue(): TestObserver<T> {
        if (valueHistory.isEmpty()) {
            throw fail("Observer never received any value")
        }
        return this
    }

    /**
     * Assert that this TestObserver never received any value.
     *
     * @return this
     */
    fun assertNoValue(): TestObserver<T> {
        if (valueHistory.isNotEmpty()) {
            throw fail("Expected no value, but received: " + value())
        }
        return this
    }

    /**
     * Assert that this TestObserver last received value was null
     *
     * @return this
     */
    fun assertNullValue(): TestObserver<T> {
        val value = value()
        if (value != null) {
            throw fail("Value " + valueAndClass(value) + " is not null")
        }
        return this
    }

    /**
     * Assert that this TestObserver received the specified number of values.
     *
     * @param expectedSize the expected number of received values
     * @return this
     */
    fun assertHistorySize(expectedSize: Int): TestObserver<T> {
        val size = valueHistory.size
        if (size != expectedSize) {
            throw fail("History size differ; Expected: $expectedSize, Actual: $size")
        }
        return this
    }

    /**
     * Assert that this TestObserver last received value is equal to the given value.
     *
     * @param expected the value to expect being equal to last value, can be null
     * @return this
     */
    fun assertValue(expected: T): TestObserver<T> {
        val value = value()
        if (notEquals(value, expected)) {
            throw fail("Expected: ${valueAndClass(expected)} , Actual:  ${valueAndClass(value)}")
        }
        return this
    }

    /**
     * Asserts that for this TestObserver last received value the provided predicate returns true.
     *
     * @param valuePredicate the predicate that receives the observed value and should return true
     * for the expected value.
     * @return this
     */
    fun assertValue(valuePredicate: (T) -> Boolean): TestObserver<T> {
        return assertValueAt(valueHistory.size.dec(), valuePredicate)
    }

    fun assertValueAnyPosition(valuePredicate: (T) -> Boolean): TestObserver<T> {
        if (valueHistory().find(valuePredicate::invoke) == null) {
            throw fail("The predicate $valuePredicate was not satisfied by any value ")
        }
        return this
    }

    fun assertValueAt(index: Int, valuePredicate: (T) -> Boolean): TestObserver<T> {
        val value = valueAt(index)
        if (valuePredicate.invoke(value).not()) {
            throw fail(
                "Value ${valueAndClass(
                    value
                )}  does not match the predicate $valuePredicate."
            )
        }
        return this
    }

    /**
     * Asserts that the TestObserver received only the specified values predicate in the specified order.
     *
     * @param valuesPredicate
     * @return
     */
    @SafeVarargs
    fun assertValueHistory(vararg valuesPredicate: (T) -> Boolean): TestObserver<T> {
        val valueHistory = valueHistory()
        val size = valueHistory.size
        if (size != valuesPredicate.size) {
            throw fail("Value count differs; expected: ${valuesPredicate.size} but was: $size ${this.valueHistory}")
        }

        for (valueIndex in 0 until size) {
            val historyItem = valueHistory[valueIndex]
            val expectedPredicate = valuesPredicate[valueIndex]
            if (expectedPredicate.invoke(historyItem).not()) {
                throw fail("Value ${valueAndClass(historyItem)} does not match the predicate $expectedPredicate.")
            }
        }
        return this
    }

    /**
     * Asserts that this TestObserver did not receive any value for which the provided predicate
     * returns true.
     *
     * @param valuePredicate the predicate that receives the observed values and should return true
     * for the value not supposed to be received.
     * @return this
     */
    fun assertNever(valuePredicate: (T) -> Boolean): TestObserver<T> {
        valueHistory.forEach {
            if (valuePredicate.invoke(it)) {
                throw fail("Value at position $it matches predicate $valuePredicate, which was not expected.")
            }
        }
        return this
    }

    /**
     * Allows assertion of some mapped value extracted from originally observed values. History of
     * observed values is retained.
     *
     *
     * This can became useful when you want to perform assertions on some complex structure and you
     * want to assert only on one field.
     *
     * @param mapper Function to map originally observed value.
     * @param <N> Type of mapper.
     * @return TestObserver for mapped value
    </N> */
    fun <N> map(mapper: (T) -> N): TestObserver<N> {
        val newObserver: TestObserver<N> = create()
        // We want the history match the current one
        valueHistory.forEach {
            newObserver.onChanged(mapper.invoke(it))
        }
        doOnChanged(Map(newObserver, mapper))
        return newObserver
    }

    /**
     * Adds a Consumer which will be triggered on each value change to allow assertion on the
     * value.
     *
     * @param onChanged Consumer to call when new value is received
     * @return this
     */
    fun doOnChanged(onChanged: Consumer<T>): TestObserver<T> {
        this.onChanged.add(onChanged)
        return this
    }

    /**
     * Awaits until this TestObserver has any value.
     *
     *
     * If this TestObserver has already value then this method returns immediately.
     *
     * @return this
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun awaitValue(): TestObserver<T> {
        valueLatch.await()
        return this
    }

    /**
     * Awaits the specified amount of time or until this TestObserver has any value.
     *
     *
     * If this TestObserver has already value then this method returns immediately.
     *
     * @return this
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun awaitValue(timeout: Long, timeUnit: TimeUnit): TestObserver<T> {
        valueLatch.await(timeout, timeUnit)
        return this
    }

    /**
     * Awaits until this TestObserver receives next value.
     *
     *
     * If this TestObserver has already value then it awaits for another one.
     *
     * @return this
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun awaitNextValue(): TestObserver<T> {
        return withNewLatch().awaitValue()
    }

    /**
     * Awaits the specified amount of time or until this TestObserver receives next value.
     *
     *
     * If this TestObserver has already value then it awaits for another one.
     *
     * @return this
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Throws(InterruptedException::class)
    fun awaitNextValue(timeout: Long, timeUnit: TimeUnit): TestObserver<T> {
        return withNewLatch().awaitValue(timeout, timeUnit)
    }

    private fun withNewLatch(): TestObserver<T> {
        valueLatch = CountDownLatch(1)
        return this
    }

    private fun fail(message: String): AssertionError {
        return AssertionError(message)
    }

    internal class Map<T, N>(
        private val newObserver: TestObserver<N>,
        private val mapper: (T) -> N
    ) : Consumer<T> {
        override fun accept(value: T) {
            newObserver.onChanged(mapper.invoke(value))
        }
    }

    companion object {
        private fun notEquals(o1: Any?, o2: Any?): Boolean {
            return o1 != o2
        }

        private fun valueAndClass(value: Any?): String {
            return if (value != null) {
                "$value" + " (class: " + value.javaClass.simpleName + ")"
            } else "null"
        }

        fun <T> create(): TestObserver<T> {
            return TestObserver()
        }

        fun <T> test(liveData: LiveData<T>): TestObserver<T> {
            val observer: TestObserver<T> = TestObserver()
            liveData.observeForever(observer)
            return observer
        }
    }
}
