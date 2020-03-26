package com.dmslob

import org.junit.Assert
import org.junit.Test

class NumberTest {

    @Test
    void integerClassTest() {
        int a = 10
        Class intClass = a.class

        Integer integer = 12
        Class integerClass = integer.getClass()

        Assert.assertEquals(intClass, integerClass)
    }

    @Test
    void upToTest() {
        1.upto 10, {
            print it
        }
    }

    @Test
    void timesTest() {
        10.times {
            print it
        }
    }

    @Test
    void mapTest() {
        (0..10).collect { el ->
            el * 10
        }.each {
            print it + ' '
        }
    }

    @Test
    void reduceTest() {
        def sum = (0..10).inject(0) { prev, elem ->
            return prev + elem
        }
        Assert.assertEquals(55, sum)
    }

    @Test
    void mapReduceTest() {
        def result = (0..10).collect { el ->
            el * 10
        }.inject(0) { prev, elem ->
            return prev + elem
        }
        Assert.assertEquals(550, result)
    }
}
