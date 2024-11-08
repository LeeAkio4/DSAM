package com.app.proyectoFinal.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class cPedido extends conexion {

    Context context;

    public cPedido(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insert(Pedido pedi) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            // Log para depurar los valores de los parámetros
            Log.d("cPedido", "Insertando pedido: codigo_usuario=" + pedi.getCodigo_u() + ", codigo_producto=" + pedi.getCodigo_prod());

            ContentValues values = new ContentValues();
            values.put("codigo_usuario", pedi.getCodigo_u());
            values.put("codigo_producto", pedi.getCodigo_prod());
            values.put("fecha_pedido", pedi.getFechaPed());
            values.put("estado", pedi.getEstado());
            values.put("total", Double.parseDouble(pedi.getTotal())); // Asegurarse de que el total sea un número

            long newRowId = database.insert(tbPedido, null, values);

            if (newRowId == -1) {
                Log.e("cPedido", "Error al insertar el pedido");
                throw new RuntimeException("Error al insertar el pedido");
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al insertar el pedido", e);
            throw new RuntimeException("Error al insertar el pedido: " + e.getMessage());
        }
    }

    public void update(Pedido dato) {
        conexion con = new cPedido(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            database.execSQL("update " + tbPedido + " set(' " +
                    "codigo_ped" + dato.getCodigo_p() + "','" +
                    "codigo_usu" + dato.getCodigo_u() + "','" +
                    "codigo_prod" + dato.getCodigo_prod() + "','" +
                    "fecha_pedido" + dato.getFechaPed() + "','" +
                    "estado" + dato.getEstado() + "','" +
                    "total" + dato.getTotal() + "','");
            database.close();
        }
    }

    public void delete(Pedido dato) {
        conexion con = new cPedido(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            database.execSQL("delete from " + tbPedido + " where " +
                    "codigo='" + dato.getCodigo_p() + "'" );
            database.close();
        }
    }

    public ArrayList<Pedido> Select() {
        conexion con = new cPedido(context);
        SQLiteDatabase database = con.getWritableDatabase();
        ArrayList<Pedido> est = new ArrayList<>();
        Cursor dato = null;
        dato = database.rawQuery("select * from " + tbPedido, null);
        if (dato.moveToFirst()) {
            do {
                est.add(new Pedido(
                                Integer.parseInt(dato.getString(0)),
                                Integer.parseInt(dato.getString(1)),
                                Integer.parseInt(dato.getString(2)),
                                dato.getString(3),
                                dato.getString(4),
                                dato.getString(5)
                        )
                );
            } while (dato.moveToNext());
        }
        return est;
    }

    // Método para obtener las tarjetas por el código del usuario
    public List<Pedido> obtenerPedidosPorUsuario(int codigoUsuario) {
        List<Pedido> pedidos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta SQL para obtener las tarjetas del usuario
        String query = "SELECT * FROM " + tbPedido + " WHERE codigo_usuario = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(codigoUsuario)});
        Log.d("cPedido", "Consultando con codigo_usuario: " + codigoUsuario);
        if (cursor.moveToFirst()) {
            do {
                // Crear un objeto Tarjeta y configurarlo con los datos de la base de datos
                Pedido pedido = new Pedido();
                pedido.setCodigo_p(cursor.getInt(cursor.getColumnIndexOrThrow("codigo_pedido")));
                pedido.setCodigo_u(cursor.getInt(cursor.getColumnIndexOrThrow("codigo_usuario")));
                pedido.setCodigo_prod(cursor.getInt(cursor.getColumnIndexOrThrow("codigo_producto")));
                pedido.setFechaPed(cursor.getString(cursor.getColumnIndexOrThrow("fecha_pedido")));
                pedido.setEstado(cursor.getString(cursor.getColumnIndexOrThrow("estado")));
                pedido.setTotal(cursor.getString(cursor.getColumnIndexOrThrow("total")));

                // Agregar el objeto Tarjeta a la lista
                pedidos.add(pedido);
            } while (cursor.moveToNext());
        }
        // Log para ver cuántas tarjetas se encontraron
        Log.d("cPedido", "Número de pedidos encontrados: " + pedidos.size());
        cursor.close();
        db.close();

        return pedidos;
    }
}
