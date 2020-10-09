package com.example.utils;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class Utils {
    //DATA USER ADMIN
    public static final String NOMBRE_ADMIN = "admin";
    public static final String PASS_ADMIN = "admin";
    public static final String PASS_DEFAULT = "123";


    //CREDENTIALS
    public static final String KEY_USER = "user_name";
    public static final String KEY_PASS = "user_pass";
    public static final String KEY_NAME = "user_name";
    public static final String KEY_LASTNAME = "user_lastname";
    public static final String KEY_ROL = "user_rol";
    public static final String KEY_OK_SESSION = "ok_session";


    //NOMBRE DE BD
    public static final String NOMBRE_DB = "db_electiva";
    //Datos de la tabla Categoria
    public static final String TABLA_CATEGORIA = "categoria";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    //Datos de la Tabla Producto
    public static final String TABLA_PRODUCTO = "producto";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_MARCA = "marca";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_CANTIDAD = "cantidad";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_CODIGO = "codigo";


    //CAMPOS DE USUARIO
    public static final String TABLE_USER = "usuario";
    public static final String CAMPO_USER = "user";
    public static final String CAMPO_USER_CLAVE = "clave";
    public static final String CAMPO_USER_IDENTIFICACION = "identificacion";
    public static final String CAMPO_USER_APELLIDO = "apellido";
    public static final String CAMPO_USER_ROL = "rol";

    public static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE " + TABLA_CATEGORIA +
            "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + CAMPO_NOMBRE + " VARCHAR DEFAULT 350," + CAMPO_DESCRIPCION + " TEXT)";
    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " + TABLA_PRODUCTO +
            "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " + CAMPO_NOMBRE + " VARCHAR DEFAULT 350," + CAMPO_DESCRIPCION + " TEXT, " +
            "" + CAMPO_FECHA + " VARCHAR DEFAULT 350, " + CAMPO_MARCA + " VARCHAR, " + CAMPO_TIPO + " INTEGER, " + CAMPO_CANTIDAD + " INTEGER, " + CAMPO_PRECIO + " NUMERIC," +
            "" + CAMPO_CODIGO + " VARCHAR)";

    //QUERY USUARIO
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            + CAMPO_USER + " TEXT, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_USER_CLAVE + " TEXT, "
            + CAMPO_USER_IDENTIFICACION + " TEXT, " + CAMPO_USER_APELLIDO + " TEXT, " + CAMPO_USER_ROL + " INTEGER)";

    public static final String query_select_user = "SELECT u." + CAMPO_USER
            + ", u." + CAMPO_NOMBRE + ", u." + CAMPO_USER_IDENTIFICACION +
            ", u." + CAMPO_USER_APELLIDO + " FROM " + TABLE_USER + " u WHERE u." + CAMPO_USER + " =? AND u." + CAMPO_USER_CLAVE + "=?";


    public static final String INSERT_ADMIN = "insert into " + TABLE_USER + "(" + CAMPO_USER + "," + CAMPO_NOMBRE + "," + CAMPO_USER_CLAVE
            + "," + CAMPO_USER_APELLIDO + " values(" + NOMBRE_ADMIN + ", " + PASS_ADMIN + ", " + NOMBRE_ADMIN + ", " + NOMBRE_ADMIN + ") ";


    public static boolean vacio(EditText campo) {
        String dato = campo.getText().toString().trim();
        if (TextUtils.isEmpty(dato)) {
            campo.setError("Campo Requerido");
            campo.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    public static Integer getAnio(Date fechaIngreso) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaIngreso);
        return c.get(Calendar.YEAR);
    }

}
