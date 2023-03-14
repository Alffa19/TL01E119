package com.eitsistemas.tl01e119;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.eitsistemas.tl01e119.configuracion.SQLiteConexion;
import com.eitsistemas.tl01e119.configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText codigo,nombre,telefono,nota;
    Button salvarcontacto, mostrarcontcto;
    Spinner opciones;
    Transacciones transa;
    int id=0;
    String pais_recibe,nombre_recibe, telefono_recibe, nota_recibe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = (EditText) findViewById(R.id.txtCodigo);
        opciones = (Spinner) findViewById(R.id.spinner3);
        nombre = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        nota = (EditText) findViewById(R.id.txtNota);
        salvarcontacto = (Button) findViewById(R.id.btnSalvarContacto);
        mostrarcontcto = (Button) findViewById(R.id.btnContactossalvado);


        Bundle parametro = getIntent().getExtras();
        if(parametro != null)
        {
            codigo.setText(parametro.getString("id"));
            //opciones.getSelectedItem(parametro.getString("pais");
            nombre.setText(parametro.getString("nombre"));
            telefono.setText(parametro.getString("telefono"));
            nota.setText(parametro.getString("nota"));
        }

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        salvarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarDatos();
                //Toast.makeText(getApplicationContext(), "HOLA:" , Toast.LENGTH_LONG).show();
            }
        });
        mostrarcontcto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostradatos = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(mostradatos);
            }
        });

    }


    private void AgregarDatos()
    {
        try
        {
            SQLiteConexion conexion = new SQLiteConexion(this,
                    Transacciones.NameDataBase,
                    null,
                    1);

            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.pais, opciones.getSelectedItem().toString());
            valores.put(Transacciones.nombre, nombre.getText().toString());
            valores.put(Transacciones.telefono, telefono.getText().toString());
            valores.put(Transacciones.nota, nota.getText().toString());

            Long resultado = db.insert(Transacciones.tablaperson, Transacciones.Id, valores);

            Toast.makeText(this, "Ingresado con exito", Toast.LENGTH_SHORT).show();

            CleaPantalla();
        }
        catch (Exception ex)
        {
            ex.toString();
        }

    }
    private void EditarDatos()
    {
        try
        {
            SQLiteConexion conexion = new SQLiteConexion(this,
                    Transacciones.NameDataBase,
                    null,
                    1);

            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            //valores.put(Transacciones.pais, opciones.getText().toString());
            valores.put(Transacciones.pais, opciones.getSelectedItem().toString());
            valores.put(Transacciones.nombre, nombre.getText().toString());
            valores.put(Transacciones.telefono, telefono.getText().toString());
            valores.put(Transacciones.nota, nota.getText().toString());


            //Long resultado  = db.update(Transacciones.tablaperson, Transacciones.Id, valores);

            Toast.makeText(this, "Ingresado con exito", Toast.LENGTH_SHORT).show();

            CleaPantalla();
        }
        catch (Exception ex)
        {
            ex.toString();
        }

    }


    private void CleaPantalla()
    {
        opciones.setSelection(0);
        nombre.setText("");
        telefono.setText("");
        nota.setText("");
        opciones.findFocus();
    }
}