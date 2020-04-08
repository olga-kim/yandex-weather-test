package headers;

import base.BaseTest;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class CheckHeaders extends BaseTest {

    @Test
    public static void checkValidHeader() {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getKey())
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public static void checkNoHeader() {
        Response response = RestAssured.given().request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public static void checkNonexistentHeaderName() {
        Response response = RestAssured.given()
                .header(RandomStringUtils.random(5), RandomStringUtils.random(5))
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public static void checkEmptyAccessKey() {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), "")
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public static void checkNonexistentAccessKey() {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), UUID.randomUUID())
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public static void checkBlockedAccessKey() {
        Response response = RestAssured.given()
                .header(ConfigReader.getHeader(), ConfigReader.getBlockedKey())
                .request(Method.GET, "/forecast");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_FORBIDDEN);
    }
}
