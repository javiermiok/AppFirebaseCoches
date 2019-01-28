package com.example.a21752434.appfirebasecoches.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

public class Coche implements Parcelable {

    private String idFirebase;
    private String matricula;
    private String modelo;
    private String color;
    private int anio;
    private double precio;

    public Coche() {
    }

    public Coche(String matricula, String modelo, String color, int anio, double precio) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.precio = precio;
    }

    /*--------------               PARCEABLE                      ------------------------*/
    public Coche(Parcel in) {
        idFirebase = in.readString();
        matricula = in.readString();
        modelo = in.readString();
        color = in.readString();
        anio = in.readInt();
        precio = in.readDouble();
    }

    public static final Creator<Coche> CREATOR = new Creator<Coche>() {
        @Override
        public Coche createFromParcel(Parcel in) {
            return new Coche(in);
        }

        @Override
        public Coche[] newArray(int size) {
            return new Coche[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idFirebase);
        dest.writeString(matricula);
        dest.writeString(modelo);
        dest.writeString(color);
        dest.writeInt(anio);
        dest.writeDouble(precio);
    }

    /*--------------               SETTERS Y GETTERS                      ------------------------*/
    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getAnio() {
        return anio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }


}
