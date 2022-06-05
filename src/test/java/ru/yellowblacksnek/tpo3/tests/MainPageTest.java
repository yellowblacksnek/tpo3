package ru.yellowblacksnek.tpo3.tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yellowblacksnek.tpo3.pages.ArticlePage;
import ru.yellowblacksnek.tpo3.pages.GlobalNavigation;
import ru.yellowblacksnek.tpo3.pages.MainPage;
import ru.yellowblacksnek.tpo3.pages.SearchPage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverConditions.url;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {
//    MainPage mainPage = new MainPage();
//    GlobalNavigation navigation = mainPage.navigation;
//    SearchPage searchPage = new SearchPage();
//    ArticlePage articlePage = new ArticlePage();

    static SelenideDriver chrome;
    static SelenideDriver firefox;
    public static List<SelenideDriver> getDrivers() {
        if(chrome == null) chrome = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
        if(firefox == null) firefox = new SelenideDriver(new SelenideConfig().browser("firefox").browserSize("1280x1024").timeout(20000));
        return Stream.of(chrome, firefox).filter(i -> i != null).collect(Collectors.toList());
    }

//    @BeforeAll
//    public static void setUpAll() {
//        Configuration.browserSize = "1280x1024";
//        Configuration.timeout = 20000;
//    }

//    @BeforeEach
////    @ParameterizedTest
////    @MethodSource("getDrivers")
//    public void setUp(SelenideDriver driver) {
//        driver.open(MainPage.url);
//    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void searchModalAppears(SelenideDriver driver) {
        driver.open(MainPage.url);

//        System.out.println(mainPage.searchButton.getAttribute("class"));
        GlobalNavigation navigation = new GlobalNavigation(driver);
        navigation.searchButton.scrollTo().click();
        navigation.searchModal.shouldBe(visible);

//        $("[data-test='search-input']").sendKeys("Selenium");
//        $("button[data-test='full-search-button']").click();
//
//        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
    }

    public void testSearch(SelenideElement searchForm, SelenideDriver driver) {
        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String testQuery = "testQuery";
        mainPage.searchLabel(searchForm).scrollTo().click();
        mainPage.searchInput(mainPage.searchLabel(searchForm)).sendKeys(testQuery);
        mainPage.searchSubmit(searchForm).scrollTo().click();
        driver.webdriver().shouldHave(url(mainPage.url+"?s="+testQuery));
        searchPage.searchHeader.shouldHave(text(testQuery));
    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void searchTop(SelenideDriver driver){
        MainPage mainPage = new MainPage(driver);
        testSearch(mainPage.searchFormTop, driver);
    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void searchBottom(SelenideDriver driver){
        MainPage mainPage = new MainPage(driver);
        testSearch(mainPage.searchFormBottom, driver);
    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void searchModal(SelenideDriver driver){
        searchModalAppears(driver);
        MainPage mainPage = new MainPage(driver);
        testSearch(mainPage.navigation.searchModalForm, driver);
    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void articlesAreCorrect(SelenideDriver driver){
        MainPage mainPage = new MainPage(driver);
        ArticlePage articlePage = new ArticlePage(driver);
        mainPage.topicsSections.forEach(i -> {
            String sectionName = mainPage.getSectionName(i);
            mainPage.getArticlesForTopic(i).forEach(j -> {
                String articleURL = j.$x("a").getAttribute("href");
                String script = String.format("window.open('%s');", articleURL);
                executeJavaScript(script);
                switchTo().window(1);
                articlePage.header.shouldBe(visible);
                assertTrue(articlePage.tags.texts().stream().anyMatch(tag -> tag.equalsIgnoreCase(sectionName)));
                closeWindow();
                switchTo().window(0);
            });
        });
    }

//    @Test
    @ParameterizedTest
    @MethodSource("getDrivers")
    public void showAllLinksAreCorrect(SelenideDriver driver){
        MainPage mainPage = new MainPage(driver);
        int size = mainPage.topicsSections.size();
        for(int i = 0; i< size; i++) {
            SelenideElement section = mainPage.topicsSections.get(i);
            String sectionName = mainPage.getSectionName(section);
            section.$x("div/div[1]/a").scrollTo().click();
            webdriver().shouldHave(urlContaining(sectionName.toLowerCase()));
            assertTrue(title().toLowerCase().contains(sectionName.toLowerCase()));
            sleep(500);
            back();
        }
    }
}
