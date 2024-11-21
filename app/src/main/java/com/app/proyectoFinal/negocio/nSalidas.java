package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cSalidas;
import com.app.proyectoFinal.modelo.Salidas;

import java.sql.Timestamp;
import java.util.ArrayList;

public class nSalidas {

    private cSalidas x;

    public nSalidas(Context context) {
        x = new cSalidas(context);
    }

    // Método para insertar una salida
    public void insertar(int idSalida, int idPedido, int cantidad, double precioUnitario, Timestamp fechaSalida) {
        Salidas salida = new Salidas(idSalida, idPedido, cantidad, precioUnitario, 0, fechaSalida);
        salida.calcularTotalSalida(); // Calcula el total antes de insertar
        x.insert(salida);
    }

    // Método para obtener todas las salidas
    public ArrayList<Salidas> seleccionar() {
        return x.select();
    }
}
