@file:JvmName("Should")

package com.natpryce.hamkrest.should

import com.natpryce.hamkrest.Described
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import kotlin.reflect.KFunction1


/**
 * Syntactic sugar for [assertThat] that can be used as an infix function
 */
infix fun <T> T.shouldMatch(matcher: Matcher<T>) {
    assertThat(this, matcher)
}

/**
 * Syntactic sugar for [assertThat] that can be used as an infix function
 */
infix fun <T> T.shouldMatch(f: KFunction1<T, Boolean>) {
    this shouldMatch Matcher(f)
}

/**
 * Combines a value with a description to be used in failure messages from [shouldMatch].
 *
 * Used as:
 *         x describedAs "x" shouldMatch equalTo(10)
 */
infix fun <T> T.describedAs(name: String) = Described(name, this)

/**
 * Syntactic sugar for [assertThat] that can be used as an infix function.
 */
infix fun <T> Described<T>.shouldMatch(criteria: Matcher<T>) = assertThat(this.description, this.value, criteria)

/**
 * Syntactic sugar for [assertThat] that can be used as an infix function.
 */
infix fun <T> Described<T>.shouldMatch(p: KFunction1<T,Boolean>) = assertThat(this.description, this.value, Matcher(p))
