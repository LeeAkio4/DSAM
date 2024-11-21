package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.adapter.EntradaAdapter;
import com.app.proyectoFinal.controlador.cEntradas;
import com.app.proyectoFinal.modelo.Entradas;

import java.util.ArrayList;
import java.util.List;

public class activity_Entradas extends AppCompatActivity {

    private RecyclerView recyclerViewEntradas;
    private EntradaAdapter entradaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);  // Asegúrate de que este layout sea correcto

        // Ajustar los insets de la pantalla para la barra de estado
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el RecyclerView
        recyclerViewEntradas = findViewById(R.id.recyclerViewEntradas);
        recyclerViewEntradas.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        entradaAdapter = new EntradaAdapter(new ArrayList<>());
        recyclerViewEntradas.setAdapter(entradaAdapter);

        // Cargar las entradas desde la base de datos
        cargarEntradas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarEntradas();
    }

    // Método para regresar a la actividad anterior
    public void Regresar(View view){
        Intent x = new Intent(this, OpcionesKardex.class);
        startActivity(x);
    }

    public void cargarEntradas() {
        try {
            cEntradas controladorEntradas = new cEntradas(this);
            List<Entradas> entradas = controladorEntradas.select();  // Obtener todas las entradas

            if (entradas != null && !entradas.isEmpty()) {
                entradaAdapter.clear();  // Limpiar entradas previas
                entradaAdapter.addAll(entradas);  // Agregar nuevas entradas al adaptador
            } else {
                Toast.makeText(this, "No se encontraron entradas", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("ActivityEntradas", "Error al cargar las entradas", e);
            Toast.makeText(this, "Error al cargar las entradas", Toast.LENGTH_SHORT).show();
        }
    }
}
