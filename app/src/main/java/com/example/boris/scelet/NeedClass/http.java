package com.example.boris.scelet.NeedClass;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;


import com.example.boris.scelet.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class http {
OkHttpClient httpClient = new OkHttpClient();
String url = "http://89.219.32.195:6080/YT/hs/TradeCity";
public String responseHttpJsonString ;


    //
//Создание медиатипа для того чтобы парсить строку в json
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "MyApp";
    // Создание объекта Json из строки так , как Requst (Body) не принимает String ;
    JSONObject json;

    {
        try {
           // json = new JSONObject(" {\n" +
             //       " \"Type\": \"Auth\",\n" +
               //     "\t\"User\": \"vIpljYV\",\n" +
                 //   "\t\"Pass\": \"vIpljYVKRk\"\n" +
                   // "}");
            json = new JSONObject("{\"Type\": \"GetCatalogs\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Создание объекта RequestBody и добавление в него строки json с указанием кодировки utf-8(JSON - mediatype)
    RequestBody formBody = (RequestBody) RequestBody.create(JSON, json.toString());

    /*RequestBody formBody = new FormBody.Builder()
            .add("Type","Auth")
            .add("User","vIpljYV")
            .add("Pass","vIpljYVKRk")
            .build();
    */




    public void start(MainActivity.ResultHandler resphttp) {


        try {
            run(resphttp);



        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
       }

     void run( final MainActivity.ResultHandler callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Basic dXBkYXRlOnVwZGF0ZQ==")
                .post(formBody)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();

                Log.e(TAG,myResponse);

callback.onSuccess(myResponse);
            }
        });

     }


}

