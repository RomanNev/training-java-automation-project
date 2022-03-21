package tests.selenideFiles.codeLesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTesExample {
    ClassLoader classLoader = getClass().getClassLoader(); //определяем classLoader

    @Test
    void parsePdfExample() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = Selenide.$(byText("PDF download")).download();

        PDF pdf = new PDF(pdfDownload);
        assertThat(pdf.author).contains("Marc Philipp");
        assertThat(pdf.numberOfPages).isEqualTo(166);
        assertThat(pdf.text).contains("JUnit 5 User Guide");
    }


    @Test
    void parseXlsTest() throws Exception {
        Selenide.open("http://romashka2008.ru/price");
        File xlsDownload = Selenide.$(".site-main__inner a[href*='prajs_ot']").download();
        XLS xls = new XLS(xlsDownload);
        assertThat(xls.excel
                .getSheetAt(0)// первая таблица
                .getRow(11) // 12 строчка
                .getCell(1) // второй столбец
                .getStringCellValue()).contains("Сахалинская обл"); // значение содержит
    }


    @Test
    void parseCsvTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("files/cities.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)).contains(
                    "City",
                    "State");
        }
    }


    @Test
    void parseZipTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("files/sample-zip-file.zip");
             ZipInputStream zis = new ZipInputStream(is)) { // возращает следующие элемент в архиве
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("sample.txt"); // архив содежрит файл
            }
        }
    }


    @Test
    void jsonCommonTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/simple.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            assertThat(jsonObject.get("name").getAsString()).isEqualTo("Dmitrii");
            assertThat(jsonObject.get("address").getAsJsonObject().get("street").getAsString()).isEqualTo("Mira");
        }
    }

}
