package com.example.chenxl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.chenxl.myapplication.entity.MyJoke;
import com.example.chenxl.myapplication.util.HttpUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getData();
    }

    private void getData() {


        HttpUtils.getInstance().getJoke(new Observer<List<MyJoke>>() {

            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {

                disposable = d;
                Log.d("MAIN","onSubscribe");
            }

            @Override
            public void onNext(List<MyJoke> myJokes) {

                Log.d("MAIN","获取数据完成：" + myJokes.size());
            }

            @Override
            public void onError(Throwable e) {

                Log.d("MAIN","onError");
            }

            @Override
            public void onComplete() {
                Log.d("MAIN","onComplete");
            }
        });

    }
}
