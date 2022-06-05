package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import java.util.Collection;

import static com.codeborne.selenide.Selenide.$x;


public class ArticlePage {

    private SelenideDriver driver;
    public ArticlePage(SelenideDriver driver) {this.driver = driver;}

    public SelenideElement header = driver.$x("//descendant::div[@class='article-header']");
    public ElementsCollection tags = header.$$x("descendant::div[contains(@class, 'article-topic-tags') and not(@hidden)]/a");
}
