package com.bagasnasution.lecturesapp.app.connect;

import com.bagasnasution.lecturesapp.app.model.ResponseLogin;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

/**
 * Created by Batavianet on 10/12/2017.
 */

public interface Services {

    @POST
    Call<ResponseLogin> login(
            @Body HashMap<String, String> params
    );

}
