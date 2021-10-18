package base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void initialise() {
        RestAssured.baseURI = ConfigReader.getUrl();
    }
    
    private void emptyMethod(){
        System.out.println("test");
    }
}
