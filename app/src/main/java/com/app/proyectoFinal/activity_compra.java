package com.app.proyectoFinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.modelo.Pedido;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class activity_compra extends AppCompatActivity {

    private TextView nombreTextView, descripcionTextView, precioTextView, stockTextView;
    private TextView marcaTextView, anioTextView, colorTextView, cilindrosTextView, transmisionTextView, tipoMotorTextView, placaTextView;
    private ImageView imageView;
    private int codigoProducto;
    private int codigoUsuario;
    private double precioProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        // Referencia a los TextViews
        nombreTextView = findViewById(R.id.idNombre);
        descripcionTextView = findViewById(R.id.idDescripcion);
        precioTextView = findViewById(R.id.idSalePrice);
        stockTextView = findViewById(R.id.idStock);
        marcaTextView = findViewById(R.id.idMarca);
        anioTextView = findViewById(R.id.idAnio);
        colorTextView = findViewById(R.id.idColor);
        cilindrosTextView = findViewById(R.id.idCilindros);
        transmisionTextView = findViewById(R.id.idTransmision);
        tipoMotorTextView = findViewById(R.id.idTipomotor);
        placaTextView = findViewById(R.id.idPlaca);
        imageView = findViewById(R.id.imgProducto);

        // Obtener el código de usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioPrefs", MODE_PRIVATE);
        codigoUsuario = sharedPreferences.getInt("codigoUsuario", -1); // Si no existe, devolverá -1

        // Verificar si el código de usuario es válido
        if (codigoUsuario == -1) {
            Toast.makeText(this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad si no hay un usuario válido
            return;
        }

        // Obtener los datos enviados desde activity_ProductosSuzuki
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String marca = intent.getStringExtra("marca");
        int anio = intent.getIntExtra("anio", -1);
        String color = intent.getStringExtra("color");
        int cilindros = intent.getIntExtra("cilindros", -1);
        String transmision = intent.getStringExtra("transmision");
        String tipoMotor = intent.getStringExtra("tipomotor");
        String placa = intent.getStringExtra("placa");
        String imagenUrl = intent.getStringExtra("imagen");
        codigoProducto = intent.getIntExtra("codigoProducto", -1);
        precioProducto = intent.getDoubleExtra("precio", -1);
        int stock = intent.getIntExtra("stock", -1);


        // Validar los datos del producto
        if (codigoProducto == -1 || precioProducto == -1 || stock == -1 || nombre == null || descripcion == null) {
            Toast.makeText(this, "Error: Datos del producto no disponibles", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Asignar los valores a los TextViews
        nombreTextView.setText(nombre);
        descripcionTextView.setText(descripcion);
        precioTextView.setText("Precio: $" + precioProducto);
        stockTextView.setText("Stock: " + stock);
        marcaTextView.setText(marca);
        anioTextView.setText("" + anio);
        colorTextView.setText(color);
        cilindrosTextView.setText("" + cilindros);
        transmisionTextView.setText(transmision);
        tipoMotorTextView.setText(tipoMotor);
        placaTextView.setText(placa);
        // Cargar la imagen del producto usando Glide
        if (imagenUrl != null && !imagenUrl.isEmpty()) {
            Log.d("ProductoURL", imagenUrl);
            Glide.with(this)
                    .load(imagenUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .override(600,600)
                    .into(imageView);
        } else {
            Log.d("ProductoURL", "URL de imagen no disponible");
            imageView.setImageResource(R.drawable.default_image); // Imagen por defecto si no hay URL
        }
    }

    public void Pagar(View view) {

        // Validar stock antes de proceder
        int stockActual = Integer.parseInt(stockTextView.getText().toString().replace("Stock: ", ""));
        if (stockActual <= 0) {
            Toast.makeText(this, "No hay stock disponible para este producto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una instancia de la clase cPedido
        cPedido pedidoController = new cPedido(this);

        // Obtener la fecha actual en el formato adecuado
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // Verificar y registrar el código de usuario
        Log.d("activity_compra", "Código de usuario actual: " + codigoUsuario);

        // Crear un objeto Pedido con los datos necesarios
        Pedido nuevoPedido = new Pedido(
                0, // Código del pedido, se puede generar automáticamente en la base de datos si está definido como autoincremental
                codigoUsuario,
                codigoProducto,
                fechaActual,
                "sin pagar",
                String.valueOf(precioProducto)
        );

        // Intentar insertar el pedido
        try {
            pedidoController.insert(nuevoPedido);
            Toast.makeText(this, "Pedido agregado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("activity_compra", "Error al agregar el pedido: " + e.getMessage());
            Toast.makeText(this, "Error al agregar el pedido", Toast.LENGTH_SHORT).show();
        }

       }

    public void Regresar9(View view){
        Intent y=new Intent(this, Menu_Marcas.class);
        startActivity(y);
    }
}
