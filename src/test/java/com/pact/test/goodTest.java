package com.pact.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
// import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// import org.openqa.selenium.Keys;

public class goodTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://eko-dev.ekoapp.com/";


    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void testLoginWithIncorrectUser() {
        driver.findElement(By.id("username")).sendKeys("chaddanai2");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[contains(@class,'styles__Submit')]")).click();
        // String userMessage = driver.findElement(By.xpath("//div[contains(@class,'ant-notification-notice-message')]")).getText();
        String userMessage = driver.findElement(By.xpath("//*[contains(text(),'The username and password you entered did not match our records. Please double-check and try again.')]")).getText();
        Assert.assertEquals(userMessage, "The username and password you entered did not match our records. Please double-check and try again.");
    }
}