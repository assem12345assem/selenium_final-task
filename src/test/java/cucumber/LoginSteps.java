package cucumber;

import driver.Browser;
import driver.DriverManager;
import io.cucumber.java.en.*;
import model.UserFactory;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SwagLabsPage;
import utils.DataLoader;
import utils.Log;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginSteps {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private LoginPage loginPage;
    private SwagLabsPage shop;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.set(DriverManager.getDriver(Browser.FIREFOX));
        String link = DataLoader.loadProperty("LINK");
        if (link == null) {
            Log.getInstance().error("Page link is not provided!");
            return;
        }
        getDriver().get(link);
        getDriver().manage().window().maximize();
        loginPage = new LoginPage(getDriver());
    }

    @When("I enter empty credentials")
    public void i_enter_empty_credentials() {
        loginPage.enterLoginDetails(UserFactory.emptyUser());
    }

    @When("I enter a valid username and leave the password field empty")
    public void i_enter_valid_username_and_leave_password_empty() {
        loginPage.enterLoginClearPassword(UserFactory.validUser());
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.enterLoginDetails(UserFactory.validUser());
        shop = new SwagLabsPage(getDriver());
    }

    @Then("I should see an error message {string}")
    public void i_should_see_error_message(String expected) {
        String actual = loginPage.getErrorMessage();
        if (expected == null) {
            Log.getInstance().error("Could not complete the test, expected value is null");
            return;
        }
        assertThat(actual, equalToIgnoringCase(expected));
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_dashboard() {
        shop = new SwagLabsPage(getDriver());
    }

    @Then("I should see the dashboard title {string}")
    public void i_should_see_dashboard_title(String expected) {
        String actualDashboardTitle = shop.getDashboardTitle();
        assertThat(actualDashboardTitle, equalToIgnoringCase(expected));
    }

    @Then("I should see the page title {string}")
    public void i_should_see_page_title(String expected) {
        String actualPageTitle = shop.getPageTitle();
        assertThat(actualPageTitle, equalToIgnoringCase(expected));
    }

    @Then("the shop content should be displayed")
    public void the_shop_content_should_be_displayed() {
        boolean isShopContent = shop.hasShopContent();
        assertThat(isShopContent, is(true));
    }

    private WebDriver getDriver() {
        return driver.get();
    }
}
