package tests;

import data.LoginData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import org.testng.annotations.BeforeTest;
import utils.JsonReaderHelper;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    LoginData loginData;

    @BeforeTest
    public void setUp() throws FileNotFoundException {
        loginData = JsonReaderHelper.getLoginData()[0];
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/V4/");
    }

    @Test(priority = 0)
    public void loginTest(){
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        String loginPageTitle = loginPage.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains(loginData.getTitle()));
        loginPage.login(loginData.getUserName(),loginData.getPassword());
        Assert.assertTrue(homePage.getHomePageUserName().toLowerCase().contains(loginData.getUserName()));

    }


    @AfterTest
    public void postCondition(){
        driver.quit();
    }
}
