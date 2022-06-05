package ru.yellowblacksnek.tpo3.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yellowblacksnek.tpo3.pages.GlobalNavigation;
import ru.yellowblacksnek.tpo3.pages.MainPage;
import ru.yellowblacksnek.tpo3.pages.ProfilePage;
import ru.yellowblacksnek.tpo3.pages.SignInPage;

import java.util.HashMap;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePageTest {
    ProfilePage profile = new ProfilePage();


    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x1024";
        Configuration.timeout = 20000;
        open(SignInPage.url);
        SignInPage signPage = new SignInPage();
        if(signPage.form.is(Condition.visible))
            signPage.sendLoginData();
    }

    @BeforeEach
    public void setUp() {
//        open(new GlobalNavigation().profileButton.getAttribute("href"));
//        open(signPage.url);
//        signPage.sendLoginData();
//        open(new MainPage().url);
        open(profile.getUrl("community", SignInPage.username));

        try {
            WebDriverWait wait = new WebDriverWait(webdriver().object(), 1 /*timeout in seconds*/);
            if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                switchTo().alert().accept();
            }
        } catch (Exception ignored) {}

        sleep(1000);
    }

//    @Test
//    public void editorOpens() {
//        profile.editProfileButton.scrollTo().click();
//        profile.profileEditor.shouldBe(Condition.visible);
//    }

