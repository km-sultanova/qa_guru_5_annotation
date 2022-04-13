package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DodoMainPage {
    SelenideElement cityInHeader = $(".header__about-slogan-text_locality");


    public void clickOnBigCity(String testData){
        $(".locality-selector-popup__big-city-container").$(byText(testData)).click();
    }

    public void clickOnNotBigCity(String testData){
        $("[data-testid='locality-selector-popup__content']").$(byText(testData)).click();
    }

    public void clickOnCityInHeader(){
        cityInHeader.click();
    }

    public void checkCityInHeader(String testData){
        cityInHeader.shouldHave(text(testData));
    }

}
