package guru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {

    @DisplayName("Проверка поиска в Яндексе по слову Selenide")
    @Test
    void selenideSearchTest(){
//        Открыт браузер со страницей ya.ru
        Selenide.open("https://ya.ru");
//        Шаги:
        $("#text").setValue("Selenide");
        $("button[type='submit']").click();
//        Ожидаемый результат:
        $$(".serp-item").find(Condition.text("Selenide")).shouldBe(visible);
    }


    @DisplayName("Проверка поиска в Яндексе по слову JUnit")
    @Test
    void yaSearchTest(){
//        Открыт браузер со страницей ya.ru
        Selenide.open("https://ya.ru");
//        Шаги:
        $("#text").setValue("JUnit");
        $("button[type='submit']").click();
//        Ожидаемый результат:
        $$(".serp-item").find(Condition.text("JUnit")).shouldBe(visible);
    }

    @ValueSource(strings = {
            "Selenide",
            "JUnit"
    })
    @ParameterizedTest(name = "Проверка поиска в яндекск по слову {0}")
    @DisplayName("Проверка поиска в Яндексе")
    void junitSearchTest(String testData){
//        Открыт браузер со страницей ya.ru
        Selenide.open("https://ya.ru");
//        Шаги:
        $("#text").setValue(testData);
        $("button[type='submit']").click();
//        Ожидаемый результат:
        $$(".serp-item").find(Condition.text(testData)).shouldBe(visible);
    }
}
