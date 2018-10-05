package com.bagasnasution.lecturesapp.app.connect;

import com.bagasnasution.lecturesapp.app.model.ResponseLogin;
import com.bagasnasution.lecturesapp.app.model.ResponseSks;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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
            @Part("Token") RequestBody token,
            @Part("Username") RequestBody username,
            @Part("Password") RequestBody password
    );

    @GET("sks_list.php")
    Call<ResponseSks> getListSks(
        @Query("Token") String token,
        @Query("Username") String username
    );
}
