package pages;

import java.util.NoSuchElementException;
import javax.lang.model.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

public class SwagLabsPage extends BasePage{
    @FindBy(xpath = "//*[@class='header_label']/div[@class='app_logo']")
    private WebElement swagLabsDiv;
    @FindBy(xpath = "//*[@id='inventory_container']")
    private WebElement inventoryContainer;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = "span.shopping_cart_badge")
    private WebElement cartBadge;


    public SwagLabsPage(WebDriver driver) {
        super(driver);
    }
    public String getDashboardTitle() {
        return swagLabsDiv.getText();
    }
    public boolean hasShopContent() {
        return inventoryContainer != null;
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
    public void clickAddToCart(String productName) {
        try {
           WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(
                    "//div[@class='inventory_item'][.//div[@class='inventory_item_name ' and text()='%s']]//button[contains(@data-test, 'add-to-cart')]",
                    productName))));
            cartButton.click();
        }catch (TimeoutException e) {
            Log.getInstance().error("Add to cart button not found or not clickable for product: " + productName);
            throw new RuntimeException("Could not click Add to cart button for: " + productName, e);
        }
    }
    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return Integer.parseInt(cartBadge.getText());
        } catch (NoSuchElementException | TimeoutException e) {
            return 0;
        }
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

}
