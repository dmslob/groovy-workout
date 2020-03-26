package com.dmslob

import org.junit.Test

class PersonTest {

    @Test
    void constructorTest() {
        def p = new Person(first: "Anna", last: "Turman")
        p.first = "Anna Marie"

        println "${p.first} ${p.last}"
    }
}
