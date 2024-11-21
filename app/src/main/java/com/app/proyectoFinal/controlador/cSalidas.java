package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Salidas;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class cSalidas extends conexion {
    Context context;

    public cSalidas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Método para insertar una salida
    public void insert(Salidas salida) {
        conexion con = new cSalidas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                String sql = "INSERT INTO " + tbSalidas+ "(idPedido, cantidad, precioUnitario, totalSalida, fechaSalida) VALUES (" +
                        salida.getIdPedido() + ", " +
                        salida.getCantidad() + ", " +
                        salida.getPrecioUnitario() + ", " +
                        salida.getTotalSalida() + ", '" +
                        new java.sql.Date(salida.getFechaSalida().getTime()) + "');";
                database.execSQL(sql);
                Log.d("cSalidas", "Salida insertada: " + sql);
            } catch (Exception e) {
                Log.e("cSalidas", "Error al insertar salida: ", e);
            } finally {
                database.close();
            }
        }
    }

    // Método para actualizar una salida
    public void update(Salidas salida) {
        conexion con = new cSalidas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                database.execSQL("UPDATE tbSalidas SET " +
                        "idPedido = " + salida.getIdPedido() + ", " +
                        "cantidad = " + salida.getCantidad() + ", " +
                        "precioUnitario = " + salida.getPrecioUnitario() + ", " +
                        "totalSalida = " + salida.getTotalSalida() + ", " +
                        "fechaSalida = '" + new java.sql.Date(salida.getFechaSalida().getTime()) + "' " +
                        "WHERE idSalida = " + salida.getIdSalida() + ";");
            } catch (Exception e) {
                Log.e("cSalidas", "Error al actualizar salida", e);
            } finally {
                database.close();
            }
        }
    }

    // Método para eliminar una salida
    public void delete(int idSalida) {
        conexion con = new cSalidas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                database.execSQL("DELETE FROM " + tbSalidas+ " WHERE idSalida = " + idSalida + ";");
            } catch (Exception e) {
                Log.e("cSalidas", "Error al eliminar salida", e);
            } finally {
                database.close();
            }
        }
    }

    // Método para obtener todas las salidas
    public ArrayList<Salidas> select() {
        ArrayList<Salidas> salidasList = new ArrayList<>();
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            conexion con = new cSalidas(context);
            database = con.getReadableDatabase();
            cursor = database.rawQuery("SELECT idSalida, idPedido, cantidad, precioUnitario, totalSalida, fechaSalida FROM " + tbSalidas, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String fechaString = cursor.getString(5);

                    if (fechaString != null && fechaString.matches("\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2})?")) {
                        if (fechaString.length() == 10) {
                            fechaString += " 00:00:00";
                        }
                        Timestamp fechaSalida = Timestamp.valueOf(fechaString);
                        salidasList.add(new Salidas(
                                cursor.getInt(0), // idSalida
                                cursor.getInt(1), // idPedido
                                cursor.getInt(2), // cantidad
                                cursor.getDouble(3), // precioUnitario
                                cursor.getDouble(4), // totalSalida
                                fechaSalida // fechaSalida
                        ));
                    } else {
                        Log.e("cSalidas", "Formato de fecha incorrecto: " + fechaString);
                    }

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("cSalidas", "Error al obtener las salidas", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return salidasList;
    }

    public void cargarPedidosPagadosEnSalidas() {
        // Crear una instancia de cPedido para obtener los pedidos con estado "Pagado"
        cPedido controladorPedido = new cPedido(context);

        // Obtener todos los pedidos con estado "Pagado"
        List<Pedido> pedidosPagados = controladorPedido.obtenerPedidosPorEstado("Pagado");

        // Verificar si hay pedidos "Pagados" y cargarlos en la tabla Salidas
        for (Pedido pedido : pedidosPagados) {
            try {
                // Intentar convertir el total de String a Double
                Double total = Double.parseDouble(pedido.getTotal());

                // Verificar si ya existe una salida con el mismo idPedido
                if (!existeSalidaConIdPedido(pedido.getCodigo_p())) {
                    // Crear el objeto Salidas, asumiendo que en este caso se toma el total del pedido como total de salida
                    Salidas salida = new Salidas(
                            0,
                            pedido.getCodigo_p(),
                            1,
                            total,  // precioUnitario
                            total,  // totalSalida
                            new Timestamp(System.currentTimeMillis())  // fechaSalida (ahora)
                    );

                    // Insertar la salida en la base de datos
                    insert(salida);  // Utiliza el método insert que ya tienes en cSalidas
                } else {
                    Log.d("cSalidas", "Ya existe una salida con el idPedido: " + pedido.getCodigo_p());
                }

            } catch (NumberFormatException e) {
                // Manejar el error en caso de que no se pueda convertir el String a Double
                Log.e("cPedido", "Error al convertir el total del pedido a Double: " + pedido.getTotal(), e);
            }
        }
    }
    private boolean existeSalidaConIdPedido(int idPedido) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        boolean existe = false;
        try {
            conexion con = new cSalidas(context);
            database = con.getReadableDatabase();
            String query = "SELECT COUNT(*) FROM " + tbSalidas + " WHERE idPedido = ?";
            cursor = database.rawQuery(query, new String[]{String.valueOf(idPedido)});

            if (cursor != null && cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                existe = count > 0;
            }
        } catch (Exception e) {
            Log.e("cSalidas", "Error al verificar existencia de salida con idPedido: " + idPedido, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return existe;
    }


}
