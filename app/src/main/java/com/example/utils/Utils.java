package com.example.utils;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //PARAMETROS DE VALIDADOR DE CEDULA - RUC
    private static final int[] PATTERN = {2, 1, 2, 1, 2, 1, 2, 1, 2};
    private static final int[] CASO_9 = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static final int[] CASO_6 = {3, 2, 7, 6, 5, 4, 3, 2};
    private static final String NUMERIC_REGEX = "^[0-9]+$";

    //DATA ORDENES
    public static final String NUM_ORDEN = "num_orden";
    public static final String NUM_FOLIO = "num_folio";
    public static final String FECHA_RECEPCION = "fecha_recepcion";
    public static final String FECHA_ACEPTACION = "fecha_aceptacion";
    public static final String IDENTIFICACION = "identificacion";
    public static final String NOMBRE = "nombre";
    public static final String DIRECCION = "direccion";
    public static final String SERVICIO = "servicio";
    public static final String DESCRIPCION = "descripcion";
    public static final String TABLE_ORDENES = "ordenes_contrato";

    //DATA ORDENES
    public static final String ID_ORDEN = "id_orden";
    public static final String ID_RESPONSABLE = "id_responsable";
    public static final String TABLE_RESPONSABLE_ORDEN = "responsable_contrato";

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


    public static final String CREATE_TABLE_ORDENES = "CREATE TABLE " + TABLE_ORDENES + "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            + NUM_ORDEN + " TEXT, " + NUM_ORDEN + " TEXT, " + FECHA_ACEPTACION + " TEXT, "
            + FECHA_RECEPCION + " TEXT, " + IDENTIFICACION + " TEXT, " + NOMBRE + " TEXT, "
            + DIRECCION + " TEXT, " + SERVICIO + " TEXT, " + DESCRIPCION + " TEXT)";

    public static final String CREATE_TABLE_RESPONSABLE_ORDEN = "CREATE TABLE " + TABLE_RESPONSABLE_ORDEN + "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            + ID_ORDEN + " INTEGER, " + ID_RESPONSABLE + " INTEGER)";

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

    public static synchronized boolean validateCCRuc(final String identificacion) {
        if (identificacion == null) {
            return false;
        }
        if (identificacion.trim().isEmpty()) {
            return false;
        }
        if (!validateNumberPattern(identificacion)) {
            return false;
        }
        if (identificacion.length() != 10 & identificacion.length() != 13) {
            return false;
        }
        int[] coeficientes = null;
        int indiceDigitoVerificador = 9;
        int modulo = 11;

        if ((identificacion.length() == 13) && !identificacion.substring(10, 13).equals("001")) {
            return false;
        }
        if (identificacion.charAt(2) == '9') {
            coeficientes = CASO_9;
        } else if (identificacion.charAt(2) == '6') {
            coeficientes = CASO_6;
            indiceDigitoVerificador = 8;
        } else if (identificacion.charAt(2) < '6') {
            coeficientes = PATTERN;
            modulo = 10;
        }
        return verify(identificacion.toCharArray(), coeficientes, indiceDigitoVerificador, modulo);
    }

    private static boolean verify(final char[] array, final int[] coeficientes,
                                  final int indiceDigitoVerificador, final int modulo) {
        if (coeficientes == null) {
            return false;
        }
        int sum = 0;
        int aux;
        for (int i = 0; i < coeficientes.length; i++) {
            aux = Integer.valueOf(String.valueOf(array[i])) * coeficientes[i];
            if ((modulo == 10) && (aux > 9)) {
                aux -= 9;
            }
            sum += aux;
        }
        int mod = sum % modulo;
        mod = mod == 0 ? modulo : mod;
        final int res = (modulo - mod);
        Integer valorVerificar = null;
        if (array.length == 13) {
            valorVerificar = Integer.valueOf(String.valueOf(array[array.length - (13 - indiceDigitoVerificador)]));
        } else if (array.length == 10) {
            valorVerificar = Integer.valueOf(String.valueOf(array[array.length - (10 - indiceDigitoVerificador)]));
        }
        return res == valorVerificar;
    }

    public static synchronized boolean validateNumberPattern(final String valor) {
        return validatePattern(NUMERIC_REGEX, valor);
    }

    public static synchronized boolean validatePattern(final String patron, final String valor) {
        final Pattern patter = Pattern.compile(patron);
        final Matcher matcher = patter.matcher(valor);
        return matcher.matches();
    }

}
