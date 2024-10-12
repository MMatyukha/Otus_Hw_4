package contents.pages.common;

import annotations.UrlPrefix;
import contents.pages.abstracts.AnyPageAbs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

    @FindBy(xpath = "//main/..//img[@alt='OTUS Logo']")
    private WebElement isLoadedElement;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isLoaded() {
        this.isLoaded(this.isLoadedElement);
        return this;
    }
}
