package tests.github;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SearchRepoGithubTest {

    @BeforeAll
    static void beforeAll() {
        //Arrange
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void shouldFindInGithub(){
        //открыть страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        // в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
        $$("[data-filterable-for=wiki-pages-filter]").findBy(text("SoftAssertions")).click();
        // внутри есть пример кода для JUnit5
        $(".markdown-body [start='3'] li").shouldHave(text("Using JUnit5 extend test class:"));


    }

}
