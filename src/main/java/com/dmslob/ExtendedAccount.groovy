package com.dmslob

class ExtendedAccount extends Account {
    def debt

    ExtendedAccount(name, value, debt) {
        setName(name)
        setValue(value)
        setDebt(debt)
    }

    def String toString() {
        "${name} ${value} ${debt}"
    }
}
