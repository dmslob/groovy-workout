package com.dmslob

import org.junit.Assert
import org.junit.Test

class ClosureTest {

    @Test
    void returnClosureTest() {
        def localVar = 10
        def outer = { param ->
            def inner = {
                return param * localVar
            }
            return inner // is not required
        }
        def inner = outer(10)
        def result = inner(10)

        Assert.assertEquals(100, result)

        localVar = 20

        inner = outer(10)
        result = inner(10)

        Assert.assertEquals(200, result)
    }
}
