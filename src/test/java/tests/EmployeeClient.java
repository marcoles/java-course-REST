package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class EmployeeClient {

    private final String BASE_URI = "https://dummy.restapiexample.com/api/v1/";
    private final ObjectMapper objectMapper;

    public EmployeeClient() {
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public EmployeesResponse sendGetEmployees() {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .get("employees");

        EmployeesResponse employeesResponse = null;

        try {
            employeesResponse =
                    objectMapper.readValue(response.getBody().asString(), EmployeesResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return employeesResponse;
    }

    public CreateResponse sendPostRequest(NewEmployee newEmployee) {
        String newEmployeeAsString = null;
        try {
            newEmployeeAsString = objectMapper.writeValueAsString(newEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(newEmployeeAsString);
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(newEmployeeAsString)
                .post("create");

        CreateResponse createResponse = null;
        try {
            createResponse =
                    objectMapper.readValue(response.getBody().asString(), CreateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createResponse;
    }

    public CreateResponse sendPutRequest(NewEmployee newEmployee) {
        String newEmployeeAsString = null;
        try {
            newEmployeeAsString = objectMapper.writeValueAsString(newEmployee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(newEmployeeAsString);
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(newEmployeeAsString)
                .put("upadate/1");

        CreateResponse createResponse = null;
        try {
            createResponse =
                    objectMapper.readValue(response.getBody().asString(), CreateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createResponse;
    }

    public DeleteResponse sendDeleteRequest(){
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .delete("delete/1");

        DeleteResponse deleteResponse = null;
        try {
            deleteResponse =
                    objectMapper.readValue(response.getBody().asString(), DeleteResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return deleteResponse;
    }
}
