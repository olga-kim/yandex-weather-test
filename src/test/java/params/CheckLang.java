package params;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigReader;
import request.Lang;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import request.RequestParams;
import response.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckLang extends BaseTest {

    @Test(dataProvider = "LangProvider")
    public static void checkLang(String lang, String url) throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.lang.name(), lang);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        assertThat(weatherResponse.getInfo().getUrl()).contains(url);
    }

    @DataProvider(name = "LangProvider")
    public Object[][] getLang() {
        Lang[] langs = Lang.values();

        Object[][] resultArray = new Object[langs.length][];
        for (int i = 0; i < langs.length; i++) {
            resultArray[i] = new Object[]{langs[i].name(), langs[i].url()};
        }

        return resultArray;
    }

    @Test
    public static void checkInvalidLang() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.lang.name(), RandomStringUtils.random(5));

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        System.out.println(weatherResponse.getInfo().getUrl());
        assertThat(weatherResponse.getInfo().getUrl()).contains(Lang.en_US.url());
    }

}
