package ru.yellowblacksnek.tpo3.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private SelenideDriver driver;
    public SearchPage(SelenideDriver driver) {this.driver=driver;}
    public SelenideElement searchHeader = $x("//descendant::header[@class='grid-content']/h1");
}
