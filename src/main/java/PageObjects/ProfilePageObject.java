package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePageObject {
    private WebDriver driver;

    private By logOutButton = By.xpath("//button[contains(text(),'Выход')]");
    private By emailField = By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/input[1]");
    private By nameField = By.xpath("//body/div[@id='root']/div[1]/main[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]/div[1]/input[1]");



    //button[contains(text(),'Выход')]
    public ProfilePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForProfilePageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }

    public void clickLogOutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        driver.findElement(logOutButton).click();
    }


    public String getEmailField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        return driver.findElement(emailField).getAttribute("value");
    }
}
