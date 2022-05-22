package tests.ownerLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import tests.ownerLibrary.config.WebDriverProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverTest {

    private WebDriver driver = new WebDriverProvider().get();

//    @Test
//    public void testGuthubTitle(){
//        WebDriver driver  = new ChromeDriver();
//        driver.get("https://github.com/");
//        assertEquals(driver.getTitle(),"GitHub: Where the world builds software · GitHub");
//
//        driver.quit();
//
//    }

    @BeforeEach
    public void startDriver(){
        driver = new WebDriverProvider().get();    }

    @Test
    public void testGuthubTitle(){
        assertEquals(driver.getTitle(), "GitHub: Where the world builds software · GitHub");
    }

    @BeforeEach
    public void closeDriver(){
        driver.quit();
    }

}
