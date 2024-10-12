package contents.pages.abstracts;

import annotations.PageValidation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AnyPageWithDynamicAnnotationAbs<T> extends AnyPageAbs<T> {
    protected String incomingValueForAnnotation;
    private By dynamicPageValidationBy;

    public AnyPageWithDynamicAnnotationAbs(WebDriver driver) {
        super(driver);
    }

    @Override
    public T isLoaded() {
        assertThat(incomingValueForAnnotation)
                .as("Нет  данных на входе для загрузки страницы")
                .isNotEmpty();
        return (T) this;
    }

    public T isLoaded(String titleOfItemPage) {
        setTitleOfItemPage(titleOfItemPage);
        String pageIsNotLoadedText = String.format("Страница '%s' с  именем '%s' не загружена, "
                        + "или локатор не найден",
                this.getClass().getSimpleName(), titleOfItemPage);
        assertThat(this.waiter.waitForElementVisible(dynamicPageValidationBy))
                .as(pageIsNotLoadedText)
                .isTrue();
        super.isLoaded($(dynamicPageValidationBy));
        return (T) this;
    }

    public T setTitleOfItemPage(String titleOfItemPage) {
        this.incomingValueForAnnotation = titleOfItemPage;
        this.dynamicPageValidationBy = getDynamicPageValidationBy();
        return (T) this;
    }

    private By getDynamicPageValidationBy() {
        if (getClass().isAnnotationPresent(PageValidation.class)) {
            PageValidation pageValidation = getClass().getAnnotation(PageValidation.class);
            String locatorTypeAndLocator = "";
            if (pageValidation.value().startsWith("template:")) {
                locatorTypeAndLocator = pageValidation.value().replace("template:", "");
            }
            return getPageLocator(locatorTypeAndLocator);
        }
        return null;
    }

    private By getPageLocator(String locatorTypeAndLocator) {
        if (locatorTypeAndLocator.isEmpty()) return null;
        locatorTypeAndLocator = String.format(locatorTypeAndLocator, this.incomingValueForAnnotation);
        return this.pageComponentUtil.defineLocatorTypeByAnnotationValue(locatorTypeAndLocator);
    }
}
