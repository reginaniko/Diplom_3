package Base;

import HttpRequests.CreateUserRequest;
import HttpRequests.CreateUserResponse;
import PageObjects.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import static io.restassured.RestAssured.given;


public class BaseTest {
    protected WebDriver driver;
    //выбрать браузер (CHROME / YANDEX / FIREFOX)
    private final BrowserDriverFactory.BrowserType browserType = BrowserDriverFactory.BrowserType.valueOf(System.getProperty("browser", "CHROME").toUpperCase());

    public final String PAGE_URL = "https://stellarburgers.nomoreparties.site";
    public final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    public final String USER_ENDPOINT = "/api/auth/register";
    public final String DELETE_USER_ENDPOINT = "/api/auth/user";
    public final String LOGIN_USER_ENDPOINT = "/api/auth/login";
    protected HomePageObject homePage;
    protected SignUpPageObject signUpPage;
    protected LoginPageObject loginPage;
    protected ProfilePageObject profilePage;
    protected ForgotPasswordPageObject forgotPasswordPage;
    protected Faker faker = new Faker();
    //тест данные - faker
    public CreateUserRequest userRequestBody = new CreateUserRequest (faker.internet().emailAddress(), faker.internet().password(), faker.name().username());
    public CreateUserResponse createUserResponse;


    @Before
    public void setUp() {
        driver = BrowserDriverFactory.createDriver(browserType);
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
        homePage = new HomePageObject(driver);
        signUpPage = new SignUpPageObject(driver);
        loginPage = new LoginPageObject(driver);
        profilePage = new ProfilePageObject(driver);
        forgotPasswordPage = new ForgotPasswordPageObject(driver);
    }

    @Step("Создать пользователя через API")
    public void createUser(){
            RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
            createUserResponse = given().header("Content-type", "application/json").body(userRequestBody).log().all()
                    .post(USER_ENDPOINT).then().log().all().extract().as(CreateUserResponse.class);
    }

    @Step("Перейти на страницу регистрации")
    public void navigateToSignUpPage(){
        homePage.clickOnProfileButton();//перейти на страницу входа
        loginPage.navigateToSignUpPage();//перейти на страницу регистрации
    }

    @Step("Заполнить поля входа и нажать кнопку Войти")
    public void populateLogInFieldsAndClickLogInButton(){
        loginPage.populateAllFields(userRequestBody.getEmail(), userRequestBody.getPassword());//заполнить поля почта и пароль на странице входа
        loginPage.clickLogInButton();//нажать кнопку войти
    }

    @Step("Перейти в профиль и сравнить поле почты с почтой созданного пользователя")
    public void navigateToProfileAndCompareEmail(){
        homePage.clickOnProfileButton();//перейти в профиль
        Assert.assertEquals(userRequestBody.getEmail(), profilePage.getEmailField());//подтвердить успешный вход
    }

    @Step("Заполнить поля регистрации и нажать кнопку Войти")
    public void populateSignUpFieldsAndClickSignUpButton(String name, String email, String password){
        signUpPage.populateNameField(name);//заполнить поле имя
        signUpPage.populateEmailField(email);//заполнить поле email
        signUpPage.populatePasswordField(password);//заполнить поле password
        signUpPage.clickSignUpButton();//нажать на кнопку Зарегистрироваться
    }


    public String getAccessToken(String email, String password){
        RestAssured.baseURI = PAGE_URL;
        CreateUserRequest body = CreateUserRequest.builder().email(email).password(password).build();
        CreateUserResponse userResponse = given().header("Content-type", "application/json")
                .body(body)
                .post(LOGIN_USER_ENDPOINT).then().log().all().extract().as(CreateUserResponse.class);
        return userResponse.getAccessToken();
    }

    public void deleteUser(String token){
        given().header("Content-type", "application/json").header("Authorization", token).log().all()
                .delete(DELETE_USER_ENDPOINT).then().log().all();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
