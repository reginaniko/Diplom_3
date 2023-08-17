package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTests extends BaseTest {

    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String name = faker.name().username();
    boolean isUserCreated = false;

    @Test
    @DisplayName("Пользователь может успешно зарегисрироваться c уникальными данными")
    public void verifyCorrectSignUpIsSuccessful(){
        navigateToSignUpPage();
        populateSignUpFieldsAndClickSignUpButton(name, email, password);
        loginPage.waitForLogInPageLoad();
        System.out.println("почта" + email + "пароль" + password);
        isUserCreated = true;
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());//подвердить пользователь перенаправлен на страницу входа
    }

    @Test
    @DisplayName("Ошибка валидации при пароле мешьше 6 символов")
    public void verifyWrongPasswordReturnsError(){
        navigateToSignUpPage();
        signUpPage.populatePasswordField(faker.internet().password(1,5));//заполнить поле password паролем с max длиной в 5 символов
        signUpPage.clickSignUpButton();//нажать на кнопку Зарегистрироваться
        Assert.assertTrue(driver.findElement(signUpPage.passwordValidationError).isDisplayed());//подтвердить что сообщение валидации
    }

    @After
    public void deleteUser(){
        if (isUserCreated){
            RestAssured.baseURI = PAGE_URL;
            String token = getAccessToken(email, password);
            deleteUser(token);
        }else{
            System.out.println("No user was created. Skipping deletion.");
        }
    }
}
