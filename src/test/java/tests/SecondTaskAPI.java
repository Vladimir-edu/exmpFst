package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.junit.Test;
import suits.MyCategories.*;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

@Feature("API")
public class SecondTaskAPI {
    @Test
    @Category({SecondT.class, FullT.class})
    @Story(" Проверка результатов поиска в reqres.in")
    @DisplayName(" Проверка наличия у пользователя соответствующего email")
    @Description(" Получаем информацию по всем пользователям в приложении используя api/users?page\n" +
            " - Определяем есть ли искомый пользователь,\n" +
            " - Cответствует ли email эталону.\n" +
            " - Нет ли иных email у пользователя")
    public void checkAPI() {

//// Баловство Получение тела JSON с сайта с помощью selenide а не через api
//        Selenide.open("https://reqres.in/api/users?page=2");
//        String result = $("pre").attr("textContent");
//        JsonPath obj1 = new JsonPath(result);

//============== testData ===================

        String nameFirst = "Emma";
        String nameLast = "Wong";
        String baseUrl="https://reqres.in/api/";
        String etalon ="emma.wong@reqres.in";
    //===========================================
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();

        List <Boolean> checkEmail = new ArrayList<Boolean>();

        int page = 1;
        String url = "users?page=";

        Response response = given().spec(requestSpec).when().get(url+page);
        String checkR = response.body().jsonPath().getString("data");
        checkR = checkR.replaceAll("[\\]\\[]", "");
        while (!checkR.equals("")){
    //            System.out.println(page);
            String result = response.body().jsonPath().getString("data.findAll{data -> data.first_name==\""+
                    nameFirst+"\" && data.last_name==\""+nameLast+"\"}.email").replaceAll("[\\]\\[]", "");
            if (!result.equals("")) checkEmail.add(result.equals(etalon));
            page++;
            response = given().spec(requestSpec).when().get(url+page);
            checkR = response.body().jsonPath().getString("data").replaceAll("[\\]\\[]", "");
        }

    //  ========================   Проверки   ==================================
        Assert.assertTrue("У пользователя "+nameFirst+" "+nameLast +" нашлось более 1 email",checkEmail.size()<2);
        Assert.assertFalse("В списке нет информации по пользователю "+nameFirst+" "+nameLast ,checkEmail.size()==0);
        Assert.assertTrue("У пользователя "+nameFirst+" "+nameLast +" не совпало значение email с эталоном = "+etalon,checkEmail.get(0));
//            System.out.println("OK");
    }
}
