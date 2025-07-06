package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener{
  @Override
  public void onTestFailure(ITestResult result) {
    Object testClass = result.getInstance();

    WebDriver driver = null;

    try {
      driver = (WebDriver) testClass.getClass().getMethod("getDriver").invoke(testClass);
    } catch (Exception e) {
      Log.getInstance().warn("Could not retrieve driver from test instance: " + e.getMessage());
    }

    if (driver != null) {
      String testName = result.getMethod().getMethodName();
      String screenshotPath = ScreenshotUtil.takeScreenshot(driver, testName);
      Log.getInstance().info("📷 Screenshot saved for failed test '" + testName + "': " + screenshotPath);
    } else {
      Log.getInstance().error("🚫 Could not take screenshot - driver was null");
    }
  }
}
