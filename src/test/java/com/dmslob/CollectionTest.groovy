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
    void eachTest() {
        int n = 5
        (1..n).each { println "Number ${it}" }
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

    @Test
    void putToMapTest() {
        def errorMap = [:]
        def underliers = getUnders()

        for (Policy policy in underliers) {
            def errorMessage = policy.errorMessage
            def valid = Boolean.parseBoolean(policy.valid)
            def policyNumber = policy.policyNumber
            def underlierType = policy.underlierName
            if (!valid) {
                addMessage(errorMap, errorMessage)
            }
        }

        println errorMap.toString()
    }

    @Test
    void findAllTest() {
        String ucConfig = "CE - Commercial Express;SBE CE - Strategic Business Express Commercial Express;SBO CE - Strategic Business Other Commercial Express;SBR CE - Strategic Business Referral Commercial Express;SBX CE - Strategic Business Exception Commercial Express"
        def ucList = ucConfig.split(';')

        def orders = orderMock()
        def ceWorkOrders = orders.findAll { it ->
            ucList.contains(it.complexity)
        }

        ceWorkOrders.each {
            println it.priority
            println it.disposition
            println it.needByDate
            println it.complexity
            println ""
        }
    }

    private void addMessage(def errorMap, def errorMessage) {
        String errorValue = (errorMessage == null ? "" : errorMessage)
        if (errorMap.containsKey("rule_8-2")) {
            def ruleMessage = errorMap["rule_8-2"]
            errorValue = "${ruleMessage}, ${errorValue}"
        }
        errorMap["rule_8-2"] = errorValue
    }

    private def getUnders() {
        def underliers = []
        underliers.add(new Policy("36039245", "Fringe Liability", "false",
                "Chubb Underlyer Fringe Liability is not found in CUW"))

        underliers.add(new Policy("36039245", "Fringe Liability", "false",
                "Chubb Underlyer Fringe Liability is not found in CUW"))

        underliers.add(new Policy("35826304", "Fringe Liability", "false",
                "Chubb Underlyer Fringe Liability is not in force in CUW"))


        underliers.add(new Policy("36039245", "General Liability", "true",
                null))

        underliers.add(new Policy("74990808", "Employers Liability", "false",
                "Chubb Underlyer Employers Liability is not found in CUW"))

        underliers.add(new Policy("73593151", "Auto Liability", "true",
                null))

        underliers.add(new Policy("73593151", "Auto Liability", "true",
                null))

        return underliers
    }

    private def orderMock() {
        def orderList = new ArrayList<Order>();
        Order o1 = new Order("80", "Accepted", "01/02/2020", "CE - Commercial Express")
        orderList.add(o1)

        Order o2 = new Order("80", "Accepted", "01/05/2020", "SBE CE - Strategic Business Express Commercial Express")
        orderList.add(o2)

        Order o3 = new Order("80", "Revised", "01/02/2020", "SBO CE - Strategic Business Other Commercial Express")
        orderList.add(o3)

        Order o4 = new Order("80", "", "02/09/2020", "SBR CE - Strategic Business Referral Commercial Express")
        orderList.add(o4)

        Order o5 = new Order("60", "Accepted", "08/09/2020", "SBX CE - Strategic Business Exception Commercial Express")
        orderList.add(o5)

        Order o6 = new Order("50", "Accepted", "02/01/2020", "SBX CE - Strategic Business Exception Commercial Express")
        orderList.add(o6)

        Order o7 = new Order("50", "", "02/02/2020", "SBX CE - Strategic Business Exception Commercial Express")
        orderList.add(o7)

        Order o8 = new Order("50", "", "02/05/2020", "SBX CE - Strategic Business Exception Commercial Express")
        orderList.add(o8)

        return orderList
    }
}

class Order {
    String priority
    String disposition
    String needByDate
    String complexity

    Order(String priority, String disposition, String needByDate, String complexity) {
        this.priority = priority
        this.disposition = disposition
        this.needByDate = needByDate
        this.complexity = complexity
    }

    String getPriority() {
        return priority
    }

    String getDisposition() {
        return disposition
    }

    String getNeedByDate() {
        return needByDate
    }

    String getComplexity() {
        return complexity
    }
}

class Policy {
    String policyNumber
    String underlierName
    String valid
    String errorMessage

    Policy(String policyNumber, String underlierName, String valid, String errorMessage) {
        this.policyNumber = policyNumber
        this.underlierName = underlierName
        this.valid = valid
        this.errorMessage = errorMessage
    }

    String getPolicyNumber() {
        return policyNumber
    }

    String getUnderlierName() {
        return underlierName
    }

    String getValid() {
        return valid
    }

    String getErrorMessage() {
        return errorMessage
    }
}