package Utils;

import io.qameta.allure.Step;
import org.junit.Assert;

public class Utils {
    @Step("Проверка результата для позиции = {number}")
    public static void checkResult(int number, String expected, String actual){
        Assert.assertEquals("На позиции №="+number+" обнаружено несоответствие.",expected,actual);
    }
}
