package com.example.restaurante.model;

public class Plato {
    // nombre del plato para q yo sepa q estoy sirviendo
    private String nombre;
    // precio apuntado rapido para no andar calculando de memoria
    private double precio;

    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
