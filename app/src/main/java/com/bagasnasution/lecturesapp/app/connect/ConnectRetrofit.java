package com.bagasnasution.lecturesapp.app.connect;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.db.DBUser;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.ResponseDefault;
import com.bagasnasution.lecturesapp.app.model.ResponseLogin;
import com.bagasnasution.lecturesapp.app.model.ResponseSks;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectRetrofit {
    private static final int timeout = 120;
    private static final String BASE_URL = Config.BASE_URL;
    private static final String TOKEN = Config.API_TOKEN;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.readTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.writeTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit;
    }

    private static Services getConnection() {
        return getRetrofit().create(Services.class);
    }

    private static ProgressDialog initProgressDialog(Context context) {
        ProgressDialog p = new ProgressDialog(context);
        p.setMessage("Loading...");
        p.setCancelable(false);
        return p;
    }

    private static boolean isSuccess(String responseCode) {
        if (responseCode.equals(Config.API_CODE_SUCCESS)) {
            return true;
        }
        return false;
    }

    private static void showMessage(Context context, String code, String message) {
        Toast.makeText(context, code + ": " + message, Toast.LENGTH_LONG).show();
    }

    public static synchronized void login(final Context context, String username, String password, final OnResponse<ResponseLogin> listener) {
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), TOKEN);
        RequestBody rUsername = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody rPassword = RequestBody.create(MediaType.parse("text/plain"), password);

        getConnection().login(token, rUsername, rPassword).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (isResponse(response)) {
                    listener.onResponse(call, response);
                } else {
                    AppHelper.showToast(context, "Connection Error [" + response.code() + "]");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                listener.onFailure(call, throwable);
            }
        });

    }

    public static synchronized void getSksList(Context context, final OnResponse<ResponseSks> listener) {
        String username = DBUser.getDataUser(context).getNpm();

        getConnection().getListSks(TOKEN, username)
                .enqueue(new Callback<ResponseSks>() {
                    @Override
                    public void onResponse(Call<ResponseSks> call, Response<ResponseSks> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseSks> call, Throwable throwable) {
                        listener.onFailure(call, throwable);
                    }
                });
    }

    private static boolean isResponse(Response<? extends ResponseDefault> response) {
        if (response.isSuccessful()) {
            return true;
        }
        return false;
    }


    public interface OnResponse<N> {
        void onResponse(Call<N> call, Response<N> response);

        void onFailure(Call<N> call, Throwable throwable);
    }

}
