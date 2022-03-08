package tests.allureTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaTestsSteps {

    @BeforeEach
    void preconditionBrowser() {
        Configuration.browserSize = "1920x1080";
    }
    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    void allureLambdaTests(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Opening the page  github", () ->{
            open("https://github.com/");
        });

        step("enter in search  RomanNev/training-java-automation-project", () ->{
            $(".header-search-input").setValue("RomanNev/training-java-automation-project").submit();
        });

        step("go to the desired repository", () -> {
            $(By.linkText("RomanNev/training-java-automation-project")).click();
            Allure.addAttachment("Page source", "text/html", WebDriverRunner.source(),"html");
        });

        step("the required repository is on the page and the Issues field is on the page", () -> {
            $("#repository-container-header").shouldHave(
                    text("training-java-automation-project"),
                    text("Issues"));
        });

    }

    @Test
    @Owner("golub")
    @Severity(SeverityLevel.NORMAL)
    @Feature("exemple test")
    @Story("new issue")
    @DisplayName("Check issue inrepository")
    @Description("annotation demonstration ")
    @Link(value = "Test", url = "https://github.com/")
    void allureWebStepsTests(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps webSteps = new WebSteps();
        webSteps.openPage();
        webSteps.searchForRepository();
        webSteps.openRepository();
        webSteps.checkingRepositoryAndTabsIssues();

        webSteps.takeScreenshot();

    }
}
