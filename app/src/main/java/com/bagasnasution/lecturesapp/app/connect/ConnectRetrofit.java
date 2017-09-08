package com.bagasnasution.lecturesapp.app.connect;


import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectRetrofit {
    private Context context;

    private static final int timeout = 10000;

    public ConnectRetrofit(Context context) {
        this.context = context;
    }

    public Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.readTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.writeTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit;
    }
}
