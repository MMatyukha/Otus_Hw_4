package contents.pages.dynamics;

import annotations.PageValidation;
import annotations.UrlPrefix;
import com.google.inject.Inject;
import contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import contents.pages.common.CoursesPage;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/catalog/courses?categories={category}")
@PageValidation("template:xpath://div[@class = 'ReactCollapse--content']//div[@value = 'true']//label[text() = '%s']")
public class CoursesDynamicVersionPage extends AnyPageWithDynamicAnnotationAbs<CoursesDynamicVersionPage> {

    @Inject
    private CoursesPage coursesPage;

    public CoursesDynamicVersionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoursesDynamicVersionPage isLoaded() {
        return super.isLoaded(this.incomingValueForAnnotation);
    }

    @Override
    public CoursesDynamicVersionPage isLoaded(String titleOfItemPage) {
        return super.isLoaded(titleOfItemPage);
    }

    public CoursesPage open(String category) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{category}", category)));
        return coursesPage;
    }


    public CoursesPage getCoursesPage() {
        return coursesPage;
    }
}
