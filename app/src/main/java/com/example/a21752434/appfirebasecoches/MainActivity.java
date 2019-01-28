package com.example.a21752434.appfirebasecoches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void consultar(View v) {
        startActivity(new Intent(MainActivity.this, ConsultaActivity.class));
    }

    public void alta(View v) {
        startActivity(new Intent(MainActivity.this, AltaActivity.class));
    }

    public void modificar(View v) {

    }

    public void borrar(View v) {

    }

}
