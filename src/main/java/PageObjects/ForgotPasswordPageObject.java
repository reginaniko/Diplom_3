package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPageObject {
    private WebDriver driver;

    private By logInButton = By.xpath("//a[contains(text(),'Войти')]");

    public ForgotPasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }
}
