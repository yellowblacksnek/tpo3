package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class PostsPage {
    public static final String url = "https://community.fandom.com/f";

    public SelenideElement categoriesToggle = $x("//button[@data-testid='category-pill-name']");
    public ElementsCollection categories = $$x("//div[@class='category-filter']//li[@class='category']");

    public ElementsCollection posts = $$x("//descendant::div[contains(@class, 'standard-post__content-wrapper')]");

    public ElementsCollection postCategoryInfos = $$x("//descendant::a[@class='post-attribution__category']");
    public ElementsCollection postTimestamps = $$x("//descendant::a[@class='post-attribution__timeago']/time");

    public SelenideElement viewToggle = $x("//div[@class='view-filter']");
    public SelenideElement newButton = viewToggle.$x("descendant::button[contains(@class, 'feed-order-switcher__button')][last()]");

    public SelenideElement loadMoreButton = $x("//button[contains(@class, 'load-more')]");
}
