package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Entradas;

import java.sql.Timestamp;

import java.util.ArrayList;

public class cEntradas extends conexion {
    Context context;

    public cEntradas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Método para insertar una entrada
    public void insert(Entradas entrada) {
        conexion con = new cEntradas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                // Modificar la sentencia SQL para no incluir idEntrada, ya que es autoincrementable
                String sql = "INSERT INTO " + tbEntradas + "(idProducto, cantidad, costoUnitario, totalEntrada, fechaEntrada) VALUES (" +
                        entrada.getIdProducto() + ", " +
                        entrada.getCantidad() + ", " +
                        entrada.getCostoUnitario() + ", " +
                        entrada.getTotalEntrada() + ", '" +
                        new java.sql.Date(entrada.getFechaEntrada().getTime()) + "');";
                database.execSQL(sql);  // Ejecutar la consulta SQL
                Log.d("cEntradas", "Entrada insertada: " + sql);
            } catch (Exception e) {
                Log.e("cEntradas", "Error al insertar entrada: ", e);
            } finally {
                database.close();
            }
        }
    }



    // Método para actualizar una entrada
    public void update(Entradas entrada) {
        conexion con = new cEntradas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                database.execSQL("UPDATE tbEntradas SET " +
                        "idProducto = " + entrada.getIdProducto() + ", " +
                        "cantidad = " + entrada.getCantidad() + ", " +
                        "costoUnitario = " + entrada.getCostoUnitario() + ", " +
                        "totalEntrada = " + entrada.getTotalEntrada() + ", " +
                        "fechaEntrada = '" + new java.sql.Date(entrada.getFechaEntrada().getTime()) + "' " +
                        "WHERE idEntrada = " + entrada.getIdEntrada() + ";");
            } catch (Exception e) {
                Log.e("cEntradas", "Error al actualizar entrada", e);
            } finally {
                database.close();
            }
        }
    }

    // Método para eliminar una entrada
    public void delete(int idEntrada) {
        conexion con = new cEntradas(context);
        SQLiteDatabase database = con.getWritableDatabase();
        if (database != null) {
            try {
                database.execSQL("DELETE FROM" + tbEntradas + " WHERE idEntrada = " + idEntrada + ";");
            } catch (Exception e) {
                Log.e("cEntradas", "Error al eliminar entrada", e);
            } finally {
                database.close();
            }
        }
    }

    public ArrayList<Entradas> select() {
        ArrayList<Entradas> entradasList = new ArrayList<>();
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            // Abre la base de datos para lectura
            conexion con = new cEntradas(context);
            database = con.getReadableDatabase();  // O usa getWritableDatabase() si es necesario
            cursor = database.rawQuery("SELECT idEntrada, idProducto, cantidad, costoUnitario, totalEntrada, fechaEntrada FROM " + tbEntradas, null);

            if (cursor != null) {  // Verificar si el cursor no es nulo antes de comenzar la iteración
                if (cursor.moveToFirst()) {  // Mover al primer registro
                    do {
                        String fechaString = cursor.getString(5); // fechaEntrada como String

                        // Verifica que la fecha esté en el formato correcto
                        if (fechaString != null && fechaString.matches("\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2})?")) {
                            // Si la fecha está en el formato correcto, conviértela a Timestamp
                            if (fechaString.length() == 10) { // Si solo contiene la fecha sin la hora
                                fechaString += " 00:00:00"; // Añadimos la hora predeterminada
                            }
                            Timestamp fechaEntrada = Timestamp.valueOf(fechaString);
                            entradasList.add(new Entradas(
                                    cursor.getInt(0), // idEntrada
                                    cursor.getInt(1), // idProducto
                                    cursor.getInt(2), // cantidad
                                    cursor.getDouble(3), // costoUnitario
                                    cursor.getDouble(4), // totalEntrada
                                    fechaEntrada // fechaEntrada
                            ));
                        } else {
                            Log.e("cEntradas", "Formato de fecha incorrecto: " + fechaString);
                        }

                    } while (cursor.moveToNext()); // Se sigue avanzando en los registros
                }
            }
        } catch (Exception e) {
            Log.e("cEntradas", "Error al obtener las entradas", e);
        } finally {
            if (cursor != null) {
                cursor.close();  // Cerrar el cursor después de usarlo
            }
            if (database != null) {
                database.close();  // Cerrar la base de datos después de usarla
            }
        }
        return entradasList;
    }

    // Método para obtener una entrada por ID
    public Entradas obtenerEntradaPorId(int idEntrada) {
        conexion con = new cEntradas(context);
        SQLiteDatabase database = con.getReadableDatabase();
        Entradas entrada = null;
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("SELECT idEntrada, idProducto, cantidad, costoUnitario, totalEntrada, fechaEntrada FROM" + tbEntradas + " WHERE idEntrada = ?", new String[]{String.valueOf(idEntrada)});

            if (cursor != null && cursor.moveToFirst()) {
                entrada = new Entradas(
                        cursor.getInt(0), // idEntrada
                        cursor.getInt(1), // idProducto
                        cursor.getInt(2), // cantidad
                        cursor.getDouble(3), // costoUnitario
                        cursor.getDouble(4), // totalEntrada
                        Timestamp.valueOf(cursor.getString(5)) // fechaEntrada
                );
            }
        } catch (Exception e) {
            Log.e("cEntradas", "Error al obtener entrada por ID", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return entrada;
    }
}

