package util;

import factory.PageFactory;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {

  public WebDriver driver;
  public PageFactory pageFactory;

  /**
   * Before test - method should be implemented where this class gets extended and that should be
   * annotated as @BeforeTest / @BeforeMethod
   */
  public abstract void beforeTest();

  /**
   * After test - method should be implemented where this class gets extended and that should be
   * annotated as @AfterTest / @AfterMethod
   */
  public abstract void afterTest();

  /**
   * Bootstrap test suite
   */
  public void bootstrap() {
    setUp("http://demo.guru99.com/V4/");
  }

  /**
   * Bootstrap test suite with given URL
   *
   * @param url - the URL
   */
  public void bootstrap(String url) {
    setUp(url);
  }

  /**
   * Clean up test suite
   */
  public void cleanup() {
    driver.quit();
  }

  private void setUp(String url) {
    // set driver path
    System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
    // initialize driver
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.get(url);
    // create page factory
    pageFactory = new PageFactory(driver);
  }

  public void proceedWithLogin() {
  }
}
