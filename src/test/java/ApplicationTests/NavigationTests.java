package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class NavigationTests extends BaseTest {

    @Test
    @DisplayName("Переход с домашней страницы на страницу входа по клику на «Личный кабинет»")
    public void navigateToLoginPageFromHomePageProfileButton(){
        homePage.clickOnProfileButton();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход с домашней страницы на страницу входа по клику на «Войти»")
    public void navigateToLoginPageFromHomePageLoginButton(){
        homePage.clickOnLoginButton();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void navigateToConstructorPageFromLoginPage(){
        homePage.clickOnProfileButton();
        loginPage.clickConstructorButton();
        Assert.assertEquals(PAGE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета на домашнюю страницу по клику на логотип Stellar Burgers")
    public void navigateToHomePageFromLoginPage(){
        homePage.clickOnProfileButton();
        loginPage.clickLogoButton();
        Assert.assertEquals(PAGE_URL + "/", driver.getCurrentUrl());
    }
}
