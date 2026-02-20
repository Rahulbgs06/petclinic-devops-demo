package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class SeleniumTest {

	private WebDriver driver;

	private WebDriverWait wait;

	// Dynamic port
	String port = System.getProperty("app.port", "8082");

	@BeforeEach
	public void setUp() {
		// WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			try {
				Thread.sleep(5000); // Pause to see the browser
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.quit();
		}
	}

	@Test
	public void openHomePage() {
		// Navigate to PetClinic home page
		driver.get("http://localhost:" + port);

		// Wait for navbar to appear
		WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".navbar-brand")));

		// Assert navbar is visible
		assertTrue(navbar.isDisplayed(), "Navbar should be visible on the home page");
	}

}