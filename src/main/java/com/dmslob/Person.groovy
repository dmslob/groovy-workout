package com.dmslob

class Person {
    def first
    def last

    void setFirst(first) {
        println "${this.first} is becoming ${first}"
        this.first = first
    }
}
