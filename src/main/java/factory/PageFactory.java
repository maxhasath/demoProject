package factory;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.Page;

/**
 * Use this factory the create any page object
 */
public class PageFactory {

  private final WebDriver driver;

  public PageFactory(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Get Page Object for given type
   *
   * @param type - the page type
   * @return - the page object
   */
  public Page getInstance(PageType type) {
    switch (type) {
      case LOGIN:
        return new LoginPage(this.driver);
      case HOME:
        return new HomePage(this.driver);
      default:
        return null;
    }
  }
}
