package com.app.proyectoFinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;
import com.app.proyectoFinal.modelo.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class conexion extends SQLiteOpenHelper {
    private static final int dbVersion=1;
    private static final String dbNombre= "dbAutonex.db";
    public static final String tbUsuario= "usuario";
    public static final String tbTarjeta= "tarjeta";
    public static final String tbPedido= "pedido";
    public static final String tbProducto= "producto";
    public static final String tbEntradas= "entradas";
    public static final String tbSalidas= "salidas";


    public conexion(@Nullable Context context){
        super(context, dbNombre, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tbUsuario + "(codigoUser INTEGER PRIMARY KEY AUTOINCREMENT, nombrescompletos TEXT NOT NULL, direccion TEXT NOT NULL, dni INTEGER NOT NULL, genero TEXT NOT NULL, correo TEXT NOT NULL, contrasena TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + tbTarjeta + " (codigo_tarjeta INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, nombre TEXT NOT NULL, correo TEXT NOT NULL, numero_tarjeta INTEGER NOT NULL, fecha_cad TEXT NOT NULL, cvv TEXT NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigoUser))");
        db.execSQL("CREATE TABLE " + tbPedido + " (codigo_pedido INTEGER PRIMARY KEY AUTOINCREMENT, codigo_usuario INTEGER NOT NULL, codigo_producto INTEGER NOT NULL, fecha_pedido TEXT NOT NULL, estado TEXT NOT NULL, total REAL NOT NULL, FOREIGN KEY (codigo_usuario) REFERENCES " + tbUsuario + "(codigoUser), FOREIGN KEY (codigo_producto) REFERENCES " + tbProducto + "(codigo_producto))");
        db.execSQL("CREATE TABLE " + tbProducto + " (codigo_prod INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL, stock INTEGER NOT NULL, anio INTEGER NOT NULL, color TEXT NOT NULL, cilindros INTEGER NOT NULL, transmision TEXT NOT NULL, tipomotor TEXT NOT NULL, placa TEXT NOT NULL, imagen TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + tbEntradas + " (idEntrada INTEGER PRIMARY KEY AUTOINCREMENT, idProducto INTEGER NOT NULL, cantidad INTEGER NOT NULL, costoUnitario REAL NOT NULL, totalEntrada REAL NOT NULL, fechaEntrada TEXT NOT NULL, FOREIGN KEY(idProducto) REFERENCES " + tbProducto + "(codigo_prod))");
        db.execSQL("CREATE TABLE " + tbSalidas + " (idSalida INTEGER PRIMARY KEY AUTOINCREMENT, idPedido INTEGER NOT NULL, cantidad INTEGER NOT NULL, precioUnitario REAL NOT NULL, totalSalida REAL NOT NULL, fechaSalida TEXT NOT NULL, FOREIGN KEY (idPedido) REFERENCES " + tbPedido + "(codigo_pedido))");
        insertarProductos(db);
        insertarUsuarios(db);
    }

    public HashMap<String, Double> calcularSumaStockYPrecios() {
        SQLiteDatabase db = this.getReadableDatabase();
        HashMap<String, Double> resultados = new HashMap<>();

        // Inicializar las variables para la suma como objetos Double
        Double sumaStock = 0.0;
        Double sumaPreciosFinales = 0.0;

        // Consulta SQL para obtener el stock y precio de cada producto
        String consulta = "SELECT stock, precio FROM " + tbProducto;
        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Iterar sobre todos los productos
            do {
                Double stock = 0.0;
                Double precio = 0.0;

                // Verificar si los valores son nulos antes de usarlos
                if (!cursor.isNull(cursor.getColumnIndexOrThrow("stock"))) {
                    stock = cursor.getDouble(cursor.getColumnIndexOrThrow("stock"));
                }

                if (!cursor.isNull(cursor.getColumnIndexOrThrow("precio"))) {
                    precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));
                }

                // Acumular la suma del stock
                sumaStock += stock;

                // Acumular la suma de los precios finales (stock * precio)
                sumaPreciosFinales += stock * precio;
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        // Guardar los resultados en el HashMap
        resultados.put("TotalStock", sumaStock);
        resultados.put("TotalPreciosFinales", sumaPreciosFinales);

        return resultados;
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
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('KIA Rio', 'KIA', 'Sedán compacto con motor 1.6L', 15000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'ABC123', 'https://neoauto.com/noticias/wp-content/uploads/2021/06/kia-rio-2022-lanzamiento-peru.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('KIA Sportage', 'KIA', 'SUV con motor 2.0L', 22000, 1, 2023, 'Negro', 4, 'Manual', 'Gasolina', 'XYZ456', 'https://images.dealer.com/ddc/vehicles/2025/Kia/Sportage/SUV/still/front-left/front-left-640-en_US.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('KIA Sorento', 'KIA', 'SUV familiar con motor 2.4L', 28000, 1, 2021, 'Blanco', 6, 'Automática', 'Gasolina', 'LMN789', 'https://acnews.blob.core.windows.net/imgnews/large/NAZ_01704c5c97214ce2b9d0a363ce7d4a41.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('KIA Picanto', 'KIA', 'City car compacto', 12000, 1, 2020, 'Azul', 4, 'Manual', 'Gasolina', 'JKL012', 'https://www.caetanoretail.es/site/uploads/sites/42/2024/05/kia-picanto-pe-gls-my25-spb-sporty-blue-14c-0000.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('KIA Seltos', 'KIA', 'SUV subcompacto', 21000, 1, 2022, 'Gris', 4, 'Automática', 'Gasolina', 'DEF345', 'https://www.santaclara.com.pe/uploads/gris-acero.jpg')");

        // Productos de TOYOTA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Toyota Corolla', 'TOYOTA', 'Sedán con motor 1.8L', 18000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'GHI678', 'https://www.toyotacorolla.com.pe/assets/images/corolla-hybrid-blanco.png')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Toyota RAV4', 'TOYOTA', 'SUV con motor híbrido', 30000, 1, 2023, 'Verde', 4, 'Automática', 'Híbrido', 'JKL012', 'https://soymotor.com/sites/default/files/usuarios/redaccion/portal/jmmspuch/toyota-rav4-2023-soymotor-6_0.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Toyota Hilux', 'TOYOTA', 'Pickup con motor 2.8L turbo diesel', 35000, 1, 2020, 'Rojo', 4, 'Manual', 'Diésel', 'MNO345', 'https://grupopana.com.pe/hubfs/20201124-TOYOTA-HILUX-2021-AMERICA-LATINA-01-750x460-1.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Toyota Prius', 'TOYOTA', 'Híbrido con tecnología avanzada', 25000, 1, 2022, 'Negro', 4, 'Automática', 'Híbrido', 'PQR678', 'https://fotologs.miarroba.st/photo/8477464/5/741x574/5b359b5caae84660/1338502752.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Toyota Yaris', 'TOYOTA', 'Hatchback compacto', 16000, 1, 2021, 'Azul', 4, 'Manual', 'Gasolina', 'STU901', 'https://www.diariomotor.com/imagenes/2023/09/toyota-yaris-130h-4-6511a17538f32.jpg')");

        // Productos de TESLA
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Tesla Model S', 'TESLA', 'Sedán eléctrico de lujo', 75000, 1, 2022, 'Blanco', 0, 'Automática', 'Eléctrico', 'XYZ987', 'https://acnews.blob.core.windows.net/imgnews/large/NAZ_c2c7562e88594afbba848ff8cf503893.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Tesla Model 3', 'TESLA', 'Sedán eléctrico compacto', 45000, 1, 2021, 'Rojo', 0, 'Automática', 'Eléctrico', 'DEF654', 'https://rentingfinders.com/wp-content/uploads/2020/02/tesla-model-3-1024x683.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Tesla Model X', 'TESLA', 'SUV eléctrico de lujo', 90000, 1, 2023, 'Negro', 0, 'Automática', 'Eléctrico', 'GHI321', 'https://img.freepik.com/fotos-premium/tesla-model-x-negro-puertas-halcon-abiertas-fondo-estudio-minimalista_927063-2331.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Tesla Model Y', 'TESLA', 'SUV eléctrico compacto', 60000, 1, 2022, 'Gris', 0, 'Automática', 'Eléctrico', 'JKL210', 'https://www.hibridosyelectricos.com/uploads/s1/38/69/91/2022122208315552024.jpeg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Tesla Cybertruck', 'TESLA', 'Pickup eléctrico futurista', 70000, 1, 2023, 'Plata', 0, 'Automática', 'Eléctrico', 'MNO123', 'https://i.insider.com/6660b7b1d0b8e1c832cc3b36?width=700')");

        // Productos de NISSAN
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Nissan Sentra', 'NISSAN', 'Sedán con motor 2.0L', 19000, 1, 2020, 'Negro', 4, 'Manual', 'Gasolina', 'ABC234', 'https://di-uploads-pod28.dealerinspire.com/avondalenissan/uploads/2020/02/2020-nissan-sentra-header.png')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Nissan Rogue', 'NISSAN', 'SUV con motor 2.5L', 25000, 1, 2021, 'Blanco', 4, 'Automática', 'Gasolina', 'DEF567', 'https://www.peruzzinissan.com/blogs/385/wp-content/uploads/2016/11/2017-rogue-e1479924001344.png')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Nissan Frontier', 'NISSAN', 'Pickup con motor 2.5L', 28000, 1, 2022, 'Azul', 4, 'Manual', 'Gasolina', 'GHI890', 'https://acnews.blob.core.windows.net/imgnews/large/NAZ_80f4fba8b950458db080098f110012dd.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Nissan Leaf', 'NISSAN', 'Hatchback eléctrico', 32000, 1, 2023, 'Gris', 0, 'Automática', 'Eléctrico', 'JKL234', 'https://forococheselectrcos.com/wp-content/uploads/2017/12/featured_Car.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Nissan Kicks', 'NISSAN', 'SUV subcompacto', 21000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'MNO567', 'https://www.nissan.pe/content/dam/Nissan/pe/vehicles/kicks2021/packshots-my22/Advance%20MT-my22.png.ximg.c1m.conf.png')");

        // Productos de SUZUKI
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Suzuki Swift', 'SUZUKI', 'Hatchback compacto', 14000, 1, 2021, 'Rojo', 4, 'Manual', 'Gasolina', 'SUZ123', 'https://www.suzuki.com.pe/media/rpaftdxf/foto_0000000920220527085542_frenos-abs-ebd-ba.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Suzuki Vitara', 'SUZUKI', 'SUV compacta', 22000, 1, 2022, 'Negro', 4, 'Automática', 'Gasolina', 'SUZ456', 'https://satelitalautomotriz.com/dercocenter/wp-content/uploads/2024/04/suzuki_nueva_vitara_negro.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Suzuki Jimny', 'SUZUKI', 'SUV compacto con motor 1.5L', 18000, 1, 2021, 'Verde', 4, 'Manual', 'Gasolina', 'SUZ789', 'https://autoland.com.pe/wp-content/uploads/2020/02/jimny1-1-1.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Suzuki S-Cross', 'SUZUKI', 'SUV mediana', 23000, 1, 2022, 'Azul', 4, 'Automática', 'Gasolina', 'SUZ012', 'https://grupogranprix.com.pe/wp-content/uploads/2021/09/foto_0000000920220526144334_azul-perlado1.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Suzuki Baleno', 'SUZUKI', 'Sedán compacto', 15000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'SUZ345', 'https://suzukisigloxxi.com.mx/wp-content/uploads/2024/01/baleno-GLX-blanco.png')");

        // Productos de VOLKSWAGEN (VOLKS)
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Volkswagen Golf', 'VOLKSWAGEN', 'Hatchback compacto', 20000, 1, 2022, 'Gris', 4, 'Manual', 'Gasolina', 'VOL123', 'https://img.remediosdigitales.com/1d2abc/volkswagen-golf-2018_/840_560.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Volkswagen Tiguan', 'VOLKSWAGEN', 'SUV mediana', 28000, 1, 2023, 'Negro', 4, 'Automática', 'Gasolina', 'VOL456', 'https://acnews.blob.core.windows.net/imgnews/paragraph/NPAZ_a904edc144e14f5a964b077d5dceeebf.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Volkswagen Jetta', 'VOLKSWAGEN', 'Sedán con motor 1.4L', 22000, 1, 2021, 'Blanco', 4, 'Manual', 'Gasolina', 'VOL789', 'https://noticias.coches.com/wp-content/uploads/2016/11/volkswagen_jetta-usa-2014_r41.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Volkswagen Polo', 'VOLKSWAGEN', 'Hatchback compacto', 17000, 1, 2022, 'Rojo', 4, 'Automática', 'Gasolina', 'VOL321', 'https://cdn.motor1.com/images/mgl/AkmlXN/s1/polo-track-first-edition-0.jpg')");
        db.execSQL("INSERT INTO " + tbProducto + " (nombre, marca, descripcion, precio, stock, anio, color, cilindros, transmision, tipomotor, placa, imagen) VALUES ('Volkswagen Passat', 'VOLKSWAGEN', 'Sedán de lujo', 35000, 1, 2023, 'Azul', 4, 'Automática', 'Gasolina', 'VOL654', 'https://img.remediosdigitales.com/08e4a9/650_1000_volkswagen-passat-blue-motion-concept--2-/1366_2000.jpg')");
    }

    public long agregarProducto(Producto producto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", producto.getNombre());
        contentValues.put("marca", producto.getMarca());
        contentValues.put("descripcion", producto.getDescripcion());
        contentValues.put("precio", producto.getPrecio());
        contentValues.put("anio", producto.getAnio());
        contentValues.put("color", producto.getColor());
        contentValues.put("cilindros", producto.getCilindros());
        contentValues.put("transmision", producto.getTransmision());
        contentValues.put("tipomotor", producto.getTipomotor());
        contentValues.put("placa", producto.getPlaca());
        contentValues.put("stock", producto.getStock());
        contentValues.put("imagen", producto.getImagen());

        // Insertar el nuevo producto
        long id = db.insert(tbProducto, null, contentValues);
        db.close();
        return id;  // Retorna el ID del producto insertado
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();  // Abrir la base de datos en modo solo lectura

        Cursor cursor = db.query("producto",
                new String[]{"codigo_prod", "nombre", "marca", "descripcion", "precio", "stock", "anio", "color", "cilindros", "transmision", "tipomotor", "placa", "imagen"},
                null, null, null, null, null);

        if (cursor != null) {
            // Imprimir los nombres de las columnas para depuración
            String[] columnNames = cursor.getColumnNames();
            for (String columnName : columnNames) {
                Log.d("Column Name", columnName);
            }

            while (cursor.moveToNext()) {
                // Verificar que las columnas existen antes de acceder a ellas
                int indexCodigo_prod = cursor.getColumnIndex("codigo_prod");
                int indexNombre = cursor.getColumnIndex("nombre");
                int indexMarca = cursor.getColumnIndex("marca");
                int indexDescripcion = cursor.getColumnIndex("descripcion");
                int indexPrecio = cursor.getColumnIndex("precio");
                int indexAnio = cursor.getColumnIndex("anio");
                int indexColor = cursor.getColumnIndex("color");
                int indexCilindros = cursor.getColumnIndex("cilindros");
                int indexTransmision = cursor.getColumnIndex("transmision");
                int indexTipoMotor = cursor.getColumnIndex("tipomotor");
                int indexPlaca = cursor.getColumnIndex("placa");
                int indexStock = cursor.getColumnIndex("stock");
                int indexImagen = cursor.getColumnIndex("imagen");

                // Asegurarse de que el índice no sea -1 (lo que indicaría que la columna no se encontró)
                if (indexNombre != -1 && indexMarca != -1 && indexDescripcion != -1 && indexPrecio != -1 && indexAnio != -1 && indexColor != -1 &&
                        indexCilindros != -1 && indexTransmision != -1 && indexTipoMotor != -1 && indexPlaca != -1 && indexStock != -1) {
                    Producto producto = new Producto();
                    producto.setCodigo_prod(cursor.getInt(indexCodigo_prod));
                    producto.setNombre(cursor.getString(indexNombre));
                    producto.setMarca(cursor.getString(indexMarca));
                    producto.setDescripcion(cursor.getString(indexDescripcion));
                    producto.setPrecio(cursor.getDouble(indexPrecio));
                    producto.setAnio(cursor.getInt(indexAnio));
                    producto.setColor(cursor.getString(indexColor));
                    producto.setCilindros(cursor.getInt(indexCilindros));
                    producto.setTransmision(cursor.getString(indexTransmision));
                    producto.setTipomotor(cursor.getString(indexTipoMotor));
                    producto.setPlaca(cursor.getString(indexPlaca));
                    producto.setStock(cursor.getInt(indexStock));
                    producto.setImagen(cursor.getString(indexImagen));
                    productos.add(producto);
                } else {
                    Log.e("Error", "Una o más columnas no se encontraron");
                }
            }
            cursor.close();
        }

        db.close();
        return productos;
    }

    public boolean actualizarProducto(Producto producto) {
        // Verificar que el código del producto no sea 0
        if (producto.getCodigo_prod() == 0) {
            Log.e("ActualizarProducto", "El código del producto es 0, no se puede actualizar.");
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un ContentValues con los nuevos datos del producto
        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("marca", producto.getMarca());
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("anio", producto.getAnio());
        values.put("color", producto.getColor());
        values.put("cilindros", producto.getCilindros());
        values.put("transmision", producto.getTransmision());
        values.put("tipoMotor", producto.getTipomotor());
        values.put("placa", producto.getPlaca());
        values.put("stock", producto.getStock());
        values.put("imagen", producto.getImagen());

        // Realizar la actualización utilizando el código del producto (código_prod)
        int result = db.update("producto", values, "codigo_prod=?", new String[]{String.valueOf(producto.getCodigo_prod())});
        Log.d("ActualizarProducto", "Filas afectadas: " + result);
        db.close(); // Cerrar la base de datos

        // Retornar true si la actualización fue exitosa, de lo contrario false
        return result > 0;
    }

    public boolean eliminarProducto(int productoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Crear la cláusula WHERE para eliminar el producto con el ID proporcionado
        String whereClause = "codigo_prod = ?";
        String[] whereArgs = new String[] { String.valueOf(productoId) };


        int rowsAffected = db.delete("producto", whereClause, whereArgs);
        db.close();

        return rowsAffected > 0;
    }


























    public long agregarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombrescompletos", usuario.getNombrescompletos());
        contentValues.put("direccion", usuario.getDireccion());
        contentValues.put("dni", usuario.getDni());
        contentValues.put("genero", usuario.getGenero());
        contentValues.put("correo", usuario.getCorreo());
        contentValues.put("contrasena", usuario.getContrasena());

        return db.insert("usuario", null, contentValues);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // Abrir la base de datos en modo solo lectura

        Cursor cursor = db.query("usuario",
                new String[]{"codigoUser", "nombrescompletos", "direccion", "dni", "genero", "correo", "contrasena"},
                null, null, null, null, null);

        if (cursor != null) {
            // Imprimir los nombres de las columnas para depuración
            String[] columnNames = cursor.getColumnNames();
            for (String columnName : columnNames) {
                Log.d("Column Name", columnName);
            }

            while (cursor.moveToNext()) {
                // Verificar que las columnas existen antes de acceder a ellas
                int indexCodigoUser = cursor.getColumnIndex("codigoUser");
                int indexNombresCompletos = cursor.getColumnIndex("nombrescompletos");
                int indexDireccion = cursor.getColumnIndex("direccion");
                int indexDni = cursor.getColumnIndex("dni");
                int indexGenero = cursor.getColumnIndex("genero");
                int indexCorreo = cursor.getColumnIndex("correo");
                int indexContrasena = cursor.getColumnIndex("contrasena");

                // Asegurarse de que el índice no sea -1 (lo que indicaría que la columna no se encontró)
                if (indexCodigoUser != -1 && indexNombresCompletos != -1 && indexDireccion != -1 && indexDni != -1 &&
                        indexGenero != -1 && indexCorreo != -1 && indexContrasena != -1) {
                    Usuario usuario = new Usuario();
                    usuario.setCodigo(cursor.getInt(indexCodigoUser));
                    usuario.setNombrescompletos(cursor.getString(indexNombresCompletos));
                    usuario.setDireccion(cursor.getString(indexDireccion));
                    usuario.setDni(cursor.getInt(indexDni));
                    usuario.setGenero(cursor.getString(indexGenero));
                    usuario.setCorreo(cursor.getString(indexCorreo));
                    usuario.setContrasena(cursor.getString(indexContrasena));
                    usuarios.add(usuario);
                } else {
                    Log.e("Error", "Una o más columnas no se encontraron");
                }
            }
            cursor.close();
        }

        db.close();
        return usuarios;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        // Verificar que el código del usuario no sea 0
        if (usuario.getCodigo() == 0) {
            Log.e("ActualizarUsuario", "El código del usuario es 0, no se puede actualizar.");
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un ContentValues con los nuevos datos del usuario
        ContentValues values = new ContentValues();
        values.put("nombrescompletos", usuario.getNombrescompletos());
        values.put("direccion", usuario.getDireccion());
        values.put("dni", usuario.getDni());
        values.put("genero", usuario.getGenero());
        values.put("correo", usuario.getCorreo());
        values.put("contrasena", usuario.getContrasena());

        // Realizar la actualización utilizando el código del usuario (codigoUser)
        int result = db.update("usuario", values, "codigoUser=?", new String[]{String.valueOf(usuario.getCodigo())});
        Log.d("ActualizarUsuario", "Filas afectadas: " + result);
        db.close(); // Cerrar la base de datos

        // Retornar true si la actualización fue exitosa, de lo contrario false
        return result > 0;
    }

    public boolean eliminarUsuario(int usuarioId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Crear la cláusula WHERE para eliminar el usuario con el ID proporcionado
        String whereClause = "codigoUser = ?";
        String[] whereArgs = new String[] { String.valueOf(usuarioId) };

        int rowsAffected = db.delete("usuario", whereClause, whereArgs);
        db.close();

        return rowsAffected > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbUsuario);
        db.execSQL("DROP TABLE IF EXISTS " + tbTarjeta);
        db.execSQL("DROP TABLE IF EXISTS " + tbPedido);
        db.execSQL("DROP TABLE IF EXISTS " + tbProducto);
        db.execSQL("DROP TABLE IF EXISTS " + tbEntradas);
        db.execSQL("DROP TABLE IF EXISTS " + tbSalidas);
        onCreate(db);
    }


}
