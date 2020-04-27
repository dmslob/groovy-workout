package com.dmslob

import org.junit.Test

class AccountTest {

    @Test
    void constructorTest() {
        def account = new Account(name: "Account #1", value: new BigDecimal(10))
        account.setName("Account #2")
        account.setValue(new BigDecimal(20))

        println account.getName()
        println account.getValue()
    }

    @Test
    void extendedAccountTest() {
        println new ExtendedAccount("A", new BigDecimal(10), 1)
    }

    @Test
    void tortTest() {
        def account = new Account(name: "Account #1", value: new BigDecimal(10))
        def newAccount = account

        assert account.is(newAccount)
    }
}