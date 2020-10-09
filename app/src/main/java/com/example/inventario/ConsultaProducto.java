package com.example.inventario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.SQLLite.MyOpenHelper;
import com.example.illidan.R;
import com.example.utils.Utils;

public class ConsultaProducto extends AppCompatActivity implements View.OnClickListener {
    private Button consulta,editar,eliminar;
    private EditText codigo,nombre,descripcion,precio,cantidad,marca;
    private Integer id_pro;
    private MyOpenHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_producto);
        consulta = (Button) findViewById(R.id.btn_busqueda_prod);
        editar = (Button)findViewById(R.id.btn_editar_guardar_prod);
        eliminar = (Button)findViewById(R.id.btn_eliminar_prod);
        codigo = (EditText)findViewById(R.id.text_consulta_cod);
        nombre = (EditText)findViewById(R.id.text_consulta_nombre);
        descripcion = (EditText)findViewById(R.id.text_consulta_descrip);
        precio = (EditText)findViewById(R.id.text_consulta_precio);
        cantidad = (EditText)findViewById(R.id.text_consulta_cantidad);
        marca = (EditText)findViewById(R.id.text_consulta_marca);
        consulta.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        con = new MyOpenHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_busqueda_prod:
                consultaProducto();
                break;
            case R.id.btn_editar_guardar_prod:
                editarProducto();
                break;
            default:
                eliminarProducto();
                break;
        }
    }

    private void eliminarProducto() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] param = {id_pro.toString()};
        db.delete(Utils.TABLA_PRODUCTO,Utils.CAMPO_ID+"=?",param);
        Toast.makeText(getApplicationContext(),"Producto Eliminado con Exito",Toast.LENGTH_LONG).show();
        db.close();
        codigo.setText("");
        limpiar();
    }

    private void editarProducto() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] param = {id_pro.toString()};
        ContentValues values = new ContentValues();
        values.put(Utils.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(Utils.CAMPO_DESCRIPCION,descripcion.getText().toString());
        values.put(Utils.CAMPO_PRECIO,Double.parseDouble(precio.getText().toString()));
        values.put(Utils.CAMPO_CANTIDAD,Integer.parseInt(cantidad.getText().toString()));
        values.put(Utils.CAMPO_MARCA,marca.getText().toString());
        db.update(Utils.TABLA_PRODUCTO,values,Utils.CAMPO_ID+"=?",param);
        Toast.makeText(getApplicationContext(),"Producto Actualizado con Exito",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void consultaProducto() {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {codigo.getText().toString()};
        String[] campos = {Utils.CAMPO_ID,Utils.CAMPO_NOMBRE,Utils.CAMPO_DESCRIPCION,Utils.CAMPO_PRECIO,Utils.CAMPO_CANTIDAD,Utils.CAMPO_MARCA};
        try {
            Cursor cursor = db.query(Utils.TABLA_PRODUCTO,campos,Utils.CAMPO_CODIGO+"=?",param,null,null,null);
            cursor.moveToFirst();
            id_pro = cursor.getInt(0);
            nombre.setText(cursor.getString(1));
            descripcion.setText(cursor.getString(2));
            precio.setText(cursor.getDouble(3)+"");
            cantidad.setText(cursor.getInt(4)+"");
            marca.setText(cursor.getString(5));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"EL Producto no Existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }
    public void limpiar(){
        nombre.setText("");
        descripcion.setText("");
        precio.setText("");
        cantidad.setText("");
        marca.setText("");
    }
}