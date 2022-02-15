package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPageTests {

    RegistrationPage registrationPage = new RegistrationPage();

    //configuration variables
    String firstName = "Roman";
    String lastName = "Golub";
    String userEmail = "Golub@mail.guli";
    String userNumber = "71112223344";
    String gender = "Male";
    String calendarDay = "24";
    String calendarMonth = "August";
    String calendarYear = "1900";
    String subjectsText = "English";
    String hobbiesText = "Music";
    String fileName = "golub.jpg";
    String currentAddress = "Lavrushinsky Ln, 10, Moscow, 119017";
    String stateName = "NCR";
    String cityName = "Delhi";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {
        registrationPage
                .openPage()
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName);


        $("#userEmail").setValue("Golub@mail.guli");
        $("#userNumber").setValue("71112223344");
        $(byText("Male")).click();

        registrationPage.setBirthDate(calendarDay,calendarMonth,calendarYear);



        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("jpg/golub.jpg");
        $("#currentAddress").setValue("Lavrushinsky Ln, 10, Moscow, 119017");
        $("#react-select-3-input").setValue("NCR");
        $("#react-select-3-option-0").click();
        $("#react-select-4-input").setValue("Delhi");
        $("#react-select-4-option-0").click();
        $("#submit").click();


        registrationPage.checkForm();

    }

}