package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {
    @Step("На yandex вводим в строку поиска текст запроса = \"{searchText}\" ")
    public List<String> getListUrl(String searchText) {
        Selenide.open("http://yandex.ru");
        String locatorSearch = "//input[contains(@class,'input__control')][@name='text']";
        SelenideElement inputElement = $(By.xpath(locatorSearch)).shouldBe(visible);
        inputElement.clear();
        inputElement.setValue(searchText);
        inputElement.pressEnter();

        String pathStr = "//li[@class='serp-item']//a[contains(@class,'link link_theme_outer path__item i-bem')][@href]/b";
        ElementsCollection result = $$(By.xpath(pathStr)).shouldHave(sizeGreaterThan(2));
        List<String> listUrl = new ArrayList<>();
        for (SelenideElement element : result) listUrl.add(element.attr("textContent"));
        return listUrl;
    }
}
