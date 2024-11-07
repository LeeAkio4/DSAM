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

public class activity_ProductosTesla extends AppCompatActivity {

    private RecyclerView recyclerViewT;
    private ProductoAdapter productoAdapterT;
    private ArrayList<Producto> listaProductosT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productos_tesla);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewT = findViewById(R.id.recyclerViewT);
        recyclerViewT.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los productos desde la base de datos
        listaProductosT = new cProducto(this).Select();

        // Ordenar la lista para que los productos de la marca 'Tesla' aparezcan primero
        Collections.sort(listaProductosT, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                // Colocar 'Tesla' al principio
                if (p1.getMarca().equalsIgnoreCase("Tesla") && !p2.getMarca().equalsIgnoreCase("Tesla")) {
                    return -1;
                } else if (!p1.getMarca().equalsIgnoreCase("Tesla") && p2.getMarca().equalsIgnoreCase("Tesla")) {
                    return 1;
                }
                return 0;
            }
        });

        // Crear el adaptador y asignarlo al RecyclerView
        productoAdapterT = new ProductoAdapter(listaProductosT);
        recyclerViewT.setAdapter(productoAdapterT);

        // Configurar el botón de "Atrás"
        Button btnAtrasProductosT = findViewById(R.id.btnAtrasProducTesla2);
        btnAtrasProductosT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_ProductosTesla.this, Menu_Marcas.class);
                startActivity(intent);
            }
        });
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
            // Inflar el layout personalizado para cada producto
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
            return new ProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
            Producto producto = listaProductos.get(position);

            // Mostrar solo productos de la marca 'Tesla'
            if (producto.getMarca().equalsIgnoreCase("Tesla")) {
                holder.nombreTextView.setText(producto.getNombre());
                holder.descripcionTextView.setText(producto.getDescripcion());
                holder.precioTextView.setText("Precio: $" + producto.getPrecio());
                holder.stockTextView.setText("Stock: " + producto.getStock());
                holder.itemView.setVisibility(View.VISIBLE);

                // Configurar el clic para cada producto
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), activity_compra.class);
                        intent.putExtra("nombre", producto.getNombre());
                        intent.putExtra("descripcion", producto.getDescripcion());
                        intent.putExtra("precio", producto.getPrecio());
                        intent.putExtra("stock", producto.getStock());
                        v.getContext().startActivity(intent);
                    }
                });
            } else {
                holder.itemView.setVisibility(View.GONE); // Ocultar si no es de la marca Tesla
            }
        }

        @Override
        public int getItemCount() {
            return listaProductos.size();
        }

        // ViewHolder para manejar las vistas
        public class ProductoViewHolder extends RecyclerView.ViewHolder {
            TextView nombreTextView;
            TextView descripcionTextView;
            TextView precioTextView;
            TextView stockTextView;

            public ProductoViewHolder(@NonNull View itemView) {
                super(itemView);
                nombreTextView = itemView.findViewById(R.id.nombreTextView);
                descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
                precioTextView = itemView.findViewById(R.id.precioTextView);
                stockTextView = itemView.findViewById(R.id.stockTextView);
            }
        }
    }
}
