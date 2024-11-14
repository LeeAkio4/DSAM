package com.app.proyectoFinal.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.R;
import com.app.proyectoFinal.activity_detalle_pedido;
import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    private List<Pedido> pedidos;
    private Context context;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        Log.d("PedidoAdapter", "Cargando pedido: " + pedido.getFechaPed() + ", " + pedido.getEstado() + ", " + pedido.getTotal());

        // Establecer los datos de los TextViews para pedido
        holder.codigoPedido.setText(String.valueOf(pedido.getCodigo_p()));
        holder.codigoUsuario.setText(String.valueOf(pedido.getCodigo_u()));
        holder.codigoProducto.setText(String.valueOf(pedido.getCodigo_prod()));
        holder.fechaPedido.setText(pedido.getFechaPed());
        holder.estado.setText(pedido.getEstado());
        holder.total.setText(pedido.getTotal());

        // Obtener los datos del producto usando el código de producto
        cProducto controladorProducto = new cProducto(context);
        Producto producto = controladorProducto.obtenerProductoPorCodigo(pedido.getCodigo_prod());

        if (producto != null) {
            holder.nombreProducto.setText(producto.getNombre());
            holder.placaProducto.setText(producto.getPlaca());
            holder.marcaProducto.setText(producto.getMarca());
            holder.anioProducto.setText(String.valueOf(producto.getAnio()));
        }

        // Configurar el OnClickListener para cada pedido
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), activity_detalle_pedido.class);
                intent.putExtra("codigo_pedido", pedido.getCodigo_p());
                intent.putExtra("codigo_usuario", pedido.getCodigo_u());
                intent.putExtra("codigo_prod", pedido.getCodigo_prod());
                intent.putExtra("fecha_pedido", pedido.getFechaPed());
                intent.putExtra("estado", pedido.getEstado());
                intent.putExtra("total", pedido.getTotal());
                intent.putExtra("nombre_prod", producto.getNombre());
                intent.putExtra("placa_prod", producto.getPlaca());
                intent.putExtra("marca_prod", producto.getMarca());
                intent.putExtra("anio_prod", producto.getAnio());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public void clear() {
        pedidos.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Pedido> nuevosPedidos) {
        pedidos.addAll(nuevosPedidos);
        notifyDataSetChanged();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView codigoPedido, codigoUsuario, codigoProducto, fechaPedido, estado, total;
        TextView nombreProducto, placaProducto, marcaProducto, anioProducto;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            codigoPedido = itemView.findViewById(R.id.codigoPedido);
            codigoUsuario = itemView.findViewById(R.id.codigoUsuario);
            codigoProducto = itemView.findViewById(R.id.codigoProducto);
            fechaPedido = itemView.findViewById(R.id.fechaPedido);
            estado = itemView.findViewById(R.id.estado);
            total = itemView.findViewById(R.id.total);

            // Campos adicionales para datos del Producto
            nombreProducto = itemView.findViewById(R.id.textViewNombre);
            placaProducto = itemView.findViewById(R.id.textViewPlaca);
            marcaProducto = itemView.findViewById(R.id.textViewMarca);
            anioProducto = itemView.findViewById(R.id.textViewAnio);
        }
    }
}
