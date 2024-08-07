/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SeleniumTests/SeleneseIT.java to edit this template
 */

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Dhanush Bajaj
 */
public class SliderSeleniumTests {
    
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the path for the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        login();
    }

    private void login() {
        driver.get("http://localhost:8080/SliderApp/faces/login.xhtml"); // URL for the login page
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin");
        loginButton.click();

        // Optional: Verify login was successful
        assertTrue(driver.findElement(By.id("logoutButton")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateSlider() {
        driver.get("http://localhost:8080/SliderApp/faces/Create.xhtml");
        WebElement sizeField = driver.findElement(By.id("size"));
        WebElement xField = driver.findElement(By.id("x"));
        WebElement yField = driver.findElement(By.id("y"));
        WebElement currentTravelField = driver.findElement(By.id("currentTravel"));
        WebElement maxTravelField = driver.findElement(By.id("maxTravel"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        sizeField.sendKeys("50");
        xField.sendKeys("10");
        yField.sendKeys("10");
        currentTravelField.sendKeys("50");
        maxTravelField.sendKeys("50");
        submitButton.click();

        // Verify successful creation by checking for the presence of the new record in the list
        driver.get("http://localhost:8080/SliderApp/faces/List.xhtml");
        WebElement newRecord = driver.findElement(By.xpath("//td[contains(text(), '50')]"));
        assertNotNull(newRecord);
    }

    @Test
    public void testEditSlider() {
        driver.get("http://localhost:8080/SliderApp/faces/List.xhtml");
        WebElement editLink = driver.findElement(By.linkText("Edit"));
        editLink.click();

        WebElement sizeField = driver.findElement(By.id("size"));
        sizeField.clear();
        sizeField.sendKeys("60");
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        // Verify successful edit by checking the updated value
        driver.get("http://localhost:8080/SliderApp/faces/List.xhtml");
        WebElement updatedRecord = driver.findElement(By.xpath("//td[contains(text(), '60')]"));
        assertNotNull(updatedRecord);
    }

    @Test
    public void testViewSlider() {
        driver.get("http://localhost:8080/SliderApp/faces/List.xhtml");
        WebElement viewLink = driver.findElement(By.linkText("View"));
        viewLink.click();

        WebElement sizeField = driver.findElement(By.id("size"));
        assertEquals("50", sizeField.getAttribute("value")); // assuming "50" is a value we expect
    }

    @Test
    public void testListSliders() {
        driver.get("http://localhost:8080/SliderApp/faces/List.xhtml");
        WebElement listTable = driver.findElement(By.id("sliderTable"));
        assertNotNull(listTable);
    }
    
}
