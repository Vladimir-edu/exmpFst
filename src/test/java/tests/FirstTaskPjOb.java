package tests;

import Pages.SearchPage;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.junit.BeforeClass;
import org.junit.Test;
import suits.MyCategories.*;
import java.util.List;
import static Utils.Utils.checkResult;

@Feature("Вариант создания тестов с использованием PjObj")
public class FirstTaskPjOb {
    @BeforeClass
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
//        Configuration.timeout = 10000;
    }

    @Test
    @Category({FirstT.class, FullT.class})
    @Story("Проверка результатов поиска в yandex")
    @DisplayName("Проверка с использованием подхода page object без подробносго отражения результатов промежуточных шагов в отчете")
    @Description("Заходит на страницу yandex.ru\n" +
                 "В строку поиска вводит cinimex\n" +
                 "Проверяет, что первой строкой выдачи является сайт нашей компании, а третьей наша страница на hh.ru")
    public void usePjObjTest() {
//============== testData ===================
        String searchText = "cinimex";
        String conditionFirst = "cinimex.ru";
        String conditionSecond = "hh.ru";
        int number; // номер строки выдачи, нумерация с ноля
//===========================================
        SearchPage searchPage = new SearchPage();
        List<String> result = searchPage.getListUrl(searchText);
        number=0;
//        Assert.assertEquals("На позиции №"+number+" обнаружено несоответствие.",conditionFirst,result.get(number));
        checkResult(number,conditionFirst,result.get(number));
        number=2;
        checkResult(number,conditionSecond,result.get(number));

    }
//    @Step("Проверка результата для позиции = {number}")
//    private void checkResult(int number, String expected, String actual){
//        Assert.assertEquals("На позиции №="+number+" обнаружено несоответствие.",expected,actual);
//    }
}
