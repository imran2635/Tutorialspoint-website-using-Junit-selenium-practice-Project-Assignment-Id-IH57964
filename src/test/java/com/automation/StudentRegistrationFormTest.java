package com.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRegistrationFormTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    // ─── Test Data ────────────────────────────────────────────────────────────
    private static final String URL      = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
    private static final String NAME     = "Imran Hossain";
    private static final String EMAIL    = "hossainmim2635@gmail.com";
    private static final String MOBILE   = "0167467464";
    private static final String DOB      = "1988-06-06";   // format: yyyy-MM-dd (HTML date input)
    private static final String SUBJECTS = "Student Registration";
    private static final String ADDRESS  = "Dhaka, Bangladesh";
    private static final String STATE    = "Uttar Pradesh";
    private static final String CITY     = "Meerut";
    // ─────────────────────────────────────────────────────────────────────────

    @BeforeAll
    static void setUp() {
        // WebDriverManager auto-downloads the correct ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Remove the next line if you want to see the browser window
        // options.addArguments("--headless=new");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─── Step 1: Navigate to URL ──────────────────────────────────────────────
    @Test
    @Order(1)
    @DisplayName("Step 1 - Navigate to the Practice Form URL")
    void navigateToUrl() {
        driver.get(URL);
        wait.until(ExpectedConditions.titleContains("Selenium"));
        System.out.println("✅ Navigated to: " + driver.getCurrentUrl());
        Assertions.assertTrue(driver.getCurrentUrl().contains("selenium_automation_practice"),
                "URL should contain 'selenium_automation_practice'");
    }

    // ─── Step 2: Fill Name ────────────────────────────────────────────────────
    @Test
    @Order(2)
    @DisplayName("Step 2a - Fill Name field")
    void fillName() {
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.clear();
        nameField.sendKeys(NAME);
        System.out.println("✅ Name entered: " + NAME);
        Assertions.assertEquals(NAME, nameField.getAttribute("value"));
    }

    // ─── Step 2b: Fill Email ──────────────────────────────────────────────────
    @Test
    @Order(3)
    @DisplayName("Step 2b - Fill Email field")
    void fillEmail() {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys(EMAIL);
        System.out.println("✅ Email entered: " + EMAIL);
        Assertions.assertEquals(EMAIL, emailField.getAttribute("value"));
    }

    // ─── Step 2c: Select Gender ───────────────────────────────────────────────
    @Test
    @Order(4)
    @DisplayName("Step 2c - Select Gender: Male")
    void selectGender() {
        // Select "Male" radio button
        WebElement maleRadio = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("gender")));
        if (!maleRadio.isSelected()) {
            maleRadio.click();
        }
        System.out.println("✅ Gender selected: Male");
        Assertions.assertTrue(maleRadio.isSelected(), "Male radio should be selected");
    }

    // ─── Step 2d: Fill Mobile ─────────────────────────────────────────────────
    @Test
    @Order(5)
    @DisplayName("Step 2d - Fill Mobile number")
    void fillMobile() {
        WebElement mobileField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("mobile")));
        mobileField.clear();
        mobileField.sendKeys(MOBILE);
        System.out.println("✅ Mobile entered: " + MOBILE);
        Assertions.assertEquals(MOBILE, mobileField.getAttribute("value"));
    }

    // ─── Step 2e: Fill Date of Birth ──────────────────────────────────────────
    @Test
    @Order(6)
    @DisplayName("Step 2e - Fill Date of Birth")
    void fillDateOfBirth() {
        WebElement dobField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dob")));
        dobField.clear();
        // Use JavaScript to set value for date input (more reliable cross-browser)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", dobField, DOB);
        System.out.println("✅ Date of Birth entered: " + DOB);
        Assertions.assertEquals(DOB, dobField.getAttribute("value"));
    }

    // ─── Step 2f: Fill Subjects ───────────────────────────────────────────────
    @Test
    @Order(7)
    @DisplayName("Step 2f - Fill Subjects field")
    void fillSubjects() {
        WebElement subjectsField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("subjects")));
        subjectsField.clear();
        subjectsField.sendKeys(SUBJECTS);
        System.out.println("✅ Subjects entered: " + SUBJECTS);
        Assertions.assertEquals(SUBJECTS, subjectsField.getAttribute("value"));
    }

    // ─── Step 2g: Select Hobbies ──────────────────────────────────────────────
    @Test
    @Order(8)
    @DisplayName("Step 2g - Select Hobby: Sports")
    void selectHobbies() {
        // Select "Sports" checkbox
        WebElement sportsCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("hobbies")));
        if (!sportsCheckbox.isSelected()) {
            sportsCheckbox.click();
        }
        System.out.println("✅ Hobby selected: Sports");
        Assertions.assertTrue(sportsCheckbox.isSelected(), "Sports checkbox should be checked");
    }

    // ─── Step 2h: Upload Picture ──────────────────────────────────────────────
    @Test
    @Order(9)
    @DisplayName("Step 2h - Upload Picture (file input)")
    void uploadPicture() {
        WebElement pictureInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input#picture.file-input")));  // matches id + class

        String imagePath = "C:/Users/h/Downloads/545340.jpg";  // your exact file path

        pictureInput.sendKeys(imagePath);  // sendKeys works directly on <input type="file">
        System.out.println("✅ Picture uploaded from: " + imagePath);
    }

    // ─── Step 2i: Fill Current Address ────────────────────────────────────────
    @Test
    @Order(10)
    @DisplayName("Step 2i - Fill Current Address")
    void fillAddress() {
        WebElement addressField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("textarea#picture")));  // textarea with id="picture"

        addressField.clear();
        addressField.sendKeys(ADDRESS);
        System.out.println("✅ Address entered: " + ADDRESS);
        Assertions.assertEquals(ADDRESS, addressField.getAttribute("value"));
    }

    // ─── Step 2j: Select State ────────────────────────────────────────────────
    @Test
    @Order(11)
    @DisplayName("Step 2j - Select State: Uttar Pradesh")
    void selectState() {
        WebElement stateDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("state")));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText(STATE);
        System.out.println("✅ State selected: " + STATE);
        Assertions.assertEquals(STATE, stateSelect.getFirstSelectedOption().getText());
    }

    // ─── Step 2k: Select City ─────────────────────────────────────────────────
    @Test
    @Order(12)
    @DisplayName("Step 2k - Select City: Meerut")
    void selectCity() {
        // Wait for city dropdown to be populated after state selection
        WebElement cityDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("city")));
        Select citySelect = new Select(cityDropdown);
        citySelect.selectByVisibleText(CITY);
        System.out.println("✅ City selected: " + CITY);
        Assertions.assertEquals(CITY, citySelect.getFirstSelectedOption().getText());
    }

    // ─── Step 3: Click Login Button ───────────────────────────────────────────
    @Test
    @Order(13)
    @DisplayName("Step 3 - Click the Login button")
    void clickLoginButton() {
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[type='submit'], input[type='submit'], .btn-primary")));

        // Scroll into view before clicking
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", loginButton);

        loginButton.click();
        System.out.println("✅ Login button clicked!");

        // Optional: Wait a moment to observe the result
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ Current URL after login click: " + driver.getCurrentUrl());
    }
}
