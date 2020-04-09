package response;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckForecast extends BaseTest {

    @Test
    public static void fullCheck() throws JsonProcessingException {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        List<Forecast> forecasts = weatherResponse.getForecasts();

        SoftAssertions allAssertions = new SoftAssertions();

        allAssertions.assertThat(forecasts).isNotEmpty();
        forecasts.forEach(forecast -> {
            allAssertions.assertThat(forecast.getDate()).isNotEmpty();
            allAssertions.assertThat(forecast.getDateTs()).isNotNull();
            allAssertions.assertThat(forecast.getWeek()).isNotNull();
            allAssertions.assertThat(forecast.getSunrise()).isNotNull();
            allAssertions.assertThat(forecast.getSunset()).isNotNull();
            allAssertions.assertThat(forecast.getMoonCode()).isIn(AcceptableValues.getMoonCodes());
            allAssertions.assertThat(forecast.getMoonText()).isIn(AcceptableValues.getMoonTexts());
        });

        allAssertions.assertAll();
    }
}
