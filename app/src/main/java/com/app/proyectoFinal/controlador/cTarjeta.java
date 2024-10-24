package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Tarjeta;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;

public class cTarjeta extends conexion{
    Context context;
    public cTarjeta(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Tarjeta dato){
        conexion con= new cTarjeta(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("insert into " +tbTarjeta+" values(' " +
                    dato.getCodigo_t()+ "','" +
                    dato.getCodigo_u()+ "','" +
                    dato.getNombre()+ "','" +
                    dato.getCorreo()+ "','" +
                    dato.getFechacad()+ "','" +
                    dato.getCvv()+ "')" );
            database.close();
        }
    }

    public void update(Tarjeta dato){
        conexion con= new cTarjeta(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("update " +tbTarjeta+" set(' " +
                    "codigo_t"+dato.getCodigo_t()+"','" +
                    "codigo_u"+dato.getCodigo_u()+"','" +
                    "nombre"+dato.getNombre()+"','" +
                    "correo"+dato.getCorreo()+"','" +
                    "fecha_caducidad"+dato.getFechacad()+"','" +
                    "cvv"+dato.getCvv()+"','");
            database.close();
        }
    }

    public void delete(Tarjeta dato){
        conexion con= new cTarjeta(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("delete from " +tbTarjeta+" where " +
                    "codigo='"+dato.getCodigo_t()+"'" );
            database.close();
        }
    }

    public ArrayList<Tarjeta> Select(){
        conexion con= new cTarjeta(context);
        SQLiteDatabase database=con.getWritableDatabase();
        ArrayList<Tarjeta> est= new ArrayList<>();
        Cursor dato=null;
        dato=database.rawQuery("select * from "+ tbTarjeta,null);
        if (dato.moveToFirst()){
            do {
                est.add(new Tarjeta(
                                Integer.parseInt(dato.getString(0)),
                                Integer.parseInt(dato.getString(1)),
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
