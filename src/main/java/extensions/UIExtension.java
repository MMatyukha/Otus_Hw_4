package extensions;

import com.google.inject.*;
import com.google.inject.Module;
import modules.GuiceContentsModule;
import modules.GuiceDriverModule;
import modules.GuiceUtilsModule;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class UIExtension implements BeforeEachCallback, AfterEachCallback, TestWatcher {

    @Inject
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        GuiceDriverModule guiceDriverModule = new GuiceDriverModule();
        Injector driverInjector = Guice.createInjector(guiceDriverModule);

        driverInjector.injectMembers(this);

        GuiceContentsModule guiceContentsModule = new GuiceContentsModule();
        GuiceUtilsModule guiceUtilsModule = new GuiceUtilsModule();

        this.injectDriverToModules(driverInjector, guiceContentsModule, guiceContentsModule);
        Injector fullInjector = Guice.createInjector(guiceDriverModule, guiceContentsModule, guiceUtilsModule);

        extensionContext.getTestInstance().ifPresent(instance -> {
            fullInjector.injectMembers(instance);
        });
        extensionContext.getTestInstance().ifPresent(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .map(f -> f.getType())
                    .forEach(c -> fullInjector.injectMembers(fullInjector.getProvider(c).get()));
        });
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                    if (driver != null) {
                        driver.quit();
                    }
                });
    }

    private void injectDriverToModules(Injector driverInjector, Module... modules) {
        for (Module module : modules) {
            driverInjector.injectMembers(module);
        }
    }
}
