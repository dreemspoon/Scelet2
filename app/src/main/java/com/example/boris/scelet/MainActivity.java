package com.example.boris.scelet;
import com.example.boris.scelet.NeedClass.CheckAutority;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boris.scelet.NeedClass.CheckAutority;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckAutority fstart = new CheckAutority();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nextintent = (Button) findViewById(R.id.button3);
        nextintent.setOnClickListener(this);
        Boolean fstrt = fstart.firststart();

        if (fstrt == true) {
    startPass(this);
}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:{
                startPass(this);
            break;
            }

        }
    }

    public void startPass(Context context) {
        Intent intent = new Intent(context,LoginFoarm.class);
        context.startActivity(intent);

    }
}