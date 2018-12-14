package dmitry.com.facultativeapp.sync;

import java.util.List;

import dmitry.com.facultativeapp.Model.AccessToken;
import dmitry.com.facultativeapp.Model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    //Интерфейс который описывает наши запросы на сервер

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);

    //метод для получения репозиторие пользователя
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>>getReposForUser(@Path("user") String user);
}
