package com.app.proyectoFinal.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Tarjeta;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class cTarjeta extends conexion{
    Context context;
    public cTarjeta(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Tarjeta dato) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("codigo_usuario", dato.getCodigo_u());
            values.put("nombre", dato.getNombre());
            values.put("correo", dato.getCorreo());
            values.put("numero_tarjeta", dato.getNumeroTar());
            values.put("fecha_cad", dato.getFechacad());
            values.put("cvv", dato.getCvv());

            long newRowId = database.insert(tbTarjeta, null, values);
            if (newRowId == -1) {
                Log.e("cTarjeta", "Error al insertar la tarjeta");
                throw new RuntimeException("Error al insertar la tarjeta");
            }
        } catch (Exception e) {
            Log.e("cTarjeta", "Error al insertar la tarjeta", e);
            throw new RuntimeException("Error al insertar la tarjeta: " + e.getMessage());
        }
    }

    public void update(Tarjeta dato) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("codigo_usuario", dato.getCodigo_u());
        values.put("nombre", dato.getNombre());
        values.put("correo", dato.getCorreo());
        values.put("numero_tarjeta", dato.getNumeroTar());
        values.put("fecha_cad", dato.getFechacad());
        values.put("cvv", dato.getCvv());

        int rowsAffected = database.update(tbTarjeta, values, "codigo_t = ?", new String[]{String.valueOf(dato.getCodigo_t())});
        if (rowsAffected == 0) {
            throw new RuntimeException("Error al actualizar la tarjeta, no se encontró.");
        }
        database.close();
    }


    // Eliminar Tarjeta
    public void delete(Tarjeta dato) {
        try (SQLiteDatabase database = getWritableDatabase()) {
            int rowsDeleted = database.delete(tbTarjeta, "codigo_t = ?", new String[]{String.valueOf(dato.getCodigo_t())});
            if (rowsDeleted == 0) {
                throw new RuntimeException("Error al eliminar la tarjeta, no se encontró.");
            }
        } catch (Exception e) {
            Log.e("cTarjeta", "Error al eliminar la tarjeta: " + e.getMessage());
        }
    }

    public ArrayList<Tarjeta> Select() {
        ArrayList<Tarjeta> est = new ArrayList<>();
        try (SQLiteDatabase database = getWritableDatabase();
             Cursor dato = database.rawQuery("SELECT * FROM " + tbTarjeta, null)) {
            if (dato.moveToFirst()) {
                do {
                    est.add(new Tarjeta(
                            Integer.parseInt(dato.getString(0)),
                            Integer.parseInt(dato.getString(1)),
                            dato.getString(2),
                            dato.getString(3),
                            Long.parseLong(dato.getString(4)),
                            dato.getString(5),
                            dato.getString(6)
                    ));
                } while (dato.moveToNext());
            }
        } catch (Exception e) {
            Log.e("cTarjeta", "Error al seleccionar las tarjetas: " + e.getMessage());
        }
        return est;
    }


    public List<Tarjeta> obtenerTarjetasPorUsuario(int codigoUsuario) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Agregar log para verificar el código del usuario
        Log.d("cTarjeta", "Consultando con codigo_usuario: " + codigoUsuario);

        // Consulta SQL para obtener las tarjetas del usuario
        String query = "SELECT * FROM " + tbTarjeta + " WHERE codigo_usuario = ?";

        // Agregar log para ver la consulta SQL que se está ejecutando
        Log.d("cTarjeta", "Consulta SQL: " + query);  // Ver la consulta generada

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(codigoUsuario)});

        if (cursor.moveToFirst()) {
            do {
                // Crear un objeto Tarjeta y configurarlo con los datos de la base de datos
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setCodigo_t(cursor.getInt(cursor.getColumnIndexOrThrow("codigo_tarjeta")));
                tarjeta.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                tarjeta.setNumeroTar(cursor.getInt(cursor.getColumnIndexOrThrow("numero_tarjeta")));
                tarjeta.setFechacad(cursor.getString(cursor.getColumnIndexOrThrow("fecha_cad")));

                // Agregar el objeto Tarjeta a la lista
                tarjetas.add(tarjeta);
            } while (cursor.moveToNext());
        }

        // Log para ver cuántas tarjetas se encontraron
        Log.d("cTarjeta", "Número de tarjetas encontradas: " + tarjetas.size());
        cursor.close();
        db.close();

        return tarjetas;
    }


}
