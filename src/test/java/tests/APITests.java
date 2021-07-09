package tests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Listeners (TestListener.class)
public class APITests {

    @BeforeClass
    public void setupURI() {
        baseURI = "https://dummy.restapiexample.com/api/v1/";
        System.out.println("Setting up base URI...");
    }


    @Test
    public void testGet() {
        //baseURI = "https://dummy.restapiexample.com/api/v1/"; ask Kamil about BeforeClass vs BeforeMethod
        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .get("employees").
        then()
                .statusCode(200)
                .body("data[0].employee_name", equalTo("Tiger Nixon"))
                .body("data[1].employee_salary", equalTo(170750));

    }

    @Test
    public void testPost() {
        /*
        Map<String, String> employeeData = new HashMap<>();
        employeeData.put("name", "Marcin");
        employeeData.put("salary", "2000");
        employeeData.put("age", "25");
        */
        given()
                .body("\"name\":\"Marcin\", \"salary\":\"2000\", \"age\":\"25\"").
        when()
                .post("create").
        then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

    @Test
    public void testPut() {
        given().
                body("\"name\":\"Marcin\", \"salary\":\"2000\", \"age\":\"25\"").
        when()
                .put("update/3").
        then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

    @Test
    public void testDelete() {
        when()
                .delete("delete/1").
        then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }
}
