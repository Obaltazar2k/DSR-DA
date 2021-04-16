package com.stardust.calculadora

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun TestSumar() {
        // Context of the app under test.
        val num1 = 10;
        val num2 = 20;
        val mainActivity = MainActivity()
        assertEquals(30, mainActivity.sumar(10, 20))
    }

    @Test
    fun TestRestar() {
        // Context of the app under test.
        val num1 = 20;
        val num2 = 10;
        val mainActivity = MainActivity()
        assertEquals(10, mainActivity.restar(20, 10))
    }
}