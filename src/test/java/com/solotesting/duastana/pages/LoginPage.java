package com.solotesting.duastana.pages;

import com.solotesting.duastana.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import static com.solotesting.duastana.constants.NameConstants.*;

public class LoginPage {
    private static final Properties properties = PropertiesUtil.getInstance().getProperties();
    private static LoginPage instance;
    private static WebDriver webDriver;
    private WebElement usernameElement;
    private WebElement passwordElement;
    private WebElement enterButton;
    private WebElement loginIdElement;
    public LoginPage(WebDriver webDriver)
    {
        LoginPage.webDriver =webDriver;
    }

    public static LoginPage getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new LoginPage(webDriver);
        }
        return instance;
    }

    public WebElement getUsernameElement() {
        usernameElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_USERNAME_XPATH)));
        return usernameElement;
    }

    public WebElement getPasswordElement() {
        passwordElement = webDriver.findElement(By.xpath(properties.getProperty(LOGIN_PASSWORD_XPATH)));
        return passwordElement;
    }

    public WebElement getEnterButton() {
        enterButton = webDriver.findElement(By.xpath(properties.getProperty(ENTER_BUTTON_XPATH)));
        return enterButton;
    }

    public WebElement getLoginIdElement() {
        loginIdElement =webDriver.findElement(By.xpath(properties.getProperty(LOGIN_ID_BUTTON)));
                return loginIdElement;
    }

    public WebElement getUsernameIdElement() {
        usernameElement =webDriver.findElement(By.name("loginfmt"));
                return usernameElement;
    }

    public WebElement getPasswordIdElement() {
        passwordElement = webDriver.findElement(By.name("passwd"));
                return passwordElement;
    }

    public WebElement getEnterIdButton() {
        enterButton = webDriver.findElement(By.xpath("//input[@type=\"submit\"]"));
                return enterButton;
    }

}
