package ru.yellowblacksnek.tpo3.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yellowblacksnek.tpo3.pages.GlobalNavigation;
import ru.yellowblacksnek.tpo3.pages.MainPage;
import ru.yellowblacksnek.tpo3.pages.SignInPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {
    SelenideDriver chrome = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    MainPage mainPage = new MainPage(chrome);
    GlobalNavigation navigation = mainPage.navigation;
    SignInPage signPage = mainPage.signPage;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x1024";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void setUp() {
        open(mainPage.url);
//        userIsNotSigned();
        if(navigation.userIsSigned()) navigation.clickSignOut();
//        navigation.clickSignIn();
        open(signPage.url);
    }



    private void userIsNotSigned() {
        navigation.userButton.hover();
        navigation.signInButton.shouldBe(visible);
        navigation.registerButton.shouldBe(visible);
    }

    private void userIsSigned() {
        navigation.userButton.hover();
//        mainPage.profileButton.shouldBe(visible);
        navigation.signOutButton.shouldBe(visible);
    }

//    @Test
//    public void signInWindowsOpens() {
//        signPage.usernameField.shouldBe(visible);
//        signPage.passwordField.shouldBe(visible);
//    }

    @Test
    public void userCanSignIn() {
        signPage.sendLoginData();
//        sleep(100000);
        refresh();
        userIsSigned();
//        open("https://community.fandom.com");
//        userIsSigned();
//        mainPage.navigationBar.shouldHave(cssClass("logged-in"));
    }

    @Test
    public void userCanSignOut() {
        userCanSignIn();
        navigation.clickSignOut();
        userIsNotSigned();
    }

    @Test
    public void userCannotSubmitEmpty() {
        signPage.submitButton.shouldBe(disabled);
    }

    @Test
    public void userSeesErrorOnIncorrectLoginData() {
        signPage.sendIncorrectLoginData();
        signPage.formError.shouldBe(visible);
    }

    @Test
    public void userCanSeePassword() {
        signPage.passwordField.shouldHave(attribute("type", "password"));
        signPage.showPasswordButton.scrollTo().click();
        signPage.passwordField.shouldHave(attribute("type", "text"));
    }
}
