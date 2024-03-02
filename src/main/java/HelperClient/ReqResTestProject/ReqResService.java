package HelperClient.ReqResTestProject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import services.ReqResTestProject.Requests.CreateUserRequest;
import services.ReqResTestProject.Responses.CreateUserResponse;

import java.util.Map;

public interface ReqResService {

    @POST("/api/users")
    Call<CreateUserResponse> createUser(
            @Body CreateUserRequest createUserRequest,
            @HeaderMap Map<String,String> headers
    );
}
