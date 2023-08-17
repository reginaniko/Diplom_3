package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory{

    public enum BrowserType {
        YANDEX, FIREFOX, CHROME
    }

    public static WebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--remote-allow-origins=*");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver(option);
                break;
            case YANDEX:
                ChromeOptions options = new ChromeOptions();
                String binaryYandexDriverFile = "src/main/resources/yandexdriver"; // Path to YandexDriver executable
                System.setProperty("webdriver.chrome.driver", binaryYandexDriverFile);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Don't know how to start " + browserType + ". Starting Firefox instead.");
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}