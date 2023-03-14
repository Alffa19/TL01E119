package com.eitsistemas.tl01e119;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.eitsistemas.tl01e119.configuracion.SQLiteConexion;
import com.eitsistemas.tl01e119.configuracion.Transacciones;
import com.eitsistemas.tl01e119.tablas.Datos;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listadatos;
    ArrayList<Datos> lista;
    ArrayList<String> ArregloDatos;
    Button eliminarcontacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        listadatos = (ListView) findViewById(R.id.listadatos);
        eliminarcontacto = (Button) findViewById(R.id.btnEliminarc);

        ObtnerListaDatos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloDatos);
        listadatos.setAdapter(adp);


        listadatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              int id = Integer.parseInt(ArregloDatos.get(position).split(" ")[0]);
              String pais = ArregloDatos.get(position).split(" ")[1];
              String nombre = ArregloDatos.get(position).split(" ")[2];
              String telefono = ArregloDatos.get(position).split(" ")[3];
              String nota = ArregloDatos.get(position).split(" ")[4];
              //Intent intent = new Intent(view.getContext(), MainActivity.class);
              Intent intent = new Intent(ActivityList.this,MainActivity.class);
              intent.putExtra("id", id);
              intent.putExtra("pais",pais);
              intent.putExtra("nombre",nombre);
              intent.putExtra("telefono",telefono);
              intent.putExtra("nota",nota);
              startActivity(intent);

               // Log.i("Click", "click en el elemento"+ position + "de mi ListView");
                //Toast.makeText(getApplicationContext(),"hola ", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void ObtnerListaDatos()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Datos dat = null;
        lista = new ArrayList<Datos>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablaperson, null);

        while (cursor.moveToNext())
        {
            dat = new Datos();
            dat.setId(cursor.getInt(0));
            dat.setPais(cursor.getString(1));
            dat.setNombre(cursor.getString(2));
            dat.setTelefono(cursor.getString(3));
            dat.setNota(cursor.getString(4));

            lista.add(dat);
        }
        cursor.close();
        filllist();
    }

    private void filllist()
    {
        ArregloDatos = new ArrayList<String>();
        for(int i =0; i<lista.size(); i++)
        {
            ArregloDatos.add(lista.get(i).getId() + "  " +
                    lista.get(i).getPais() + "  "+
                    lista.get(i).getNombre()+ "  " +
                    lista.get(i).getTelefono() + " ");
        }
    }

}