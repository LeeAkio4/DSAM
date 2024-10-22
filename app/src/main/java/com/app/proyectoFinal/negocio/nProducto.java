package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;

public class nProducto {

    cProducto x;

    public nProducto(Context context){
        x=new cProducto(context);
    }
    public void insertar(int cod_prod, String nombre, String marca, String descripcion, String precio){
        x.insert(new Producto(cod_prod,nombre,marca,descripcion,precio));
    }
    public ArrayList<Producto> seleccionar(){
        return x.Select();
    }
}