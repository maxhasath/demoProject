package tests;

import data.LoginData;
import factory.PageType;
import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import util.BaseTest;
import utils.JsonReaderHelper;

@Slf4j
public class LoginTest extends BaseTest {

  LoginPage loginPage;
  HomePage homePage;
  LoginData loginData;

  @Override
  @BeforeTest
  public void beforeTest() {
    this.bootstrap();
    try {
      loginData = JsonReaderHelper.getLoginData()[0];
    } catch (FileNotFoundException ex) {
      log.error("FileNotFoundException : ", ex);
    }
  }

  @Override
  @AfterTest
  public void afterTest() {
    this.cleanup();
  }

  @Test(priority = 0)
  public void loginTest() {
    loginPage = (LoginPage) pageFactory.getInstance(PageType.LOGIN);
    homePage = (HomePage) pageFactory.getInstance(PageType.HOME);

    // get the login page title
    String loginPageTitle = loginPage.getLoginTitle();
    Assert.assertTrue(loginPageTitle.toLowerCase().contains(loginData.getTitle()));

    loginPage.login(loginData.getUserName(), loginData.getPassword());
    // check the home page user name to verify success login
    Assert
        .assertTrue(homePage.getHomePageUserName().toLowerCase().contains(loginData.getUserName()));
    log.info("login test successfully completed");
  }
}
