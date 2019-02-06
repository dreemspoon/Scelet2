package com.example.boris.scelet;
import com.example.boris.scelet.NeedClass.CheckAutority;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.boris.scelet.NeedClass.SqlApi;
import com.example.boris.scelet.NeedClass.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Обявляем интерфейс для его последующей реализации .
    public interface ResultHandler {
        void onSuccess(String response);

        void onFailure(IOException e);
    }

    //Переменная куда возвращается ответ то http json
    public String result;


    CheckAutority fstart = new CheckAutority();
    private static final String TAG = "MyApp";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button nextintent = (Button) findViewById(R.id.button3);

        nextintent.setOnClickListener(this);

        Boolean fstrt = fstart.firststart();

        Button gethttp = (Button) findViewById(R.id.gethttp);

        gethttp.setOnClickListener(this);
        //Создание объекта интерфейса


        if (fstrt == true) {
            startPass(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button3: {
                startPass(this);
                break;

            }
            case R.id.gethttp: {
                http gethttpapi = new http();
                gethttpapi.start(resphttp);
                Toast.makeText(this, "ETO" + result, LENGTH_LONG).show();

                JSONArray jsonout;
                if (result == null){}
                else {
                    try {
                        jsonout = new JSONArray(result);
                        for (int i = 0; i < jsonout.length(); i++)
                        {
                            JSONObject jsonObj = jsonout.getJSONObject(i);

                            System.out.println("FromJson " + jsonObj.getString("Наименование") +"   "+i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String fromjson = "notnull";

              /*  try {


                    fromjson = jsonout.getString("Код").toString();Log.e("Onclick", "json is null");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (fromjson.equals(null)) {
    Log.e("Onlick","String is null" );
}
                */
                break;
            }
        }

    }

    // РЕализуем интерфейс для передачи ответа из вызова http
    ResultHandler resphttp = new ResultHandler() {
        @Override
        public void onSuccess(String response) {

            result = response;
            SqlApi writedb = new SqlApi();
            // writedb.insertInTable(context,result);
            Log.e("ResultHandler", result);

        }

        @Override
        public void onFailure(IOException e) {

        }
    };

    public void startPass(Context context) {
        Intent intent = new Intent(context, LoginFoarm.class);
        context.startActivity(intent);

    }
//метод , который конвертит Stirng в JsonObject
/*
    public JSONArray convertStringToJson(String result) {
        JSONArray resultJson = new JSONArray();
        try {
            resultJson = new JSONObject(result);
            Log.d("My App", resultJson.toString());
            return resultJson;

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: " + t.toString());


        }
        
        return resultJson;
    }
*/
}