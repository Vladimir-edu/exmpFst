package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.junit.BeforeClass;
import org.junit.Test;
import suits.FirstTask;
import suits.MyCategories.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static Utils.Utils.checkResult;

@Feature("Авральный вариант создания тестов")
public class FirstTaskSmplFast {
    @BeforeClass
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
//        Configuration.timeout = 10000;
    }

    @Test
    @Category({FirstT.class, FullT.class})
    @Story("Проверка результатов поиска в yandex")
    @DisplayName("Очень быстрая проверка")
    @Description("Заходит на страницу yandex.ru\n" +
                 "В строку поиска вводит cinimex\n" +
                 "Проверяет, что первой строкой выдачи является сайт нашей компании, а третьей наша страница на hh.ru")
    public void veryFast() {
        String name = "cinimex";
        String conditionFirst = "cinimex.ru";
        String conditionSecond = "hh.ru";

        Selenide.open("http://yandex.ru");
//        ----------- search ----------------
        SelenideElement inputElement =$(By.xpath("//input[@id='text']")).shouldBe(visible);
        inputElement.clear();
        inputElement.setValue(name);
        inputElement.pressEnter();
//        ------------ check ---------------
        String pathStr = "//li[@class='serp-item']//a[contains(@class,'link link_theme_outer path__item i-bem')][@href]/b";
        ElementsCollection resultS = $$(By.xpath(pathStr)).shouldHave(sizeGreaterThan(2));
        Assert.assertEquals(conditionFirst,resultS.get(0).attr("textContent"));
        Assert.assertEquals(conditionSecond,resultS.get(2).attr("textContent"));
    }

    @Test
    @Category({FirstT.class, FullT.class})
    @Story("Проверка результатов поиска в yandex")
    @DisplayName("Очень быстрая проверка, с отражением результатов промежуточных шагов в отчете")
    @Description("Заходит на страницу yandex.ru\n" +
                 "В строку поиска вводит cinimex\n" +
                 "Проверяет, что первой строкой выдачи является сайт нашей компании, а третьей наша страница на hh.ru")
    public void veryFastAllure() {
//============== testData ===================
        String searchText = "cinimex";
        String conditionFirst = "cinimex.ru";
        String conditionSecond = "hh.ru";
        int number; // номер строки выдачи, нумерация с ноля
//===========================================
        searchYandexByText(searchText);
        ElementsCollection result = getResults();

        number = 0;
        checkResult(number, conditionFirst, result.get(number).attr("textContent"));
        number = 2;
        checkResult(number, conditionSecond, result.get(number).attr("textContent"));
//        Если отчет будет читать человек не связанный с IT, то ,например, можно передавать первый аргумент не number, а number+1

    }

    @Step("Поиск с помощью yandex запроса = {searchText} ")
    private void searchYandexByText(String searchText){
        Selenide.open("http://yandex.ru");
        SelenideElement inputElement =$(By.xpath("//input[@id='text']")).shouldBe(visible);
        inputElement.clear();
        inputElement.setValue(searchText);
        inputElement.pressEnter();
    }

    @Step("Получение результата")
    private ElementsCollection getResults(){
        String pathStr = "//li[@class='serp-item']//a[contains(@class,'link link_theme_outer path__item i-bem')][@href]/b";
        return $$(By.xpath(pathStr)).shouldHave(sizeGreaterThan(2));
    }

//    @Step("Проверка результата для позиции = {number}")
//    private void checkResult(int number, String expected, String actual){
//        Assert.assertEquals("На позиции №="+number+" обнаружено несоответствие.",expected,actual);
//    }

}
