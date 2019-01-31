package com.example.boris.scelet.NeedClass;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.boris.scelet.LoginFoarm;

import static android.content.Context.MODE_PRIVATE;

public class CheckAutority {
  String name;
  String pass;
  String addr;
  SharedPreferences prefLogin;

  public Boolean firststart() {
//Возвращает значение , есть ли успешно введеные данные.
    Boolean state = true;

    return state;
  }

  public Boolean saveLogin(Context context, String name, String pass, String addr) {
    // Сохранение данных авторизации
try {
  prefLogin = context.getSharedPreferences("APP_LOGIN_DATA", MODE_PRIVATE);
  SharedPreferences.Editor ed = prefLogin.edit();
  ed.putString("name", name);
  ed.putString("pass", pass);
  ed.putString("addr", addr);
  ed.commit();
  return true;
}catch (Error e){
System.out.println(e.toString());
  return false;
}
  }
}