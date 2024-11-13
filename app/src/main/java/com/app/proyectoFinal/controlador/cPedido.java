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

    // Método para insertar un nuevo pedido
    public void insert(Pedido pedido) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            Log.d("cPedido", "Insertando pedido: codigo_usuario=" + pedido.getCodigo_u() + ", codigo_producto=" + pedido.getCodigo_prod());

            ContentValues values = new ContentValues();
            values.put("codigo_usuario", pedido.getCodigo_u());
            values.put("codigo_producto", pedido.getCodigo_prod());
            values.put("fecha_pedido", pedido.getFechaPed());
            values.put("estado", pedido.getEstado());
            values.put("total", Double.parseDouble(pedido.getTotal()));

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

    // Método para actualizar un pedido existente
    public void update(Pedido pedido) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("codigo_usuario", pedido.getCodigo_u());
            values.put("codigo_producto", pedido.getCodigo_prod());
            values.put("fecha_pedido", pedido.getFechaPed());
            values.put("estado", pedido.getEstado());
            values.put("total", Double.parseDouble(pedido.getTotal()));

            int rowsAffected = database.update(
                    tbPedido, values, "codigo_pedido = ?", new String[]{String.valueOf(pedido.getCodigo_p())}
            );

            if (rowsAffected == 0) {
                Log.e("cPedido", "No se encontró el pedido para actualizar");
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al actualizar el pedido", e);
            throw new RuntimeException("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    // Método para eliminar un pedido
    public void delete(int codigoPedido) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            int rowsDeleted = database.delete(
                    tbPedido, "codigo_pedido = ?", new String[]{String.valueOf(codigoPedido)}
            );

            if (rowsDeleted == 0) {
                Log.e("cPedido", "No se encontró el pedido para eliminar");
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al eliminar el pedido", e);
            throw new RuntimeException("Error al eliminar el pedido: " + e.getMessage());
        }
    }


    // Método para obtener todos los pedidos
    public ArrayList<Pedido> Select() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        try (SQLiteDatabase database = getReadableDatabase();
             Cursor cursor = database.rawQuery("SELECT * FROM " + tbPedido, null)) {

            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido(
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_pedido")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_usuario")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_producto")),
                            cursor.getString(cursor.getColumnIndexOrThrow("fecha_pedido")),
                            cursor.getString(cursor.getColumnIndexOrThrow("estado")),
                            cursor.getString(cursor.getColumnIndexOrThrow("total"))
                    );
                    listaPedidos.add(pedido);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al obtener los pedidos", e);
            throw new RuntimeException("Error al obtener los pedidos: " + e.getMessage());
        }
        return listaPedidos;
    }

    // Método para obtener pedidos por el código de usuario
    public List<Pedido> obtenerPedidosPorUsuario(int codigoUsuario) {
        List<Pedido> pedidos = new ArrayList<>();
        try (SQLiteDatabase db = getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM " + tbPedido + " WHERE codigo_usuario = ?", new String[]{String.valueOf(codigoUsuario)})) {

            Log.d("cPedido", "Consultando con codigo_usuario: " + codigoUsuario);
            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido(
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_pedido")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_usuario")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("codigo_producto")),
                            cursor.getString(cursor.getColumnIndexOrThrow("fecha_pedido")),
                            cursor.getString(cursor.getColumnIndexOrThrow("estado")),
                            cursor.getString(cursor.getColumnIndexOrThrow("total"))
                    );
                    pedidos.add(pedido);
                } while (cursor.moveToNext());
            }
            Log.d("cPedido", "Número de pedidos encontrados: " + pedidos.size());
        } catch (Exception e) {
            Log.e("cPedido", "Error al obtener pedidos por usuario", e);
            throw new RuntimeException("Error al obtener pedidos por usuario: " + e.getMessage());
        }
        return pedidos;
    }

    public boolean actualizarEstadoPedido(int codigoPedido, String nuevoEstado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("estado", nuevoEstado);  // Establecer el nuevo estado

        int rowsAffected = db.update(
                "pedido",  // Nombre de la tabla
                values,  // Valores a actualizar
                "codigo_pedido = ?",  // Condición para seleccionar el pedido
                new String[]{String.valueOf(codigoPedido)}  // El código del pedido como parámetro
        );

        db.close();
        return rowsAffected > 0;  // Retorna true si al menos una fila fue actualizada
    }

}
