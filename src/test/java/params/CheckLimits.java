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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import request.RequestParams;
import response.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckLimits extends BaseTest {

    private static final int MAX_LIMIT = 7;

    @Test(dataProvider = "LimitProvider")
    public static void checkMaxLimit(int actualLimit, int expectedLimit) throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(RequestParams.limit.name(), actualLimit);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        assertThat(weatherResponse.getForecasts().size()).isEqualTo(expectedLimit);
    }

    @DataProvider(name = "LimitProvider")
    public Object[][] getLimit() {
        return new Object[][]{
                {MAX_LIMIT, MAX_LIMIT},
                {MAX_LIMIT + 1, MAX_LIMIT},
                {MAX_LIMIT - 1, MAX_LIMIT - 1},
                {-1, MAX_LIMIT},
                {0, MAX_LIMIT},
                {1, MAX_LIMIT}, //looks a bit strange
                {2, 2}
        };
    }

}
