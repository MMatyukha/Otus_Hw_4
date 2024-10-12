package contents.pages.dynamics;

import annotations.PageValidation;
import annotations.UrlPrefix;
import contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import exceptions.UrlIsNeededParametersException;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/lessons/{prefixName}/")
@PageValidation("template:xpath://h1[text()='%s']")
public class CourseItemPage extends AnyPageWithDynamicAnnotationAbs<CourseItemPage> {

    public CourseItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CourseItemPage open() {
        throw new UrlIsNeededParametersException("{prefixName}. Use method - open(prefixName)");
    }

    public CourseItemPage open(String prefixName) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{prefixName}", prefixName)));
        return this;
    }
}