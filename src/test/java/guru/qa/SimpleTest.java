package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    //@Disabled - для задизейбла
    @DisplayName("Демонстрационный тест")
    @Test
    void firstTest(){
        Assertions.assertTrue(3>2, "Проверяем что 3 больше 2");
        Assertions.assertFalse(3<2);
        //в AssertAll выполнятся все проверки, если писать отдельно - то после упавшего теста не будут выполнены следующие
        Assertions.assertEquals("Foo", "Foo");
        Assertions.assertAll(
                () -> Assertions.assertTrue(3<2, "Проверяем что 3 больше 2")
        );
    }
}
