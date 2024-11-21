package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;

public class cProducto extends conexion {
    Context context;
    public cProducto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insert(Producto dato) {
        conexion con = new cProducto(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            database.execSQL("INSERT INTO " + tbProducto + " VALUES('" +
                    dato.getCodigo_prod() + "', '" +
                    dato.getNombre() + "', '" +
                    dato.getMarca() + "', '" +
                    dato.getDescripcion() + "', '" +
                    dato.getPrecio() + "', '" +
                    dato.getStock() + "', '" +
                    dato.getAnio() + "', '" +
                    dato.getColor() + "', '" +
                    dato.getCilindros() + "', '" +
                    dato.getTransmision() + "', '" +
                    dato.getTipomotor() + "', '" +
                    dato.getPlaca() + "', '" +
                    dato.getImagen() + "');"
            );
            database.close();
        }
    }

    public void update(Producto dato) {
        conexion con = new cProducto(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            database.execSQL("UPDATE " + tbProducto + " SET " +
                    "nombre = '" + dato.getNombre() + "', " +
                    "marca = '" + dato.getMarca() + "', " +
                    "descripcion = '" + dato.getDescripcion() + "', " +
                    "precio = " + dato.getPrecio() + ", " +
                    "stock = " + dato.getStock() + ", " +
                    "anio = " + dato.getAnio() + ", " +
                    "color = '" + dato.getColor() + "', " +
                    "cilindros = " + dato.getCilindros() + ", " +
                    "transmision = '" + dato.getTransmision() + "', " +
                    "tipomotor = '" + dato.getTipomotor() + "', " +
                    "placa = '" + dato.getPlaca() + "', " +
                    "imagen = '" + dato.getImagen() + "' "+
                    "WHERE codigo_prod = " + dato.getCodigo_prod() + ";"
            );
            database.close();
        }
    }

    public void delete(Producto dato) {
        conexion con = new cProducto(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            database.execSQL("DELETE FROM " + tbProducto + " WHERE codigo_prod = " + dato.getCodigo_prod() + ";");
            database.close();
        }
    }

    public ArrayList<Producto> Select() {
        ArrayList<Producto> est = new ArrayList<>();
        SQLiteDatabase database = null;
        Cursor dato = null;
        try {
            conexion con = new cProducto(context);
            database = con.getWritableDatabase();
            dato = database.rawQuery("SELECT codigo_prod, nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen FROM " + tbProducto, null);

            if (dato.moveToFirst()) {
                do {
                    est.add(new Producto(
                            dato.getInt(0),      // codigo_prod
                            dato.getString(1),   // nombre
                            dato.getString(2),   // marca
                            dato.getString(3),   // descripcion
                            dato.getDouble(4),    // precio
                            dato.getInt(5),      // stock
                            dato.getInt(6),      // anio
                            dato.getString(7),   // color
                            dato.getInt(8),      // cilindros
                            dato.getString(9),   // transmision
                            dato.getString(10),  // tipomotor
                            dato.getString(11),// placa
                            dato.getString(12)
                    ));
                } while (dato.moveToNext());
            }
        } catch (Exception e) {
            Log.e("cProducto", "Error al obtener los productos", e);
        } finally {
            if (dato != null) {
                dato.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return est;
    }


    public Producto obtenerProductoPorCodigo(int codigo) {
        conexion con = new cProducto(context);
        SQLiteDatabase database = con.getReadableDatabase();
        Producto producto = null;

        // Agregar un log para ver el código del producto que se está consultando
        Log.d("cProducto", "Consultando producto con codigo_producto: " + codigo);

        Cursor cursor = database.rawQuery("SELECT * FROM " + tbProducto + " WHERE codigo_prod = ?", new String[]{String.valueOf(codigo)});

        if (cursor != null && cursor.moveToFirst()) {
            producto = new Producto(
                    cursor.getInt(0),       // codigo_prod
                    cursor.getString(1),    // nombre
                    cursor.getString(2),    // marca
                    cursor.getString(3),    // descripcion
                    cursor.getDouble(4),     // precio
                    cursor.getInt(5),       // stock
                    cursor.getInt(6),       // año
                    cursor.getString(7),    // color
                    cursor.getInt(8),       // cilindros
                    cursor.getString(9),    // transmision
                    cursor.getString(10),   // tipomotor
                    cursor.getString(11),    // placa
                    cursor.getString(12)
            );
            cursor.close();
        }
        database.close();
        return producto;
    }

}
