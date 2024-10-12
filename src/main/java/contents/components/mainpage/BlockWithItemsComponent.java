package contents.components.mainpage;

import annotations.Component;
import contents.components.abstracts.AnyComponentAbs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AnyComponentAbs<BlockWithItemsComponent> {

    private final String hrefXpath = ".//a[./div]";

    public BlockWithItemsComponent(WebDriver driver) {
        super(driver);
    }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }

    public void clickItemByName(String blockName, String name) {
        getComponentEntity(blockName).findElement(By.xpath(String.format(".//*[./div[text()='%s']]", name))).click();
    }

    public BlockWithItemsComponent setItemList() {
        this.itemList = this.waiter.waitForElementsVisible(getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath(hrefXpath)));
        return this;
    }

    public String getItemWithTextParameterAndIndex(int indexItem, int indexParameter) {
        return this.getItemParameterWebElementByIndex(
                        this.getItemWebElement(indexItem),
                        ".//div[text()]",
                        indexParameter)
                .getText();
    }
}
