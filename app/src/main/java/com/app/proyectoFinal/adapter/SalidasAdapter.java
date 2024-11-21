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
import com.app.proyectoFinal.controlador.cPedido;
import com.app.proyectoFinal.controlador.cProducto;
import com.app.proyectoFinal.modelo.Pedido;
import com.app.proyectoFinal.modelo.Producto;
import com.app.proyectoFinal.modelo.Salidas;

import java.text.SimpleDateFormat;
import java.util.List;

public class SalidasAdapter extends RecyclerView.Adapter<SalidasAdapter.SalidaViewHolder> {

    private List<Salidas> salidas;
    private Context context;

    public SalidasAdapter(Context context, List<Salidas> salidas) {
        this.context = context;
        this.salidas = salidas;
    }

    @Override
    public SalidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el diseño item_salidas.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salidas, parent, false);
        return new SalidaViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SalidaViewHolder holder, int position) {
        Salidas salida = salidas.get(position);
        Log.d("SalidaAdapter", "Cargando salida: " + salida.getFechaSalida() + ", Total: " + salida.getTotalSalida());

        // Establecer los datos de los TextViews para la salida
        holder.codigoSalida.setText(String.valueOf(salida.getIdSalida()));
        holder.precioUnitario.setText(String.format("$%.2f", salida.getPrecioUnitario()));
        holder.cantidadSalida.setText(String.valueOf(salida.getCantidad()));
        holder.totalSalida.setText(String.format("$%.2f", salida.getTotalSalida()));

        // Formatear la fecha para que solo tenga el formato "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(salida.getFechaSalida());

        holder.fechaEntrada.setText(fechaFormateada);  // Mostrar solo la fecha, sin horas

        // Obtener el pedido por ID
        cPedido controladorPedido = new cPedido(context);
        Pedido pedido = controladorPedido.obtenerPedidoPorId(salida.getIdPedido());

        if (pedido != null) {
            // Establecer los datos de usuario y producto si el pedido fue encontrado
            holder.codigoUsuario.setText(String.valueOf(pedido.getCodigo_u())); // Código de usuario del pedido
            holder.codigoProducto.setText(String.valueOf(pedido.getCodigo_prod())); // Código de producto del pedido

            // Obtener los datos del producto usando el id de producto (si es necesario)
            cProducto controladorProducto = new cProducto(context);
            Producto producto = controladorProducto.obtenerProductoPorCodigo(pedido.getCodigo_prod());

            if (producto != null) {
                holder.nombreProducto.setText(producto.getMarca());
                holder.placaProducto.setText(producto.getPlaca());
            }
        } else {
            // Si no se encuentra el pedido, mostrar valores predeterminados o vacío
            holder.codigoUsuario.setText("No encontrado");
            holder.codigoProducto.setText("No encontrado");
        }
    }



    @Override
    public int getItemCount() {
        return salidas.size();
    }

    public void clear() {
        salidas.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Salidas> nuevasSalidas) {
        salidas.addAll(nuevasSalidas);
        notifyDataSetChanged();
    }

    public class SalidaViewHolder extends RecyclerView.ViewHolder {
        TextView codigoSalida, codigoUsuario, codigoProducto, cantidadSalida, precioUnitario, totalSalida, fechaEntrada;
        TextView nombreProducto, placaProducto;

        public SalidaViewHolder(View itemView) {
            super(itemView);
            codigoSalida = itemView.findViewById(R.id.codigoSalida);
            codigoUsuario = itemView.findViewById(R.id.codigoUsuario);
            codigoProducto = itemView.findViewById(R.id.codigoProducto);
            cantidadSalida = itemView.findViewById(R.id.cantidadSalida);
            precioUnitario = itemView.findViewById(R.id.preciounit);
            totalSalida = itemView.findViewById(R.id.totalSalida);
            fechaEntrada = itemView.findViewById(R.id.fechaEntrada);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            placaProducto = itemView.findViewById(R.id.placaProducto);
        }
    }
}
