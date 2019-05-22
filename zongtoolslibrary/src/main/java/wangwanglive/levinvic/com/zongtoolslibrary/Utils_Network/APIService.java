package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

    @Multipart
    @POST("{url}Dynamic/dynamic_upload/")
    Single<Response<JsonObject>> upload_example(@Path("url") String url, @Part("description") RequestBody description,
                                          @Part List<MultipartBody.Part> files, @Part("body") JsonObject jsonObject);

    @POST("{url}Member/member_about_me/")
    Single<Response<JsonObject>> Call_example_API(@Path("url") String url, @Body JsonObject body);
}
