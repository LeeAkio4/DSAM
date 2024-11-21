package com.app.proyectoFinal.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;
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
        SQLiteDatabase db = null;
        try {
            // Abrir la base de datos en modo escritura
            db = this.getWritableDatabase();

            // Verificar si la base de datos está abierta antes de intentar realizar operaciones
            if (db.isOpen()) {
                // Obtener el pedido a actualizar
                Pedido pedido = obtenerPedidoPorId(codigoPedido);
                if (pedido == null) {
                    Log.e("cPedido", "Pedido no encontrado para código: " + codigoPedido);
                    return false;
                }

                // Obtener el producto asociado con el pedido
                cProducto productoController = new cProducto(context);
                Producto producto = productoController.obtenerProductoPorCodigo(pedido.getCodigo_prod());

                if (producto == null) {
                    Log.e("cPedido", "Producto no encontrado para código: " + pedido.getCodigo_prod());
                    return false;
                }

                // Verificar stock y actualizar si es necesario
                int stockActual = producto.getStock();
                if (stockActual > 0) {
                    // Actualizar el stock
                    producto.setStock(stockActual - 1);
                    ContentValues values = new ContentValues();
                    values.put("stock", producto.getStock());
                    db.update("producto", values, "codigo_prod = ?", new String[]{String.valueOf(producto.getCodigo_prod())});
                } else {
                    Log.d("cPedido", "Stock insuficiente, no se actualiza el producto");
                }

                // Actualizar el estado del pedido
                ContentValues pedidoValues = new ContentValues();
                pedidoValues.put("estado", nuevoEstado);
                db.update("pedido", pedidoValues, "codigo_pedido = ?", new String[]{String.valueOf(codigoPedido)});

                return true;  // Si todo salió bien
            } else {
                Log.e("cPedido", "Base de datos no abierta");
                return false;
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al actualizar el pedido", e);
            return false;
        }
    }



    // Método para obtener pedidos filtrados por estado
    public List<Pedido> obtenerPedidosPorEstado(String estado) {
        List<Pedido> pedidos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();  // Abrir la base de datos de forma controlada
            cursor = db.rawQuery("SELECT * FROM " + tbPedido + " WHERE estado = ?", new String[]{estado});

            Log.d("cPedido", "Consultando con estado: " + estado);
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
            Log.d("cPedido", "Número de pedidos encontrados con estado '" + estado + "': " + pedidos.size());
        } catch (Exception e) {
            Log.e("cPedido", "Error al obtener pedidos por estado", e);
        }
        return pedidos;
    }

    // Método para obtener un pedido por el código de pedido (ID)
    public Pedido obtenerPedidoPorId(int codigoPedido) {
        Pedido pedido = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();  // Abrir la base de datos de forma controlada
            cursor = db.rawQuery("SELECT * FROM " + tbPedido + " WHERE codigo_pedido = ?", new String[]{String.valueOf(codigoPedido)});

            Log.d("cPedido", "Consultando con codigo_pedido: " + codigoPedido);
            if (cursor.moveToFirst()) {
                pedido = new Pedido(
                        cursor.getInt(cursor.getColumnIndexOrThrow("codigo_pedido")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("codigo_usuario")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("codigo_producto")),
                        cursor.getString(cursor.getColumnIndexOrThrow("fecha_pedido")),
                        cursor.getString(cursor.getColumnIndexOrThrow("estado")),
                        cursor.getString(cursor.getColumnIndexOrThrow("total"))
                );
            } else {
                Log.d("cPedido", "No se encontró un pedido con el código: " + codigoPedido);
            }
        } catch (Exception e) {
            Log.e("cPedido", "Error al obtener el pedido por ID", e);
        }
        return pedido;
    }

}
