# Cross-Platform Test Automation Framework (Java)

A clean, scalable and production-ready **automation framework** supporting:

- 🌐 **Web** testing → Selenium WebDriver  
- 📱 **Mobile** testing → Appium (Android + iOS)  
- 🔌 **API** testing → RestAssured  

Built with modern Java practices, TestNG, Page Object Model, parallel execution support, beautiful reporting and easy CI/CD integration.

---

## ✨ Key Highlights

- Unified driver management for Web, Mobile & API
- Thread-safe parallel test execution
- Custom TestNG annotations to filter test types
- JSON-powered data-driven testing
- Automatic screenshots + rich HTML reports (ExtentReports)
- Environment-aware configuration (QA / Staging / Prod)
- Maven-based → simple setup & CI-ready
- Highly extensible architecture

---

## 🏗 Project Structure Overview

automation-framework/
├── src/
│   ├── main/
│   │   ├── java/com/example/framework/
│   │   │   ├── api/                  → RestAssured helpers, request builders, DTOs
│   │   │   ├── annotations/          → @WebTest, @MobileTest, ...
│   │   │   ├── config/               → Property reader & report manager
│   │   │   ├── core/                 → BaseTest, BasePage, TestContext (thread-local)
│   │   │   ├── drivers/              → DriverFactory + platform-specific configs
│   │   │   ├── enums/                → PlatformType, Environment
│   │   │   ├── listeners/            → Custom TestNG listener for annotation filtering
│   │   │   ├── pages/                → POM classes
│   │   │   │   ├── web/             → Web page objects
│   │   │   │   └── mobile/          → Mobile screen objects
│   │   │   └── utils/                → Waits, assertions, JSON reader, screenshot capture
│   │   └── resources/
│   │       ├── config.properties
│   │       └── testdata/
│   │           └── testdata.json
│   └── test/
│       └── java/com/example/tests/
│           ├── api/
│           ├── mobile/
│           ├── web/
│           └── runners/             → Programmatic runner for CI
├── .github/workflows/                → GitHub Actions example
├── pom.xml
├── testng.xml
└── README.md

---

## ⚡ Quick Start

### 1. Prerequisites

- Java 17 (or higher)
- Maven 3.8+
- Node.js + Appium 
- Chrome / Firefox + matching driver
- Android SDK + emulator/device (cho Android)
- Xcode + simulator/device

### 2. Clone & Install

```bash
git clone <your-repo-url>
cd automation-framework

mvn clean install
```

### 3. Configuration
 - Modify the file src/main/resources/config.properties:
```
 # env
environment      = qa
platform         = WEB                # WEB | ANDROID | IOS | API

# Web
browser          = chrome
web.url          = https://example.com

# Mobile (Appium)
appium.server    = http://127.0.0.1:4723
android.app.path = /path/to/your-app.apk
ios.app.path     = /path/to/your-app.ipa
android.device   = emulator-5554
ios.device       = iPhone 15

# API
api.base.url     = https://api.example.com
```

### 4. Run Test
- All Test Suite

```bash
mvn clean test

- Run Only one platform

```bash
# Web
mvn clean test -Dplatform=WEB
# Android
mvn clean test -Dplatform=ANDROID
# API
mvn clean test -Dplatform=API

- Run via TestRunner

```bash
mvn exec:java -Dexec.mainClass="com.example.tests.runners.TestRunner" -Dplatform=WEB

## 🚀 CI/CD – GitHub Actions
Create the file ".github/workflows/ci.yml":

name: Automated Testing

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        platform: [WEB, ANDROID, API]

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Install Appium (mobile)
        if: matrix.platform == 'ANDROID'
        run: npm install -g appium

      - name: Run tests
        run: mvn clean test -Dplatform=${{ matrix.platform }}

      - name: Upload test report
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: report-${{ matrix.platform }}
          path: reports/
