package com.example.a21752434.appfirebasecoches.javabeans;

public class Coche {

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
