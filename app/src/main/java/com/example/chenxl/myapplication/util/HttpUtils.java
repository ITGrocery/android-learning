package com.example.chenxl.myapplication.util;

import com.example.chenxl.myapplication.entity.MyJoke;
import com.example.chenxl.myapplication.service.ApiService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxl on 2017/9/6.
 */

public class HttpUtils {

    private static volatile HttpUtils httpUtils;
    private static final String BASE_URL = "http://api.laifudao.com/open/";
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private ApiService apiService;

    private HttpUtils() {

        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpUtils getInstance() {

        if (httpUtils == null) {

            synchronized (HttpUtils.class) {

                httpUtils = new HttpUtils();
            }

        }

        return httpUtils;
    }

    public void getJoke(Observer<List<MyJoke>> observer){

        apiService.getData().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
