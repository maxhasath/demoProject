package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

  By homePageUserName = By.xpath("//table//tbody//tr[@class='heading3']/td");
  By homePageNewCustomer = By.xpath("//ul[@class='menusubnav']//li[2]/a");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public String getHomePageUserName() {
    return this.getText(homePageUserName);
  }

  public void clickNewCustomer() {
    this.click(homePageNewCustomer);
  }
}
