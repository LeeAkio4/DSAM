package com.app.proyectoFinal.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.R;
import com.app.proyectoFinal.activity_compra;
import com.app.proyectoFinal.activity_detalle_pedido;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    private List<Pedido> pedidos;

    public PedidoAdapter(List<Pedido> pedidos) {
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

        // Establecer los datos de los TextViews
        holder.codigoPedido.setText(String.valueOf(pedido.getCodigo_p()));
        holder.codigoUsuario.setText(String.valueOf(pedido.getCodigo_u()));
        holder.codigoProducto.setText(String.valueOf(pedido.getCodigo_prod()));
        holder.fechaPedido.setText(pedido.getFechaPed());
        holder.estado.setText(pedido.getEstado());
        holder.total.setText(pedido.getTotal());

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

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public void clear() {
        pedidos.clear(); // Limpiar la lista de tarjetas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    public void addAll(List<Pedido> nuevosPedidos) {
        pedidos.addAll(nuevosPedidos); // Agregar nuevas tarjetas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }
    public class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView codigoPedido, codigoUsuario, codigoProducto, fechaPedido, estado, total;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            // Vincular los TextViews del layout
            codigoPedido = itemView.findViewById(R.id.codigoPedido);
            codigoUsuario = itemView.findViewById(R.id.codigoUsuario);
            codigoProducto = itemView.findViewById(R.id.codigoProducto);
            fechaPedido = itemView.findViewById(R.id.fechaPedido);
            estado = itemView.findViewById(R.id.estado);
            total = itemView.findViewById(R.id.total);
        }
    }
}
