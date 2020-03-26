package com.dmslob

import org.junit.Test

class ImmutableClassTest {

    @Test
    void canNotBeChangedClassTest() {
        def immutable = new ImmutableClass(a: "a", b: 1)
        //immutable.a = "" // Error
        //immutable.setA("") //Error
        println immutable.a
    }
}
