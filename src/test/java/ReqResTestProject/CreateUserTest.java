package ReqResTestProject;

import HelperClient.ReqResTestProject.ReqResServiceClient;
import Utils.ReportSetup;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import services.ReqResTestProject.Responses.CreateUserResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class CreateUserTest extends ReportSetup {

    private final ReqResServiceClient reqResServiceClient;

    public CreateUserTest() {
        this.reqResServiceClient = new ReqResServiceClient();
    }

    @Test(description = "Verify create user flow when name and job fields are passed")
    public void verifyCreateUserFlow() throws IOException {
        String name = "Test1";
        String job = "Assistant";
        CreateUserResponse createUserResponse = reqResServiceClient.createUser(name,job);
        Assert.assertEquals(createUserResponse.getStatusCode(),201);
    }
}
