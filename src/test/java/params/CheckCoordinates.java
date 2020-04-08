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
import org.assertj.core.data.MapEntry;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import request.RequestParams;
import response.WeatherResponse;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

public class CheckCoordinates extends BaseTest {

    private static final String TALLINN_LATITUDE = "59.437320";
    private static final String TALLINN_LONGITUDE = "24.749909";

    private static final String TALLINN_TIMEZONE_NAME = "Europe/Tallinn";
    private static final String DEFAULT_TIMEZONE_NAME = "Europe/Moscow";


    @Test(dataProvider = "CoordinatesProvider")
    public static void checkCoordinates(Map<String, String> params, String expectedPlace) throws JsonProcessingException {

        RequestSpecification httpRequest = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .queryParams(params);

        Response response = httpRequest.request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(response.getBody().asString(), WeatherResponse.class);

        assertThat(weatherResponse.getInfo().getTzinfo().getName()).isEqualTo(expectedPlace);
    }

    @DataProvider(name = "CoordinatesProvider")
    public Object[][] getCoordinates() {
        return new Object[][]
                {
                        {
                                Collections.unmodifiableMap(Stream.of(
                                        entry(RequestParams.lat.name(), TALLINN_LATITUDE),
                                        entry(RequestParams.lon.name(), TALLINN_LONGITUDE)).
                                        collect(Collectors.toMap(MapEntry::getKey, MapEntry::getValue))),

                                TALLINN_TIMEZONE_NAME
                        },

                        {
                                Collections.unmodifiableMap(Stream.of(
                                        entry(RequestParams.lat.name(), TALLINN_LATITUDE)).
                                        collect(Collectors.toMap(MapEntry::getKey, MapEntry::getValue))),

                                DEFAULT_TIMEZONE_NAME
                        },

                        {
                                Collections.unmodifiableMap(Stream.of(
                                        entry(RequestParams.lon.name(), TALLINN_LONGITUDE)).
                                        collect(Collectors.toMap(MapEntry::getKey, MapEntry::getValue))),

                                DEFAULT_TIMEZONE_NAME
                        },

                        {
                                Collections.unmodifiableMap(Stream.of(
                                        entry(RequestParams.lat.name(), "0"),
                                        entry(RequestParams.lon.name(), "0")).
                                        collect(Collectors.toMap(MapEntry::getKey, MapEntry::getValue))),

                                "Etc/GMT"
                        },

                        {
                                Collections.unmodifiableMap(Stream.of(
                                        entry(RequestParams.lat.name(), "53.173027"),
                                        entry(RequestParams.lon.name(), "-32.623952")).
                                        collect(Collectors.toMap(MapEntry::getKey, MapEntry::getValue))),

                                "Etc/GMT+2"
                        },

                        {
                                Collections.emptyMap(),

                                DEFAULT_TIMEZONE_NAME
                        }
                };

    }
}
