package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_compra extends AppCompatActivity {

    private TextView nombreTextView, descripcionTextView, precioTextView, stockTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        // Referencia a los TextViews
        nombreTextView = findViewById(R.id.idNombre);
        descripcionTextView = findViewById(R.id.idDescripcion);
        precioTextView = findViewById(R.id.idSalePrice);
        stockTextView = findViewById(R.id.idStock);  // Asegúrate de tener este TextView en el layout

        // Obtener los datos enviados desde activity_ProductosSuzuki
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        double precio = intent.getDoubleExtra("precio", 0);
        int stock = intent.getIntExtra("stock", 0);

        // Asignar los valores a los TextViews
        nombreTextView.setText(nombre);
        descripcionTextView.setText(descripcion);
        precioTextView.setText("Precio: $" + precio);
        stockTextView.setText("Stock: " + stock);  // Mostrar el stock aquí
    }
}
