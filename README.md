# Selenium Automation – TutorialsPoint Practice Form

## Tech Stack
| Tool | Version |
|------|---------|
| Java | 11+ |
| Selenium | 4.18.1 |
| JUnit | 5 (Jupiter) |
| WebDriverManager | 5.7.0 |
| Build Tool | Gradle |

---

## Project Structure

```
selenium-practice/
├── build.gradle
├── settings.gradle
├── README.md
└── src/
    └── test/
        ├── java/com/automation/
        │   └── StudentRegistrationFormTest.java
        └── resources/
            └── (place your image file here as "download (1).jpg")
```

---

## Prerequisites

1. **Java 11+** installed and `JAVA_HOME` set
2. **Google Chrome** browser installed
3. **Gradle** installed (or use the Gradle wrapper)

> **Note:** ChromeDriver is auto-managed by WebDriverManager — no manual download needed!

---

## How to Run

### Run all tests
```bash
./gradlew test
```

### Run a specific test method
```bash
./gradlew test --tests "com.automation.StudentRegistrationFormTest.clickLoginButton"
```

### Run with a visible browser (default)
The browser window is visible by default. To run **headless**, uncomment this line in the test:
```java
options.addArguments("--headless=new");
```

---

## Test Steps (in order)

| Order | Test Method | Action |
|-------|-------------|--------|
| 1 | `navigateToUrl` | Opens the TutorialsPoint practice form URL |
| 2 | `fillName` | Types "Pias Paul" into the Name field |
| 3 | `fillEmail` | Types "piaspaul111@gmail.com" into the Email field |
| 4 | `selectGender` | Selects the "Male" radio button |
| 5 | `fillMobile` | Types "01690134270" into the Mobile field |
| 6 | `fillDateOfBirth` | Sets Date of Birth to 06/06/1988 |
| 7 | `fillSubjects` | Types "jhguetgrtg" into the Subjects field |
| 8 | `selectHobbies` | Checks the "Sports" checkbox |
| 9 | `uploadPicture` | Uploads an image from `src/test/resources/` |
| 10 | `fillAddress` | Types "jgyuyguyg" into the Current Address field |
| 11 | `selectState` | Selects "Uttar Pradesh" from the State dropdown |
| 12 | `selectCity` | Selects "Meerut" from the City dropdown |
| 13 | `clickLoginButton` | Clicks the Login/Submit button |

---

## Image Upload Setup
Place your test image at:
```
src/test/resources/download (1).jpg
```
The test will skip (not fail) if the file is missing.

---

## Test Report
After running, view the HTML report at:
```
build/reports/tests/test/index.html
```
