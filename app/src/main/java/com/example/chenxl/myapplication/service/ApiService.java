package com.example.chenxl.myapplication.service;

import com.example.chenxl.myapplication.entity.MyJoke;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by chenxl on 2017/9/6.
 */

public interface ApiService {

    @GET("xiaohua.json")
    Observable<List<MyJoke>> getData();
}
