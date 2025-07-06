package pages;

import java.time.Duration;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataLoader;
import utils.Log;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement username;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;
    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement submitButton;
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void enterLoginDetails(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        submitButton.click();
    }
    public void enterLoginClearPassword(User user) {
        enterUsername(user.getUsername());
        enterAndClearPassword(user.getPassword());
        submitButton.click();
    }
    public String getErrorMessage() {
        try {
            waitForVisibility(errorMessage);
        } catch (TimeoutException e) {
            Log.getInstance().error("Could not locate error message web element within 5 seconds");
            return DataLoader.loadProperty("default_error_message") + " error message";
        }
        return errorMessage.getText();
    }
    private void enterUsername(String data) {
        if(data == null) {
            Log.getInstance().warn("enterUsername: entered username is null");
            return;
        }
        if(username == null) {
            Log.getInstance().error("could not locate username web element");
            return;
        }
        username.clear();
        username.sendKeys(data);
    }

    private void enterPassword(String data) {
        if(data == null) {
            Log.getInstance().warn("enterPassword: entered password is null");
            return;
        }
        if(password == null) {
            Log.getInstance().error("could not locate password web element");
            return;
        }
        password.sendKeys(data);
    }
    private void enterAndClearPassword(String data) {
        if(data == null) {
            Log.getInstance().warn("enterPassword: entered password is null");
            return;
        }
        if(password == null) {
            Log.getInstance().error("could not locate password web element");
            return;
        }
        password.sendKeys(data);
        password.clear();
    }
}
