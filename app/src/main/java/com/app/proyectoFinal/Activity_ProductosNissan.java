package com.app.proyectoFinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Activity_ProductosNissan extends AppCompatActivity {

    private RecyclerView recyclerViewS;
    private ProductoAdapter productoAdapter;
    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productos_nissan);  // Cambia el nombre del layout si es necesario
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAtrasProductos = findViewById(R.id.btnAtrasProducNissan);  // Cambia el ID del botón si es necesario
        btnAtrasProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar a la nueva actividad
                Intent intent = new Intent(Activity_ProductosNissan.this, Menu_Marcas.class);  // Cambia el nombre de la actividad si es necesario
                startActivity(intent);
            }
        });

        // Configurar el RecyclerView
        recyclerViewS = findViewById(R.id.recyclerViewS);
        recyclerViewS.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los productos desde la base de datos
        listaProductos = new cProducto(this).Select();

        // Ordenar la lista para que los productos de la marca 'Nissan' aparezcan primero
        Collections.sort(listaProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                // Colocar 'Nissan' al principio
                if (p1.getMarca().equalsIgnoreCase("Nissan") && !p2.getMarca().equalsIgnoreCase("Nissan")) {
                    return -1; // p1 (Nissan) va primero
                } else if (!p1.getMarca().equalsIgnoreCase("Nissan") && p2.getMarca().equalsIgnoreCase("Nissan")) {
                    return 1; // p2 (Nissan) va primero
                }
                return 0; // Si ambas marcas son iguales o no son 'Nissan', no se cambia el orden
            }
        });

        // Crear el adaptador y asignarlo al RecyclerView
        productoAdapter = new ProductoAdapter(listaProductos);
        recyclerViewS.setAdapter(productoAdapter);
    }

    // Adaptador para el RecyclerView
    public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

        private ArrayList<Producto> listaProductos;

        public ProductoAdapter(ArrayList<Producto> listaProductos) {
            this.listaProductos = listaProductos;
        }

        @NonNull
        @Override
        public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflar un layout vacío o genérico
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
            // Obteniendo el producto en la posición actual
            Producto producto = listaProductos.get(position);

            // Filtrar para mostrar solo los productos de la marca 'Nissan'
            if (producto.getMarca().equalsIgnoreCase("Nissan")) {
                // Mostrar el nombre y el precio del producto
                holder.itemView.setText(
                                "Producto: " + producto.getNombre() + "\n" +
                                "Precio: $ " + producto.getPrecio() + "\n" +
                                "Descripción: " + producto.getDescripcion() + "\n" +
                                "-----------------------------------------------------------"
                );
                holder.itemView.setVisibility(View.VISIBLE); // Asegurarse de que el elemento sea visible
            } else {
                // Si no es de la marca 'Nissan', lo ocultamos
                holder.itemView.setVisibility(View.GONE); // Ocultar el elemento
            }
        }

        @Override
        public int getItemCount() {
            return listaProductos.size();
        }

        // ViewHolder para manejar las vistas
        public class ProductoViewHolder extends RecyclerView.ViewHolder {
            TextView itemView;

            public ProductoViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = (TextView) itemView;  // SimpleListItem1 tiene un TextView por defecto
            }
        }
    }
}
