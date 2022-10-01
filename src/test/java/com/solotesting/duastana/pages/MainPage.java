package com.solotesting.duastana.pages;

import com.solotesting.duastana.util.ActionsUtil;
import com.solotesting.duastana.util.PropertiesUtil;
import com.solotesting.duastana.util.WebDriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import static com.solotesting.duastana.constants.NameConstants.*;

public class MainPage {
    public static final Properties properties= PropertiesUtil.getInstance().getProperties();
    private static WebDriverWaitUtil webDriverWaitUtil =WebDriverWaitUtil.getInstance();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static MainPage instance;
    private static WebDriver webDriver;
    private WebElement profileDropElement;
    private WebElement logOutButton;
    private WebElement profileButton;
    private WebElement curTableButton;
    private WebElement teachersTabButton;
    private WebElement transcriptTabButton;
    private WebElement diseaseTabButton;
    private WebElement certificateTabButton;
    private WebElement logoutTabButton;
    private WebElement typeOfCertificateButton;
    private WebElement howToSendButton;
    private WebElement typeOfCertificate;
    private WebElement howToSend;
    private WebElement switcherButton;
    private String color;

    public MainPage(WebDriver webDriver) {
        MainPage.webDriver = webDriver;
    }

    public static MainPage getInstance(WebDriver webDriver) {
        if(instance==null) {
            instance = new MainPage(webDriver);
        }
        return instance;
    }

    public WebElement getProfileDropElement() {
        profileDropElement = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_DROP_XPATH)));
        return profileDropElement;
    }

    public WebElement getProfileButton() {
        Actions actions = actionsUtil.getActions(webDriver);
        actions.moveToElement(getProfileDropElement()).perform();
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(PROFILE_BUTTON_XPATH))));
        profileButton = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_BUTTON_XPATH)));
        return profileButton;
    }
    public WebElement getLogOutButton() {
        Actions actions = actionsUtil.getActions(webDriver);
        actions.moveToElement(getProfileDropElement()).perform();
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(PROFILE_LOGOUT_XPATH))));
        logOutButton = webDriver.findElement(By.xpath(properties.getProperty(PROFILE_LOGOUT_XPATH)));
        return logOutButton;
    }

    public WebElement getCurTableButton() {
        WebDriverWait wait = webDriverWaitUtil.getWebDriverWait(webDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(CUR_TABLE_XPATH))));
        curTableButton = webDriver.findElement(By.xpath(properties.getProperty(CUR_TABLE_BUTTON_XPATH)));
        return curTableButton;
    }

    public WebElement getTeachersTabButton() {
        teachersTabButton =  webDriver.findElement(By.xpath(properties.getProperty(TEACHERS_TAB_BUTTON)));
        return teachersTabButton;
    }
    public WebElement getTranscriptTabButton() {
        transcriptTabButton = webDriver.findElement(By.xpath(properties.getProperty(TRANSCRIPT_TAB_BUTTON)));
        return transcriptTabButton;
    }
    public WebElement getDiseaseTabButton() {
        diseaseTabButton = webDriver.findElement(By.xpath(properties.getProperty(DISEASE_TAB_BUTTON)));
        return diseaseTabButton;
    }
    public WebElement getCertificateTabButton() {
        certificateTabButton = webDriver.findElement(By.xpath(properties.getProperty(CERTIFICATE_TAB_BUTTON)));
        return certificateTabButton;
    }

    public WebElement getTypeOfCertificateButton() {
        typeOfCertificateButton = webDriver.findElement(By.xpath(properties.getProperty(TYPE_OF_CERTIFICATE_BUTTON_XPATH)));
        return typeOfCertificateButton;
    }
    public WebElement getHowToSendButton() {
        howToSendButton = webDriver.findElement(By.xpath(properties.getProperty(HOW_TO_SEND_CERTIFICATE_BUTTON_XPATH)));
        return howToSendButton;
    }

    public WebElement getLogoutTabButton() {
        logoutTabButton = webDriver.findElement(By.xpath(properties.getProperty(LOGOUT_TAB_BUTTON)));
        return logoutTabButton;
    }

    public WebElement getSwitcherButton() {
        switcherButton = webDriver.findElement(By.xpath(properties.getProperty(SWITCHER_BUTTON_XPATH)));
        return switcherButton;
    }
    public String getColor() {
        color = Color.fromString(String.valueOf((webDriver.findElement(By.tagName("body"))).getCssValue("background-color"))).asHex();
        return color;
    }
}
