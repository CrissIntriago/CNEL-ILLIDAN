package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.illidan.R;

public class ConsultaProducto extends AppCompatActivity {
    private Button consulta,editar,eliminar;
    private EditText codigo,nombre,descripcion,precio,cantidad,marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_producto);
        consulta = (Button) findViewById(R.id.btn_consulta_prod);
        editar = (Button)findViewById(R.id.btn_editar_guardar_prod);
        eliminar = (Button)findViewById(R.id.btn_eliminar_prod);
    }
}