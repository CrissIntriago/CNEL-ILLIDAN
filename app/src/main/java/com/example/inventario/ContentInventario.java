package com.example.inventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.Index;
import com.example.Model.Persona;
import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.contratos.activitys.RegistroOrdenes;
import com.example.illidan.R;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

public class ContentInventario extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private Button btn_categoria, btn_inventario, btn_lista_prov,btn_consulta;
    private String CREDENTIALS;
    Persona persona = new Persona();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_inventario);
        btn_categoria = (Button) findViewById(R.id.btn_categoria);
        btn_inventario = (Button) findViewById(R.id.btn_inventario);
        btn_lista_prov = (Button) findViewById(R.id.btn_lista_inv);
        btn_consulta = (Button) findViewById(R.id.btn_consulta_prod);
        btn_categoria.setOnClickListener(this);
        btn_inventario.setOnClickListener(this);
        btn_lista_prov.setOnClickListener(this);
        btn_consulta.setOnClickListener(this);
        CREDENTIALS = getString(R.string.credentials);
        loadDataMenu(findViewById(R.id.drawer_layout_inventario), findViewById(R.id.nav_view_inventario), findViewById(R.id.toolbar_inventario), R.id.nav_inventario);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_categoria:
                startActivity(new Intent(ContentInventario.this, CategoriaBeans.class));
                break;
            case R.id.btn_inventario:
                startActivity(new Intent(ContentInventario.this, ProductoBeans.class));
                break;
            case R.id.btn_consulta_prod:
                startActivity(new Intent(ContentInventario.this, ConsultaProducto.class));
                break;
            default:
                startActivity(new Intent(ContentInventario.this, ListaProductos.class));
                break;
        }
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences(CREDENTIALS, Context.MODE_PRIVATE);
        persona.setUser(preferences.getString(Utils.KEY_USER, ""));
        persona.setNombre(preferences.getString(Utils.KEY_NAME, ""));
        persona.setApellido(preferences.getString(Utils.KEY_LASTNAME, ""));
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
            default:
                break;
        }
        //Cerrando el drawer en cada seleccion de item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}