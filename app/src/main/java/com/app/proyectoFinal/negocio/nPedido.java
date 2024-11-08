package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.ArrayList;

public class nPedido {

    cPedido x;

    public nPedido(Context context){
        x=new cPedido(context);
    }
    public void insertar(int cod_p, int cod_u, int cod_prod, String fechaped, String est, String total){
        x.insert(new Pedido(cod_p,cod_u,cod_prod, fechaped,est,total));
    }
    public ArrayList<Pedido> seleccionar(){
        return x.Select();
    }
}
