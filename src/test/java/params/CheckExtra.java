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
import org.testng.annotations.Test;
import request.RequestParams;
import response.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckExtra extends BaseTest {

    @Test
    public static void checkWithExtra() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.extra.name(), "true");

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        assertThat(weatherResponse.getFact().getPrecType()).isNotNull();
        assertThat(weatherResponse.getFact().getPrecStrength()).isNotNull();
    }

    @Test
    public static void checkWithoutExtra() throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.extra.name(), "false");

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        assertThat(weatherResponse.getFact().getPrecType()).isNull();
        assertThat(weatherResponse.getFact().getPrecStrength()).isNull();
    }
}
