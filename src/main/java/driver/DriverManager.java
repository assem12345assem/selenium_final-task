package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static WebDriver instance;

    private DriverManager() {}

    public static WebDriver getDriver(Browser browser) {
        if(instance == null) {
            switch (browser) {
                case Browser.FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    instance = new FirefoxDriver();
                    break;
                case Browser.EDGE:
                    WebDriverManager.edgedriver().setup();
                    instance = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    instance = new ChromeDriver();
            }
        }
        return instance;
    }
    public static void quitDriver() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}
