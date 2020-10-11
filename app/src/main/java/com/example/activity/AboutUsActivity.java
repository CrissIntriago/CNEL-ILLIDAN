package com.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Index;
import com.example.RegisterUser;
import com.example.contratos.activitys.AsignarBienes;
import com.example.contratos.activitys.RegistroOrdenes;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.google.android.material.navigation.NavigationView;


public class AboutUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    String[] integrants = {"John Intriago", "Cristhian Choez", "Ricardo Muñoz", "David Borbor"};
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        listView = (ListView) findViewById(R.id.listView_about_us);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.text_view_datos, integrants);
        listView.setAdapter(arrayAdapter);
        loadDataMenu(findViewById(R.id.drawer_layout_inventario), findViewById(R.id.nav_view_about_us), findViewById(R.id.toolbar_about_us), R.id.nav_about_us);
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
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.item_bienes:
                startActivity(new Intent(this, AsignarBienes.class));
                break;
            default:
                break;
        }
        //Cerrando el drawer en cada seleccion de item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}