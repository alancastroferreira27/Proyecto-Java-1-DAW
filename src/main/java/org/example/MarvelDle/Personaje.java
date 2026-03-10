package org.example;

public class Personaje {
    private String nombre;
    private String genero;
    private String tipo;
    private String especie;
    private String origen;
    private String afiliacion;
    private int aparicion;

    public Personaje(String nombre, String genero, String tipo, String especie, String origen, String afiliacion, int aparicion) {
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.especie = especie;
        this.origen = origen;
        this.afiliacion = afiliacion;
        this.aparicion = aparicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEspecie() {
        return especie;
    }

    public String getOrigen() {
        return origen;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public int getAparicion() {
        return aparicion;
    }
}
