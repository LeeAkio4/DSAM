package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;

public class nProducto {

    cProducto x;

    public nProducto(Context context) {
        x = new cProducto(context);
    }

    // Actualizamos el método insertar para incluir los nuevos campos del modelo Producto
    public void insertar(int codigo_prod, String nombre, String marca, String descripcion, double precio, int stock, int cilindros, int anio, String color, String transmision, String tipomotor, String placa) {
        x.insert(new Producto(codigo_prod, nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa));
    }

    public ArrayList<Producto> seleccionar() {
        return x.Select();
    }
}

