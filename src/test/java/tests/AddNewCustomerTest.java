package tests;

import conditions.AuthCondition;
import factory.PageType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

@Slf4j
public class AddNewCustomerTest extends AuthCondition {

  HomePage homePage;

  @Override
  @BeforeMethod
  public void beforeTest() {
    this.bootstrap();
    this.preExecute();
  }

  @Override
  @AfterMethod
  public void afterTest() {
    this.postExecute();
    this.cleanup();
  }

  @Test(priority = 0)
  public void AddNewCustomerTest() {
    homePage = (HomePage) pageFactory.getInstance(PageType.HOME);
    // click add new customer link
    homePage.clickNewCustomer();
  }
}
