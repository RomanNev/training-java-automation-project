package tests.allureTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ExempleAllureTest {


    @BeforeAll
    static void beforeAll() {
        //Arrange
        Configuration.browserSize = "1920x1080";
    }



    @Test
    void allureTestGithub() {
        SelenideLogger.addListener("allure", new AllureSelenide()); //подключаем визуализацию шагов в отчете

        open("https://github.com/");
        $(".header-search-input").setValue("eroshenkoam/allure-example").submit();
        $(By.linkText("eroshenkoam/allure-example")).click(); //клик по полой ссылке
        $(By.partialLinkText("Issue")).click();// клик по фрагменту текста  ссылки
        $(withText("68")).should(Condition.exist);


    }
}
