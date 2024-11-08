package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Producto;

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
                    dato.getPrecio()+ "')"  +
                    dato.getStock()+ "')" );
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
                    "precio"+dato.getPrecio()+"','" +
                    "stock"+dato.getStock()+"','");
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
                                dato.getDouble(4),
                                Integer.parseInt(dato.getString(5))
                        )
                );
            }while (dato.moveToNext());
        }
        return est;
    }
    public Producto obtenerProductoPorCodigo(int codigo) {
        conexion con = new cProducto(context);
        SQLiteDatabase database = con.getReadableDatabase();
        Producto producto = null;

        // Agregar un log para ver el código del producto que se está consultando
        Log.d("cProducto", "Consultando producto con codigo_producto: " + codigo);

        Cursor cursor = database.rawQuery("SELECT * FROM " + tbProducto + " WHERE codigo_producto = ?", new String[]{String.valueOf(codigo)});

        if (cursor != null && cursor.moveToFirst()) {
            producto = new Producto(
                    cursor.getInt(0),         // codigo_producto
                    cursor.getString(1),      // nombre
                    cursor.getString(2),      // marca
                    cursor.getString(3),      // descripcion
                    cursor.getDouble(4),      // precio
                    cursor.getInt(5)          // stock
            );
            cursor.close();
        }
        database.close();
        return producto;
    }

}
