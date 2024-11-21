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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
        String imagenUrl = getIntent().getStringExtra("imagen");
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
        imagenProducto = findViewById(R.id.imagenProducto);

        // Asignar los valores a los TextViews
        tvCodigoPedido.setText("Código del Pedido: " + codigoPedido);
        tvFechaPedido.setText("Fecha del Pedido: " + fechaPedido);
        tvEstado.setText("Estado: " + estado);
        tvTotal.setText("Total: " + total);

        tvNombreProducto.setText("Nombre del Producto: " + nombreProducto);
        tvPlacaProducto.setText("Placa: " + placaProducto);
        tvMarcaProducto.setText("Marca: " + marcaProducto);
        tvAnioProducto.setText("Año: " + anioProducto);

        // Cargar la imagen del producto usando Glide
        if (imagenUrl != null && !imagenUrl.isEmpty()) {
            Log.d("ProductoURL", imagenUrl);
            Glide.with(this)
                    .load(imagenUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .override(400,400)
                    .into(imagenProducto);
        } else {
            Log.d("ProductoURL", "URL de imagen no disponible");
            imagenProducto.setImageResource(R.drawable.default_image); // Imagen por defecto si no hay URL
        }

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
