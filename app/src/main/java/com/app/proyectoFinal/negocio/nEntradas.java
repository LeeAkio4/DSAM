package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cEntradas;
import com.app.proyectoFinal.modelo.Entradas;

import java.sql.Timestamp;
import java.util.ArrayList;

public class nEntradas {

    private cEntradas x;

    public nEntradas(Context context) {
        x = new cEntradas(context);
    }

    // Método para insertar una entrada
    public void insertar(int idEntrada, int idProducto, int cantidad, double costoUnitario, Timestamp fechaEntrada) {
        Entradas entrada = new Entradas(idEntrada, idProducto, cantidad, costoUnitario, 0, fechaEntrada);
        entrada.calcularTotalEntrada(); // Calcula el total antes de insertar
        x.insert(entrada);
    }

    // Método para seleccionar todas las entradas
    public ArrayList<Entradas> seleccionar() {
        return x.select();
    }
}
