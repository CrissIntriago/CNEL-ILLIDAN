package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.SQLLite.MyOpenHelper;
import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.contratos.activitys.RegistroOrdenes;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

public class RegisterUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String rol = "";
    private Integer rolInt = 0;
    private EditText edit_name;
    private EditText edit_lastname;
    private EditText edit_name_user;
    private EditText edit_identification;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        edit_name = findViewById(R.id.id_ordenes);
        edit_lastname = findViewById(R.id.input_last_name_user);
        edit_name_user = findViewById(R.id.input_user);
        edit_identification = findViewById(R.id.input_identification);
        Button button_cancel = (Button) findViewById(R.id.button_register_cancel);
        Button button_register = (Button) findViewById(R.id.button_register);

        loadDataMenu(findViewById(R.id.drawer_layout_register_user), findViewById(R.id.nav_view_register_user), findViewById(R.id.toolbar_register_user), R.id.nav_perfil);

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.array_rol);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rol = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), rol, Toast.LENGTH_LONG).show();
                assignRol();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    if (saveUser()) {
                        loadData();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void loadDataMenu(DrawerLayout drawerLayoutView, NavigationView navigationViewId, Toolbar toolbarId, int navId) {
        drawerLayout = drawerLayoutView;
        navigationView = navigationViewId;
        toolbar = toolbarId;
        setSupportActionBar(toolbar);
        // MUESTRA LA SELECCION EN EL MENU .. DE CADA ITEM
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(navId);
    }

    private void loadData() {
        edit_name.setText("");
        edit_lastname.setText("");
        edit_name_user.setText("");
        edit_identification.setText("");
        rol = "";
        rolInt = 0;
    }

    private void assignRol() {
        switch (rol) {
            case "Administrador":
                rolInt = 1;
                break;
            case "Invitado":
                rolInt = 2;
                break;
        }
    }

    @SuppressLint("ShowToast")
    private Boolean validar() {
        System.out.println("llega aqui");
        if (edit_name == null || edit_name.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Ingrese el nombre \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edit_lastname == null || edit_lastname.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Ingrese el Apellido \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edit_name_user == null || edit_name_user.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Ingrese el nombre de usuario \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edit_identification == null || edit_identification.getText().length() == 0) {
            Toast.makeText(getBaseContext(), "Ingrese la identificación \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        if (rol.isEmpty()) {
            Toast.makeText(getBaseContext(), "Escoja un rol \uD83D\uDE15", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Boolean saveUser() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        if (db != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Utils.CAMPO_NOMBRE, edit_name.getText().toString());
            contentValues.put(Utils.CAMPO_USER, edit_name_user.getText().toString());
            contentValues.put(Utils.CAMPO_USER_CLAVE, Utils.PASS_DEFAULT);
            contentValues.put(Utils.CAMPO_USER_IDENTIFICACION, edit_identification.getText().toString());
            contentValues.put(Utils.CAMPO_USER_APELLIDO, edit_lastname.getText().toString());
            contentValues.put(Utils.CAMPO_USER_ROL, rolInt);
            db.insert(Utils.TABLE_USER, null, contentValues);
            Toast.makeText(this, "Usuario " + edit_name.getText().toString() + " " + edit_lastname.getText().toString()
                    + " Ingresado con éxito \uD83D\uDE01", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(this, "Error en el registro intente nuevamente ☹", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_configuration:
                startActivity(new Intent(this, ConfigurationActivity.class));
                break;
            case R.id.nav_perfil:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.item_orden:
                startActivity(new Intent(this, RegistroOrdenes.class));
                break;
            case R.id.nav_inventario:
                startActivity(new Intent(this, ContentInventario.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(this, Index.class));
                break;
            case R.id.nav_registro_usuario:
                break;
            default:
                break;
        }
        //Cerrando el drawer en cada seleccion de item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}