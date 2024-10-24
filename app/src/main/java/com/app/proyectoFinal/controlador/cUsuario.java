package com.app.proyectoFinal.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.dao.conexion;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;

public class cUsuario extends conexion{
    Context context;
    public cUsuario(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void insert(Usuario dato) {
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "INSERT INTO " + tbUsuario + " (codigo, nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindLong(1, dato.getCodigo());
        stmt.bindString(2, dato.getNombrescompletos());
        stmt.bindString(3, dato.getDireccion());
        stmt.bindLong(4, dato.getDni());
        stmt.bindString(5, dato.getGenero());
        stmt.bindString(6, dato.getCorreo());
        stmt.bindString(7, dato.getContrasena());
        stmt.executeInsert();
        database.close();
    }


    public void update(Usuario dato) {
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "UPDATE " + tbUsuario + " SET nombrescompletos = ?, direccion = ?, dni = ?, genero = ?, correo = ?, contrasena = ? WHERE codigo = ?";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, dato.getNombrescompletos());
        stmt.bindString(2, dato.getDireccion());
        stmt.bindLong(3, dato.getDni());
        stmt.bindString(4, dato.getGenero());
        stmt.bindString(5, dato.getCorreo());
        stmt.bindString(6, dato.getContrasena());
        stmt.bindLong(7, dato.getCodigo());
        stmt.executeUpdateDelete();
        database.close();
    }


    public void delete(Usuario dato) {
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "DELETE FROM " + tbUsuario + " WHERE codigo = ?";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindLong(1, dato.getCodigo());
        stmt.executeUpdateDelete();
        database.close();
    }


    public ArrayList<Usuario> Select(){
        conexion con= new cUsuario(context);
        SQLiteDatabase database=con.getWritableDatabase();
        ArrayList<Usuario> est= new ArrayList<>();
        Cursor dato=null;
        dato=database.rawQuery("select * from "+ tbUsuario,null);
        if (dato.moveToFirst()){
            do {
                est.add(new Usuario(
                                Integer.parseInt(dato.getString(0)),
                                dato.getString(1),
                                dato.getString(2),
                                Integer.parseInt(dato.getString(3)),
                                dato.getString(4),
                                dato.getString(5),
                                dato.getString(6)
                        )
                );
            }while (dato.moveToNext());
        }
        return est;
    }

    public Usuario getUsuarioPorCodigo(int codigo) {
        conexion con = new cUsuario(context);
        SQLiteDatabase database = con.getReadableDatabase();
        Usuario usuario = null;
        Cursor dato = null;

        dato = database.rawQuery("SELECT * FROM " + tbUsuario + " WHERE codigoUser = ?", new String[]{String.valueOf(codigo)});

        if (dato.moveToFirst()) {
            usuario = new Usuario(
                    Integer.parseInt(dato.getString(0)),
                    dato.getString(1),
                    dato.getString(2),
                    Integer.parseInt(dato.getString(3)),
                    dato.getString(4),
                    dato.getString(5),
                    dato.getString(6)
            );
        }

        if (dato != null) {
            dato.close();
        }
        database.close();

        return usuario;
    }

    public Usuario ValidarUsuario(String email, String password) {
        conexion con = new cUsuario(context);
        SQLiteDatabase database = con.getReadableDatabase();
        Usuario usuario = null;

        Cursor dato = database.rawQuery("SELECT * FROM " + tbUsuario + " WHERE correo = ? AND contrasena = ?", new String[]{email, password});

        if (dato.moveToFirst()) {
            usuario = new Usuario(
                    Integer.parseInt(dato.getString(0)),
                    dato.getString(1),
                    dato.getString(2),
                    Integer.parseInt(dato.getString(3)),
                    dato.getString(4),
                    dato.getString(5),
                    dato.getString(6)
            );
        }

        if (dato != null) {
            dato.close();
        }
        database.close();

        return usuario;
    }
}

