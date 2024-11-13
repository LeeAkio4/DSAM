package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Producto;

public class activity_detalle_pedido extends AppCompatActivity {

    private TextView tvTituloProducto, tvIdProducto, tvStock, tvPrecioOrignal, tvSalePrice;
    private ImageView imagenProducto;
    int codigoUsuario;
    private Button btnVerProducto;
    private int codigoProducto; // Esta variable se declara aquí una sola vez
    private int codigoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pedido);
        // Obtener el código del producto desde el Intent
        codigoProducto = getIntent().getIntExtra("codigo_prod", -1); // Se asigna a la variable global
        codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);
        codigoPedido = getIntent().getIntExtra("codigo_pedido", -1);
        // Log para mostrar el valor de codigoProducto
        Log.d("DetallePedido", "Valor de codigoProducto: " + codigoProducto);
        Log.d("DetallePedido", "Valor de codigoPedido: " + codigoPedido); // Verificar que el codigo_pedido se pasa correctamente
        // Enlazar vistas
        tvTituloProducto = findViewById(R.id.tvTituloProducto);
        tvIdProducto = findViewById(R.id.tvIdProducto);
        tvStock = findViewById(R.id.tvStock);
        tvPrecioOrignal = findViewById(R.id.tvPrecioOrignal);
        tvSalePrice = findViewById(R.id.tvSalePrice);
        imagenProducto = findViewById(R.id.imagenProducto);
        btnVerProducto = findViewById(R.id.btnVerProducto);

        // Cargar detalles del producto
        cargarDetallesProducto();

        // Configurar el listener para el botón
        btnVerProducto.setOnClickListener(view -> abrirMetodoDePago());
    }

    private void cargarDetallesProducto() {
        if (codigoProducto != -1) {
            cProducto controladorProducto = new cProducto(this);
            Producto producto = controladorProducto.obtenerProductoPorCodigo(codigoProducto);

            if (producto != null) {
                tvTituloProducto.setText(producto.getNombre());
                tvIdProducto.setText("ID: " + producto.getCodigo_prod());
                tvStock.setText("Stock: " + producto.getStock());
                tvPrecioOrignal.setText("" + producto.getPrecio());
                tvSalePrice.setText("" + producto.getPrecio());
                // Puedes cargar la imagen del producto si tienes una URL o recurso
                // imagenProducto.setImageResource(producto.getImagen());
            } else {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Código de producto inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirMetodoDePago() {
        Intent intent = new Intent(activity_detalle_pedido.this, activity_metodos_de_pago.class);
        // Pasar el codigo_pedido al Intent
        intent.putExtra("codigoPedido", codigoPedido);
        startActivity(intent);
    }
}

