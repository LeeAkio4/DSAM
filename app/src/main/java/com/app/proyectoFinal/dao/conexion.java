package com.app.proyectoFinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class conexion extends SQLiteOpenHelper {
    private static final int dbVersion=2;
    private static final String dbNombre= "dbAutonex.db";
    public static final String tbUsuario= "usuario";
    public static final String tbTarjeta= "tarjeta";
    public static final String tbPedido= "pedido";
    public static final String tbProducto= "producto";

    public conexion(@Nullable Context context){
        super(context, dbNombre, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tbUsuario + "(codigoUser INTEGER PRIMARY KEY AUTOINCREMENT, nombrescompletos TEXT NOT NULL, direccion TEXT NOT NULL, dni INTEGER NOT NULL, genero TEXT NOT NULL, correo TEXT NOT NULL, contrasena TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + tbTarjeta + " (codigo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, nombre TEXT NOT NULL, correo TEXT NOT NULL, numero_tarjeta INTEGER NOT NULL,fecha_cad TEXT NOT NULL, cvv TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbPedido + " (codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, codigo_producto INTEGER NOT NULL, fecha_pedido TEXT NOT NULL, estado TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario), FOREIGN KEY (codigo_producto) REFERENCES " + tbProducto + "(codigo_producto))");
        db.execSQL("CREATE TABLE " + tbProducto + " (codigo_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL, stock INTEGER NOT NULL)");
        insertarProductos(db);
        insertarUsuarios(db);
    }

    private void insertarUsuarios(SQLiteDatabase db1){
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('admin', 'admin', '00000001', 'admin', 'admin', 'admin')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Sebastian Ernesto Bedon Oscco', 'Av. BolasLLenas', '69697777', 'Masculino', 'sebastian@gmail.com', '123456')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Lee Akio Bruno Mauricio Taboada', 'Av. Algo', '69697777', 'Masculino', 'lee@gmail.com', '123456')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Mario Cesar Silva Salcedo', 'Av. Chorrillos', '71589648', 'Masculino', 'mario@gmail.com', '123456')");
    }

    public boolean verificarLogin(String correo, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tbUsuario + " WHERE correo = ? AND contrasena = ?", new String[]{correo, contrasena});

        boolean existeUsuario = cursor.getCount() > 0;  // Si el cursor tiene resultados, significa que el usuario existe.
        cursor.close();
        db.close();

        return existeUsuario;
    }

    public boolean registrarse(String nomcom, String direc, String dni, String gener, String corr, String contra) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Primero, verifica si el correo ya está registrado
        Cursor cursor = db.rawQuery("SELECT * FROM " + tbUsuario + " WHERE correo = ?", new String[]{corr});
        boolean existeUsuario = cursor.getCount() > 0;  // Si el cursor tiene resultados, significa que el correo ya está registrado.

        if (!existeUsuario) {
            // Si el usuario no existe, procede a registrar
            ContentValues values = new ContentValues();
            values.put("nombrescompletos", nomcom);
            values.put("direccion", direc);
            values.put("dni", dni);
            values.put("genero", gener);
            values.put("correo", corr);
            values.put("contrasena", contra);

            // Inserta el nuevo registro
            long result = db.insert(tbUsuario, null, values);
            cursor.close();
            db.close();

            // Devuelve true si la inserción fue exitosa (result > 0)
            return result > 0;
        }

        cursor.close();
        db.close();
        return false; // Retorna false si el usuario ya existe
    }

    private void insertarProductos(SQLiteDatabase db) {
        // Productos de KIA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('KIA Rio', 'KIA', 'Sedán compacto con motor 1.6L', 15000, 10)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('KIA Sportage', 'KIA', 'SUV con motor 2.0L', 22000, 15)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('KIA Sorento', 'KIA', 'SUV familiar con motor 2.4L', 28000, 8)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('KIA Picanto', 'KIA', 'City car compacto', 12000, 20)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('KIA Seltos', 'KIA', 'SUV subcompacto', 21000, 12)");

        // Productos de TOYOTA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Toyota Corolla', 'TOYOTA', 'Sedán con motor 1.8L', 18000, 14)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Toyota RAV4', 'TOYOTA', 'SUV con motor híbrido', 30000, 7)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Toyota Hilux', 'TOYOTA', 'Pickup con motor 2.8L turbo diesel', 35000, 5)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Toyota Prius', 'TOYOTA', 'Híbrido con tecnología avanzada', 25000, 9)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Toyota Yaris', 'TOYOTA', 'Hatchback compacto', 16000, 13)");

        // Productos de TESLA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Tesla Model S', 'TESLA', 'Sedán eléctrico de lujo', 75000, 3)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Tesla Model 3', 'TESLA', 'Sedán eléctrico compacto', 45000, 6)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Tesla Model X', 'TESLA', 'SUV eléctrico de lujo', 90000, 2)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Tesla Model Y', 'TESLA', 'SUV eléctrico compacto', 60000, 4)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Tesla Cybertruck', 'TESLA', 'Pickup eléctrico futurista', 70000, 5)");

        // Productos de NISSAN
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Nissan Sentra', 'NISSAN', 'Sedán con motor 2.0L', 19000, 11)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Nissan Rogue', 'NISSAN', 'SUV con motor 2.5L', 25000, 6)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Nissan Frontier', 'NISSAN', 'Pickup con motor 2.5L', 28000, 8)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Nissan Leaf', 'NISSAN', 'Hatchback eléctrico', 32000, 4)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Nissan Kicks', 'NISSAN', 'SUV subcompacto', 21000, 9)");

        // Productos de SUZUKI
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Suzuki Swift', 'SUZUKI', 'Hatchback subcompacto', 14000, 10)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Suzuki Vitara', 'SUZUKI', 'SUV compacto', 23000, 12)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Suzuki Jimny', 'SUZUKI', 'Off-roader compacto', 18000, 8)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Suzuki S-Cross', 'SUZUKI', 'SUV crossover', 22000, 9)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Suzuki Baleno', 'SUZUKI', 'Hatchback mediano', 15000, 11)");

        // Productos de VOLKS
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Volkswagen Golf', 'VOLKS', 'Hatchback compacto', 20000, 14)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Volkswagen Passat', 'VOLKS', 'Sedán mediano', 25000, 7)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Volkswagen Tiguan', 'VOLKS', 'SUV compacto', 28000, 5)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Volkswagen Jetta', 'VOLKS', 'Sedán compacto', 22000, 10)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock) VALUES ('Volkswagen Touareg', 'VOLKS', 'SUV de lujo', 45000, 4)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbUsuario);
        db.execSQL("DROP TABLE IF EXISTS " + tbTarjeta);
        db.execSQL("DROP TABLE IF EXISTS " + tbPedido);
        db.execSQL("DROP TABLE IF EXISTS " + tbProducto);
        onCreate(db);  // Recrear las tablas
    }

}
