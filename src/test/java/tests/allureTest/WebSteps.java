package tests.allureTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Opening the page  github")
    public void openPage() {
        open("https://github.com/");

    }

    @Step("enter in search  RomanNev/training-java-automation-project")
    public void searchForRepository() {
        $(".header-search-input").setValue("RomanNev/training-java-automation-project").submit();

    }

    @Step("go to the desired repository")
    public void openRepository() {
        $(By.linkText("RomanNev/training-java-automation-project")).click();
    }

    @Step("the required repository is on the page and the Issues field is on the page")
    public void checkingRepositoryAndTabsIssues() {
        $("#repository-container-header").shouldHave(
                text("training-java-automation-project"),
                text("Issues"));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
