package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import fromdriver.waiters.Waiter;

public class GuiceDriverModule extends AbstractModule {

    private final WebDriver driver = new DriverFactory().getDriver();
    private final Actions actions = new Actions(driver);
    private final Waiter waiter = new Waiter(driver);

    @Provides
    public WebDriver getDriver() { return driver; }

    @Provides
    public Waiter getWaiter() { return waiter; }

    @Provides
    public Actions getActions() { return actions; }
}
