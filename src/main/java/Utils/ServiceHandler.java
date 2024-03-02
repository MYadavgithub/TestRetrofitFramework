package Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import org.testng.Reporter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ServiceHandler extends ReportSetup{

    public Retrofit retrofit;
    public Gson gson;

    public ServiceHandler(String host){
        retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).client(new OkHttpClient().newBuilder().build()).build();
        this.gson = new Gson();
    }

    public <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

    public <T> retrofit2.Response<T> execute(Call<T> call) throws IOException {
        Request request = call.request();
        reportLog("Request URL: \n"+ request.url());
        reportLog("Request headers: \n"+ request.headers());
        reportLog("Request body: \n"+ requestBodyToString(request));
        reportLog("============================================================");
        Response<T> response = call.execute();
        reportLog("API Response:-");
        reportLog(String.format("Response status code: " + response.code()));
        reportLog(String.format("Response body: \n"+(gson.toJson(response.body()))));
        if(response.body() != null){

        }
        else {


        }
        return response;
    }

    public static String requestBodyToString(Request request){
        RequestBody requestBody = request.body();
        String requestBodyString = null;
        if (requestBody != null) {
            try {
                Buffer buffer = new Buffer();
                ((RequestBody) requestBody).writeTo(buffer);
                requestBodyString = buffer.readUtf8();
            } catch (IOException e) {
                return "request body is null";
            }
        }
        return requestBodyString;
    }

}
