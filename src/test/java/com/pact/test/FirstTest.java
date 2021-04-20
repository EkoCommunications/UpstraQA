package com.pact.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	
	
	public void firstTest() {
		System.setProperty("webdriver.chromr.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com");
		System.out.print("Open");
		driver.quit();
	}

}
