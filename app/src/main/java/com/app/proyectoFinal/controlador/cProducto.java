package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Producto;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.ArrayList;

public class cProducto extends conexion{
    Context context;
    public cProducto(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Producto dato){
        conexion con= new cProducto(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("insert into " +tbProducto+" values(' " +
                    dato.getCodigo_prod()+ "','" +
                    dato.getNombre()+ "','" +
                    dato.getMarca()+ "','" +
                    dato.getDescripcion()+ "','" +
                    dato.getPrecio()+ "')" );
            database.close();
        }
    }

    public void update(Producto dato){
        conexion con= new cProducto(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("update " +tbProducto+" set(' " +
                    "codigo_prod"+dato.getCodigo_prod()+"','" +
                    "nombre"+dato.getNombre()+"','" +
                    "marca"+dato.getMarca()+"','" +
                    "descripcion"+dato.getDescripcion()+"','" +
                    "precio"+dato.getPrecio()+"','");
            database.close();
        }
    }

    public void delete(Producto dato){
        conexion con= new cProducto(context);
        SQLiteDatabase database=con.getWritableDatabase();
        if (database!=null){
            database.execSQL("delete from " +tbProducto+" where " +
                    "codigo='"+dato.getCodigo_prod()+"'" );
            database.close();
        }
    }

    public ArrayList<Producto> Select(){
        conexion con= new cProducto(context);
        SQLiteDatabase database=con.getWritableDatabase();
        ArrayList<Producto> est= new ArrayList<>();
        Cursor dato=null;
        dato=database.rawQuery("select * from "+ tbProducto,null);
        if (dato.moveToFirst()){
            do {
                est.add(new Producto(
                                Integer.parseInt(dato.getString(0)),
                                dato.getString(1),
                                dato.getString(2),
                                dato.getString(3),
                                dato.getString(4)
                        )
                );
            }while (dato.moveToNext());
        }
        return est;
    }
}
