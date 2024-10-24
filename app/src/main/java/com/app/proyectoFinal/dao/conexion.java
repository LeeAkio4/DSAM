package com.app.proyectoFinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexion extends SQLiteOpenHelper {
    private static final int dbVersion=1;
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
        db.execSQL("CREATE TABLE " + tbTarjeta + " (codigo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, nombre TEXT NOT NULL, correo TEXT NOT NULL, fecha_cad TEXT NOT NULL, cvv TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbPedido + " (codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, fecha_pedido TEXT NOT NULL, estado TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigo_usuario))");
        db.execSQL("CREATE TABLE " + tbProducto + " (codigo_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL)");
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
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('KIA Rio', 'KIA', 'Sedán compacto con motor 1.6L', 15000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('KIA Sportage', 'KIA', 'SUV con motor 2.0L', 22000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('KIA Sorento', 'KIA', 'SUV familiar con motor 2.4L', 28000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('KIA Picanto', 'KIA', 'City car compacto', 12000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('KIA Seltos', 'KIA', 'SUV subcompacto', 21000)");

        // Productos de TOYOTA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Toyota Corolla', 'TOYOTA', 'Sedán con motor 1.8L', 18000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Toyota RAV4', 'TOYOTA', 'SUV con motor híbrido', 30000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Toyota Hilux', 'TOYOTA', 'Pickup con motor 2.8L turbo diesel', 35000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Toyota Prius', 'TOYOTA', 'Híbrido con tecnología avanzada', 25000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Toyota Yaris', 'TOYOTA', 'Hatchback compacto', 16000)");

        // Productos de TESLA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Tesla Model S', 'TESLA', 'Sedán eléctrico de lujo', 75000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Tesla Model 3', 'TESLA', 'Sedán eléctrico compacto', 45000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Tesla Model X', 'TESLA', 'SUV eléctrico de lujo', 90000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Tesla Model Y', 'TESLA', 'SUV eléctrico compacto', 60000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Tesla Cybertruck', 'TESLA', 'Pickup eléctrico futurista', 70000)");

        // Productos de NISSAN
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Nissan Sentra', 'NISSAN', 'Sedán con motor 2.0L', 19000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Nissan Rogue', 'NISSAN', 'SUV con motor 2.5L', 25000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Nissan Frontier', 'NISSAN', 'Pickup con motor 2.5L', 28000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Nissan Leaf', 'NISSAN', 'Hatchback eléctrico', 32000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Nissan Kicks', 'NISSAN', 'SUV subcompacto', 21000)");

        // Productos de SUZUKI
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Suzuki Swift', 'SUZUKI', 'Hatchback subcompacto', 14000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Suzuki Vitara', 'SUZUKI', 'SUV compacto', 23000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Suzuki Jimny', 'SUZUKI', 'Off-roader compacto', 18000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Suzuki S-Cross', 'SUZUKI', 'SUV crossover', 22000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Suzuki Baleno', 'SUZUKI', 'Hatchback mediano', 15000)");

        // Productos de VOLKS
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Volkswagen Golf', 'VOLKS', 'Hatchback compacto', 20000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Volkswagen Passat', 'VOLKS', 'Sedán mediano', 25000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Volkswagen Tiguan', 'VOLKS', 'SUV compacto', 28000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Volkswagen Jetta', 'VOLKS', 'Sedán compacto', 22000)");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio) VALUES ('Volkswagen Touareg', 'VOLKS', 'SUV de lujo', 45000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+tbUsuario);
        db.execSQL("drop table "+tbTarjeta);
        db.execSQL("drop table "+tbPedido);
        db.execSQL("drop table "+tbProducto);
        onCreate(db);
    }


}
