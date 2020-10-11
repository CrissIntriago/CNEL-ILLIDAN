package com.example.contratos.activitys;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.Index;
import com.example.RegisterUser;
import com.example.SQLLite.MyOpenHelper;
import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

public class AsignarResponsable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView num_orden;
    TextView num_folio;
    TextView fecha_aceptacion;

    TextView num_identificacion;
    TextView nombre_user;

    EditText orden_contrato;
    EditText usser;

    Button button_cancel;
    Button button_register;
    Button button_buscar_orden;
    Button button_buscar_responsable;

    private Integer id_orden = 0;
    private Integer id_usuario = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_responsable);
        loadDataMenu(findViewById(R.id.drawer_responsables), findViewById(R.id.nav_view_responsables), findViewById(R.id.toolbar_responsables), R.id.item_responsable);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_register = (Button) findViewById(R.id.button_register);
        button_buscar_orden = (Button) findViewById(R.id.button_buscar);
        button_buscar_responsable = (Button) findViewById(R.id.button_buscar_2);

        orden_contrato = (EditText) findViewById(R.id.num_orden_buscar);
        usser = (EditText)findViewById(R.id.id_usuario);

        num_orden = findViewById(R.id.num_orden);
        num_folio = findViewById(R.id.num_folio);
        fecha_aceptacion = findViewById(R.id.fecha_aceptacion);
        num_identificacion = findViewById(R.id.ci_empleado_view);
        nombre_user = findViewById(R.id.nombre_usuario_view);

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
                    if (save()) {
                        loadData();
                    }
                }
            }
        });
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
            case R.id.item_bienes:
                startActivity(new Intent(this, AsignarBienes.class));
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

    @SuppressLint("ShowToast")
    private Boolean validar() {
        if (orden_contrato == null || orden_contrato.getText().length() == 0) {
            mensaje("No hay una orden de contrato seleccionado");
            return false;
        }
        if (usser == null || usser.getText().length()>10) {
            mensaje("La cantidad de digitos es incorrecta");
            return false;
        }else{
            if(!Utils.validateCCRuc(usser.getText().toString())){
                mensaje("Identificación incorrecta");
                return false;
            }
        }
        return true;
    }
    private void mensaje (String mensaje){
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    private void loadData() {
        num_orden.setText("");
        num_folio.setText("");
        fecha_aceptacion.setText("");
        nombre_user.setText("");
        num_identificacion.setText("");
        id_usuario=0;
        id_orden=0;
        orden_contrato.setText("");
        usser.setText("");
    }

    private Boolean save() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        if (db != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Utils.ID_ORDEN, id_orden);
            contentValues.put(Utils.ID_RESPONSABLE, id_usuario);
            db.insert(Utils.TABLE_RESPONSABLE_ORDEN, null, contentValues);
            mensaje("ORDEN DE CONTRATO NO. " + num_orden.getText().toString() + " FUE ASIGNADO A  "
                    + nombre_user.getText().toString()
                    + " CON EXITO");
            return true;
        }
        Toast.makeText(this, "Error en el registro intente nuevamente ☹", Toast.LENGTH_SHORT).show();
        return false;
    }
}