//    @Test
//    public void editorInputsAreSaved() {
//        editorOpens();
//
//        String name = "testName";
//        String bio = "testBio";
//        String twitter = "testTwitter";
//        String facebook = "testFacebook";
//        String discord = "testDiscord";
//        String website = "testWebsite";
//        profile.setInput(profile.editorNameInput, name);
//        profile.setInput(profile.editorBioInput, bio);
//        profile.setInput(profile.editorTwitterInput, twitter);
//        profile.setInput(profile.editorFacebookInput, facebook);
//        profile.setInput(profile.editorDiscordInput, discord);
//        profile.setInput(profile.editorWebsiteInput, website);
//
////        profile.saveEditorButton.scrollTo().click();
//        executeJavaScript("arguments[0].scrollTo().click()", profile.saveEditorButton);
//
//        sleep(1000);
//
//        assertEquals(profile.secondUsername.text().substring(3), name);
//        refresh();
//        assertEquals(profile.secondUsername.text().substring(3), name);
//    }

    public void openEditAbout() {
        profile.editAbout.scrollTo().click();
        sleep(1000);
        if(profile.modalFoot.isDisplayed()) {
            profile.modalFoot.$x("descendant::span").scrollTo().click();
        }
    }

    public void setAbout(String text) {
        openEditAbout();
        profile.setInput(profile.aboutEditorFirst, text);
        saveAboutEdit();
    }

    public void clearAbout() {
        if(profile.aboutContent.text().isEmpty()) return;
        setAbout(null);
        assertTrue(profile.aboutContent.text().isEmpty());
    }

    public void saveAboutEdit() {
        assertTrue(profile.aboutSaveButton.getAttribute("class").contains("oo-ui-widget-enabled"));
        if(profile.aboutSaveButton.is(Condition.visible))
            profile.aboutSaveButton.scrollTo().click();
        else executeJavaScript("arguments[0].click()", profile.aboutCancelButton.$x("a/span[@class='oo-ui-labelElement-label']"));
    }

    public void cancelAboutEdit() {
        assertTrue(profile.aboutCancelButton.getAttribute("class").contains("oo-ui-widget-enabled"));
        profile.aboutCancelButton.shouldBe(Condition.visible);
            executeJavaScript("arguments[0].click()", profile.aboutCancelButton.$x("a/span[@class='oo-ui-labelElement-label']"));
        sleep(1000);
        if(profile.modal.is(Condition.visible)) {
            profile.modalFootRightButton.scrollTo().click();
        }
    }

    public String getNotMatchingString() {
        String a = String.valueOf(new Random().nextInt());
        while(profile.aboutContent.text().contains(a)) {
            a = String.valueOf(new Random().nextInt());
        }
        return a;
    }

    @Test
    public void aboutEditNoWarningOnCancelWhenNotChanged() {
        openEditAbout();
        profile.aboutCancelButton.scrollTo().click();
        profile.modal.shouldBe(Condition.not(Condition.visible));
        profile.aboutEditorSurface.shouldBe(Condition.not(Condition.visible));
    }

    @Test
    public void aboutEditShowsWarningOnCancelWhenChanged() {
        openEditAbout();
        profile.sendInput(profile.aboutEditorFirst, "text");
        profile.aboutCancelButton.scrollTo().click();
        profile.modal.shouldBe(Condition.visible);
        profile.modalFootRightButton.scrollTo().click();
    }

    @Test
    public void aboutEditDiscardOnCancel() {
        openEditAbout();
        String a = getNotMatchingString();
        profile.setInput(profile.aboutEditorFirst, a);
        cancelAboutEdit();
        profile.aboutContent.shouldHave(Condition.not(Condition.text(a)));
    }


    @Test
    public void aboutEditsAreSaved() {
        String a = getNotMatchingString();
        setAbout(a);
        profile.aboutContent.shouldHave(Condition.text(a));
    }

    @Test
    public void userCanPreviewAboutWhenNotChanged() {
        openEditAbout();
        profile.aboutShowChangesButton.scrollTo().click();
        profile.diffNoChanges.shouldBe(Condition.visible);

        profile.diffResumeButton.scrollTo().click();
        cancelAboutEdit();
    }

    @Test
    public void userCanPreviewAboutWhenChanged() {

        String a = getNotMatchingString();
        openEditAbout();
        profile.setInput(profile.aboutEditorFirst, a);

        profile.aboutShowChangesButton.scrollTo().click();

        profile.modal.shouldBe(Condition.exist);
        profile.diffAdded.shouldHave(Condition.exactText(a));

        profile.diffResumeButton.scrollTo().click();
        cancelAboutEdit();
    }

    @Test
    public void userCanChangeAboutTextStyle() {

        String testText = "testText";
        openEditAbout();
        profile.setInput(profile.aboutEditorFirst, testText);

        profile.aboutEditorFirst.scrollTo().click();
        Actions actionProvider = new Actions(webdriver().object());
        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("a").keyUp(Keys.LEFT_CONTROL).build().perform();
//        profile.aboutEditorFirst.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
//        profile.aboutEditorBoldButton.scrollTo().click();
        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("b").keyUp(Keys.LEFT_CONTROL).build().perform();

        profile.aboutEditorFirst.$x("b[text()='testText']").should(Condition.exist);
        cancelAboutEdit();
    }

    @Test
    public void userCanOpenEditorOptionsSelective() {
        openEditAbout();
//        String[] names = {"meta", "categories", "settings", "advancedSettings", "languages", "templatesUsed"};

        HashMap<String, SelenideElement> mappings = new HashMap<>();
        mappings.put("meta", profile.categoriesOption);
        mappings.put("categories", profile.categoriesOption);
        mappings.put("settings", profile.settingsOption);
        mappings.put("advancedSettings", profile.advancedOption);
        mappings.put("languages", profile.languagesOption);
        mappings.put("templatesUsed", profile.templatesOption);

        for(String name : mappings.keySet()) {
            profile.aboutEditorOptionsButton.scrollTo().click();
            SelenideElement group = $x("//descendant::div[contains(@class, 'oo-ui-popupToolGroup-active-tools')]");
            SelenideElement elem = group.$x(String.format("span[contains(@class, 'oo-ui-tool-name-%s')]", name));
            elem.scrollTo().click();
            mappings.get(name).shouldBe(Condition.attribute("aria-selected", "true"));
            profile.diffResumeButton.scrollTo().click();
        }

        cancelAboutEdit();
    }

    @Test
    public void userCanOpenEditorGuide() {
        openEditAbout();

        profile.aboutEditorModesButton.scrollTo().click();
        profile.aboutEditorGuideButton.scrollTo().click();

        switchTo().window(1);
        webdriver().shouldHave(url(profile.guideUrl));
        closeWindow();
        switchTo().window(0);

        cancelAboutEdit();
    }

    @Test
    public void userCanOpenShortcutsHelp() {
        openEditAbout();

        profile.aboutEditorModesButton.scrollTo().click();
        profile.aboutEditorShortcutsButton.shouldBe(Condition.visible);
        profile.aboutEditorShortcutsButton.scrollTo().click();

        $x("//descendant::div[@class='ve-ui-commandHelpDialog-container']").shouldBe(Condition.visible);
        profile.diffSaveButton.scrollTo().click();

        cancelAboutEdit();
    }



}
