package com.app.proyectoFinal;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_compra extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compra);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener los datos pasados desde el intent
        Intent intent = getIntent();
        String nombreProducto = intent.getStringExtra("nombre");
        String descripcionProducto = intent.getStringExtra("descripcion");
        double precioProducto = intent.getDoubleExtra("precio", 0);
        int cantidadVendidos = intent.getIntExtra("cantidadVendidos", 0); // Asegúrate de que esto esté presente en el modelo de Producto

        // Mostrar los datos en los TextViews correspondientes
        TextView nombreTextView = findViewById(R.id.idDescripcion);
        TextView precioTextView = findViewById(R.id.idSalePrice);
        TextView vendidosTextView = findViewById(R.id.idVendidos);

        nombreTextView.setText(descripcionProducto);
        precioTextView.setText("S/ " + precioProducto);
        vendidosTextView.setText(cantidadVendidos + " Vendidos");
    }
}
