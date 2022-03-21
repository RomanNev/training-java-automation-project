package tests.selenideFiles;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tests.selenideFiles.domain.UserJsonObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class jsonTestParsingJackson {
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void parsingTestJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = classLoader.getResourceAsStream("files/json_file.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            UserJsonObject userJsonObject = objectMapper.readValue(json, UserJsonObject.class);
            assertThat(userJsonObject.name).isEqualTo("Roman");
            assertThat(userJsonObject.surname).isEqualTo("Golub");
            assertThat(userJsonObject.address.street).isEqualTo("Mira");
            assertThat(userJsonObject.address.house).isEqualTo(60);
        }
    }
}
