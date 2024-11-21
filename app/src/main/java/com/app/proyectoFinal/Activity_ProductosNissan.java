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
        setContentView(R.layout.activity_productos_nissan);  // Cambia el layout a activity_productos_nissan
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAtrasProductos = findViewById(R.id.btnAtrasProducNissan);  // Cambia el ID del botón si es necesario
        btnAtrasProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ProductosNissan.this, Menu_Marcas.class);
                startActivity(intent);
            }
        });

        // Configurar el RecyclerView
        recyclerViewS = findViewById(R.id.recyclerViewS); // Cambia el ID si es necesario
        recyclerViewS.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los productos desde la base de datos
        listaProductos = new cProducto(this).Select();

        // Ordenar los productos para que 'Nissan' aparezca primero
        Collections.sort(listaProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                if (p1.getMarca().equalsIgnoreCase("Nissan") && !p2.getMarca().equalsIgnoreCase("Nissan")) {
                    return -1;
                } else if (!p1.getMarca().equalsIgnoreCase("Nissan") && p2.getMarca().equalsIgnoreCase("Nissan")) {
                    return 1;
                }
                return 0;
            }
        });

        // Crear el adaptador y asignarlo al RecyclerView
        productoAdapter = new ProductoAdapter(listaProductos);
        recyclerViewS.setAdapter(productoAdapter);
    }

    // Adaptador para el RecyclerView
    public class ProductoAdapter extends RecyclerView.Adapter<Activity_ProductosNissan.ProductoAdapter.ProductoViewHolder> {

        private ArrayList<Producto> listaProductos;

        public ProductoAdapter(ArrayList<Producto> listaProductos) {
            this.listaProductos = listaProductos;
        }

        @NonNull
        @Override
        public Activity_ProductosNissan.ProductoAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflar el layout personalizado para el producto
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
            return new Activity_ProductosNissan.ProductoAdapter.ProductoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Activity_ProductosNissan.ProductoAdapter.ProductoViewHolder holder, int position) {
            Producto producto = listaProductos.get(position);

            // Mostrar solo productos de la marca 'Nissan'
            if (producto.getMarca().equalsIgnoreCase("Nissan")) {
                holder.nombreTextView.setText(producto.getNombre());
                holder.marcaTextView.setText(producto.getMarca());
                holder.descripcionTextView.setText(producto.getDescripcion());
                holder.precioTextView.setText(String.format("$%.2f", producto.getPrecio()));
                holder.stockTextView.setText(String.valueOf(producto.getStock()));
                holder.anioTextView.setText(String.valueOf(producto.getAnio()));
                holder.colorTextView.setText(producto.getColor());
                holder.cilindrosTextView.setText(String.valueOf(producto.getCilindros()));
                holder.transmisionTextView.setText(producto.getTransmision());
                holder.tipomotorTextView.setText(producto.getTipomotor());
                holder.placaTextView.setText(producto.getPlaca());

                holder.itemView.setVisibility(View.VISIBLE);

                // Configurar el OnClickListener para cada producto
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), activity_compra.class);

                        intent.putExtra("codigoProducto", producto.getCodigo_prod());
                        intent.putExtra("nombre", producto.getNombre());
                        intent.putExtra("marca", producto.getMarca());
                        intent.putExtra("descripcion", producto.getDescripcion());
                        intent.putExtra("precio", producto.getPrecio());
                        intent.putExtra("stock", producto.getStock());
                        intent.putExtra("anio", producto.getAnio());
                        intent.putExtra("color", producto.getColor());
                        intent.putExtra("cilindros", producto.getCilindros());
                        intent.putExtra("transmision", producto.getTransmision());
                        intent.putExtra("tipomotor", producto.getTipomotor());
                        intent.putExtra("placa", producto.getPlaca());
                        intent.putExtra("imagen",producto.getImagen());
                        v.getContext().startActivity(intent);
                    }
                });

            } else {
                holder.itemView.setVisibility(View.GONE); // Ocultar si no es Kia
            }
        }

        @Override
        public int getItemCount() {
            return listaProductos.size();
        }

        // ViewHolder para manejar las vistas
        public class ProductoViewHolder extends RecyclerView.ViewHolder {
            TextView nombreTextView;
            TextView marcaTextView;
            TextView descripcionTextView;
            TextView precioTextView;
            TextView stockTextView;
            TextView anioTextView;
            TextView colorTextView;
            TextView cilindrosTextView;
            TextView transmisionTextView;
            TextView tipomotorTextView;
            TextView placaTextView;

            public ProductoViewHolder(@NonNull View itemView) {
                super(itemView);
                nombreTextView = itemView.findViewById(R.id.nombreTextView);
                marcaTextView = itemView.findViewById(R.id.marcaTextView);
                descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
                precioTextView = itemView.findViewById(R.id.precioTextView);
                stockTextView = itemView.findViewById(R.id.stockTextView);
                anioTextView = itemView.findViewById(R.id.anioTextView);
                colorTextView = itemView.findViewById(R.id.colorTextView);
                cilindrosTextView = itemView.findViewById(R.id.cilindrosTextView);
                transmisionTextView = itemView.findViewById(R.id.transmisionTextView);
                tipomotorTextView = itemView.findViewById(R.id.tipomotorTextView);
                placaTextView = itemView.findViewById(R.id.placaTextView);
            }
        }
    }
}

