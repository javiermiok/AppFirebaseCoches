package com.example.a21752434.appfirebasecoches;

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

public class AltaActivity extends AppCompatActivity {

    private DatabaseReference dbR;

    private EditText etMatricula;
    private EditText etModelo;
    private EditText etColor;
    private EditText etAnio;
    private EditText etPrecio;

    private Button btnAlta;

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

        //Si es la opción de modificar
        if(getIntent().getIntExtra("CLAVE_ACCION",1) == MainActivity.REQ_CODE_MOD) {
            btnAlta = findViewById(R.id.btnAltaMain);
            btnAlta.setText(getString(R.string.btn_modificar_main));

            Coche c = getIntent().getParcelableExtra("COCHE_MOD");
            etMatricula.setText(c.getMatricula());
            etMatricula.setActivated(false);
            etModelo.setText(c.getMatricula());
            etColor.setText(c.getMatricula());
            etAnio.setText(c.getMatricula());
            etPrecio.setText(c.getMatricula());
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
            Toast.makeText(getBaseContext(), getString(R.string.toast_error_alta), Toast.LENGTH_SHORT).show();
        } else {
            Coche c = new Coche(matricula, modelo, color, Integer.parseInt(anio), Double.parseDouble(precio));
            String clave = dbR.push().getKey();
            startActivity(new Intent(AltaActivity.this, MainActivity.class));
        }

    }

    public void cancelar(View v) {
        //startActivity(new Intent(AltaActivity.this, MainActivity.class));
    }
}
