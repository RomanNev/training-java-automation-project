package tests.allureTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ListenerTest {

    @BeforeEach
    void preconditionBrowser() {
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }


    @Test
    void allureTestListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        // открыть github
        open("https://github.com/");
        // ввести в поиск RomanNev/training-java-automation-project
        $(".header-search-input").setValue("RomanNev/training-java-automation-project").submit();
        // перейти в искомый репозиторий
        $(By.linkText("RomanNev/training-java-automation-project")).click();

        //искомый репозиторий есть на странице
        //есть поле Issues
        $("#repository-container-header").shouldHave(
                text("training-java-automation-project"),
                text("Issues"));

    }

}
