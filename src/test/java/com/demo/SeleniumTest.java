package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Explicit wait tied to this driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                // Pause 5 seconds to see the browser
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    @Test
    public void openHomePage() {
        // Navigate to PetClinic home page
        driver.get("http://localhost:8080/");

        // Wait for navbar to appear
        WebElement navbar = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".navbar-brand"))
        );

        // Assert navbar is visible
        assertTrue(navbar.isDisplayed(), "Navbar should be visible on the home page");
    }
}
