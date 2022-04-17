package guru.qa.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.docs.enums.Cities;
import guru.qa.pages.DodoMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @MethodSource("checkPizzaID")
    @ParameterizedTest(name = "Проверка ID пиццы")
    void checkPizzaID(String pizza, String pizzaId){
        String mainPage = "https://dodopizza.ru/moscow";
        Selenide.open(mainPage);
        $("[data-testid='" + pizza + "']").click();
        if($("[class='k0j10-1.eqmleZ'").exists()){
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            System.out.println("CURRENT " + currentUrl);
            System.out.println("EXPECTED " + mainPage + pizzaId);
            assertEquals(currentUrl, mainPage + pizzaId);
        }
    }

    static Stream<Arguments> checkPizzaID(){
        return Stream.of(
                Arguments.of("menu__meta-product_336", "?product=11ECA997BC2E794513CF8B698A698610"),
                Arguments.of("menu__meta-product_277", "?product=11EBD33EA16D434657C802F3D9D7E550"),
                Arguments.of("menu__meta-product_275", "?product=11EBC74F8B5963217BB5645371D2DB40"),
                Arguments.of("menu__meta-product_231", "?product=11EA9450A3227F0AA5878FF7D8379FC0")
        );
    }


    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
