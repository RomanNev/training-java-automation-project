package junitTest;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.open;


public class ExampleParameterizedTest {

    @BeforeEach
    void precondition(){
        open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser(){
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"test1","test2"}) // один аргумент --> один запуск теста
    @ParameterizedTest(name = "Проверка отображения поисковых результатов \"{0}\" ")
    void simpleTest(String testData){
        Selenide.$("#text").setValue(testData);

    }

    @CsvSource(value = {
            "testdata1, testdata2", //каждый запуск одна строчка по 2 аргумента
            "testdata3, testdata4",

    },
            delimiter = '|'
    )

    @ParameterizedTest(name = "Проверка отображения поисковых результатов \"{0}\" ")
    void ComplexSimpleTest(String testData, String exectedTest ){
        Selenide.$("#text").setValue(testData);
        Selenide.$("#text").setValue(exectedTest);

    }


    static Stream<Arguments> mixedArgumentsTestData(){
        return Stream.of( // каждый аргумент передается на каждом запуске теста
          Arguments.of("data1", List.of(1,2,3)), //с любыми типами данных
          Arguments.of("data2", List.of(1,2))
        );

    }

    @MethodSource(value = "mixedArgumentsTestData") // принимает специальный метод, который предоставит набор аргументов произвольного типа
    @ParameterizedTest
    void mixedArgumentsTest(String arc, List<Integer> secondArc){
        System.out.println("string " + arc + "list " + secondArc);

    }

}
