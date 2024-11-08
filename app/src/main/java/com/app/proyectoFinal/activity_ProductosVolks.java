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

public class activity_ProductosVolks extends AppCompatActivity {

    private RecyclerView recyclerViewS;
    private activity_ProductosVolks.ProductoAdapter productoAdapter;
    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productos_volks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAtrasProductos = findViewById(R.id.btnAtrasProducVolks);
        btnAtrasProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar a la nueva actividad
                Intent intent = new Intent(activity_ProductosVolks.this, Menu_Marcas.class);
                startActivity(intent);
            }
        });

        // Configurar el RecyclerView
        recyclerViewS = findViewById(R.id.recyclerViewS);
        recyclerViewS.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los productos desde la base de datos
        listaProductos = new cProducto(this).Select();

        Collections.sort(listaProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                // Colocar 'Volks' al principio
                if (p1.getMarca().equalsIgnoreCase("Volks") && !p2.getMarca().equalsIgnoreCase("Volks")) {
                    return -1; // p1 (Volks) va primero
                } else if (!p1.getMarca().equalsIgnoreCase("Volks") && p2.getMarca().equalsIgnoreCase("Volks")) {
                    return 1; // p2 (Volks) va primero
                }
                return 0; // Si ambas marcas son iguales o no son 'Suzuki', no se cambia el orden
            }

        });
        productoAdapter = new activity_ProductosVolks.ProductoAdapter(listaProductos);
        recyclerViewS.setAdapter(productoAdapter);

    }

    // Adaptador para el RecyclerView
    public class ProductoAdapter extends RecyclerView.Adapter<activity_ProductosVolks.ProductoAdapter.ProductoViewHolder> {

        private ArrayList<Producto> listaProductos;

        public ProductoAdapter(ArrayList<Producto> listaProductos) {
            this.listaProductos = listaProductos;
        }

        @NonNull
        @Override
        public activity_ProductosVolks.ProductoAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflar el layout personalizado para el producto
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
            return new activity_ProductosVolks.ProductoAdapter.ProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull activity_ProductosVolks.ProductoAdapter.ProductoViewHolder holder, int position) {
            Producto producto = listaProductos.get(position);

            // Mostrar solo productos de la marca 'Volks'
            if (producto.getMarca().equalsIgnoreCase("Volks")) {
                holder.nombreTextView.setText(producto.getNombre());
                holder.descripcionTextView.setText(producto.getDescripcion());
                holder.precioTextView.setText("Precio: $" + producto.getPrecio());
                holder.stockTextView.setText("Stock: " + producto.getStock());

                holder.itemView.setVisibility(View.VISIBLE);

                // Configurar el OnClickListener para cada producto
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), activity_compra.class);

                        intent.putExtra("codigoProducto", producto.getCodigo_prod());
                        intent.putExtra("nombre", producto.getNombre());
                        intent.putExtra("descripcion", producto.getDescripcion());
                        intent.putExtra("precio", producto.getPrecio());
                        intent.putExtra("stock", producto.getStock());

                        v.getContext().startActivity(intent);
                    }
                });

            } else {
                holder.itemView.setVisibility(View.GONE); // Ocultar si no es Suzuki
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
                stockTextView= itemView.findViewById(R.id.stockTextView);
            }
        }
    }
}

