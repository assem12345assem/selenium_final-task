package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[@class='header_label']/div[@class='app_logo']")
    private WebElement swagLabsDiv;
    @FindBy(xpath = "//*[@id='inventory_container']")
    private WebElement inventoryContainer;

    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
}
