package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
  private static final String SCREENSHOT_DIR = "output/screenshots/";

  public static String takeScreenshot(WebDriver driver, String testName) {
    File screenshotDir = new File(SCREENSHOT_DIR);
    if (!screenshotDir.exists()) {
      screenshotDir.mkdirs();
    }
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
    String filePath = SCREENSHOT_DIR + fileName;

    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destFile = new File(filePath);

    try {
      FileUtils.copyFile(srcFile, destFile);
    } catch (IOException e) {
      Log.getInstance().error("❌ Failed to save screenshot: " + e.getMessage());
    }

    return filePath;
  }
}
