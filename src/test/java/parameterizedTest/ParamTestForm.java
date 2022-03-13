package parameterizedTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParamTestForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void precondition() {
        open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }


    // вариант с  CsvSource
    @ValueSource(strings = {"Alan", "Bob"}) // один аргумент --> один запуск теста
    @ParameterizedTest(name = "Проверка регистрационной формы через CsvSource  \"{0}\" ")
    void simpleTest(String name) {
        $("#userName").setValue(name);
        $("#userEmail").setValue("test@email.ru");
        $("#currentAddress").setValue("125 W Houston St");
        $("#permanentAddress").setValue("45 Carnation Ave");
        $("#submit").click();
        $("#output > .border").shouldHave(
                text(name),
                text("test@email.ru"),
                text("125 W Houston St"),
                text("45 Carnation Ave"));

    }


    // вариант с  CsvSource
    @CsvSource(value = {
            "Alan | alan@email.ru | 25 W Houston St, New York, USA | 145 Carnation Ave, Floral Park, USA",
            "Bob | bob@email.ru | Warren St, Staten Island, NY, USA | 60 Floral Blvd, Floral Park, USA",

    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Проверка регистрационной формы через CsvSource  \"{0}\" ")
    void ComplexFormTest(String name, String email, String currentAddress, String permanentAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();
        $("#output > .border").shouldHave(
                text(name),
                text(email),
                text(currentAddress),
                text(permanentAddress));
    }


    // вариант с  MethodSource
    static Stream<Arguments> mixedFormTest() {
        return Stream.of( // каждый аргумент передается на каждом запуске теста
                Arguments.of("Alan", "alan@email.ru", "25 W Houston St, New York, USA", " 145 Carnation Ave, Floral Park, USA"),
                Arguments.of("Bob", "bob@email.ru", "Warren St, Staten Island NY, USA", "60 Floral Blvd, Floral Park, USA")
        );

    }

    @MethodSource(value = "mixedFormTest")
    // принимает специальный метод, который предоставит набор аргументов произвольного типа
    @ParameterizedTest(name = "Проверка регистрационной формы через MethodSource  \"{0}\" ")
    void mixedFormTest(String name, String email, String currentAddress, String permanentAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();
        $("#output > .border").shouldHave(
                text(name),
                text(email),
                text(currentAddress),
                text(permanentAddress));
    }

}
