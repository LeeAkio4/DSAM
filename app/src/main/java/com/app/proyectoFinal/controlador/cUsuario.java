package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;

public class cUsuario extends conexion{
    Context context;
    public cUsuario(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Usuario dato){
        conexion con= new cUsuario(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("insert into " +tbUsuario+" values(' " +
                    dato.getCodigo()+ "','" +
                    dato.getNombre()+ "','" +
                    dato.getApellido()+ "','" +
                    dato.getCelular()+ "','" +
                    dato.getCorreo()+ "','" +
                    dato.getContrasena()+ "')" );
            database.close();
        }
    }

    public void update(Usuario dato){
        conexion con= new cUsuario(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("update " +tbUsuario+" set(' " +
                    "codigo"+dato.getCodigo()+"','" +
                    "nombre"+dato.getNombre()+"','" +
                    "apellido"+dato.getApellido()+"','" +
                    "celular"+dato.getCelular()+"','" +
                    "correo"+dato.getCorreo()+"','" +
                    "contrasena"+dato.getContrasena()+"','");
            database.close();
        }
    }

    public void delete(Usuario dato){
        conexion con= new cUsuario(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("delete from " +tbUsuario+" where " +
                    "codigo='"+dato.getCodigo()+"'" );
            database.close();
        }
    }

    public ArrayList<Usuario> Select(){
        conexion con= new cUsuario(context);
        SQLiteDatabase database=con.getWritableDatabase();
        ArrayList<Usuario> est= new ArrayList<>();
        Cursor dato=null;
        dato=database.rawQuery("select * from "+ tbUsuario,null);
        if (dato.moveToFirst()){
            do {
                est.add(new Usuario(
                                Integer.parseInt(dato.getString(0)),
                                dato.getString(1),
                                dato.getString(2),
                                dato.getString(3),
                                dato.getString(4),
                                dato.getString(5)
                        )
                );
            }while (dato.moveToNext());
        }
        return est;
    }
}

