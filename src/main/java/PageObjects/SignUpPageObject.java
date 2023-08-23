package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPageObject {
    private WebDriver driver;

    //private By nameInputField = By.xpath("//label[contains(text(),'Имя')]");
    private By nameInputField = By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/input[1]");
    //private By emailInputField = By.xpath("//label[contains(text(),'Email')]");
    private By emailInputField = By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/form[1]/fieldset[2]/div[1]/div[1]/input[1]");
    private By passwordInputField = By.xpath("//input[@name='Пароль']");
    private By signUpButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    public By passwordValidationError = By.xpath("//p[contains(text(),'Некорректный пароль')]");
    public By logInButton = By.xpath("//a[contains(text(),'Войти')]");

    public SignUpPageObject(WebDriver driver){
        this.driver = driver;
    }

    //дождаться загрузки страницы

    //метод популяции поля Имя
    public void populateNameField(String username){
        driver.findElement(nameInputField).sendKeys(username);
    }

    //метод популяции поля Email
    public void populateEmailField(String email){
        driver.findElement(emailInputField).sendKeys(email);
    }

    //метод популяции поля password
    public void populatePasswordField(String password){
        driver.findElement(passwordInputField).sendKeys(password);
    }

    //метод клика по кнопке зарегистрироваться
    public void clickSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }
}
