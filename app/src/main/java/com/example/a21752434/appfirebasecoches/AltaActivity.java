package com.example.a21752434.appfirebasecoches;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21752434.appfirebasecoches.javabeans.Coche;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AltaActivity extends AppCompatActivity {

    private DatabaseReference dbR;

    private EditText etMatricula;
    private EditText etModelo;
    private EditText etColor;
    private EditText etAnio;
    private EditText etPrecio;

    private Button btnAlta;
    private Button btnBorrar;

    private Coche c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etMatricula = findViewById(R.id.etMatriculaAlta);
        etModelo = findViewById(R.id.etModeloAlta);
        etColor = findViewById(R.id.etColorAlta);
        etAnio = findViewById(R.id.etAnioAlta);
        etPrecio = findViewById(R.id.etPrecioAlta);

        dbR = FirebaseDatabase.getInstance().getReference().child("Coleccion_Coches");

        //SI ES LA OPCIÓN DE MODIFICAR
        if(getIntent().getIntExtra("CLAVE_ACCION",1) == MainActivity.REQ_CODE_MOD) {
            //SE CAMBIA EL TEXTO DEL BOTÓN
            btnAlta = findViewById(R.id.btnCrearAlta);
            btnAlta.setText(getString(R.string.btn_modificar_main));

            //SE CARGAN LOS DATOS DEL COCHE QUE SE QUIRE MODIFICAR
            c = getIntent().getParcelableExtra("DATOS_SELECCION");
            etMatricula.setText(c.getMatricula());
            etModelo.setText(c.getModelo());
            etColor.setText(c.getColor());
            etAnio.setText(String.valueOf(c.getAnio()));
            etPrecio.setText(String.valueOf(c.getPrecio()));

        } else {
            //SI ES LA OPCIÓN DE CREAR SE QUITA EL BOTÓN DE BORRAR
            btnBorrar = findViewById(R.id.btnBorrarAlta);
            btnBorrar.setVisibility(View.GONE);
        }

    }

    public void crear(View v) {

        String matricula = etMatricula.getText().toString();
        String modelo = etModelo.getText().toString();
        String color = etColor.getText().toString();
        String anio = etAnio.getText().toString();
        String precio = etPrecio.getText().toString();

        if(matricula.isEmpty() || modelo.isEmpty() || color.isEmpty() || anio.isEmpty() || precio.isEmpty()
                || matricula.trim().equals("") || modelo.trim().equals("") || color.trim().equals("")) {
            //Si no están los campos rellenos
            Toast.makeText(getBaseContext(), getString(R.string.toast_error_alta), Toast.LENGTH_SHORT).show();

        } else if(getIntent().getIntExtra("CLAVE_ACCION",1) == MainActivity.REQ_CODE_MOD) {
            //Comprobar que se ha modificado algo
            if(matricula.equals(c.getMatricula()) && modelo.equals(c.getModelo()) && color.equals(c.getColor())
                    && Integer.parseInt(anio) == c.getAnio() && Double.parseDouble(precio) == c.getPrecio()) {

                Toast.makeText(getBaseContext(), getString(R.string.toast_error_sin_cambios), Toast.LENGTH_SHORT).show();

            } else {
                Map<String, Object> mapa = new HashMap<String, Object>();
                System.out.println("Modificando coche con idFirabes: "+c.getIdFirebase());
                String clave = c.getIdFirebase();
                c = new Coche(matricula, modelo, color, Integer.parseInt(anio), Double.parseDouble(precio));
                c.setIdFirebase(clave);
                mapa.put(clave, c);
                Toast.makeText(getBaseContext(), "MODIFICANDO", Toast.LENGTH_SHORT).show();
                dbR.updateChildren(mapa);

                startActivity(new Intent(AltaActivity.this, MainActivity.class));
                finish();

            }

        } else{
            c = new Coche(matricula, modelo, color, Integer.parseInt(anio), Double.parseDouble(precio));
            String clave = dbR.push().getKey();
            c.setIdFirebase(clave);
            System.out.println("Id de coche nuevo: " +c.getIdFirebase());

            dbR.child(clave).setValue(c);

            startActivity(new Intent(AltaActivity.this, MainActivity.class));
            finish();
        }

    }

    public void borrar(View v) {
        crearDialogo().show();

    }

    public void cancelar(View v) {
        startActivity(new Intent(AltaActivity.this, MainActivity.class));
        Toast t = Toast.makeText(getBaseContext(), getString(R.string.toast_no_mod), Toast.LENGTH_SHORT);
        t.show();
        finish();
    }

    private Dialog crearDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AltaActivity.this);
        //builder.setCancelable(false);
        builder.setMessage(R.string.dialgo_mensaje_confirmación);

        builder.setPositiveButton(R.string.dialog_proceder, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbR.child(c.getIdFirebase()).removeValue();
                startActivity(new Intent(AltaActivity.this, MainActivity.class));
                finish();

            }
        });

        builder.setNegativeButton(R.string.dialog_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    } // fin crearDialogo()
}
