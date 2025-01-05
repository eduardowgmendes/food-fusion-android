package br.com.edu.jet.foodfusion;

import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodFusionApplication extends Application {

    private static Retrofit retrofit = null;
    public static final String TAG = FoodFusionApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Response response = chain.proceed(chain.request());
                    if (!response.isSuccessful()) {
                        Log.e(TAG, String.format("Failed to retrieve data!\nCode: %d\nMessage: %s\n", response.code(), response.message()));
                        Log.e(TAG, String.format("Response body error!\nMessage: %s\n", response.body().toString()));
                    }
                    return response;
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.100:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
