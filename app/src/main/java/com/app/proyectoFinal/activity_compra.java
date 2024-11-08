package com.app.proyectoFinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.modelo.Pedido;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class activity_compra extends AppCompatActivity {

    private TextView nombreTextView, descripcionTextView, precioTextView, stockTextView;
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
        codigoProducto = intent.getIntExtra("codigoProducto", 0);
        precioProducto = intent.getDoubleExtra("precio", 0);
        int stock = intent.getIntExtra("stock", 0);

        // Asignar los valores a los TextViews
        nombreTextView.setText(nombre);
        descripcionTextView.setText(descripcion);
        precioTextView.setText("Precio: $" + precioProducto);
        stockTextView.setText("Stock: " + stock);
    }

    public void Pagar(View view) {
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

        // Insertar el pedido usando el método insert de cPedido
        pedidoController.insert(nuevoPedido);

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Pedido agregado exitosamente", Toast.LENGTH_SHORT).show();
    }
}
