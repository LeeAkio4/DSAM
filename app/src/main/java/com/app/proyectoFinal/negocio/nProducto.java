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
<<<<<<< HEAD
    public void insertar(int cod_prod, String nombre, String marca, String descripcion, String precio){
        x.insert(new Producto(cod_prod,nombre,marca,descripcion,precio));
=======
    public void insertar(int cod_prod, String nombre, String marca, String descripcion, String precio, String stock){
        x.insert(new Producto(cod_prod,nombre,marca,descripcion,precio,stock));
>>>>>>> 6b31fd5ecb7a697e051d42e96d9e34d725baa4b3
    }
    public ArrayList<Producto> seleccionar(){
        return x.Select();
    }
}
