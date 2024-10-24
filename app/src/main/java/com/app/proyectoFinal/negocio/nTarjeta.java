package com.app.proyectoFinal.negocio;

import android.content.Context;

import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.controlador.cUsuario;
import com.app.proyectoFinal.modelo.Tarjeta;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;

public class nTarjeta {

    cTarjeta x;

    public nTarjeta(Context context){
        x=new cTarjeta(context);
    }
    public void insertar(int cod_t, int cod_u, String nom, String corr, String fechacad, String cvv){
        x.insert(new Tarjeta(cod_t,cod_u,nom,corr,fechacad,cvv));
    }
    public ArrayList<Tarjeta> seleccionar(){
        return x.Select();
    }
}
