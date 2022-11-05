package stepDefinitions;

import com.solotesting.duastana.pages.LoginPage;
import com.solotesting.duastana.pages.MainPage;
import com.solotesting.duastana.util.DBUtils;
import com.solotesting.duastana.util.DriverSettings;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.solotesting.duastana.constants.NameConstants.*;
import static com.solotesting.duastana.constants.NameConstants.WWW_DU_ASTANA_KZ;
import static com.solotesting.duastana.pages.MainPage.properties;

public class Steps {
    MainPage mainPage;
    LoginPage loginPage;
    WebDriver webDriver;
    DriverSettings driverSettings = new DriverSettings();
    WebDriverWait wait;
    @Before
    public void setUp(Scenario scenario) {

    }

    @Given("^User is on the login page - www\\.du\\.astanait\\.edu\\.kz/login$")
    public void user_is_on_the_login_page_www_du_astanait_edu_kz_login() throws Throwable {
        driverSettings.initDriver();
        webDriver = driverSettings.getDriver();
        wait = new WebDriverWait(webDriver, 10); // seconds
        webDriver.navigate().to(properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
    }


    @When("^User goes to outlook$")
    public void user_goes_to_outlook() throws Throwable {
        loginPage = LoginPage.getInstance(webDriver);
        loginPage.getLoginIdElement().click();//redirects to outlook
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(properties.getProperty(OUTLOOK_LOGIN_NAME))));
    }




    @When("^User on main page$")
    public void user_goes_to_profile_page() throws Throwable {
        webDriver = driverSettings.getDriver();
        mainPage = MainPage.getInstance(webDriver);
        mainPage.getProfileButton().click();
    }

    @Then("^User can see the profile page$")
    public void user_can_see_the_profile_page() throws Throwable {
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_PROFILE_KZ));
    }

    @When("^User on profile page$")
    public void user_on_profile_page() throws Throwable {
        webDriver.navigate().to("www.du.astanait.edu.kz/profile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(CUR_TABLE_XPATH))));
    }

    @Then("^User can see Curriculum$")
    public void user_can_see_Curriculum() throws Throwable {
        mainPage.getCurTableButton().click();
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(CUR_TABLE_CON_XPATH))).isDisplayed());
    }

    @Then("^User can see main page$")
    public void user_can_see_main_page() throws Throwable {
        webDriver.navigate().to("www.du.astanait.edu.kz");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(MAIN_PAGE_COL_XPATH))));
    }

    @Then("^User can see Teachers Tab$")
    public void user_can_see_Teachers_Tab() throws Throwable {

        mainPage.getTeachersTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(TEACHERS_TABLE_EX_XPATH))));
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(TEACHERS_TABLE_EX_XPATH))).isDisplayed());
    }

    @Then("^User can see Transcript Tab$")
    public void user_can_see_Transcript_Tab() throws Throwable {

        mainPage.getTranscriptTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(TRANSCRIPT_TABLE_EX_XPATH))));
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(TRANSCRIPT_TABLE_EX_XPATH))).isDisplayed());
    }

    @Then("^User can see Disease Tab$")
    public void user_can_see_Disease_Tab() throws Throwable {

        mainPage.getDiseaseTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(DISEASE_TABLE_HEADER_XPATH))));
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(DISEASE_TABLE_XPATH))).isDisplayed());
    }

    @Then("^User can see Certificate Tab$")
    public void user_can_see_Certificate_Tab() throws Throwable {

        mainPage.getCertificateTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(CERTIFICATE_TABLE_HEADER_XPATH))));
        Assert.assertTrue(webDriver.findElement(By.xpath(properties.getProperty(CERTIFICATE_TABLE_XPATH))).isDisplayed());
    }

    @Then("^User can change the color$")
    public void user_can_change_the_color() throws Throwable {
        String firstColor = mainPage.getColor();
        mainPage.getSwitcherButton().click();
        String secondColor = mainPage.getColor();
        mainPage.getSwitcherButton().click();
        Assert.assertNotEquals(firstColor,secondColor);
    }

    @Then("^User can log out with tab$")
    public void user_can_log_out_with_tab() throws Throwable {
        mainPage.getLogOutButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOGIN_PAGE_LOGO_XPATH))));
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
    }
    @When("^User on login page$")
    public void user_on_login_page() throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOGIN_PAGE_LOGO_XPATH))));
    }

    @Then("^Log in by cache$")
    public void log_in_by_cache() throws Throwable {
        loginPage.getLoginIdElement().click();//redirects to outlook
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(MAIN_PAGE_COL_XPATH))));
    }

    @Then("^User can log out by button$")
    public void user_can_log_out_by_button() throws Throwable {
        mainPage.getLogoutTabButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(LOGIN_PAGE_LOGO_XPATH))));
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_LOGIN_KZ));
    }

    private String username;
    private String passord;
    @Given("^User enter the inputs$")
    public void user_enter_the_inputs(DataTable arg1) throws Throwable {
        List<List<String>> rows = arg1.asLists(String.class);
        LoginPage loginPage = LoginPage.getInstance(webDriver);
        String usernamedata = (rows.get(0).get(0)).toString();
        loginPage.getUsernameIdElement().sendKeys(usernamedata, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(properties.getProperty(OUTLOOK_PASS_NAME))));
        loginPage.getPasswordIdElement().sendKeys(rows.get(0).get(1).toString(), Keys.ENTER);
    }

    @Then("^User click log in by Outlook$")
    public void user_click_log_in_by_Outlook() throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(OUTLOOK_BUTTON_XPATH))));
        loginPage.getEnterIdButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty(MAIN_PAGE_COL_XPATH))));
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty(WWW_DU_ASTANA_KZ));
    }
}
