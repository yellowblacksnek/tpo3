package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class GlobalNavigation {
    private SelenideDriver driver;

    public GlobalNavigation(SelenideDriver driver) {
        this.driver = driver;
        signPage = new SignInPage(driver);
    }

    public SignInPage signPage;

    public SelenideElement navigationBar = driver.$x("//div[contains(@class, 'global-navigation')]");
    public SelenideElement navigationBottom = navigationBar.$x("descendant::div[contains(@class, 'global-navigation__bottom')]");
    public SelenideElement navigationTop = navigationBar.$x("descendant::div[contains(@class, 'global-navigation__top')]");

    public SelenideElement searchButton = navigationTop.$x("nav/a[contains(@class, 'global-navigation__search')]");
    //            $x("//descendant::div[contains(@class, 'global-navigation')]/" +
//                    "descendant::div[contains(@class, 'global-navigation__top')]/" +
//                    "nav/a[contains(@class, 'global-navigation__search')]");

    public SelenideElement searchModal = navigationBar.$x( "div[contains(@class,'global-nav-search-modal')]");
    public SelenideElement searchModalForm = searchModal.$x("descendant::form");
    public SelenideElement searchModalLabel = searchModalForm.$x("descendant::form/descendant::label[@class='search-box__search-label']");
    public SelenideElement searchModalInput = searchModalLabel.$x("input");
//            $x("//descendant::div[contains(@class, 'global-navigation')]/" +
//                    "div[contains(@class,'global-nav-search-modal')]");

    public SelenideElement userButton = navigationBottom.$x("div[last()]");
//            $x("//descendant::div[contains(@class, 'global-navigation')]/" +
//                    "descendant::div[contains(@class, 'global-navigation__bottom')]/" +
//                    "div[last()]");//"descendant::div[contains(@class, 'wds-dropdown__toggle')]");

    public SelenideElement signInButton = userButton.$x("div[contains(@class, 'wds-dropdown__content')]/a[contains(@href, 'signin')]");
//            $x("//div[contains(@class, 'global-navigation')]/" +
//                    "descendant::div[contains(@class, 'global-navigation__bottom')]/" +
//                    "descendant::div[contains(@class, 'wds-dropdown__content')]/" +
//                    "a[contains(@href, 'signin')]");

    public SelenideElement registerButton = userButton.$x("descendant::a[contains(@href, 'register')]");
//            $x("//descendant::div[contains(@class, 'global-navigation')]/" +
//                    "descendant::div[contains(@class, 'global-navigation__bottom')]/" +
////                    "descendant::div[contains(@class, 'wds-dropdown__content')]/" +
//                    "descendant::a[contains(@href, 'register')]");

    public SelenideElement profileButton =
            driver.$x("//descendant::div[contains(@class, 'global-navigation')]/" +
                    "descendant::div[contains(@class, 'global-navigation__bottom')]/" +
//                    "descendant::div[contains(@class, 'wds-dropdown__content')]/" +
                    "descendant::a[contains(@href, 'wiki/User')]");

    public SelenideElement signOutButton = userButton.$x("descendant::button[@type='submit' and contains(@class, 'signout') or contains(@class, 'sign-out')]");
//            $x("//descendant::div[contains(@class, 'global-navigation')]/" +
//                    "descendant::div[contains(@class, 'global-navigation__bottom')]/" +
////                    "descendant::div[contains(@class, 'wds-dropdown__content')]/" +
//                    "descendant::button[@type='submit' and contains(@class, 'signout') or contains(@class, 'sign-out')]");

    public void clickSignIn() {
        userButton.scrollTo();
        userButton.hover();
        signInButton.shouldBe(visible).click();

//        signPage.sendLoginData();
    }

    public void clickProfile() {
        userButton.scrollTo();
        userButton.hover();
        profileButton.click();
    }

    public void clickSignOut() {
        userButton.scrollTo();
        userButton.hover();
        signOutButton.shouldBe(visible).click();
    }

    public boolean userIsSigned() {
        userButton.hover();
        return signInButton.is(not(visible)) && signOutButton.is(visible);
    }

//    public void signOut() {
//        if(userIsSigned()) {
//            clickSignOut();
//        }
//    }
//
//    public void signIn() {
//        if(!userIsSigned()) {
//            clickSignIn();
//        }
//    }
}
