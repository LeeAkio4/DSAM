package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Producto;

public class activity_detalle_pedido extends AppCompatActivity {

    private TextView tvTituloProducto, tvCodigoPedido, tvFechaPedido, tvEstado, tvTotal,
            tvNombreProducto, tvPlacaProducto, tvMarcaProducto, tvAnioProducto;
    private ImageView imagenProducto;
    private Button btnVerProducto;
    private int codigoProducto, codigoPedido, codigoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pedido);  // Usamos el XML proporcionado

        // Obtener los datos del Intent
        codigoProducto = getIntent().getIntExtra("codigo_prod", -1);
        codigoUsuario = getIntent().getIntExtra("codigo_usuario", -1);
        codigoPedido = getIntent().getIntExtra("codigo_pedido", -1);
        String fechaPedido = getIntent().getStringExtra("fecha_pedido");
        String estado = getIntent().getStringExtra("estado");
        String total = getIntent().getStringExtra("total");
        String nombreProducto = getIntent().getStringExtra("nombre_prod");
        String placaProducto = getIntent().getStringExtra("placa_prod");
        String marcaProducto = getIntent().getStringExtra("marca_prod");
        int anioProducto = getIntent().getIntExtra("anio_prod", -1);

        // Log de depuración
        Log.d("DetallePedido", "Código del producto: " + codigoProducto);

        // Enlazar vistas con el XML
        tvTituloProducto = findViewById(R.id.tvNombreProducto);
        tvCodigoPedido = findViewById(R.id.tvCodigoPedido);
        tvFechaPedido = findViewById(R.id.tvFechaPedido);
        tvEstado = findViewById(R.id.tvEstado);
        tvTotal = findViewById(R.id.tvTotal);
        tvNombreProducto = findViewById(R.id.tvNombreProducto);
        tvPlacaProducto = findViewById(R.id.tvPlacaProducto);
        tvMarcaProducto = findViewById(R.id.tvMarcaProducto);
        tvAnioProducto = findViewById(R.id.tvAnioProducto);
        imagenProducto = findViewById(R.id.imagenProducto);
        btnVerProducto = findViewById(R.id.btnVerProducto);

        // Asignar los valores a los TextViews
        tvCodigoPedido.setText("Código del Pedido: " + codigoPedido);
        tvFechaPedido.setText("Fecha del Pedido: " + fechaPedido);
        tvEstado.setText("Estado: " + estado);
        tvTotal.setText("Total: " + total);

        tvNombreProducto.setText("Nombre del Producto: " + nombreProducto);
        tvPlacaProducto.setText("Placa: " + placaProducto);
        tvMarcaProducto.setText("Marca: " + marcaProducto);
        tvAnioProducto.setText("Año: " + anioProducto);

        // Aquí deberías manejar la imagen del producto, usando la URL o recurso.
        // Puedes usar Picasso, Glide, o asignar directamente una imagen.
        // Ejemplo con Picasso (asegúrate de agregar la librería en tu build.gradle):
        // Picasso.get().load("url_de_imagen_o_recurso").into(imagenProducto);

        // Configurar el listener para el botón
        btnVerProducto.setOnClickListener(view -> abrirMetodoDePago());
    }

    private void abrirMetodoDePago() {
        // Intent para redirigir al método de pago
        Intent intent = new Intent(activity_detalle_pedido.this, activity_metodos_de_pago.class);
        intent.putExtra("codigoPedido", codigoPedido);
        startActivity(intent);
    }
}
