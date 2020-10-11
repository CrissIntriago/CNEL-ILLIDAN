package com.example;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.contratos.activitys.AsignarBienes;
import com.example.contratos.activitys.AsignarResponsable;
import com.example.contratos.activitys.RegistroOrdenes;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView_user;
    private String CREDENTIALS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        loadDataMenu(findViewById(R.id.drawer_layout_index), findViewById(R.id.nav_view_index), findViewById(R.id.toolbar_index), R.id.nav_home);
        presentarNameUsuario();
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

    public void presentarNameUsuario() {
        CREDENTIALS = getString(R.string.credentials);
        SharedPreferences preferences = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE);
        textView_user = (TextView) findViewById(R.id.text_view_user);
        textView_user.setText(preferences.getString(Utils.KEY_USER, ""));
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
                break;
            case R.id.nav_registro_usuario:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.item_bienes:
                startActivity(new Intent(this, AsignarBienes.class));
                break;
            case R.id.item_responsable:
                startActivity(new Intent(this, AsignarResponsable.class));
                break;
            default:
                break;
        }
        //Cerrando el drawer en cada seleccion de item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}