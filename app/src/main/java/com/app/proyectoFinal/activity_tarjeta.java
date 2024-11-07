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

import com.app.proyectoFinal.adapter.TarjetaAdapter;
import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class activity_tarjeta extends AppCompatActivity {
    public RecyclerView recyclerViewTarjetas;
    public TarjetaAdapter tarjetaAdapter;
    int codigoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mi_tarjeta);

        // Ajustar los insets de la pantalla para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Obtener el código del usuario desde el Intent que inicia esta actividad
        codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);

        // Configurar el RecyclerView
        recyclerViewTarjetas = findViewById(R.id.recyclerViewTarjetas);
        recyclerViewTarjetas.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        tarjetaAdapter = new TarjetaAdapter(new ArrayList<>()); // Asegúrate de pasar los datos si es necesario
        recyclerViewTarjetas.setAdapter(tarjetaAdapter);

        // Cargar las tarjetas del usuario desde la base de datos
        cargarTarjetas();

    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarTarjetas();
    }

    public void cargarTarjetas() {
        try {
            cTarjeta controladorTarjeta = new cTarjeta(this);
            List<Tarjeta> tarjetas = controladorTarjeta.obtenerTarjetasPorUsuario(codigoUsuario);

            if (tarjetas != null && !tarjetas.isEmpty()) {
                tarjetaAdapter.clear(); // Limpiar tarjetas previas
                tarjetaAdapter.addAll(tarjetas); // Agregar las nuevas tarjetas al adaptador
            } else {
                Toast.makeText(this, "No se encontraron tarjetas", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("activity_tarjeta", "Error al cargar las tarjetas", e);
            Toast.makeText(this, "Error al cargar las tarjetas", Toast.LENGTH_SHORT).show();
        }
    }

    public void AñadirTarjeta(View view) {
        if (codigoUsuario == -1) {
            Toast.makeText(this, "Error: usuario no autenticado", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, activity_añadir_tarjeta.class);
        intent.putExtra("codigoUsuario", codigoUsuario);
        startActivity(intent);
    }

    public void Regresar(View view) {
        Intent intent = new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(intent);
    }

}