package com.example.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class MesaStorage {
    // guardo las mesas aqui para q no se me pierdan entre pantallas
    private static final List<Mesa> mesas = new ArrayList<>();

    public static List<Mesa> obtenerMesas() {
        // si esta vacio lo relleno rapido con 5 mesas de arranque
        if (mesas.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                mesas.add(new Mesa(i));
            }
        }
        return mesas;
    }

    public static Mesa obtenerMesaPorNumero(int numero) {
        // busco la mesa q me pidieron a ver si existe
        for (Mesa mesa : obtenerMesas()) {
            if (mesa.getNumero() == numero) {
                return mesa;
            }
        }
        return null;
    }
}
