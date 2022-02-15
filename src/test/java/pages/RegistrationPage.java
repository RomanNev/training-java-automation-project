package pages;

import com.codeborne.selenide.SelenideElement;
import pages.component.CalendarComponent;



import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput =  $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput =  $("#userNumber"),
            subjectsTextInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateSelect =  $("#react-select-3-input"),
            stateSelectSet =   $("#react-select-3-option-0"),
            citySelect =   $("#react-select-4-input"),
            citySelectSet = $("#react-select-4-option-0"),
            submitForm = $("#submit");


    //actions

    public RegistrationPage submitForm(){
        submitForm.click();
        return this;
    }

    public RegistrationPage selectStateAndCity(String stateName, String cityName){
        stateSelect.setValue(stateName);
        stateSelectSet.click();
        citySelect.setValue(cityName);
        citySelectSet.click();
        return this;
    }

    public RegistrationPage setCurrentAddressInput(String currentAddress){
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public RegistrationPage selectUploadPicture(String fileName){
        uploadPictureInput.uploadFromClasspath("jpg/" + fileName);
        return this;
    }

    public RegistrationPage selectGender(String gender){
        $(byText(gender)).click();
        return this;
    }

    public RegistrationPage setHobbies(String hobbies){
        $(byText(hobbies)).click();
        return this;
    }


    public RegistrationPage setSubjectsInput(String subjects){
        subjectsTextInput.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationPage setUserEmailInput(String userEmail){
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setUserNumberInput(String userNumber){
        userNumberInput.setValue(userNumber);
        return this;
    }

   public RegistrationPage openPage(){
       open("/automation-practice-form");
       return this;
   }

   public RegistrationPage setFirstNameInput(String firstName) {
       firstNameInput.setValue(firstName);
       return this;
   }

   public RegistrationPage setLastNameInput(String lastName) {
       lastNameInput.setValue(lastName);
       return this;
   }

   public RegistrationPage setBirthDate(String day, String month, String year){
       $("#dateOfBirthInput").click();
       calendarComponent.setDate(day,month,year);
       return this;

   }

   public void checkForm(){
       $(".table-responsive").shouldHave(
               text("Roman Golub"),
               text("Golub@mail.guli"),
               text("Male"),
               text("7111222334"),
               text("24 August,1900"),
               text("English"),
               text("Music"),
               text("golub.jpg"),
               text("Lavrushinsky Ln, 10, Moscow, 119017"),
               text("NCR Delhi"));

   }

}
