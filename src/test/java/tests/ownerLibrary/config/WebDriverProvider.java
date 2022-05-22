package tests.ownerLibrary.config;

import com.google.common.base.Supplier;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class WebDriverProvider  implements Supplier<WebDriver> {

    private WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createWebDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    private WebDriver createWebDriver() {
        if (Objects.nonNull(config.getBrowser())) {
            switch (config.getBrowser()) {
                case CHROME: {
                    return new ChromeDriver();
                }
                case FIREFOX: {
                    return new FirefoxDriver();
                }
                default: {
                    throw new RuntimeException("Тип браузера не поддерживается");
                }
            }
        }
        throw new RuntimeException("Тип браузер не может быть null");
    }

}
