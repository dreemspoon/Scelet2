package com.example.boris.scelet.NeedClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.boris.scelet.MainActivity;

public class SqlApi  {
//Класс для работы с sqllite здесь все фунции и процедуры запись данных .


    //создание таблицы
public void insertInTable (Context context, String datarequest ){
    Log.d("httpDataBase", "старт инсерта =");
    DBHelper dbHelper = new DBHelper(context);
// создаем объект для данных
    ContentValues cv = new ContentValues();
    Log.d("httpDataBase", "row inserted, ID = " + datarequest);
    // подключаемся к БД
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    Log.d("httpDataBase", "--- Insert in mytable: ---");
    // подготовим данные для вставки в виде пар: наименование столбца - значение
    cv.put("httpresponse", datarequest);
// вставляем запись и получаем ее ID
    long rowID = db.insert("httpresponse", null, cv);
    Log.d("httpDataBase", "row inserted, ID = " + rowID);
    dbHelper.close();
}



    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "dbHttp", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("SQLCreate", "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table httpresponse ("
                    + "id integer primary key autoincrement,"
                    + "httpresponse text" +  ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
