package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    PageFactory.initElements(driver, this);
  }
  protected WebElement waitForVisibility(WebElement element) {
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected WebElement waitForClickability(WebElement element) {
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }
}
