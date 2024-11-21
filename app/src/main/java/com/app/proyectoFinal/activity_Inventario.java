package com.app.proyectoFinal;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.app.proyectoFinal.dao.conexion;

import java.util.HashMap;

public class activity_Inventario extends AppCompatActivity {

    private conexion dbConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        // Inicializar conexión a la base de datos
        dbConexion = new conexion(this);

        HashMap<String, Double> resultados = dbConexion.calcularSumaStockYPrecios();

        // Obtener los valores del HashMap
        Double totalStock = resultados.get("TotalStock");
        Double totalPrecios = resultados.get("TotalPreciosFinales");

        // Verificar si son nulos antes de usarlos
        if (totalStock != null && totalPrecios != null) {
            // Usar los valores de manera segura
            Log.d("Inventario", "TotalStock: " + totalStock);
            Log.d("Inventario", "TotalPreciosFinales: " + totalPrecios);
        } else {
            // Manejar el caso en el que alguno de los valores sea nulo
            Log.e("Inventario", "Uno de los valores es nulo");
        }

        // Buscar los TextView de la tabla
        TextView stockTextView = findViewById(R.id.stockTextView); // Asigna un ID en el XML
        TextView precioTextView = findViewById(R.id.precioTextView); // Asigna un ID en el XML

        // Asignar los valores a los TextView
        stockTextView.setText(String.valueOf(totalStock));
        precioTextView.setText(String.format("$%.2f", totalPrecios));
    }

    // Método para regresar a la actividad anterior
    public void Volver2(View view){
        Intent x = new Intent(this, OpcionesKardex.class);
        startActivity(x);
    }
}
