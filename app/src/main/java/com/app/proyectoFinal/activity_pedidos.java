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

import com.app.proyectoFinal.adapter.PedidoAdapter;
import com.app.proyectoFinal.adapter.TarjetaAdapter;
import com.app.proyectoFinal.controlador.cTarjeta;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class activity_pedidos extends AppCompatActivity {

    private int codigoUsuario; // Aquí debes obtener el ID del usuario, por ejemplo, al iniciar sesión
    private RecyclerView recyclerViewPedidos;
    private PedidoAdapter pedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);

        // Ajustar los insets de la pantalla para la barra de estado
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Obtener el código del usuario desde el Intent que inicia esta actividad
        codigoUsuario = getIntent().getIntExtra("codigoUsuario", -1);

        // Configurar el RecyclerView
        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        pedidoAdapter = new PedidoAdapter(new ArrayList<>()); // Asegúrate de pasar los datos si es necesario
        recyclerViewPedidos.setAdapter(pedidoAdapter);

        // Cargar las tarjetas del usuario desde la base de datos
        cargarPedidos();

    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPedidos();
    }

    // Método para regresar a la actividad anterior
    public void Regresar(View view){
        Intent x = new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(x);
    }

    public void cargarPedidos() {
        try {

            cPedido controladorPedidos = new cPedido(this);
            List<Pedido> pedidos = controladorPedidos.obtenerPedidosPorUsuario(codigoUsuario);

            if (pedidos != null && !pedidos.isEmpty()) {
                pedidoAdapter.clear(); // Limpiar tarjetas previas
                pedidoAdapter.addAll(pedidos); // Agregar las nuevas tarjetas al adaptador
            } else {
                Toast.makeText(this, "No se encontraron pedidos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("activity_pedidos", "Error al cargar los pedidos", e);
            Toast.makeText(this, "Error al cargar las pedidos", Toast.LENGTH_SHORT).show();
        }
    }
}
