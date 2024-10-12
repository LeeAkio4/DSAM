package com.app.proyectoFinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexion extends SQLiteOpenHelper {
    private static final int dbVersion=1;
    private static final String dbNombre= "dbAutonex.db";
    public static final String tbUsuario= "usuario";
    public static final String tbTarjeta= "tarjeta";
    public static final String tbPerfil= "perfil";
    public static final String tbPedido= "pedido";
    public static final String tbProducto= "producto";

    public conexion(@Nullable Context context){
        super(context, dbNombre, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tbUsuario + "(codigoUser INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, apellido TEXT NOT NULL, celular TEXT NOT NULL, correo TEXT NOT NULL, contrasena TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + tbTarjeta + " (codigo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, nombre TEXT NOT NULL, correo TEXT NOT NULL, fecha_cad TEXT NOT NULL, cvv TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbPerfil + " (codigo_perfil INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, genero TEXT NOT NULL, fecha_ven TEXT NOT NULL, direccion TEXT NOT NULL, nro_dni TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbPedido + " (codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, fecha_pedido TEXT NOT NULL, estado TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbProducto + " (codigo_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+tbUsuario);
        onCreate(db);
    }
}
