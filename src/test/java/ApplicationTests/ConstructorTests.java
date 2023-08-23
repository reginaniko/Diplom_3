package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTests extends BaseTest {


    @Test
    @DisplayName("Проверить переход к разделу Булки")
    public void verifyNavigationToBunsSectionIsSuccessful() {
        homePage.clickOnToppingsButton();
        homePage.clickOnBunButton();
        Assert.assertTrue(driver.findElement(homePage.getBunButton()).getAttribute("class").contains("current"));
    }

    @Test
    @DisplayName("Проверить переход к разделу Соусы")
    public void verifyNavigationToSauceSectionIsSuccessful() {
        homePage.clickOnSauceButton();
        Assert.assertTrue(driver.findElement(homePage.getSauceButton()).getAttribute("class").contains("current"));
    }

    @Test
    @DisplayName("Проверить переход к разделу Начинки")
    public void verifyNavigationToToppingsSectionIsSuccessful() {
        homePage.clickOnToppingsButton();
        Assert.assertTrue(driver.findElement(homePage.getToppingsButton()).getAttribute("class").contains("current"));
    }
}
