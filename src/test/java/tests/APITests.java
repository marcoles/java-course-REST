package tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners (TestListener.class)
public class APITests {
    private EmployeeClient client;

    @BeforeClass
    public void setupURI() {
        client = new EmployeeClient();
    }


    @Test
    public void testGet() {
        EmployeesResponse employeesResponse = client.sendGetEmployees();

        Integer secondEmployeeAge = 63;
        String successStatus = "success";
        String firstEmployeeName = "Tiger Nixon";


        MatcherAssert.assertThat("Check if response is not null", employeesResponse, CoreMatchers.notNullValue());
        Assert.assertEquals(employeesResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(employeesResponse.getData().get(0).getEmployee_name(), firstEmployeeName, "Data is not matching" );
        Assert.assertEquals(employeesResponse.getData().get(1).getEmployee_age(), secondEmployeeAge, "Data is not matching");

    }

    @Test
    public void testPost() {
        NewEmployee newEmployee = new NewEmployee("Marcin", "2000", "25");
        CreateResponse createResponse = client.sendPostRequest(newEmployee);
        String successStatus = "success";

        MatcherAssert.assertThat("Check if response is not null", createResponse, CoreMatchers.notNullValue());
        Assert.assertEquals(createResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(createResponse.getData().getName(), newEmployee.getName(), "Data is not matching" );
        Assert.assertEquals(createResponse.getData().getSalary(), newEmployee.getSalary(), "Data is not matching");


    }

    @Test
    public void testPut() {
        NewEmployee newEmployee = new NewEmployee("Marcin", "2000", "25");
        CreateResponse createResponse = client.sendPutRequest(newEmployee);

        String successStatus = "success";

        MatcherAssert.assertThat("Check if response is not null", createResponse, CoreMatchers.notNullValue());
        Assert.assertEquals(createResponse.getStatus(), successStatus, "Wrong status");
        Assert.assertEquals(createResponse.getData().getName(), newEmployee.getName(), "Data is not matching" );
        Assert.assertEquals(createResponse.getData().getSalary(), newEmployee.getSalary(), "Data is not matching");
    }

    @Test
    public void testDelete() {
        DeleteResponse deleteResponse = client.sendDeleteRequest();

        String successStatus = "success";

        MatcherAssert.assertThat("Check if response is not null", deleteResponse, CoreMatchers.notNullValue());
        Assert.assertEquals(deleteResponse.getStatus(), successStatus, "Wrong status");

    }
}
