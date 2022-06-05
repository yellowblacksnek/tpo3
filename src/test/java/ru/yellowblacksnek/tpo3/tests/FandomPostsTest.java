package ru.yellowblacksnek.tpo3.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yellowblacksnek.tpo3.pages.PostsPage;
import ru.yellowblacksnek.tpo3.pages.ProfilePage;
import ru.yellowblacksnek.tpo3.pages.SignInPage;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FandomPostsTest {
    PostsPage posts = new PostsPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x1024";
        Configuration.timeout = 20000;
//        open(SignInPage.url);
//        SignInPage signPage = new SignInPage();
//        if(signPage.form.is(Condition.visible))
//            signPage.sendLoginData();
    }

    @BeforeEach
    public void setUp() {
//        open(new GlobalNavigation().profileButton.getAttribute("href"));
//        open(signPage.url);
//        signPage.sendLoginData();
//        open(new MainPage().url);
        open(PostsPage.url);
        sleep(1000);
    }

//    @Test
//    public void userCanChangeCategories() {
//        posts.categoriesToggle.hover();
//        posts.categories.first().shouldBe(visible);
//        posts.categories.forEach(i -> {
//            String name = i.$x("descendant::div[@class='category__name']").text();
//            System.out.println(name);
//            if(name.isEmpty() || name.equals("All")) return;
//
//            i.click();
////            executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
//            posts.postCategoryInfos.first().shouldBe(Condition.visible);
//            posts.postCategoryInfos.forEach(postCategory -> {
//                postCategory.shouldBe(visible);
//                System.out.println(postCategory.text());
//                assertTrue(postCategory.text().contains(name));
//            });
//        });
//    }

    @Test
    public void userCanSortByTime() {
        posts.viewToggle.hover();
        posts.newButton.click();

        posts.postTimestamps.first().shouldBe(Condition.visible);
        String prev = posts.postTimestamps.first().getAttribute("datetime");
        posts.postTimestamps.forEach(i -> {
            assertTrue(prev.compareTo(i.getAttribute("datetime")) >= 0);
        });
    }
}
