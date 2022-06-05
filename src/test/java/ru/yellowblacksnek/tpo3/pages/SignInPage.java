package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {
    private SelenideDriver driver;
    public SignInPage(SelenideDriver driver) {this.driver = driver;}

    public static final String url = "https://www.fandom.com/signin";
    public static final String title = "Fandom Auth";

    public static String username = System.getProperty("username") != null ? System.getProperty("username") : "Menorealuser";//"Menorealuser" "Menorealuser2";
    public static String password = System.getProperty("password") != null ? System.getProperty("password") : "Vx2w7VXkX3z5akL";//"Vx2w7VXkX3z5akL";

    public SelenideElement form = driver.$x("//descendant::form[contains(@class, 'login_form')]");
    public SelenideElement usernameField = form.$x("descendant::input[@data-test='signin-username-field']");
    public SelenideElement passwordField = form.$x("descendant::input[@data-test='signin-password-field']");
    public SelenideElement submitButton = form.$x("descendant::button[@data-test='Sign in-submit-button']");
    public SelenideElement formError = form.$x("descendant::div[@data-test='error-form']");

    public SelenideElement showPasswordButton = form.$x("descendant::span[contains(@class, 'Password_icon')]");


    public void sendLoginData() {
        usernameField.scrollTo().click();
        usernameField.sendKeys(username);
        passwordField.scrollTo().click();
        passwordField.sendKeys(password);
        submitButton.scrollTo().click();
    }

    public void sendIncorrectLoginData() {
        usernameField.scrollTo().click();
        usernameField.sendKeys(username);
        passwordField.scrollTo().click();
        passwordField.sendKeys("0123");
        submitButton.scrollTo().click();
    }

}
