package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageObject {
    private WebDriver driver;

    public By constructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
    public By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a");
    public By emailInputField = By.xpath("//input[@name='name']");
    private By passwordInputField = By.xpath("//input[@name='Пароль']");
    private By logInButton = By.xpath("//button[contains(text(),'Войти')]");
    public By signUpButton = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    public By restorePasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");


    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToSignUpPage(){
        driver.findElement(signUpButton).click();
    }

    public void waitForLogInPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(signUpButton));

    }

    //метод популяции поля Email
    public void populateEmailField(String email){
        driver.findElement(emailInputField).sendKeys(email);
    }

    //метод популяции поля password
    public void populatePasswordField(String password){
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void populateAllFields(String email, String password){
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }

    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton(){
        driver.findElement(logoButton).click();
    }

    public void clickSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    public void clickRestorePasswordButton(){
        driver.findElement(restorePasswordButton).click();
    }

    public By getEmailInputField() {
        return emailInputField;
    }
}
