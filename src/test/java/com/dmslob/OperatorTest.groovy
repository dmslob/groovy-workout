package com.dmslob

import org.junit.Assert
import org.junit.Test

class OperatorTest {

    @Test(expected = NullPointerException)
    void npeTest() {
        Account acc = null
        acc.name
    }

    @Test
    void elvisTest() {
        Account acc
        def name = acc?.name

        Assert.assertNull(name)
    }

    @Test
    void spreadTest() {
        def sizes = ['string', 'long string']*.size()
        println sizes

        def x = [2, 3]
        def y = [0, 1, *x, 4]
        println y

        def a = [3: 'c', 4: 'd']
        def b = [1: 'a', 2: 'b', *: a, 5: 'e']
        println b
    }

    @Test
    void overloadPlus() {
        def r = new RandomVal()
        println(r)
        r++
        println(r)
    }
}
