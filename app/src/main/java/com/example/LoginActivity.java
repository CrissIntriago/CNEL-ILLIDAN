package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.Model.Persona;
import com.example.SQLLite.MyOpenHelper;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Cursor c;
    private SQLiteDatabase db;
    private String[] args;
    private EditText text_user;
    private EditText text_pass;

    private String CREDENTIALS;

    private Boolean okSession = Boolean.FALSE;
    private Persona persona = new Persona(Utils.NOMBRE_ADMIN, Utils.NOMBRE_ADMIN, 0, "", "", Utils.PASS_ADMIN,
            "", true, "", "", "1");
    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myOpenHelper = new MyOpenHelper(this);

        final Button btton_login = (Button) findViewById(R.id.button_session);
        text_user = (EditText) findViewById(R.id.input_email);
        text_pass = (EditText) findViewById(R.id.input_password);
        CREDENTIALS = getString(R.string.credentials);
        loadPreferences();
        if (okSession) {
            intentActivity();
        }
        btton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    login();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        c.close();
        db.close();
        super.onDestroy();
    }

    public void login() {
        if (!userAdmin()) {
            args = new String[]{text_user.getText().toString(), text_pass.getText().toString()};
            searchUser();
        }
        savePreferences();
        intentActivity();
    }


    public Boolean validar() {
        if (text_user.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Ingrese un usuario \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        if (text_pass.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Ingrese una contraseña \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

   /* private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Sesión!");
        builder.setMessage("¿Desea guardar la sesión?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savePreferences();
                intentActivity();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intentActivity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/

    private void savePreferences() {
        okSession = Boolean.TRUE;
        SharedPreferences preferences = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.KEY_USER, persona.getUser());
        editor.putString(Utils.KEY_PASS, persona.getPassword());
        editor.putString(Utils.KEY_NAME, persona.getNombre());
        editor.putString(Utils.KEY_LASTNAME, persona.getApellido());
        editor.putString(Utils.KEY_ROL, persona.getApellido());
        editor.putBoolean(Utils.KEY_OK_SESSION, okSession);
        editor.commit();
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE);
        text_user.setText(preferences.getString(Utils.KEY_USER, ""));
        text_pass.setText(preferences.getString(Utils.KEY_PASS, ""));
        okSession = preferences.getBoolean(Utils.KEY_OK_SESSION, Boolean.FALSE);
    }

    private void intentActivity() {
        Toast.makeText(getBaseContext(), "Bienvenido " + persona.getPrimerNombrePrimerApellido() + " \uD83D\uDE01", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Index.class);
        intent.putExtra("name_user", persona.getNombre());
        startActivityForResult(intent, 0);
    }

    public Boolean searchUser() {
        db = myOpenHelper.getWritableDatabase();
        c = db.rawQuery(Utils.query_select_user, args);
        if (c != null) {
            c.moveToFirst();
            do {
                persona = new Persona();
                persona.setNombre(c.getString(c.getColumnIndex("nombre")));
                persona.setApellido(c.getString(c.getColumnIndex("apellido")));
                persona.setUser(c.getString(c.getColumnIndex("user")));
                persona.setPassword(c.getString(c.getColumnIndex("clave")));
            } while (c.moveToNext());
            return persona.getNombre().isEmpty();
        } else {
            Toast.makeText(getBaseContext(), "No existen datos de usuario \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private Boolean userAdmin() {
        return text_user.getText().toString().equals(Utils.NOMBRE_ADMIN) && text_pass.getText().toString().equals(Utils.PASS_ADMIN);
    }

}