package com.example.SQLLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.utils.Utils;

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, Utils.NOMBRE_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utils.CREAR_TABLA_CATEGORIA);
        db.execSQL(Utils.CREAR_TABLA_PRODUCTO);
        db.execSQL(Utils.CREATE_TABLE_USER);
        db.execSQL(Utils.CREATE_TABLE_ORDENES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int vesionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_ORDENES);
    }


}
