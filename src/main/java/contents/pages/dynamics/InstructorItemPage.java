package contents.pages.dynamics;

import annotations.PageValidation;
import annotations.UrlPrefix;
import contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import exceptions.UrlIsNeededParametersException;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/instructors/{id}")
@PageValidation("template:xpath://div[text()='%s']")
public class InstructorItemPage extends AnyPageWithDynamicAnnotationAbs<InstructorItemPage> {

    public InstructorItemPage(WebDriver driver) {
        super(driver);
    }

    public InstructorItemPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorItemPage open(String id) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{id}", id)));
        return this;
    }
}
