package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

//    public SelenideElement username = $x("//descendant::div[@data-test='header-attributes']/h1");
//    public SelenideElement secondUsername = $x("//descendant::div[@data-test='header-attributes']/h2");
//    public SelenideElement bio = $x("descendant::div[@class='user-identity-bio ']");
//
//    public SelenideElement social = $x("descendant::ul[@class='user-identity-social']");
//    public SelenideElement twitter = social.$x("descendant::a[descendant::*[name()='use' and @*='#wds-icons-twitter']]");
//    public SelenideElement facebook = social.$x("descendant::a[descendant::*[name()='use' and @*='#wds-icons-facebook']]");
//    public SelenideElement discord = social.$x("descendant::div[descendant::*[name()='use' and @*='#wds-icons-discord']]");
//    public SelenideElement website = social.$x("descendant::a[descendant::*[name()='use' and @*='#wds-icons-link']]");
//
//    public SelenideElement editProfileButton = $x("//descendant::div[@data-test='header-actions']/button");
//
//    public SelenideElement profileEditor = $x("//descendant::main/descendant::div[@class='modal-wrapper user-profile-editor']");
//    public SelenideElement closeEditorButton = profileEditor.$x("div[@class='modal-wrapper__header']/button[1]");
//    public SelenideElement saveEditorButton = profileEditor.$x("div[@class='modal-wrapper__header']/button[2]");
//
//    public SelenideElement editorNameInput = profileEditor.$x("descendant::div[label/text() = 'Name']/input");
//    public SelenideElement editorBioInput = profileEditor.$x("descendant::div[label/text() = 'Bio']/textarea");
//    public SelenideElement editorTwitterInput = profileEditor.$x("descendant::div[label/text() = 'Twitter']/input");
//    public SelenideElement editorFacebookInput = profileEditor.$x("descendant::div[label/text() = 'Facebook']/input");
//    public SelenideElement editorDiscordInput = profileEditor.$x("descendant::div[label/text() = 'Discord']/input");
//    public SelenideElement editorWebsiteInput = profileEditor.$x("descendant::div[label/text() = 'Website']/input");

    public final String guideUrl = "https://www.mediawiki.org/wiki/Help:VisualEditor/User_guide";

    public SelenideElement aboutContent = $x("descendant::div[contains(@class, 'page-content')]/descendant::div[@class='mw-parser-output']");

    public SelenideElement editAbout = $x("descendant::a[contains(@href, '?veaction=edit')]"); //@data-tracking-label='ca-ve-edit'
    public SelenideElement aboutEditor = $x("descendant::div[contains(@class, 'page-content')]/div[contains(@class, 've-init-target')]");
    public SelenideElement aboutEditorSurface =
            $x("//descendant::div[contains(@class, 've-ce-surface')]/" +
                    "div[contains(@class, 've-ce-branchNode')]");
    public SelenideElement aboutEditorFirst = aboutEditorSurface.$x("*");

    public SelenideElement aboutSaveButton = $x("descendant::span[contains(@class, 've-ui-summaryPanel-saveButton')]");
    public SelenideElement aboutCancelButton = $x("descendant::span[contains(@class, 've-ui-summaryPanel-cancelButton')]");

    public SelenideElement aboutShowChangesButton = $x("descendant::span[contains(@class, 've-ui-summaryPanel-showChangesButton')]/a");
    public SelenideElement aboutEditorBoldButton = $x("descendant::span[contains(@class, 'oo-ui-tool-name-bold')]/a");
    public SelenideElement aboutEditorOptionsButton = $x("descendant::div[contains(@class, 've-test-toolbar-pageMenu')]");

    public SelenideElement aboutEditorHeader = $x("//descendant::div[@class='ve-fd-header']");
    public SelenideElement aboutEditorModesButton = aboutEditorHeader.$x("descendant::span[contains(@class, 've-ui-modeSwitchPopupButtonWidget')]");
    public SelenideElement aboutEditorGuideButton = aboutEditorHeader.$x("descendant::a[contains(@class, 've-ui-modeSwitchPopupButtonWidget-help')]/parent::span");
    public SelenideElement aboutEditorShortcutsButton = aboutEditorHeader.$x("descendant::a[contains(@class, 've-ui-modeSwitchPopupButtonWidget-keyboardShortcuts')]/parent::span");

    public SelenideElement modal = $x("//descendant::div[contains(@class, 'oo-ui-window-content') and contains(@class, 'oo-ui-window-content-ready')]");
    public SelenideElement modalFoot = modal.$x("descendant::div[@class='oo-ui-window-foot']");
    public SelenideElement modalFootLeftButton = modalFoot.$x("div/span");
    public SelenideElement modalFootRightButton = modalFoot.$x("div/span[last()]");

    public SelenideElement diffNoChanges = modal.$x("descendant::div[@class='ve-ui-mwSaveDialog-no-changes']");
    public SelenideElement diffTable = modal.$x("descendant::table");
    public SelenideElement diffAdded = diffTable.$x("descendant::td[@class='diff-addedline']");
    public SelenideElement diffDeleted = diffTable.$x("descendant::td[@class='diff-deletedline']");
    public SelenideElement diffSaveButton = $x("descendant::div[contains(@class, 'oo-ui-processDialog-actions-primary')]/span");
    public SelenideElement diffResumeButton = $x("descendant::div[contains(@class, 'oo-ui-processDialog-actions-safe')]/span");

    public SelenideElement abstractOption = $x("//descendant::div[@class='oo-ui-menuLayout-menu']");// +
