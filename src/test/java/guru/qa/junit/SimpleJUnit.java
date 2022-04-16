package guru.qa.junit;

import guru.qa.tests.DodoTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// !!! НЕ смотреть, потом попробую поковырять сама !!!

public class SimpleJUnit {
    public static void main(String[] args) throws Exception {
        // Находит классы с тестами
        //Method[] declaredMethods = SimpleTest.class.getDeclaredMethods();
        Method[] declaredMethods = DodoTest.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            // Смотрит есть ли над методом @Test
            //Test testAnnotation = method.getAnnotation(Test.class);
            Disabled disabled = method.getAnnotation(Disabled.class);
            AfterEach afterEach = method.getAnnotation(AfterEach.class);
            ParameterizedTest parameterizedTest = method.getAnnotation(ParameterizedTest.class);
            ValueSource valueSource = method.getAnnotation(ValueSource.class);
            //if (disabled != null) {
            if (parameterizedTest != null && valueSource != null) {
                // Запускает
                try {
                    System.out.println("in try");
                    var arr = valueSource.strings();
                    //todo MethodSource - аннотация погуглить, можно проверить наличие аргументов
                    //method.invoke(DodoTest.class.getDeclaredConstructor().newInstance());
                    method.invoke(DodoTest.class.getDeclaredConstructor().newInstance(), "123");
                    //method.invoke(DodoTest.class.getDeclaredConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    System.out.println("Тест упал: " + e.getCause().getMessage());
                    throw  e;
                }
            }
//            if(afterEach != null){
//                System.out.println("After each method exists");
//            }
        }
    }
}
