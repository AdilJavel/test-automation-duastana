package com.solotesting.duastana;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.solotesting.duastana.constants.NameConstants;
import com.solotesting.duastana.pages.LoginPage;
import com.solotesting.duastana.pages.MainPage;
import com.solotesting.duastana.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import static com.solotesting.duastana.constants.NameConstants.*;

public class MainTesting {

    DriverSettings driverSettings = new DriverSettings();
    public static ExtentReports extent;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentTest extentTest;
    private static Properties properties = PropertiesUtil.getInstance().getProperties();
    private static ActionsUtil actionsUtil = ActionsUtil.getInstance();
    private static WebDriverWaitUtil webDriverWaitUtil = WebDriverWaitUtil.getInstance();
    private WebDriver webDriver;
    static ExcelUtil excel = new ExcelUtil();
    static String excelFilePath ="C:\\Users\\Javel\\Desktop\\Testing\\test-automation-duastana\\src\\test\\resources\\testdata\\ExTest.xlsx";


    @BeforeTest
    public void setUp() {
        excel.setExcelRow(0);
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        int col = excel.getExcelCol();
        int row = excel.getExcelRow();
        if(result.getStatus()==ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, result.getTestName());
            excel.setCellValue(row,col,"FAILURE", excelFilePath);
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getTestName());
            excel.setCellValue(row,col,"PASSED", excelFilePath);
        }
        else {
            extentTest.log(Status.SKIP, result.getTestName());
            excel.setCellValue(row,col,"SKIPPED", excelFilePath);
        }
    }
    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }

    @BeforeMethod
    public void dataDrivenTesting() throws Exception {
        excel.setExcelFile(excelFilePath);
        excel.incExcelRow();
        int i = excel.getExcelRow();
        excel.setExcelCol(4);

    }




        //Test001
    @Test(groups = {"simpleLogin", "dropGroup"})
    public void logIn() {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
        LoginPage loginPage = LoginPage.getInstance(webDriver);
        loginPage.getUsernameElement().sendKeys(properties.getProperty(TEST_VALUES));
        loginPage.getPasswordElement().sendKeys(properties.getProperty(TEST_VALUES));
        loginPage.getEnterButton().click();
        extentTest = extent.createTest("Test");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://du.astanait.edu.kz");
    }
    //Test002
    @Test(groups = {"loginWithId"})

    public void logInWithId() {
//        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
        LoginPage loginPage = LoginPage.getInstance(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // seconds
        loginPage.getLoginIdElement().click();//redirects to outlook
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(properties.getProperty(OUTLOOK_LOGIN_NAME))));
        loginPage.getUsernameIdElement().sendKeys(properties.getProperty(LOGIN_VALUE), Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(properties.getProperty(OUTLOOK_PASS_NAME))));
        loginPage.getPasswordIdElement().sendKeys(properties.getProperty(PASS_VALUE), Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(OUTLOOK_BUTTON_XPATH))));
        loginPage.getEnterIdButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(MAIN_PAGE_COL_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_KZ));
    }
    @BeforeGroups("logoutGroup")
    public void logInId() {
        webDriver = driverSettings.getDriver();
        webDriver.navigate().to(properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
        LoginPage loginPage = LoginPage.getInstance(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // seconds
        loginPage.getLoginIdElement().click();//redirects to main page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(MAIN_PAGE_COL_XPATH))));
    }

    //Test003
    @BeforeGroups("profilePage")
    @Test (groups = {"mainPage", "dropGroup"})
    public void testProfilePage() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // seconds
        MainPage mainPage = MainPage.getInstance(webDriver);
        mainPage.getProfileButton().click();
        extentTest = extent.createTest("Test");
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_PROFILE_KZ));
    }

    //Test004
    @Test (groups = {"mainPage", "profilePage", "dropGroup"})
    public void testCurTable() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver,10); //seconds
        MainPage mainPage = MainPage.getInstance(webDriver);
        mainPage.getCurTableButton().click();
        extentTest = extent.createTest("Test");
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(CUR_TABLE_CON_XPATH))).isDisplayed());
    }

    //Test005
    @Test(groups = {"mainPage", "dropGroup"})
    public void testTeacherTab() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        MainPage mainpage = MainPage.getInstance(webDriver);
        mainpage.getTeachersTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(TEACHERS_TABLE_EX_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(TEACHERS_TABLE_EX_XPATH))).isDisplayed());
    }

    //Test006
    @Test(groups = {"mainPage", "dropGroup"})
    public void testTranscriptTab() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        MainPage mainpage = MainPage.getInstance(webDriver);
        mainpage.getTranscriptTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(TRANSCRIPT_TABLE_EX_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(TRANSCRIPT_TABLE_EX_XPATH))).isDisplayed());
    }

    //Test007
    @Test(groups = {"mainPage", "dropGroup"})
    public void testDiseaseTab() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        MainPage mainpage = MainPage.getInstance(webDriver);
        mainpage.getDiseaseTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(DISEASE_TABLE_HEADER_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(DISEASE_TABLE_XPATH))).isDisplayed());
    }

    //Test008
    @Test(groups = {"mainPage", "dropGroup"})
    public void testCertificateTab() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        MainPage mainpage = MainPage.getInstance(webDriver);
        mainpage.getCertificateTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(CERTIFICATE_TABLE_HEADER_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(CERTIFICATE_TABLE_XPATH))).isDisplayed());
    }
    //Test009
    @Test(groups = {"mainPage", "dropGroup"})
    public void testLogoutTab() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        MainPage mainpage = MainPage.getInstance(webDriver);
        mainpage.getLogoutTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOGIN_PAGE_LOGO_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
    }

    //Test010
    @Test (groups = {"mainPage", "dropGroup", "logoutGroup"})
    public void testLogout() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // seconds
        MainPage mainPage = MainPage.getInstance(webDriver);
        mainPage.getLogOutButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOGIN_PAGE_LOGO_XPATH))));
        extentTest = extent.createTest("Test");
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
    }

    //Test011
    @Test (groups = {"mainPage", "dropGroup"})
    public void testSwitcher() {
        webDriver = driverSettings.getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // seconds
        MainPage mainPage = MainPage.getInstance(webDriver);
        String firstColor = mainPage.getColor();
        mainPage.getSwitcherButton().click();
        String secondColor = mainPage.getColor();
        mainPage.getSwitcherButton().click();
        extentTest = extent.createTest("Test");
        Assert.assertNotEquals(firstColor,secondColor);
    }




    @AfterMethod
    public void sleepTest() throws InterruptedException {
        Thread.sleep(400);
    }


}
