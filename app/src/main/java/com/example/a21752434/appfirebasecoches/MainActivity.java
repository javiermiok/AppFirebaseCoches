package com.example.a21752434.appfirebasecoches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coches, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_insertar) {

        } else if(item.getItemId() == R.id.menu_modificar) {

        } else if(item.getItemId() == R.id.menu_consulta_todos) {

        } else if(item.getItemId() == R.id.menu_consultar_anio) {

        } else if(item.getItemId() == R.id.menu_finalizar) {

        }

        return super.onOptionsItemSelected(item);
    }
}
