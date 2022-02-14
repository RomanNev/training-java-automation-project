package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    // locators
    SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput =  $("#lastName");



    //actions

   public void openPage(){
       open("/automation-practice-form");

   }

   public void setFirstNameInput(String firstName) {
       firstNameInput.setValue(firstName);

   }

   public void setLastNameInput(String lastName) {
       lastNameInput.setValue(lastName);

   }

   public void checkForm(){
       $(".table-responsive").shouldHave(
               text("checkData[0]"),
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
