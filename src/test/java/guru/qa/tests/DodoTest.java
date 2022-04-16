package guru.qa.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.docs.enums.Cities;
import guru.qa.pages.DodoMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Класс с выбором и сменой города")
public class DodoTest {

    DodoMainPage dodoMainPage = new DodoMainPage();

    @ValueSource(strings = {"Москва", "Санкт-Петербург"})
    @ParameterizedTest(name = "Выбор города {0}")
    void selectCity(String testData){
        Selenide.open("https://dodopizza.ru/");
        dodoMainPage.clickOnBigCity(testData);
        dodoMainPage.checkCityInHeader(testData);
    }

    @ValueSource(strings = {"Лабытнанги", "Электроугли"})
    @ParameterizedTest(name = "Смена города на {0}")
    void changeCity(String testData){
        Selenide.open("https://dodopizza.ru/moscow");
        dodoMainPage.clickOnCityInHeader();
        dodoMainPage.clickOnNotBigCity(testData);
        dodoMainPage.checkCityInHeader(testData);
    }

    @EnumSource(Cities.class)
    @ParameterizedTest(name = "Проверка страницы лояльности в городе {0}")
    void checkLoyaltyPage(Cities c){
        Selenide.open("https://dodopizza.ru/" + c);
        dodoMainPage.clickOnDodocoins();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        dodoMainPage.isLoyaltyPage(String.valueOf(c), currentUrl);
    }


    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
