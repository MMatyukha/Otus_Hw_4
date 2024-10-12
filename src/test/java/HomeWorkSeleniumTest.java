import com.google.inject.Inject;
import contents.pages.dynamics.CoursesDynamicVersionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import contents.pages.common.CoursesPage;
import contents.pages.dynamics.CourseItemPage;
import extensions.UIExtension;
import contents.components.mainpage.NavMenuComponent;

@ExtendWith(UIExtension.class)
@Tag("@homework1")
@DisplayName(" сценарии при помощи Selenium WebDriver")
public class HomeWorkSeleniumTest {
    @Inject
    private CoursesPage coursesPage;

    @Inject
    private CourseItemPage courseItemPage;

    @Inject
    private NavMenuComponent navMenuComponent;

    @Inject
    private CoursesDynamicVersionPage coursesDynamicVersionPage;



    @Test
    @Tag("@scenario1")
    @DisplayName("Найти курс по имени")
    public void findCoursePage() {
        String courseName = "DevOps практики и инструменты";

        coursesPage.open()
                .isLoaded()
                .clickNeededCourse(courseName);

        courseItemPage.isLoaded(courseName);
    }

    @Test
    @Tag("@scenario2")
    @DisplayName("Найти курсы, которые стартуют раньше и позже всех")
    public void filterCoursesWithDateAndCheck() {
        coursesPage.open()
                .isLoaded()

                .setTheEarliestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP()

                .setTheLatestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP();
    }
    @Test
    @Tag("@scenario3")
    @DisplayName("Выбор случайной категории курсов из меню 'Обучение' ")
    public void choseRandomCategoryTest() {
        coursesPage.open().isLoaded();

        String name = navMenuComponent
                .chooseNeededBlockAndSetItemList("Обучение")
                .resetItemListViaLinkContainsPath("https://otus.ru/categories")
                .getRandomItemName();

        navMenuComponent.clickItemByName(name);

        coursesDynamicVersionPage.setTitleOfItemPage(name)
                .isLoaded()
                .getCoursesPage()
                .isLoaded();

        coursesDynamicVersionPage.isLoaded(name);
    }

}
