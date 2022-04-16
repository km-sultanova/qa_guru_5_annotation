package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DodoMainPage {
    SelenideElement cityInHeader = $(".header__about-slogan-text_locality");
    SelenideElement dodocoinsLink = $x("//a[contains(@href,'loyaltyprogram')]");


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

    public void clickOnDodocoins(){
        dodocoinsLink.click();
    }

    public void isLoyaltyPage(String city, String currentUrl)
    {
        String expectedUrl = "https://dodopizza.ru/" + city + "/loyaltyprogram";
        assertEquals(currentUrl, expectedUrl);
    }

}
