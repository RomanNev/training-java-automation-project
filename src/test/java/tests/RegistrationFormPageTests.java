package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationFormPageTests {

    RegistrationPage registrationPage = new RegistrationPage();

    //configuration variables
    String firstName = "Volodya";
    String lastName = "Golub";
    String userEmail = "Golub@mail.guli";
    String userNumber = "7111222334";
    String gender = "Male";
    String calendarMonth = "August";
    String calendarYear = "1900";
    String subjectsText = "English";
    String hobbiesText = "Music";
    String fileName = "golub.jpg";
    String currentAddress = "Lavrushinsky Ln, 10, Moscow, 119017";
    String stateName = "NCR";
    String cityName = "Delhi";
    String dateOfBirth = "24 August,1900";
    String stateAndCityName = "NCR Delhi";
    String fullName = "Roman Golub";


    @BeforeAll
    static void beforeAll() {
        //Arrange
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {
        // Act
        registrationPage
                .openPage()
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setUserEmailInput(userEmail)
                .setUserNumberInput(userNumber)
                .selectGender(gender)
                .setBirthDate(calendarMonth, calendarYear)
                .setSubjectsInput(subjectsText)
                .setHobby(hobbiesText)
                .selectUploadPicture(fileName)
                .setCurrentAddressInput(currentAddress)
                .selectStateAndCity(stateName, cityName)
                .submitForm();


        // Assert
        registrationPage
                .checkForm(fullName)
                .checkForm(userEmail)
                .checkForm(gender)
                .checkForm(userNumber)
                .checkForm(dateOfBirth)
                .checkForm(subjectsText)
                .checkForm(fileName)
                .checkForm(currentAddress)
                .checkForm(stateAndCityName);


    }

}