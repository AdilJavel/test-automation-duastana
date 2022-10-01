package com.solotesting.duastana.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitUtil {
    private static WebDriverWaitUtil instance;
    private WebDriverWait webDriverWait;

    private WebDriverWaitUtil()
    {

    }

    public static WebDriverWaitUtil getInstance() {
        if (instance == null) {
            instance = new WebDriverWaitUtil();
        }
        return instance;
    }

    public WebDriverWait getWebDriverWait(WebDriver webDriver)
    {
        if (webDriverWait == null) {
            synchronized (this) {
                if (webDriverWait == null) {
                    webDriverWait= new WebDriverWait(webDriver, 20);
                }
            }
        }
        return webDriverWait;
    }
}
