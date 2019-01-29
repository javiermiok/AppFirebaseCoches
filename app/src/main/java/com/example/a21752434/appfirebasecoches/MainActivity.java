package com.example.a21752434.appfirebasecoches;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.a21752434.appfirebasecoches.javabeans.Coche;
import com.example.a21752434.appfirebasecoches.javabeans.CocheAdaptador;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_ALTA = 1;
    public static final int REQ_CODE_MOD = 2;
    private RecyclerView rvCoches;
    private CocheAdaptador adapter;
    private LinearLayoutManager manager;

    private ArrayList<Coche> datos;

    private DatabaseReference dbR;
    private ChildEventListener cel;

    private String remitente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciar atributos
        remitente = "persona1";
        rvCoches = findViewById(R.id.rvMain);
        datos = new ArrayList<Coche>();
        adapter = new CocheAdaptador(datos, getApplicationContext());
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test
                String msg = "Seleccionada la opción " + rvCoches.getChildAdapterPosition(v) ;
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();

                //Pasar a la ventana PantallaNotasALumno
                Intent i = new Intent(MainActivity.this, AltaActivity.class);
                i.putExtra("CLAVE_ACCION", REQ_CODE_MOD);
                i.putExtra("DATOS_SELECCION", datos.get(rvCoches.getChildAdapterPosition(v)));
                startActivity(i);
            }
        });
        manager = new LinearLayoutManager(this);

        rvCoches.setAdapter(adapter);
        rvCoches.setLayoutManager(manager);
        rvCoches.setItemAnimator(new DefaultItemAnimator());

        dbR = FirebaseDatabase.getInstance().getReference().child("Coleccion_Coches");
        addChildEventListener();
    }


    public void alta(View v) {
        Intent i = new Intent(MainActivity.this, AltaActivity.class);
       // i.putExtra("CLAVE_ACCION", REQ_CODE_ALTA);
        //i.putParcelableArrayListExtra("DATA", datos);
        //startActivity(i);
        //startActivityForResult(i, REQ_CODE_ALTA);
        startActivity(i);
    }

    /*SIN USAR*/
    public void modificar(View v) {
        //Intent i = new Intent(MainActivity.this, AltaActivity.class);
        //i.putExtra("CLAVE_ACCION", REQ_CODE_MOD);
        //i.putParcelableArrayListExtra("DATA", datos);
        //startActivity(i);
        //startActivityForResult(i, REQ_CODE_MOD);
    }

    /*SIN USAR*/
    public void borrar(View v) {

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {

        } else if(resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == REQ_CODE_MOD) {
                Toast t = Toast.makeText(getBaseContext(), getString(R.string.toast_no_mod), Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }*/

    private void addChildEventListener() {
        if(cel == null) {
            cel = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Coche c = dataSnapshot.getValue(Coche.class);
                    datos.add(c);
                    adapter.notifyItemChanged(datos.size()-1);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    /*Coche c = dataSnapshot.getValue(Coche.class);
                    int pos = 0;
                    for(int i = 0; i < datos.size(); i++) {
                        if(datos.get(i).getMatricula().equals(c.getMatricula())) {        // se busca el mismo coche
                            datos.set(i, c);
                            pos = i;
                        }
                    }
                    adapter.notifyItemChanged(pos);*/
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    /*
                    Eliminar con dbR.child(?/matricula = AAA12345).removeValue
                    */
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            dbR.addChildEventListener(cel);
        }
    }

    /**
     * Liberar recursos cuando se
     * pausa la actividad
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(cel != null) {
            dbR.removeEventListener(cel);
            cel = null;
        }
        adapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addChildEventListener();
    }

}
