package com.example.contratos.activitys;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.Index;
import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class RegistroOrdenes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private int anio_1, mes_1, dia_1;
    private int anio_2, mes_2, dia_2;
    Calendar c1 = Calendar.getInstance();
    private EditText fecha_1;
    private EditText fecha_2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ordenes);

        mes_1 = c1.get(Calendar.MONTH);
        dia_1 = c1.get(Calendar.DAY_OF_MONTH);
        anio_1 = c1.get(Calendar.YEAR);

        mes_2 = c1.get(Calendar.MONTH);
        dia_2 = c1.get(Calendar.DAY_OF_MONTH);
        anio_2 = c1.get(Calendar.YEAR);

        fecha_1 = (EditText) findViewById(R.id.fecha_recepcion);
        fecha_2 = (EditText) findViewById(R.id.fecha_aceptacion);


        fecha_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        fecha_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        loadDataMenu(findViewById(R.id.drawer_ordenes), findViewById(R.id.nav_view_ordenes), findViewById(R.id.toolbar_ordenes), R.id.item_orden);

    }

    private void colocar_fecha(Boolean accion) {
        if (accion) {
            fecha_1.setText(dia_1 + "/" + (mes_1 + 1) + "/" + anio_1);
        } else {
            fecha_2.setText(dia_2 + "/" + (mes_2 + 1) + "/" + anio_2);
        }

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    anio_1 = year;
                    mes_1 = monthOfYear;
                    dia_1 = dayOfMonth;
                    colocar_fecha(true);
                }
            };


    private DatePickerDialog.OnDateSetListener mDateSetListener_ =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    anio_2 = year;
                    mes_2= monthOfYear;
                    dia_2= dayOfMonth;
                    colocar_fecha(false);
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, mDateSetListener, anio_1, mes_1, dia_1);
            case 1:
                return new DatePickerDialog(this, mDateSetListener_, anio_2, mes_2, dia_2);
        }
        return null;
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