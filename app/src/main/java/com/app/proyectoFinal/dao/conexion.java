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
    private static final int dbVersion=3;
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
        db.execSQL("CREATE TABLE " + tbTarjeta + " (codigo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, nombre TEXT NOT NULL, correo TEXT NOT NULL, numero_tarjeta INTEGER NOT NULL, fecha_cad TEXT NOT NULL, cvv TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigoUser))");
        db.execSQL("CREATE TABLE " + tbPedido + " (codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, codigo_producto INTEGER NOT NULL, fecha_pedido TEXT NOT NULL, estado TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigoUser), FOREIGN KEY (codigo_producto) REFERENCES " + tbProducto + "(codigo_producto))");
        db.execSQL("CREATE TABLE " + tbProducto + " (codigo_prod INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL, stock INTEGER NOT NULL, anio INTEGER NOT NULL, color STRING NOT NULL, cilindros INTEGER NOT NULL, transmision STRING NOT NULL, tipomotor STRING NOT NULL, placa STRING NOT NULL)");
        insertarProductos(db);
        insertarUsuarios(db);
    }


    public boolean actualizarUsuario(int codigoUser, String nombrescompletos, String direccion, String genero, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombrescompletos", nombrescompletos);
        values.put("direccion", direccion);
        values.put("genero", genero);
        values.put("correo", correo);
        values.put("contrasena", contrasena);

        int result = db.update(tbUsuario, values, "codigoUser=?", new String[]{String.valueOf(codigoUser)});
        db.close();

        return result > 0;
    }



    private void insertarUsuarios(SQLiteDatabase db1){
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('admin', 'admin', '00000001', 'admin', 'admin', 'admin')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Sebastian Ernesto Bedon Oscco', 'Av. BolasLLenas', '69697777', 'Masculino', 'sebastian@gmail.com', '123456')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Lee Akio Bruno Mauricio Taboada', 'Av. Algo', '69697777', 'Masculino', 'lee@gmail.com', '123456')");
        db1.execSQL("INSERT INTO " + tbUsuario + " (nombrescompletos, direccion, dni, genero, correo, contrasena) VALUES ('Cesar Valentin', 'Av. Chorrillos', '71509648', 'Masculino', 'valentin@gmail.com', '123456')");
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
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('KIA Rio', 'KIA', 'Sedán compacto con motor 1.6L', 15000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'ABC123')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('KIA Sportage', 'KIA', 'SUV con motor 2.0L', 22000, 1, 2023, 'Negro', 4, 'Manual', 'Gasolina', 'XYZ456')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('KIA Sorento', 'KIA', 'SUV familiar con motor 2.4L', 28000, 1, 2021, 'Blanco', 6, 'Automática', 'Gasolina', 'LMN789')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('KIA Picanto', 'KIA', 'City car compacto', 12000, 1, 2020, 'Azul', 4, 'Manual', 'Gasolina', 'JKL012')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('KIA Seltos', 'KIA', 'SUV subcompacto', 21000, 1, 2022, 'Gris', 4, 'Automática', 'Gasolina', 'DEF345')");

        // Productos de TOYOTA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Toyota Corolla', 'TOYOTA', 'Sedán con motor 1.8L', 18000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'GHI678')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Toyota RAV4', 'TOYOTA', 'SUV con motor híbrido', 30000, 1, 2023, 'Verde', 4, 'Automática', 'Híbrido', 'JKL012')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Toyota Hilux', 'TOYOTA', 'Pickup con motor 2.8L turbo diesel', 35000, 1, 2020, 'Rojo', 4, 'Manual', 'Diésel', 'MNO345')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Toyota Prius', 'TOYOTA', 'Híbrido con tecnología avanzada', 25000, 1, 2022, 'Negro', 4, 'Automática', 'Híbrido', 'PQR678')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Toyota Yaris', 'TOYOTA', 'Hatchback compacto', 16000, 1, 2021, 'Azul', 4, 'Manual', 'Gasolina', 'STU901')");

        // Productos de TESLA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Tesla Model S', 'TESLA', 'Sedán eléctrico de lujo', 75000, 1, 2022, 'Blanco', 0, 'Automática', 'Eléctrico', 'XYZ987')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Tesla Model 3', 'TESLA', 'Sedán eléctrico compacto', 45000, 1, 2021, 'Rojo', 0, 'Automática', 'Eléctrico', 'DEF654')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Tesla Model X', 'TESLA', 'SUV eléctrico de lujo', 90000, 1, 2023, 'Negro', 0, 'Automática', 'Eléctrico', 'GHI321')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Tesla Model Y', 'TESLA', 'SUV eléctrico compacto', 60000, 1, 2022, 'Gris', 0, 'Automática', 'Eléctrico', 'JKL210')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Tesla Cybertruck', 'TESLA', 'Pickup eléctrico futurista', 70000, 1, 2023, 'Plata', 0, 'Automática', 'Eléctrico', 'MNO123')");

        // Productos de NISSAN
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Nissan Sentra', 'NISSAN', 'Sedán con motor 2.0L', 19000, 1, 2020, 'Negro', 4, 'Manual', 'Gasolina', 'ABC234')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Nissan Rogue', 'NISSAN', 'SUV con motor 2.5L', 25000, 1, 2021, 'Blanco', 4, 'Automática', 'Gasolina', 'DEF567')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Nissan Frontier', 'NISSAN', 'Pickup con motor 2.5L', 28000, 1, 2022, 'Azul', 4, 'Manual', 'Gasolina', 'GHI890')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Nissan Leaf', 'NISSAN', 'Hatchback eléctrico', 32000, 1, 2023, 'Verde', 0, 'Automática', 'Eléctrico', 'JKL234')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Nissan Kicks', 'NISSAN', 'SUV subcompacto', 21000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'MNO567')");

        // Productos de SUZUKI
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Suzuki Swift', 'SUZUKI', 'Hatchback compacto', 14000, 1, 2021, 'Rojo', 4, 'Manual', 'Gasolina', 'SUZ123')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Suzuki Vitara', 'SUZUKI', 'SUV compacta', 22000, 1, 2022, 'Negro', 4, 'Automática', 'Gasolina', 'SUZ456')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Suzuki Jimny', 'SUZUKI', 'SUV compacto con motor 1.5L', 18000, 1, 2021, 'Verde', 4, 'Manual', 'Gasolina', 'SUZ789')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Suzuki S-Cross', 'SUZUKI', 'SUV mediana', 23000, 1, 2022, 'Azul', 4, 'Automática', 'Gasolina', 'SUZ012')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Suzuki Baleno', 'SUZUKI', 'Sedán compacto', 15000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'SUZ345')");

        // Productos de VOLKSWAGEN (VOLKS)
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Volkswagen Golf', 'VOLKSWAGEN', 'Hatchback compacto', 20000, 1, 2022, 'Gris', 4, 'Manual', 'Gasolina', 'VOL123')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Volkswagen Tiguan', 'VOLKSWAGEN', 'SUV mediana', 28000, 1, 2023, 'Negro', 4, 'Automática', 'Gasolina', 'VOL456')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Volkswagen Jetta', 'VOLKSWAGEN', 'Sedán con motor 1.4L', 22000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'VOL789')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Volkswagen Polo', 'VOLKSWAGEN', 'Hatchback compacto', 17000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'VOL321')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa) VALUES ('Volkswagen Passat', 'VOLKSWAGEN', 'Sedán de lujo', 35000, 1, 2023, 'Azul', 4, 'Automática', 'Gasolina', 'VOL654')");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbUsuario);
        db.execSQL("DROP TABLE IF EXISTS " + tbTarjeta);
        db.execSQL("DROP TABLE IF EXISTS " + tbPedido);
        db.execSQL("DROP TABLE IF EXISTS " + tbProducto);
        onCreate(db);
    }


}
