package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import contents.components.mainpage.BlockWithItemsComponent;
import contents.components.mainpage.NavMenuComponent;
import contents.pages.common.CoursesPage;
import contents.pages.common.MainPage;
import contents.pages.dynamics.CourseItemPage;
import contents.pages.dynamics.CoursesDynamicVersionPage;
import contents.pages.dynamics.InstructorItemPage;
import org.openqa.selenium.WebDriver;

public class GuiceContentsModule extends AbstractModule {

    @Inject
    private WebDriver driver;

    @Provides
    @Singleton
    public BlockWithItemsComponent getBlockWithItemsComponent() {
        return new BlockWithItemsComponent(driver);
    }

    @Provides
    @Singleton
    public NavMenuComponent getNavMenuComponent() {
        return new NavMenuComponent(driver);
    }

    @Provides
    @Singleton
    public CoursesDynamicVersionPage getCoursesDynamicVersionPage() {
        return new CoursesDynamicVersionPage(driver);
    }

    @Provides
    @Singleton
    public CourseItemPage getCourseItemPage() {
        return new CourseItemPage(driver);
    }

    @Provides
    @Singleton
    public InstructorItemPage getInstructorItemPage() {
        return new InstructorItemPage(driver);
    }

    @Provides
    @Singleton
    public CoursesPage getCoursesPage() {
        return new CoursesPage(driver);
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }
}
