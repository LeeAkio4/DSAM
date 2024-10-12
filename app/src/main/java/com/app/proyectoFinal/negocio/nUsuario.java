package com.app.proyectoFinal.negocio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.proyectoFinal.controlador.cUsuario;
import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;

public class nUsuario{

    cUsuario x;

    public nUsuario(Context context){
        x=new cUsuario(context);
    }
    public void insertar(int cod, String nom, String ape, String cel, String corr, String contra){
        x.insert(new Usuario(cod,nom,ape,cel,corr,contra));
    }
    public ArrayList<Usuario> seleccionar(){
        return x.Select();
    }
}
