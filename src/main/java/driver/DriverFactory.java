package driver;

import driver.impl.ChromeWebDriver;
import driver.impl.IDriver;
import exceptions.WebDriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {
    private final String browserName = System.getProperty("browser.name").toLowerCase(Locale.ROOT);
    private final String browserVersion = System.getProperty("browser.version").toLowerCase(Locale.ROOT);


    @Override
    public WebDriver getDriver() {
        switch (this.browserName) {
            case "chrome": {
                WebDriverManager.chromiumdriver().browserVersion(browserVersion).setup();
                IDriver<ChromeOptions> browserSettings = new ChromeWebDriver();
                return new EventFiringDecorator<>(new ActionsListener())
                        .decorate(new ChromeDriver(browserSettings.getDriverOptions()));
            }
            default:
                throw new WebDriverNotSupportedException(browserName);
        }
    }
}
