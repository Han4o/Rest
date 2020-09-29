package steps;


import io.cucumber.java.Before;
import utilities.RestAssuredExtension;

public class TestInitializer {

    @Before
    public void setup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
