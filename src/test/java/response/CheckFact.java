package response;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import request.RequestParams;

import java.util.Objects;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckFact extends BaseTest {

    @Test
    public static void fullCheck() throws JsonProcessingException {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        SoftAssertions allAssertions = new SoftAssertions();
        allAssertions.assertThat(weatherResponse.getFact().getCondition()).isIn(AcceptableValues.getConditions());
        allAssertions.assertThat(weatherResponse.getFact().getTemp()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getFeelsLike()).isNotNull();
//        allAssertions.assertThat(weatherResponse.getFact().getTempWater()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getIcon()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getWindSpeed()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getWindGust()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getWindDir()).isIn(AcceptableValues.getWindDirections());
        allAssertions.assertThat(weatherResponse.getFact().getPressureMm()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getPressurePa()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getHumidity()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getDaytime()).isIn(AcceptableValues.getDayTimes());
        allAssertions.assertThat(weatherResponse.getFact().getPolar()).isNotNull();
        allAssertions.assertThat(weatherResponse.getFact().getSeason()).isIn(AcceptableValues.getSeasons());
        allAssertions.assertThat(weatherResponse.getFact().getObsTime()).isNotNull();
//        allAssertions.assertThat(weatherResponse.getFact().getPrecType()).isIn(AcceptableValues.getPrecipitationTypes());
//        allAssertions.assertThat(weatherResponse.getFact().getPrecStrength()).isIn(AcceptableValues.getPrecipitationStrength());
//        allAssertions.assertThat(weatherResponse.getFact().getCloudness()).isIn(AcceptableValues.getCloudiness());
        allAssertions.assertAll();

    }

}
