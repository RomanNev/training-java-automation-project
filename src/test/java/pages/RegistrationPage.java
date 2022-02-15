package pages;

import com.codeborne.selenide.SelenideElement;
import pages.component.CalendarComponent;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput =  $("#lastName");

    //actions

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

   public void setBirthDate(String day, String month, String year){
       $("#dateOfBirthInput").click();
       calendarComponent.setDate(day,month,year);


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
