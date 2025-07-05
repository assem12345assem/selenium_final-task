package selenium;


import driver.Browser;
import driver.DriverManager;
import model.UserFactory;

import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.SwagLabsPage;
import utils.DataLoader;
import utils.Log;

@Test(groups = "smoke")
public class SwagLabsTests {

  private static WebDriver driver;
  private static LoginPage loginPage;
  private static SwagLabsPage swagLabsPage;
  private static CartPage cartPage;
  String[] products = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};


  @BeforeClass
  public static void setUp() {
    driver = DriverManager.getDriver(Browser.FIREFOX);

    String link = DataLoader.loadProperty("LINK");
    if (link == null) {
      Log.getInstance().error("Page link is not provided!");
      return;
    }

    driver.get(link);
    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
    loginPage.enterLoginDetails(UserFactory.validUser());
    swagLabsPage = new SwagLabsPage(driver);
  }

  @AfterClass
  public static void quit() {
    DriverManager.quitDriver();
  }

  @Test(priority = 1)
  public void verifyCartBadgeIsInitiallyEmpty() {
    assertEquals(swagLabsPage.getCartItemCount(), 0,
        "Expected cart to be initially empty, but it has items: "
            + swagLabsPage.getCartItemCount());
  }

  @Test(priority = 2)
  public void verifyAddingItemsToCartChangesBadgeNumber() {
    for (String product : products) {
      swagLabsPage.clickAddToCart(product);
    }
    assertEquals(swagLabsPage.getCartItemCount(), 2,
        "Expected cart to have 2 items, but was: " + swagLabsPage.getCartItemCount());
  }

  @Test(priority = 3)
  public void verifyItemsInTheCart() {
    swagLabsPage.clickCartIcon();
    cartPage = new CartPage(driver);
    SoftAssert softAssert = new SoftAssert();
    for (String product : products) {
      softAssert.assertTrue(cartPage.isProductDisplayed(product));
    }
    softAssert.assertAll();
  }
}
