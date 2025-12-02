package com.example.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    // aqui guardo el numero de mesa para no liarme con cual es cual
    private final int numero;
    // lista con los platos q lleva esta mesa para tenerlo todo a mano
    private final List<Plato> platos = new ArrayList<>();

    public Mesa(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public boolean agregarPlato(Plato plato) {
        // si ya hay 10 platos mejor parar q luego toca correr
        if (platos.size() >= 10) {
            return false;
        }
        return platos.add(plato);
    }

    public boolean eliminarUltimoPlato() {
        // si no hay platos no hay nada q borrar asi q aviso
        if (platos.isEmpty()) {
            return false;
        }
        platos.remove(platos.size() - 1);
        return true;
    }

    public double calcularTotal() {
        // sumo todo por si acaso q luego se me olvida cuanto van pagando
        double total = 0;
        for (Plato plato : platos) {
            total += plato.getPrecio();
        }
        return total;
    }
}
