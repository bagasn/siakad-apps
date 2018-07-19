package com.bagasnasution.lecturesapp.app.connect;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.db.DBUser;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.GetSlideHomeResponse;
import com.bagasnasution.lecturesapp.app.model.response.ResponseCurrentJadwal;
import com.bagasnasution.lecturesapp.app.model.response.ResponseDefault;
import com.bagasnasution.lecturesapp.app.model.response.ResponseJadwal;
import com.bagasnasution.lecturesapp.app.model.response.ResponseListSKS;
import com.bagasnasution.lecturesapp.app.model.response.ResponseLogin;
import com.bagasnasution.lecturesapp.app.model.response.ResponseMatkul;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNews;
import com.bagasnasution.lecturesapp.app.model.response.ResponseNilai;

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

    public static void getSlideHome(Context context, final OnResponse<GetSlideHomeResponse> listener) {

        getConnection().getslideHome(Config.API_TOKEN)
                .enqueue(new Callback<GetSlideHomeResponse>() {
                    @Override
                    public void onResponse(Call<GetSlideHomeResponse> call, Response<GetSlideHomeResponse> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<GetSlideHomeResponse> call, Throwable throwable) {
                        listener.onFailure(call, throwable);
                    }
                });
    }

    public static synchronized void getNilai(Context context, final OnResponse<ResponseNilai> listener) {
        String username = DBUser.getDataUser(context).getNpm();

        getConnection().getNilai(TOKEN, username)
                .enqueue(new Callback<ResponseNilai>() {
                    @Override
                    public void onResponse(Call<ResponseNilai> call, Response<ResponseNilai> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseNilai> call, Throwable throwable) {
                        listener.onFailure(call, throwable);
                    }
                });
    }

    public static synchronized void getJadwal(Context context, final OnResponse<ResponseJadwal> listener) {
        String username = DBUser.getDataUser(context).getNpm();

        getConnection().getJadwal(TOKEN, username)
                .enqueue(new Callback<ResponseJadwal>() {
                             @Override
                             public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                                 listener.onResponse(call, response);
                             }

                             @Override
                             public void onFailure(Call<ResponseJadwal> call, Throwable throwable) {
                                 listener.onFailure(call, throwable);
                             }
                         }
                );
    }

    public static synchronized void getCurrentJadwal(Context context, final OnResponse<ResponseCurrentJadwal> listener) {
        String username = DBUser.getDataUser(context).getNpm();

        getConnection().getCurrentJadwal(TOKEN).enqueue(new Callback<ResponseCurrentJadwal>() {
            @Override
            public void onResponse(Call<ResponseCurrentJadwal> call, Response<ResponseCurrentJadwal> response) {
                listener.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseCurrentJadwal> call, Throwable throwable) {
                listener.onFailure(call, throwable);
            }
        });
    }

    public static synchronized void getMatakuliah(Context context, final OnResponse<ResponseMatkul> listener) {

        getConnection().getMatakuliah(TOKEN)
                .enqueue(new Callback<ResponseMatkul>() {
                    @Override
                    public void onResponse(Call<ResponseMatkul> call, Response<ResponseMatkul> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseMatkul> call, Throwable throwable) {
                        listener.onFailure(call, throwable);
                    }
                });
    }

    public static synchronized void getListSks(Context context, final OnResponse<ResponseListSKS> listener) {

        getConnection().getListSks(TOKEN)
                .enqueue(new Callback<ResponseListSKS>() {
                    @Override
                    public void onResponse(Call<ResponseListSKS> call, Response<ResponseListSKS> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseListSKS> call, Throwable throwable) {
                        listener.onFailure(call, throwable);
                    }
                });
    }

    public static synchronized void getNews(final OnResponse<ResponseNews> listener) {

        getConnection().getNews(TOKEN)
                .enqueue(new Callback<ResponseNews>() {
                    @Override
                    public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                        listener.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseNews> call, Throwable throwable) {
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
