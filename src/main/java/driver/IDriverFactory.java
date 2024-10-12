package driver;

import exceptions.WebDriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
    WebDriver getDriver() throws WebDriverNotSupportedException;
}
