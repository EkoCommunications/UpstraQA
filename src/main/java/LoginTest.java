package com.pact.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * <b>Selenium WebDriver Examples </b>
 * <p>
 * PTL HRM System : Login Test Cases
 */
public class LoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://hrm.pragmatictestlabs.com";


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
    public void testLoginWithValidCredentials() {

        //<input name="txtUsername" id="txtUsername" type="text" value="Admin">
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");


        //Check if password input is of type="password"
        Assert.assertEquals(driver.findElement(By.id("txtPassword")).getAttribute("type"), "password");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");


        Assert.assertEquals(driver.findElement(By.id("txtUsername")).getAttribute("value"), "Admin");

        driver.findElement(By.id("btnLogin")).click();
        String strWelcomeMessage = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(strWelcomeMessage, "Welcome Admin");
    }


    @Test
    public void testValidUserLoginUsingKeyboardKeys() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtUsername")).sendKeys(Keys.TAB);

        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("txtPassword")).sendKeys(Keys.RETURN);


        String strWelcomeMessage = driver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(strWelcomeMessage, "Welcome Admin");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("InvalidPWd");
        driver.findElement(By.id("btnLogin")).click();

        ///<span id="spanMessage">Invalid credentials</span>

        String msgError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(msgError, "Invalid credentials");
    }

    @Test
    public void testLoginWithBlankUsername() {
        driver.findElement(By.id("txtUsername")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();

        String msgError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(msgError, "Username cannot be empty");
    }


    @Test
    public void testLoginWithBlankUsernameAndPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("btnLogin")).click();

        String msgError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(msgError, "Username cannot be empty");

    }

    @Test
    public void testLoginWithBlankPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("btnLogin")).click();

        String msgError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(msgError, "Password cannot be empty");

    }


    @Test
    public void testCaseSensitivityOfPassword() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("ptl@#321");
        driver.findElement(By.id("btnLogin")).click();

        String msgError = driver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(msgError, "Invalid credentials");
    }


    @Test
    public void testLogoutFromSystem() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "http://hrm.pragmatictestlabs.com/symfony/web/index.php/auth/login");
        driver.navigate().back();
        Assert.assertFalse(driver.findElement(By.id("welcome")).getText().length() > 0);

    }


}