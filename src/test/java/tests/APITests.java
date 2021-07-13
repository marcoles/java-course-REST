package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;

@Listeners (TestListener.class)
public class APITests {
    ObjectMapper objectMapper;

    @BeforeClass
    public void setupURI() {
        baseURI = "https://dummy.restapiexample.com/api/v1/";
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    @Test
    public void testGet() {
        Response response = given()
                                .baseUri(baseURI)
                                .contentType(ContentType.JSON)
                                .get("employees");

        Integer statusCode = response.getStatusCode();
        EmployeesResponse employeesResponse = null;

        try {
            employeesResponse =
                    objectMapper.readValue(response.getBody().asString(), EmployeesResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // how to deal with the try block and null check (if)?
        Integer expectedStatusCode = 200;
        Integer secondEmployeeAge = 63;// ?
        String successStatus = "success";
        String firstEmployeeName = "Tiger Nixon";

        assert employeesResponse != null; // ?
        Assert.assertEquals(statusCode, expectedStatusCode, "Wrong status code");
        Assert.assertEquals(employeesResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(employeesResponse.getData().get(0).getEmployee_name(), firstEmployeeName, "Data is not matching" );
        Assert.assertEquals(employeesResponse.getData().get(1).getEmployee_age(), secondEmployeeAge, "Data is not matching");

    }

    @Test
    public void testPost() {
        NewEmployee newEmployee = new NewEmployee("Marcin", "2000", "25");
        String newEmployeeAsString = null;
        try {
            newEmployeeAsString = objectMapper.writeValueAsString(newEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(newEmployeeAsString);
        Response response = given()
                                .baseUri(baseURI)
                                .contentType(ContentType.JSON)
                                .body(newEmployeeAsString)
                                .post("create");

        Integer statusCode = response.getStatusCode();
        CreateResponse createResponse = null;
        try {
            createResponse =
                    objectMapper.readValue(response.getBody().asString(), CreateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer expectedStatusCode = 200;
        String successStatus = "success";

        assert createResponse != null;
        Assert.assertEquals(statusCode, expectedStatusCode, "Wrong status code");
        Assert.assertEquals(createResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(createResponse.getData().getName(), newEmployee.getName(), "Data is not matching" );
        Assert.assertEquals(createResponse.getData().getSalary(), newEmployee.getSalary(), "Data is not matching");


    }

    @Test
    public void testPut() {
        NewEmployee newEmployee = new NewEmployee("Marcin", "2000", "25");
        String newEmployeeAsString = null;
        try {
            newEmployeeAsString = objectMapper.writeValueAsString(newEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(newEmployeeAsString);
        Response response = given()
                                .baseUri(baseURI)
                                .contentType(ContentType.JSON)
                                .body(newEmployeeAsString)
                                .put("update/1");

        Integer statusCode = response.getStatusCode();
        CreateResponse createResponse = null;
        try {
            createResponse =
                    objectMapper.readValue(response.getBody().asString(), CreateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Integer expectedStatusCode = 200;
        String successStatus = "success";

        assert createResponse != null;
        Assert.assertEquals(statusCode, expectedStatusCode, "Wrong status code");
        Assert.assertEquals(createResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(createResponse.getData().getName(), newEmployee.getName(), "Data is not matching" );
        Assert.assertEquals(createResponse.getData().getSalary(), newEmployee.getSalary(), "Data is not matching");
    }

    @Test
    public void testDelete() {
        Response response = given()
                                .baseUri(baseURI)
                                .contentType(ContentType.JSON)
                                .delete("delete/1");

        Integer statusCode = response.getStatusCode();
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse =
                    objectMapper.readValue(response.getBody().asString(), DeleteResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Integer expectedStatusCode = 200;
        String successStatus = "success";

        assert deleteResponse != null;
        Assert.assertEquals(statusCode, expectedStatusCode, "Wrong status code");
        Assert.assertEquals(deleteResponse.getStatus(), successStatus, "Wrong status");

    }
}
