package ApplicationTests;

import Base.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SignUpTests extends BaseTest {

    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String name = faker.name().username();
    List<String> userTokens = new ArrayList<>();

    @Test
    @DisplayName("Пользователь может успешно зарегисрироваться c уникальными данными")
    public void verifyCorrectSignUpIsSuccessful(){
        navigateToSignUpPage();
        populateSignUpFieldsAndClickSignUpButton(name, email, password);
        loginPage.waitForLogInPageLoad();
        System.out.println("почта" + email + "пароль" + password);
        userTokens.add(getAccessToken(email, password));
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
        if (!userTokens.isEmpty()){
            RestAssured.baseURI = PAGE_URL;
            for (String token : userTokens) {
                deleteUser(token);
                System.out.println("Deleted created user with token: " + token);
            }
        }else{
            System.out.println("No user was created. Skipping deletion.");
        }
    }
}
