package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{


  public CartPage(WebDriver driver) {
    super(driver);
  }

  public boolean isProductDisplayed(String productName) {
    String xpath = String.format("//div[@class='inventory_item_name' and text()='%s']", productName);
    try {
      return !driver.findElements(By.xpath(xpath)).isEmpty();
    } catch (Exception e) {
      return false;
    }
  }


}
