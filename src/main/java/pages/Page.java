package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@Slf4j
@Getter
public class Page {

  private final WebDriver driver;

  public Page(WebDriver webDriver) {
    this.driver = webDriver;
  }

  /**
   * Send keys for given element with inputs
   *
   * @param locator - the UI element locator
   * @param input   - the input values
   */
  public void sendKeys(By locator, String input) {
    try {
      waitForPageLoad();
      elementToBeVisible(locator);
      WebElement sendKeys = driver.findElement(locator);
      sendKeys.clear();
      sendKeys.sendKeys(input);
    } catch (NoSuchElementException e) {
      fail("send keys failed, no such element", locator);
    }
  }

  /**
   * Click element
   *
   * @param locator - the UI element locator
   */
  public void click(By locator) {
    try {
      waitForPageLoad();
      elementToBeClickable(locator);
      driver.findElement(locator).click();
    } catch (NoSuchElementException exception) {
      fail("no clickable element found for given locator", locator);
    }
  }

  /**
   * Click dropdown with given value
   *
   * @param locator - the UI element locator
   * @param value   - the value
   */
  public void clickWithDropDown(By locator, String value) {
    try {
      waitForPageLoad();
      elementToBeClickable(locator);
      driver.findElement(locator).click();
      sendKeys(locator, value);
      selectDropdown(locator, 1);
    } catch (NoSuchElementException exception) {
      fail("couldn't select the given value from the selector dropdown", locator);
    }
  }

  /**
   * Get text for given element
   *
   * @param locator - the UI element locator
   * @return - the text from the element
   */
  public String getText(By locator) {
    String text = "";
    try {
      waitForPageLoad();
      elementToBeVisible(locator);
      WebElement message = driver.findElement(locator);
      text = message.getText();
    } catch (Exception e) {
      fail("issue occurred while fetch text for given element", locator);
    }
    return text;
  }

  /**
   * Get current URL of the element's page
   *
   * @param locator - the UI element locator
   * @return - the URL of the page
   */
  public String geUrl(By locator) {
    String url = "";
    try {
      waitForPageLoad();
      elementToBeVisible(locator);
      url = driver.getCurrentUrl();
    } catch (Exception e) {
      fail("issue occurred while getting current URL", locator);
    }
    return url;
  }

  /**
   * Locate element by given locator
   *
   * @param locator - the UI element locator
   * @return - the web element of the locator
   */
  public WebElement locateElement(By locator) {
    WebElement webElement = null;
    try {
      waitForPageLoad();
      elementToBeVisible(locator);
      webElement = driver.findElement(locator);
    } catch (Exception e) {
      fail("unable to locate given element", locator);
    }
    return webElement;
  }

  /**
   * Locate elements by given locator
   *
   * @param locator - the UI element locator
   * @return - the web elements of the locator
   */
  public List<WebElement> locateElements(By locator) {
    List<WebElement> elements = new ArrayList<>();
    try {
      waitForPageLoad();
      elementToBeVisible(locator);
      elements = driver.findElements(locator);
    } catch (Exception e) {
      fail("unable to locate given elements", locator);
    }
    return elements;
  }

  /**
   * Is element displayed
   *
   * @param element - the element
   * @return - the element displayed status
   */
  public boolean isDisplayed(By element) {
    boolean isDisplayed = false;
    try {
      waitForPageLoad();
      elementToBeVisible(element);
      isDisplayed = driver.findElement(element).isDisplayed();
    } catch (Exception e) {
      fail("unable to get displayed status of the element", element);
    }
    return isDisplayed;
  }

  /**
   * Wait for page load on the current view
   */
  public void waitForPageLoad() {
    ExpectedCondition<Boolean> expectation = driver -> {
      assert driver != null;
      return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
          .equals("complete");
    };
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
      wait.until(expectation);
    } catch (Throwable error) {
      fail("couldn't load the page due to exception", error);
    }
  }

  /**
   * Wait until element to be clickable state Max wait time is 60 seconds and if no element found,
   * the `NoSuchElement` exception will be given the element polling check will happen every 10
   * seconds till max wait time.
   *
   * @param locator - the element locator ref
   */
  public void elementToBeClickable(By locator) {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(locator));
    wait.ignoring(NoSuchElementException.class);
  }

  /**
   * Wait util element is visible for given locator
   *
   * @param locator - the location of the element
   */
  protected void elementToBeVisible(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Select dropdown element
   *
   * @param selector - the selector element
   * @param index    - the index to be selected
   */
  public void selectDropdown(By selector, int index) {
    Select select = new Select(driver.findElement(selector));
    select.selectByIndex(index);
  }

  private void fail(String errorMessage) {
    log.error("FAILED DUE TO : {}", errorMessage);
    Assert.fail();
  }

  private void fail(String errorMessage, By element) {
    log.error("FAILED DUE TO : {} >> {}", element, errorMessage);
    Assert.fail();
  }

  private void fail(String error, Throwable stackTrace) {
    log.error("FAILED DUE TO : {} >> {}", error, stackTrace);
    Assert.fail();
  }
}
