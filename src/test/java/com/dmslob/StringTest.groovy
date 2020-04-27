package com.dmslob

import groovy.json.JsonSlurper
import org.junit.Assert
import org.junit.Test

import com.google.gson.Gson
import java.util.stream.Collector
import java.util.stream.Collectors


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

    @Test
    void removeItemFromListTest() {
        String formToRemove = "Pappas & Co, A California Corporation";
        String existedFormsString = "Pappas Family Farms, Inc.\nPappas & Co, A California Corporation\nPappas Family Farms I, LP";

        String newForms = Arrays.asList(existedFormsString.split("\n")).stream()
                .filter { !it.equalsIgnoreCase(formToRemove) }
                .collect(Collectors.joining("\n"))

        println newForms
    }

    @Test
    void test() {
        String namedInsuredToRemove = "Pappas & Co, A California Corporation";
        String existingNamedInsured = getWebElementExtended(byXpath("//textarea[@fieldref='AccountInput.AdditionalNamedInsured']"), { getText() });
        String newNamedInsured = Arrays.stream(existingNamedInsured.split("\n"))
                .filter { !it.equalsIgnoreCase(namedInsuredToRemove) }
                .collect(Collectors.joining("\n"));
        if (!newNamedInsured.equalsIgnoreCase(existingNamedInsured)) {
            getWebElementExtended(byXpath("//textarea[@fieldref='AccountInput.AdditionalNamedInsured']"), { setValue(newNamedInsured) });
        }
    }

    @Test
    void listToStringTest() {
        def pvList = ["test 1", "test 2", "test 3", "test 4"]
        pvList = []
        def pvListAsString = pvList.join(',')

        println "1: " + pvListAsString

        def newList = pvListAsString.split(',').join("\n")
        println "2: " + newList
    }

    @Test
    void jsonParsingTest() {
        Gson gsonHelper = new Gson()
        JsonSlurper jsonSlurper = new JsonSlurper()

        //def responseList = gsonHelper.fromJson(jsonResponse.toString(), List.class)
        def responseList = jsonSlurper.parseText(jsonResponse2.toString())

        def policyViewInfo = responseList.get(0).get("policyViewInfo")
        if (null != policyViewInfo) {
            policyViewInfo.each {
                def form_number = it.get("formNumber")
                if (null != form_number) {
                    if (form_number.equals("07-02-1477")) {
                        findAndGetAdditionalNamedInsured(it)
                    }
                }
            }
        }

        println additionalNamedInsuredValue

        def newList = additionalNamedInsuredValue.split(";").join("\n")
        println newList
    }

    def findAndGetAdditionalNamedInsured = { def policyViewItem ->
        def namedInsuredList = policyViewItem.get("formData").get("param_1")
        if (null != namedInsuredList) {
            additionalNamedInsuredValue = namedInsuredList.join(";")
        }
    }

    def additionalNamedInsuredValue = null
    String jsonResponse2 = "[{\n" +
            "  \"policyViewInfo\": [\n" +
            "    {\n" +
            "      \"formNumber\": \"07-10-0542\",\n" +
            "      \"formName\": \"PREMIUM BILL\",\n" +
            "      \"formEffectiveDate\": null,\n" +
            "      \"policySequenceNumber\": null,\n" +
            "      \"issuedDate\": null,\n" +
            "      \"formData\": {\n" +
            "        \"param_1\": null\n" +
            "      }\n" +
            "    },\n" +
            "\t{\n" +
            "\t  \"formNumber\":\"07-02-1477\",\n" +
            "\t\t\"formName\":\"NAMED INSURED\",\n" +
            "\t\t\"formEffectiveDate\":1548478800000,\n" +
            "\t\t\"policySequenceNumber\":\"9998\",\n" +
            "\t\t\"issuedDate\":[\n" +
            "\t\t\t2020,\n" +
            "\t\t\t4,\n" +
            "\t\t\t13\n" +
            "\t\t],\n" +
            "\t\t\"formData\":{\n" +
            "\t\t\t\"param_1\":[\n" +
            "\t\t\t\t\"Your Behavioral Health, LLC\",\n" +
            "\t\t\t\t\"IBHS Group Holdings, LLC\",\n" +
            "\t\t\t\t\"Health Assets Management, LLC\",\n" +
            "\t\t\t\t\"Treatment Concepts, LLC\",\n" +
            "\t\t\t\t\"Infinity Behavioral Health Client Network, LLC\",\n" +
            "\t\t\t\t\"IBHS International, LLC\"\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t}\n" +
            "  ],\n" +
            "  \"effectiveDate\": 1556078400000,\n" +
            "  \"expirationDate\": 1556078400000,\n" +
            "  \"mailingAddress\": \"821 3RD AVE SWFLORIDA\",\n" +
            "  \"status\": \"Working\",\n" +
            "  \"producerName\": \"BRAISHFIELD ASSOCIATES A DIV OF HULL & COMPANY LLC\",\n" +
            "  \"legacySubProducer\": \"99999\",\n" +
            "  \"policyType\": \"UMBRELLA\",\n" +
            "  \"customerState\": \"FLORIDA\",\n" +
            "  \"commission\": null,\n" +
            "  \"expiringPremium\": \"625\",\n" +
            "  \"excessOf\": null,\n" +
            "  \"inExcessOf\": null,\n" +
            "  \"eachOccurrence\": \"1000000\",\n" +
            "  \"aggregate\": \"1000000\",\n" +
            "  \"reinsurance\": \"\",\n" +
            "  \"expiringPolicyReinsurance\": null,\n" +
            "  \"coveragrDescriotion01\": null,\n" +
            "  \"liability\": null,\n" +
            "  \"cpNumber\": \"466011206440\",\n" +
            "  \"uaPhoneNumber\": null,\n" +
            "  \"uaEmailAddress\": null,\n" +
            "  \"producerStreetAddress\": \"P O BOX 691809\",\n" +
            "  \"producerCity\": \"ORLANDO\",\n" +
            "  \"producerState\": \"FL\",\n" +
            "  \"producerZip\": \"32869-0000\",\n" +
            "  \"underwritingCompany\": \"FEDERAL INSURANCE COMPANY\",\n" +
            "  \"annualizedPremium\": \"\$631\",\n" +
            "  \"annualizedTriaPremium\": \"6\",\n" +
            "  \"commissionRate\": \"20\",\n" +
            "  \"totalPremiumChange\": \"631\",\n" +
            "  \"installmentPlan\": \"Prepaid\",\n" +
            "  \"billingType\": \"Agency\",\n" +
            "  \"stateTaxes\": \"No state taxes or surcharges are applicable.\",\n" +
            "  \"surcharges\": null,\n" +
            "  \"customerStreetAddress\": \"821 3RD AVE SW\",\n" +
            "  \"customerCity\": \"LARGO\",\n" +
            "  \"customerZip\": \"33770\",\n" +
            "  \"terrorismExcluded\": \"1\",\n" +
            "  \"branchCode\": \"0847\",\n" +
            "  \"branchAbbreviation\": \"ECE\",\n" +
            "  \"sicSusceptibility\": \"L\",\n" +
            "  \"underliers\": [\n" +
            "    {\n" +
            "      \"underlierName\": \"Fringe Liability\",\n" +
            "      \"carrier\": \"ASPEN AMERICAN INSURANCE COMPANY\",\n" +
            "      \"liabilityName\": \"NON-OWNED & HIRED AUTO LIABILITY\",\n" +
            "      \"policyNumber\": \"TBD\",\n" +
            "      \"inceptionDate\": 1556078400000,\n" +
            "      \"expirationDate\": 1587700800000,\n" +
            "      \"policyViewErrorMessage\": \"Policy View information is missing for that underlyer\",\n" +
            "      \"errorMessage\": \"Underlying Carrier is not chubb company\",\n" +
            "      \"chubbCompany\": false,\n" +
            "      \"valid\": true,\n" +
            "      \"foundInPolicyView\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"underlierName\": \"Fringe Liability\",\n" +
            "      \"carrier\": \"ASPEN AMERICAN INSURANCE COMPANY\",\n" +
            "      \"liabilityName\": \"DIRECTORS & OFFICER'S LIABILITY\",\n" +
            "      \"policyNumber\": \"TBD\",\n" +
            "      \"inceptionDate\": 1556078400000,\n" +
            "      \"expirationDate\": 1587700800000,\n" +
            "      \"policyViewErrorMessage\": \"Policy View information is missing for that underlyer\",\n" +
            "      \"errorMessage\": \"Underlying Carrier is not chubb company\",\n" +
            "      \"chubbCompany\": false,\n" +
            "      \"valid\": true,\n" +
            "      \"foundInPolicyView\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"underlierName\": \"General Liability\",\n" +
            "      \"carrier\": \"ASPEN AMERICAN INSURANCE COMPANY\",\n" +
            "      \"liabilityName\": \"\",\n" +
            "      \"policyNumber\": \"tbd\",\n" +
            "      \"inceptionDate\": 1556078400000,\n" +
            "      \"expirationDate\": 1587700800000,\n" +
            "      \"policyViewErrorMessage\": \"Policy View information is missing for that underlyer\",\n" +
            "      \"errorMessage\": \"Underlying Carrier is not chubb company\",\n" +
            "      \"chubbCompany\": false,\n" +
            "      \"valid\": true,\n" +
            "      \"foundInPolicyView\": false\n" +
            "    }\n" +
            "  ],\n" +
            "  \"faxNumber\": \"8883356615\",\n" +
            "  \"previousPeriodEffectiveDate\": null,\n" +
            "  \"errorLogList\": []\n" +
            "}]";

    String jsonResponse = "[{\n" +
            "\"policyViewInfo\":[\n" +
            "{\n" +
            "\"formNumber\":\"07-02-1477\",\n" +
            "\"formName\":\"NAMED INSURED\",\n" +
            "\"formEffectiveDate\":1548478800000,\n" +
            "\"policySequenceNumber\":\"9998\",\n" +
            "\"issuedDate\":[\n" +
            "2020,\n" +
            "4,\n" +
            "13\n" +
            "],\n" +
            "\"formData\":{\n" +
            "\"param_1\":[\n" +
            "\"Your Behavioral Health, LLC\",\n" +
            "\"IBHS Group Holdings, LLC\",\n" +
            "\"Health Assets Management, LLC\",\n" +
            "\"Treatment Concepts, LLC\",\n" +
            "\"Infinity Behavioral Health Client Network, LLC\",\n" +
            "\"IBHS International, LLC\"\n" +
            "]\n" +
            "}\n" +
            "},\n" +
            "{\n" +
            "\"formNumber\":\"07-10-0542\",\n" +
            "\"formName\":\"PREMIUM BILL\",\n" +
            "\"formEffectiveDate\":null,\n" +
            "\"policySequenceNumber\":null,\n" +
            "\"issuedDate\":null,\n" +
            "\"formData\":{\n" +
            "\"param_1\":null\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"effectiveDate\":1548453600000,\n" +
            "\"expirationDate\":1548453600000,\n" +
            "\"mailingAddress\":\"4620 N STATE RD 7FLORIDA\",\n" +
            "\"status\":\"Bound\",\n" +
            "\"producerName\":\"AON RISK SERVICES CENTRAL INC\",\n" +
            "\"legacySubProducer\":\"99999\",\n" +
            "\"policyType\":\"UMBRELLA\",\n" +
            "\"customerState\":\"FLORIDA\",\n" +
            "\"commission\":null,\n" +
            "\"expiringPremium\":null,\n" +
            "\"excessOf\":null,\n" +
            "\"inExcessOf\":null,\n" +
            "\"eachOccurrence\":\"5000000\",\n" +
            "\"aggregate\":\"5000000\",\n" +
            "\"reinsurance\":\"None\",\n" +
            "\"expiringPolicyReinsurance\":null,\n" +
            "\"coveragrDescriotion01\":null,\n" +
            "\"liability\":null,\n" +
            "\"cpNumber\":\"296011254213\",\n" +
            "\"uaPhoneNumber\":null,\n" +
            "\"uaEmailAddress\":null,\n" +
            "\"producerStreetAddress\":\"4220 DUNCAN AVE STE 401\",\n" +
            "\"producerCity\":\"ST. LOUIS\",\n" +
            "\"producerState\":\"MO\",\n" +
            "\"producerZip\":\"63110-0000\",\n" +
            "\"underwritingCompany\":\"FEDERAL INSURANCE COMPANY\",\n" +
            "\"annualizedPremium\":\"\$0\",\n" +
            "\"annualizedTriaPremium\":null,\n" +
            "\"commissionRate\":\"15\",\n" +
            "\"totalPremiumChange\":\"0\",\n" +
            "\"installmentPlan\":\"Prepaid\",\n" +
            "\"billingType\":\"Agency\",\n" +
            "\"stateTaxes\":\"No state taxes or surcharges are applicable.\",\n" +
            "\"surcharges\":null,\n" +
            "\"customerStreetAddress\":\"4620 N STATE RD 7\",\n" +
            "\"customerCity\":\"LAUDERDALE LAKES\",\n" +
            "\"customerZip\":\"33309\",\n" +
            "\"terrorismExcluded\":\"0\",\n" +
            "\"branchCode\":\"0848\",\n" +
            "\"branchAbbreviation\":\"WCE\",\n" +
            "\"sicSusceptibility\":null,\n" +
            "\"underliers\":[\n" +
            "{\n" +
            "\"underlierName\":\"Fringe Liability\",\n" +
            "\"carrier\":\"FEDERAL INSURANCE COMPANY\",\n" +
            "\"liabilityName\":\"EMPLOYEE BENEFITS LIABILITY\",\n" +
            "\"policyNumber\":\"3603-99-16\",\n" +
            "\"inceptionDate\":1548453600000,\n" +
            "\"expirationDate\":1579989600000,\n" +
            "\"policyViewErrorMessage\":null,\n" +
            "\"errorMessage\":null,\n" +
            "\"valid\":true,\n" +
            "\"chubbCompany\":true,\n" +
            "\"foundInPolicyView\":true\n" +
            "},\n" +
            "{\n" +
            "\"underlierName\":\"Fringe Liability\",\n" +
            "\"carrier\":\"GREAT NORTHERN INSURANCE COMPANY\",\n" +
            "\"liabilityName\":\"NON-OWNED & HIRED AUTO LIABILITY\",\n" +
            "\"policyNumber\":\"7539 66 53\",\n" +
            "\"inceptionDate\":1548453600000,\n" +
            "\"expirationDate\":1579989600000,\n" +
            "\"policyViewErrorMessage\":null,\n" +
            "\"errorMessage\":\"Chubb Underlyer Fringe Liability is not found in CUW\",\n" +
            "\"valid\":false,\n" +
            "\"chubbCompany\":true,\n" +
            "\"foundInPolicyView\":true\n" +
            "},\n" +
            "{\n" +
            "\"underlierName\":\"Fringe Liability\",\n" +
            "\"carrier\":\"THE INSURANCE COMPANY OF THE STATE OF PENNSYLVANIA\",\n" +
            "\"liabilityName\":\"FOREIGN AUTOMOBILE LIABILITY\",\n" +
            "\"policyNumber\":\"WS11012799\",\n" +
            "\"inceptionDate\":1548453600000,\n" +
            "\"expirationDate\":1579989600000,\n" +
            "\"policyViewErrorMessage\":\"Policy View information is missing for that underlyer\",\n" +
            "\"errorMessage\":\"Underlying Carrier is not chubb company\",\n" +
            "\"valid\":true,\n" +
            "\"chubbCompany\":false,\n" +
            "\"foundInPolicyView\":false\n" +
            "},\n" +
            "{\n" +
            "\"underlierName\":\"Fringe Liability\",\n" +
            "\"carrier\":\"THE INSURANCE COMPANY OF THE STATE OF PENNSYLVANIA\",\n" +
            "\"liabilityName\":\"FOREIGN EMPLOYER'S LIABILITY\",\n" +
            "\"policyNumber\":\"WS11012799\",\n" +
            "\"inceptionDate\":1548453600000,\n" +
            "\"expirationDate\":1579989600000,\n" +
            "\"policyViewErrorMessage\":\"Policy View information is missing for that underlyer\",\n" +
            "\"errorMessage\":\"Underlying Carrier is not chubb company\",\n" +
            "\"valid\":true,\n" +
            "\"chubbCompany\":false,\n" +
            "\"foundInPolicyView\":false\n" +
            "},\n" +
            "{\n" +
            "\"underlierName\":\"Fringe Liability\",\n" +
            "\"carrier\":\"THE INSURANCE COMPANY OF THE STATE OF PENNSYLVANIA\",\n" +
            "\"liabilityName\":\"FOREIGN GENERAL LIABILITY\",\n" +
            "\"policyNumber\":\"WS11012799\",\n" +
            "\"inceptionDate\":1548453600000,\n" +
            "\"expirationDate\":1579989600000,\n" +
            "\"policyViewErrorMessage\":\"Policy View information is missing for that underlyer\",\n" +
            "\"errorMessage\":\"Underlying Carrier is not chubb company\",\n" +
            "\"valid\":true,\n" +
            "\"chubbCompany\":false,\n" +
            "\"foundInPolicyView\":false\n" +
            "}\n" +
            "],\n" +
            "\"faxNumber\":\"3148540890\",\n" +
            "\"previousPeriodEffectiveDate\":null,\n" +
            "\"errorLogList\":[\n" +
            "]\n" +
            "}]"
}
