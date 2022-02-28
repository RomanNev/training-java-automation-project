package tests.dragAndDrop;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class DragAndDropTest {

    @BeforeAll
    static void beforeAll() {
        //Arrange
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void shouldDragAndDrop(){
        //act
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");

        //assert
        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));



    }
}
