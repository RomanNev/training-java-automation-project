package tests.selenideFiles;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFileTestExample {

    @Test
    void selenideDownloadTest() throws Exception {  // скачивание файла
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile =  Selenide.$("#raw-url").download(); // хранит ссылку или путь к файлу, работает только с элементами, которые содержат атрибут href
        //чтение файла старый способ
        try (InputStream is = new FileInputStream(downloadedFile)){
            assertThat(new String(is.readAllBytes(), UTF_8)).contains("This repository is the home of the next generation of JUnit"); // проверка нахождения подстроки в файле
        }
        //чтение файла новый способ
        // Files.readString(downloadedFile.toPath(), UTF_8);
    }


    @Test
    void uploadSelenideTest(){ // загрузка файла
        Selenide.open("https://the-internet.herokuapp.com/upload"); // загрузка через input file
        Selenide.$("input[type='file']").uploadFromClasspath("files/1.txt");
        Selenide.$("#file-submit").click();
        Selenide.$("div.example").shouldHave(Condition.text("File Uploaded!"));
        Selenide.$("#uploaded-files").shouldHave(Condition.text("1.txt"));
    }


}
