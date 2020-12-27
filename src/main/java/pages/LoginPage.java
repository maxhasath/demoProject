package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends Page {

  By txtUserName = By.xpath("//input[@name='uid']");
  By txtPassword = By.xpath("//input[@name='password']");
  By lblTitleText = By.className("barone");
  By btnLogin = By.name("btnLogin");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void setUserName(String userName) {
    this.sendKeys(txtUserName, userName);
  }

  public void setPassword(String password) {
    this.sendKeys(txtPassword, password);
  }

  public void clickLogin() {
    this.click(btnLogin);
  }

  public String getLoginTitle() {
    return this.getText(lblTitleText);
  }

  public void login(String userName, String passWord) {
    this.setUserName(userName);
    this.setPassword(passWord);
    this.clickLogin();
  }
}


