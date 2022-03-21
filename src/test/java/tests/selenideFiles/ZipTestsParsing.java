package tests.selenideFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZipTestsParsing {

    @Test
    void parseZipTest() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        ZipFile zipFile = new ZipFile("src/test/resources/files/archive.zip");

        try (InputStream is = classLoader.getResourceAsStream("files/archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) { // достаем файлы из архива
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) { // перебираем файлы
                if (entry.getName().contains("pdf_sample.pdf")) {
                    PDF pdf = new PDF(zipFile.getInputStream(entry));
                    assertThat(pdf.text).contains("A Simple PDF File");
                    assertThat(pdf.text).contains("This is a small demonstration");

                } else if (entry.getName().contains("cities.csv")) {
                    try (CSVReader reader = new CSVReader(new InputStreamReader(zipFile.getInputStream(entry)));) {
                        List<String[]> content = reader.readAll();
                        assertThat(content.get(0)).contains(
                                "City",
                                "State");
                    }
                } else if (entry.getName().contains("sample3.xlsx")) {
                    XLS xls = new XLS(zipFile.getInputStream(entry));
                    assertThat(xls.excel
                            .getSheetAt(0)// первая таблица
                            .getRow(1) // 2 строчка
                            .getCell(1) // второй столбец
                            .getStringCellValue()).contains("January"); // значение содержит

                }

            }
        }
    }

}
