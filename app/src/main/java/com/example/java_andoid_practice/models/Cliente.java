package com.example.java_andoid_practice.models;

public class Cliente {

    @Override
    public String toString() {
        return nombre + ',' +
                edad + ','+
                genero + ',' +
                aceptaTerminos +','+
                esperaPromociones;
    }

    public Cliente(String nombre, int edad, String genero, boolean aceptaTerminos, boolean esperaPromociones) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.aceptaTerminos = aceptaTerminos;
        this.esperaPromociones = esperaPromociones;
    }
    private String nombre;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isEsperaPromociones() {
        return esperaPromociones;
    }

    public void setEsperaPromociones(boolean esperaPromociones) {
        this.esperaPromociones = esperaPromociones;
    }

    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    private int edad;
    private String genero;
    private boolean aceptaTerminos;
    private boolean esperaPromociones;
}
