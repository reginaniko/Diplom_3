package ApplicationTests;
import io.qameta.allure.junit4.DisplayName;
import Base.BaseTest;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class AuthenticationTests extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInWithSignInButtonIsSuccessful(){
        createUser();//создать пользователя
        homePage.clickOnLoginButton();//кликнуть по кнопке «Войти в аккаунт» на главной
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void logInWithProfileButtonIsSuccessful(){
        createUser();//создать пользователя
        homePage.clickOnProfileButton();//кликнуть по кнопке «Личный кабинет» на главной
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void logInFromSignUpPageButtonIsSuccessful(){
        createUser();//создать пользователя
        homePage.clickOnProfileButton();//кликнуть по кнопке «Личный кабинет» на главной
        loginPage.clickSignUpButton();//кликнуть по кнопке "Зарегистрироваться" на странице входа
        signUpPage.clickLogInButton();//кликнуть по кнопке "Войти" на странице регистрации
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void logInFromRestorePasswordPageIsSuccessful(){
        createUser();//создать пользователя
        homePage.clickOnProfileButton();//кликнуть по кнопке «Личный кабинет» на главной
        loginPage.clickRestorePasswordButton();//кликнуть по кнопке "Восстановить пароль" на странице входа
        forgotPasswordPage.clickLogInButton();// кликнуть по кнопке "Войти" на странице восстановления пароля
        populateLogInFieldsAndClickLogInButton();
        navigateToProfileAndCompareEmail();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void verifyLogOutIsSuccessful(){
        createUser();//создать пользователя
        homePage.clickOnLoginButton();//кликнуть по кнопке «Войти в аккаунт» на главной
        populateLogInFieldsAndClickLogInButton();
        homePage.clickOnProfileButton();//перейти в профиль
        profilePage.clickLogOutButton();//нажать на кнопку "Выход"
        loginPage.waitForLogInPageLoad();
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());//подтвердить успешный выход
    }

    @After
    public void deleteUser(){
        RestAssured.baseURI = PAGE_URL;
        given().header("Content-type", "application/json").header("Authorization", createUserResponse.getAccessToken()).log().all()
                .delete(DELETE_USER_ENDPOINT).then().log().all();
    }
}
