package com.solotesting.duastana.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSettings {
    PropertiesReader configFactory = ConfigFactory.create(PropertiesReader.class);
    WebDriver webDriver;
    public void initDriver() {

            switch (configFactory.browser()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
            }
            webDriver.manage().window().maximize();

    }

    public WebDriver getDriver()
    {
        return webDriver;
    }

}
