package pages;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DataLoader;
import utils.Log;

public class LoginPage {
    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement username;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;
    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement submitButton;
    @FindBy(xpath = "//*[@data-test='error']")
    private WebElement errorMessage;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void enterLoginDetails(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        submitButton.click();
    }
    public String getErrorMessage() {
        if(errorMessage == null) {
            Log.getInstance().error("could not locate error message web element");
            return STR."\{DataLoader.loadProperty("default_error_message")} error message";
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
        username.sendKeys(data);
    }
    private void enterPassword(String data) {
        if(data == null) {
            Log.getInstance().warn("enterPassword: entered username is null");
            return;
        }
        if(password == null) {
            Log.getInstance().error("could not locate password web element");
            return;
        }
        password.sendKeys(data);
    }
}