//            "descendant::div[contains(@class, 'oo-ui-optionWidget')]");

    public SelenideElement categoriesOption = abstractOption.$x("descendant::span[contains(@class, 'oo-ui-icon-tag')]/parent::div");
    public SelenideElement settingsOption = abstractOption.$x("descendant::span[contains(@class, 'oo-ui-icon-pageSettings')]/parent::div");
    public SelenideElement advancedOption = abstractOption.$x("descendant::span[contains(@class, 'oo-ui-icon-settings')]/parent::div");
    public SelenideElement languagesOption = abstractOption.$x("descendant::span[contains(@class, 'oo-ui-icon-textLanguage')]/parent::div");
    public SelenideElement templatesOption = abstractOption.$x("descendant::span[contains(@class, 'oo-ui-icon-puzzle')]/parent::div");


    public void clearInput(SelenideElement input) {
        input.scrollTo();
        input.click();
//        new WebDriverWait(webdriver().object(), 2).until(ExpectedConditions.elementToBeClickable(input)).click();
        Actions actionProvider = new Actions(webdriver().object());
        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("a").build().perform();
        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("0").build().perform();
        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("m").build().perform();
        actionProvider.keyUp(Keys.LEFT_CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();

//        input.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a")); //select all
//        input.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "0")); //set style=paragraph
//        input.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "m")); //remove all styles

//        input.sendKeys(Keys.BACK_SPACE);
//        executeJavaScript("arguments[0].value = ''", input);
    }

    public void setInput(SelenideElement input, String value) {
//        input.scrollTo();
//        input.click();
//        Actions actionProvider = new Actions(webdriver().object());
//        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("a").build().perform();
//        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("0").build().perform();
//        actionProvider.keyDown(Keys.LEFT_CONTROL).sendKeys("m").build().perform();
//        actionProvider.keyUp(Keys.LEFT_CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
//
//        if(value!=null) {
//            for(int i = 0; i < value.length(); i++) {
//                actionProvider.sendKeys(String.valueOf(value.charAt(i))).build().perform();
//            }
//        }

        clearInput(input);
        if(value!=null) sendInput(input, value);
    }

    public void sendInput(SelenideElement input, String value) {
        input.scrollTo();
        input.click();
        sleep(500);
//        input.sendKeys(value);
//        executeJavaScript("arguments[0].innerText = arguments[1]", input, value);
        Actions actionProvider = new Actions(webdriver().object());
//        for(int i = 0; i < value.length(); i++) {
//            actionProvider.sendKeys(String.valueOf(value.charAt(i)));
//        }
//        actionProvider.build().perform();
        actionProvider.sendKeys(value).build().perform();
    }

    public String getUrl(String fandom, String username) {
        return String.format("https://%s.fandom.com/wiki/User:%s", fandom, username);
    }

}
