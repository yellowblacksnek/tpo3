package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideDriver driver;
    public MainPage(SelenideDriver driver) {
        this.driver = driver;
        navigation = new GlobalNavigation(driver);
        signPage = navigation.signPage;
    }

    public static String url = "https://www.fandom.com/";
    public GlobalNavigation navigation;
    public SignInPage signPage;

    public SelenideElement searchFormTop =
            $x("//descendant::div[@class='feed-header']/" +
                    "descendant::form[not(@hidden) and contains(@class, 'search-box')]");
    public SelenideElement searchFormBottom =
            $x("//descendant::div[@class='search-box-bottom-wrapper']/" +
                    "descendant::form[not(@hidden) and contains(@class, 'search-box')]");

    public SelenideElement searchLabel(SelenideElement searchForm) {
        return searchForm.$x("descendant::label[contains(@class, 'search-box')]");
    }

    public SelenideElement searchInput(SelenideElement searchLabel) {
        return searchLabel.$x("input[@name='s']");
    }

    public SelenideElement searchSubmit(SelenideElement searchForm) {
        return searchForm.$x("descendant::button[@type='submit']");
    }


    public SelenideElement feed = $x("//div[@class='feed-layout']");
    public ElementsCollection topicsSections = feed.$$x("div[contains(@class, 'feed-layout')]/div");

    public String getSectionName(SelenideElement section) {
        return section.$x("div/div[1]/h2").text();
    }

    public ElementsCollection getArticlesForTopic(SelenideElement section) {
        return section.$$x("div/div[2]/div[@class='feed-section__cards']/div");
    }

    public SelenideElement getShowAllLinkForTopic(SelenideElement section) {
        return section.$x("div/div[1]/a");
    }

}

//jetip47331@dufeed.com
//Menorealuser
//Vx2w7VXkX3z5akL
