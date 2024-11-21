package com.app.proyectoFinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.R;
import com.app.proyectoFinal.modelo.Entradas;

import java.text.SimpleDateFormat;
import java.util.List;

public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.EntradaViewHolder> {
    private final List<Entradas> entradasList;

    public EntradaAdapter(List<Entradas> entradasList) {
        this.entradasList = entradasList;
    }

    @NonNull
    @Override
    public EntradaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entradas, parent, false);
        return new EntradaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradaViewHolder holder, int position) {
        Entradas entrada = entradasList.get(position);

        // Asignamos valores a los TextViews de item_entradas
        holder.idEntradaTextView.setText(String.valueOf(entrada.getIdEntrada()));
        holder.idProductoTextView.setText(String.valueOf(entrada.getIdProducto()));
        holder.cantidadTextView.setText(String.valueOf(entrada.getCantidad()));
        holder.totalEntradaTextView.setText(String.format("%.2f", entrada.getTotalEntrada()));

        // Formateamos la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(entrada.getFechaEntrada());
        holder.fechaEntradaTextView.setText(fechaFormateada);
    }

    @Override
    public int getItemCount() {
        return entradasList.size();
    }

    public void clear() {
        entradasList.clear(); // Limpiar la lista de entradas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    public void addAll(List<Entradas> nuevasEntradas) {
        entradasList.addAll(nuevasEntradas); // Agregar nuevas entradas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    public static class EntradaViewHolder extends RecyclerView.ViewHolder {
        TextView idEntradaTextView, idProductoTextView, cantidadTextView, totalEntradaTextView, fechaEntradaTextView;

        public EntradaViewHolder(@NonNull View itemView) {
            super(itemView);
            idEntradaTextView = itemView.findViewById(R.id.idEntrada);
            idProductoTextView = itemView.findViewById(R.id.idProducto);
            cantidadTextView = itemView.findViewById(R.id.cantidad);
            totalEntradaTextView = itemView.findViewById(R.id.totalEntrada);
            fechaEntradaTextView = itemView.findViewById(R.id.fechaEntrada);
        }
    }
}
