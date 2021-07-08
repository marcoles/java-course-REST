package tests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class APITests {

    @Test
    public void testGet() {

        baseURI = "https://dummy.restapiexample.com/api/v1/";

        Response response = get("employees");

        System.out.println(response.statusCode());


        //System.out.println(get("/employees"));


    }
}
