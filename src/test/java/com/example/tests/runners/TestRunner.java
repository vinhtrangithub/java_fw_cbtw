package com.example.tests.runners;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        String platform = System.getProperty("platform", "WEB");
        String suiteName = System.getProperty("suite", "all");

        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("AutomationSuite");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(4);

        if (suiteName.equals("all") || suiteName.equals("web")) {
            XmlTest webTest = new XmlTest(suite);
            webTest.setName("WebTests");
            webTest.addParameter("platform", "WEB");
            XmlClass webTestClass = new XmlClass("com.example.tests.web.WebLoginTest");
            webTest.setXmlClasses(List.of(webTestClass));
        }

        if (suiteName.equals("all") || suiteName.equals("mobile")) {
            XmlTest mobileTest = new XmlTest(suite);
            mobileTest.setName("MobileTests");
            mobileTest.addParameter("platform", platform.equals("ANDROID") ? "ANDROID" : "IOS");
            XmlClass mobileTestClass = new XmlClass("com.example.tests.mobile.MobileLoginTest");
            mobileTest.setXmlClasses(List.of(mobileTestClass));
        }

        if (suiteName.equals("all") || suiteName.equals("api")) {
            XmlTest apiTest = new XmlTest(suite);
            apiTest.setName("ApiTests");
            apiTest.addParameter("platform", "API");
            XmlClass apiTestClass = new XmlClass("com.example.tests.api.ApiTest");
            apiTest.setXmlClasses(List.of(apiTestClass));
        }

        testNG.setXmlSuites(Arrays.asList(suite));
        testNG.run();
    }
}