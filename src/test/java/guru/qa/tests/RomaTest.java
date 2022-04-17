package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Класс Ромы")
public class RomaTest {
     @BeforeAll
    static void setUp(){
         Configuration.holdBrowserOpen = true;
         Configuration.baseUrl = "https://inno.tech/";
         Configuration.browserSize = "1920 x 1080";
     }

     @Test
    void choiceVanacies(){
         open("vacancies");
     }
}
