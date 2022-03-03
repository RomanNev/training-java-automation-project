package parameterizedTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class ParamTestForm {

    @BeforeEach
    void precondition(){
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closeBrowser(){
        Selenide.closeWebDriver();
    }

    // вариант с  CsvSource
    @CsvSource(value = {
            "Alan | alan@email.ru | 25 W Houston St, New York, USA | 145 Carnation Ave, Floral Park, USA",
            "Bob | bob@email.ru | Warren St, Staten Island, NY, USA | 60 Floral Blvd, Floral Park, USA",

    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Проверка регистрационной формы через CsvSource  \"{0}\" ")
    void ComplexFormTest(String name, String email, String currentAddress, String permanentAddress ){
        Selenide.$("#userName").setValue(name);
        Selenide.$("#userEmail").setValue(email);
        Selenide.$("#currentAddress").setValue(currentAddress);
        Selenide.$("#permanentAddress").setValue(permanentAddress);
        Selenide.$("#submit").click();
        Selenide.$("#output > .border").shouldHave(
                text(name),
                text(email),
                text(currentAddress),
                text(permanentAddress));
    }


    // вариант с  MethodSource
    static Stream<Arguments> mixedFormTest(){
        return Stream.of( // каждый аргумент передается на каждом запуске теста
                Arguments.of("Alan", "alan@email.ru", "25 W Houston St, New York, USA", " 145 Carnation Ave, Floral Park, USA"),
                Arguments.of("Bob", "bob@email.ru", "Warren St, Staten Island NY, USA", "60 Floral Blvd, Floral Park, USA")
        );


    }

    @MethodSource(value = "mixedFormTest") // принимает специальный метод, который предоставит набор аргументов произвольного типа
    @ParameterizedTest(name = "Проверка регистрационной формы через MethodSource  \"{0}\" ")
    void mixedFormTest(String name, String email, String currentAddress, String permanentAddress ){
        Selenide.$("#userName").setValue(name);
        Selenide.$("#userEmail").setValue(email);
        Selenide.$("#currentAddress").setValue(currentAddress);
        Selenide.$("#permanentAddress").setValue(permanentAddress);
        Selenide.$("#submit").click();
        Selenide.$("#output > .border").shouldHave(
                text(name),
                text(email),
                text(currentAddress),
                text(permanentAddress));

    }



}
