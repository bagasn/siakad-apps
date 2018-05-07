package com.bagasnasution.lecturesapp.app.connect;

import com.bagasnasution.lecturesapp.app.model.response.GetSlideHomeResponse;
import com.bagasnasution.lecturesapp.app.model.response.ResponseLogin;
import com.bagasnasution.lecturesapp.app.model.response.ResponseSks;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Batavianet on 10/12/2017.
 */

public interface Services {

    @Multipart
    @POST("login.php")
    Call<ResponseLogin> login(
            @Part("token") RequestBody token,
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

    @GET("sks_list.php")
    Call<ResponseSks> getListSks(
            @Query("token") String token,
            @Query("username") String username
    );

    @GET("get_slide_list.php")
    Call<GetSlideHomeResponse> getslideHome(
            @Query("token") String token
    );

}
