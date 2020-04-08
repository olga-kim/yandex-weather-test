package params;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import request.RequestParams;
import response.Forecast;
import response.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckHours extends BaseTest {

    @Test
    public static void checkWithHours() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.hours.name(), "true",
                        RequestParams.limit.name(), 2);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        SoftAssertions allAssertions = new SoftAssertions();
        for (Forecast forecast : weatherResponse.getForecasts()) {
            allAssertions.assertThat(forecast.getHours().size()).isEqualTo(24);
        }
        allAssertions.assertAll();
    }

    @Test
    public static void checkWithoutHours() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.hours.name(), "false",
                        RequestParams.limit.name(), 2);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        SoftAssertions allAssertions = new SoftAssertions();
        for (Forecast forecast : weatherResponse.getForecasts()) {
            allAssertions.assertThat(forecast.getHours()).isEmpty();
        }
        allAssertions.assertAll();
    }

    @Test
    public static void checkWithEmptyHours() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.hours.name(), "",
                        RequestParams.limit.name(), 2);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        SoftAssertions allAssertions = new SoftAssertions();
        for (Forecast forecast : weatherResponse.getForecasts()) {
            allAssertions.assertThat(forecast.getHours().size()).isEqualTo(24);
        }
        allAssertions.assertAll();
    }
}
