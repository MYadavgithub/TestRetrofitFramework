package HelperClient.ReqResTestProject;

import Utils.ServiceHandler;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Response;
import services.ReqResTestProject.Requests.CreateUserRequest;
import services.ReqResTestProject.Responses.CreateUserResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static Properties.TestProperties.REQRES_TestHost;

public class ReqResServiceClient {

    private ServiceHandler serviceHandler;

    public ReqResServiceClient(){
        serviceHandler = new ServiceHandler(REQRES_TestHost);
    }

    public Map<String,String> getHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("content-Type","application/json");
        headers.put("x-consumer-name","AG");
        headers.put("deviceId","OnePlus9");
        return headers;
    }


    public CreateUserResponse createUser(String name, String job) throws IOException {

        ReqResService reqResService = serviceHandler.createService(ReqResService.class);
        Call<CreateUserResponse> retrofitCurl = reqResService.createUser(getCreateUserRequest(name,job),getHeaders());
        Response<CreateUserResponse> response = serviceHandler.execute(retrofitCurl);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        if(response.body()!=null){
            createUserResponse = response.body();
        }
        else{
            try {
                Gson gson = new Gson();
                createUserResponse = gson.fromJson(response.message(),CreateUserResponse.class);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        createUserResponse.setStatusCode(response.code());
        return createUserResponse;
    }

    private CreateUserRequest getCreateUserRequest(String name, String job){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(name);
        createUserRequest.setJob(job);

        return createUserRequest;
    }
}
