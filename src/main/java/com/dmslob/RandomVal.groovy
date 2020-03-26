package com.dmslob

class RandomVal {
    private def value
    private Random randomGen = new Random()

    def next() {
        this.value = randomGen.nextInt()
    }

    RandomVal() {
        this.value = randomGen.nextInt()
    }

    def String toString() {
        "${this.value}"
    }
}
