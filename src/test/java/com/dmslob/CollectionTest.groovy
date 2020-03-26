package com.dmslob

import org.junit.Assert
import org.junit.Test

class CollectionTest {

    @Test
    void intRangeClassTest() {
        def intRange = 1..9
        assert intRange instanceof IntRange
    }

    @Test
    void rangeTest() {
        def a = 1..9
        Assert.assertEquals(9, a.size())
    }

    @Test
    void listOfRangesTest() {
        def intRange = 1..5
        def intList = [intRange]

        Assert.assertEquals(1, intList.size())
        Assert.assertEquals(5, intList[0].size())
    }

    @Test
    void listClassTest() {
        def intList = [1..9]
        assert intList instanceof List
    }

    @Test
    void forInTest() {
        def intRange = 1..9
        for (i in intRange) {
            print i
        }
    }

    @Test
    void forTest() {
        def intRange = 1..9
        for (Integer i : intRange) {
            print i
        }
    }

    @Test
    void forInMapTest() {
        def integerToStringMap = [:]
        integerToStringMap.put(1, "one")
        integerToStringMap.put(2, "two")

        for (i in integerToStringMap) {
            println "${i.key} : ${i.value}"
        }

        integerToStringMap.each {
            println "${it.key} : ${it.value}"
        }
    }

    @Test
    void heterogeneousListTest() {
        def heterogeneous = [1, "a", true]
        println heterogeneous
    }

    @Test
    void arrayTest() {
        String[] arrStr = ['Ananas', 'Banana', 'Kiwi']
        assert arrStr instanceof String[]

        def numArr = [1, 2, 3] as int[]
        assert numArr instanceof int[]
        assert numArr.size() == 3
    }

    @Test
    void mapTest() {
        def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']
        assert colors['red'] == '#FF0000'
        assert colors.green == '#00FF00'

        colors['pink'] = '#FF00FF'
        colors.yellow = '#FFFF00'

        assert colors.pink == '#FF00FF'
        assert colors['yellow'] == '#FFFF00'

        assert colors instanceof java.util.LinkedHashMap
    }
}
