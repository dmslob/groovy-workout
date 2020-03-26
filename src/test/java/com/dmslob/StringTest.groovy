package com.dmslob

import org.junit.Assert
import org.junit.Test


class StringTest {

    @Test
    void stringIncrementTest() {
        String abc = 'abc'
        String incrementedAbc = --abc

        Assert.assertEquals("abb", incrementedAbc)
    }

    @Test
    void stringNameIncrementTest() {
        String test = 'Test'
        String incrementedTest = --test

        Assert.assertEquals("Tess", incrementedTest)
    }

    @Test
    void gStringTest() {
        String abc = 'ABC'
        String gString = "${abc}"

        Assert.assertEquals(abc, gString)
    }

    @Test
    void multipleTest() {
        String abc = 'abc'
        String result = abc * 2

        Assert.assertEquals('abcabc', result)
    }

    @Test
    void eachTest() {
        'qwerty'.each {
            println it
        }
    }

    @Test
    void alphabetTest() {
        ('a'..'z').each {
            print it
        }
    }

    @Test
    void filterTest() {
        ('a'..'z').findAll { el ->
            el in ['e', 'y', 'u', 'i', 'o', 'a']
        }.each {
            print it + ' '
        }
    }
}
