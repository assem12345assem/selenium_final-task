package selenium;

import driver.Browser;
import driver.DriverManager;
import model.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SwagLabsPage;
import utils.DataLoader;
import utils.Log;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

@Test(groups = "regression")
class LoginTests {
    private static final ThreadLocal<WebDriver> driver;
    private LoginPage loginPage;

    static {
        driver = new ThreadLocal<>();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeEach
    public void setUp() {
        driver.set(DriverManager.getDriver(Browser.FIREFOX));
        String link = DataLoader.loadProperty("LINK");
        if(link == null) {
            Log.getInstance().error("Page link is not provided!");
            return;
        }
        getDriver().get(link);
        getDriver().manage().window().maximize();
        loginPage = new LoginPage(getDriver());
    }

    @AfterClass
    public static void quit() {
        DriverManager.quitDriver();
    }

    @Test
    void testLoginWithEmptyCredentials() {
        loginPage.enterLoginDetails(UserFactory.emptyUser());
        String actual = loginPage.getErrorMessage();
        String expected = DataLoader.loadProperty("empty_error");
        if (expected == null) {
            Log.getInstance().error("Could not complete the test, expected value is null" + " testLoginWithEmptyCredentials");
            return;
        }
        assertThat(actual, equalToIgnoringCase(expected));
    }

    @Test
    void testLoginPassingOnlyUsername() {
        loginPage.enterLoginClearPassword(UserFactory.validUser());
        String actual = loginPage.getErrorMessage();
        String expected = DataLoader.loadProperty("empty_password_error");
        if (expected == null) {
            Log.getInstance().error("Could not complete the test, expected value is null" + " testLoginPassingOnlyUsername");
            return;
        }
        assertThat(actual, equalToIgnoringCase(expected));
    }

    @Test
    void testLoginPassingValidCredentials() {
        loginPage.enterLoginDetails(UserFactory.validUser());
        SwagLabsPage shop = new SwagLabsPage(getDriver());
        String actualDashboardTitle = shop.getDashboardTitle();
        String actualPageTitle = shop.getPageTitle();
        boolean isShopContent = shop.hasShopContent();
        String expected = DataLoader.loadProperty("swag_labs");
        if (expected == null) {
            Log.getInstance().error("Could not complete the test, expected value is null" + " testLoginPassingValidCredentials");
            return;
        }
        assertThat(actualDashboardTitle, equalToIgnoringCase(expected));
        assertThat(actualPageTitle, equalToIgnoringCase(expected));
        assertThat(isShopContent, is(true));
    }
}
