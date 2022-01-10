package com.challenge;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	private WebDriver driver;

	@Before
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:4000/login");
	}

	@Test
	public void LoginTest() {

		try {
			String email = "email";
			WebElement emailElemnt = driver.findElement(By.id(email));
			emailElemnt.clear();
			emailElemnt.sendKeys("liseht@");

			String pass = "password";
			WebElement passElement = driver.findElement(By.id(pass));
			passElement.clear();
			passElement.sendKeys("liseht@");

			String btn = "login";
			WebElement registerElement = driver.findElement(By.id(btn));
			registerElement.click();

			String msg = "msg";
			WebElement textMessage = driver.findElement(By.id(msg));

			assertTrue("LOGIN VALID".equals(textMessage.getText()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
