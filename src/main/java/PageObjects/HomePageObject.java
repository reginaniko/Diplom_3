package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class HomePageObject {
    private WebDriver driver;

    public By profileButton = By.xpath("//header/nav[1]/a[1]");
    public By loginButton = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    public By bunButton = By.xpath("//body/div[@id='root']/div[1]/main[1]/section[1]/div[1]/div[1]");
    public By sauceButton = By.xpath("//body/div[@id='root']/div[1]/main[1]/section[1]/div[1]/div[2]");
    public By toppingsButton = By.xpath("//body/div[@id='root']/div[1]/main[1]/section[1]/div[1]/div[3]");


    public HomePageObject (WebDriver driver){
        this.driver = driver;
    }

    public void clickOnProfileButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        driver.findElement(profileButton).click();
    }

    public void clickOnLoginButton(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(loginButton)).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void clickOnBunButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        driver.findElement(bunButton).click();
    }

    public void clickOnSauceButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        driver.findElement(sauceButton).click();
    }

    public void clickOnToppingsButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]")));
        driver.findElement(toppingsButton).click();
    }

}
