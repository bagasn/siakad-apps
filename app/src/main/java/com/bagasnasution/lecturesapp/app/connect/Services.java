package com.bagasnasution.lecturesapp.app.connect;

import com.bagasnasution.lecturesapp.app.model.response.*;

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

    @GET("get_slide_list.php")
    Call<GetSlideHomeResponse> getslideHome(
            @Query("token") String token
    );

    @GET("get_nilai.php")
    Call<ResponseNilai> getNilai(
            @Query("token") String token,
            @Query("npm") String username
    );

    @GET("get_jadwal.php")
    Call<ResponseJadwal> getJadwal(
            @Query("token") String token,
            @Query("npm") String npm
    );

    @GET("get_jadwal_hari_ini.php")
    Call<ResponseCurrentJadwal> getCurrentJadwal(
            @Query("token") String token
    );

    @GET("get_matkul.php")
    Call<ResponseMatkul> getMatakuliah(
            @Query("token") String token
    );

    @GET("get_list_sks.php")
    Call<ResponseListSKS> getListSks(
            @Query("token") String token
    );

    @GET("get_news.php")
    Call<ResponseNews> getNews(
            @Query("token") String token
    );

}
