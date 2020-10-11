package com.example.contratos.activitys;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.Index;
import com.example.SQLLite.MyOpenHelper;
import com.example.activity.AboutUsActivity;
import com.example.activity.ConfigurationActivity;
import com.example.activity.ProfileActivity;
import com.example.illidan.R;
import com.example.inventario.ContentInventario;
import com.example.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class RegistroOrdenes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private int anio_1, mes_1, dia_1;
    private int anio_2, mes_2, dia_2;
    Calendar c1 = Calendar.getInstance();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_register;
    Button button_cancel;
    EditText num_orden;
    EditText num_folio;
    EditText fecha_recepcion;
    EditText fecha_aceptacion;
    EditText identificacion;
    EditText nombre;
    EditText direccion;
    EditText servicio;
    EditText descripcion;

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

        fecha_recepcion = (EditText) findViewById(R.id.fecha_recepcion);
        fecha_aceptacion = (EditText) findViewById(R.id.fecha_aceptacion);
        num_orden = (EditText) findViewById(R.id.numOrden);
        num_folio = (EditText) findViewById(R.id.numfolio);
        fecha_recepcion = (EditText) findViewById(R.id.fecha_recepcion);
        fecha_aceptacion = (EditText) findViewById(R.id.fecha_aceptacion);
        identificacion = (EditText) findViewById(R.id.input_identification);
        nombre = (EditText) findViewById(R.id.nombre);
        direccion = (EditText) findViewById(R.id.direccion);
        servicio = (EditText) findViewById(R.id.servicio);
        descripcion = (EditText) findViewById(R.id.descripcion);

        fecha_recepcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        fecha_aceptacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        loadDataMenu(findViewById(R.id.drawer_ordenes), findViewById(R.id.nav_view_ordenes), findViewById(R.id.toolbar_ordenes), R.id.item_orden);

        button_register = (Button) findViewById(R.id.button_register);
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
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    private void colocar_fecha(Boolean accion) {
        if (accion) {
            fecha_recepcion.setText(dia_1 + "/" + (mes_1 + 1) + "/" + anio_1);
        } else {
            fecha_aceptacion.setText(dia_2 + "/" + (mes_2 + 1) + "/" + anio_2);
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

    private Boolean saveUser() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        if (db != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Utils.NUM_ORDEN, num_orden.getText().toString());
            contentValues.put(Utils.NUM_FOLIO, num_folio.getText().toString());
            contentValues.put(Utils.FECHA_RECEPCION, fecha_recepcion.getText().toString());
            contentValues.put(Utils.FECHA_ACEPTACION, fecha_aceptacion.getText().toString());
            contentValues.put(Utils.IDENTIFICACION, identificacion.getText().toString());
            contentValues.put(Utils.NOMBRE, nombre.getText().toString());
            contentValues.put(Utils.DIRECCION, direccion.getText().toString());
            contentValues.put(Utils.SERVICIO, servicio.getText().toString());
            contentValues.put(Utils.DESCRIPCION, descripcion.getText().toString());
            db.insert(Utils.TABLE_ORDENES, null, contentValues);
            mensaje("ORDEN DE CONTRATO NO. " + num_orden.getText().toString() + " DEL CLIENTE CON CI. "
                    + identificacion.getText().toString()
                    + " HA SIDO INGRESADO CON EXITO");
            return true;
        }
        Toast.makeText(this, "Error en el registro intente nuevamente ☹", Toast.LENGTH_SHORT).show();
        return false;
    }

    @SuppressLint("ShowToast")
    private Boolean validar() {
        if (identificacion == null || identificacion.getText().length()>10) {
            mensaje("La cantidad de digitos es incorrecta");
            return false;
        }else{
            if(!Utils.validateCCRuc(identificacion.getText().toString())){
                mensaje("Identificación incorrecta");
                return false;
            }
        }
        if (num_orden == null || num_orden.getText().length() == 0) {
            mensaje("Ingrese el numero de la orden");
            return false;
        }
        if (num_folio == null || num_folio.getText().length() == 0) {
            mensaje("Ingrese el numero del folio ");
            return false;
        }
        if (fecha_recepcion == null || fecha_recepcion.getText().length() == 0) {
            mensaje("Ingrese la fecha de aceptacion");
            return false;
        }
        if (fecha_aceptacion == null || fecha_aceptacion.getText().length() == 0) {
            mensaje("Ingrese el numero del folio ");
            return false;
        }
        if (nombre == null || nombre.getText().length() == 0) {
            mensaje("Ingrese el nombre del solicitante");
            return false;
        }
        if (direccion == null || direccion.getText().length() == 0) {
            mensaje("Ingrese la direccion ");
            return false;
        }
        if (servicio == null || servicio.getText().length() == 0) {
            mensaje("Ingrese un motivo del servicio");
            return false;
        }
        if (descripcion == null || descripcion.getText().length() == 0) {
            mensaje("Ingrese un motivo del servicio");
            return false;
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
        fecha_recepcion.setText("");
        identificacion.setText("");
        nombre.setText("");
        direccion.setText("");
        servicio.setText("");
        descripcion.setText("");
    }

}