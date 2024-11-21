package com.app.proyectoFinal;

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

import com.app.proyectoFinal.adapter.SalidasAdapter;
import com.app.proyectoFinal.controlador.cSalidas;
import com.app.proyectoFinal.modelo.Salidas;

import java.util.ArrayList;
import java.util.List;

public class activity_Salidas extends AppCompatActivity {

    private RecyclerView recyclerViewSalidas;
    private SalidasAdapter salidasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salidas);

        // Ajustar los insets de la pantalla para la barra de estado
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el RecyclerView
        recyclerViewSalidas = findViewById(R.id.recyclerViewSalidas);
        recyclerViewSalidas.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        salidasAdapter = new SalidasAdapter(this, new ArrayList<>());
        recyclerViewSalidas.setAdapter(salidasAdapter);

        // Cargar las salidas
        cargarSalidas();

        // Cargar los pedidos pagados en Salidas
        cargarPedidosPagadosEnSalidas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarSalidas();
    }

    // Método para regresar a la actividad anterior
    public void Regresar(View view){
        finish(); // Regresa a la actividad anterior
    }

    // Método para cargar las salidas desde el controlador
    public void cargarSalidas() {
        try {
            cSalidas controladorSalidas = new cSalidas(this);
            List<Salidas> salidas = controladorSalidas.select(); // Método para obtener las salidas desde la base de datos

            if (salidas != null && !salidas.isEmpty()) {
                salidasAdapter.clear(); // Limpiar salidas previas
                salidasAdapter.addAll(salidas); // Agregar las nuevas salidas al adaptador
            } else {
                Toast.makeText(this, "No se encontraron salidas", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("activity_Salidas", "Error al cargar las salidas", e);
            Toast.makeText(this, "Error al cargar las salidas", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para cargar los pedidos pagados en Salidas
    public void cargarPedidosPagadosEnSalidas() {
        try {
            cSalidas controladorSalidas = new cSalidas(this);
            controladorSalidas.cargarPedidosPagadosEnSalidas(); // Llamada al método del controlador
        } catch (Exception e) {
            Log.e("activity_Salidas", "Error al cargar los pedidos pagados en salidas", e);
        }
    }
}
