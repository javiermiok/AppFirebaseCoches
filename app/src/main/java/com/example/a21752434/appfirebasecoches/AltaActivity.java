package com.example.a21752434.appfirebasecoches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
            dbR.push().setValue(c);
            startActivity(new Intent(AltaActivity.this, MainActivity.class));
        }

    }

    public void cancelar(View v) {
        startActivity(new Intent(AltaActivity.this, MainActivity.class));
    }
}
