package com.app.proyectoFinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.proyectoFinal.R;
import com.app.proyectoFinal.modelo.Tarjeta;

import java.util.List;

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.TarjetaViewHolder> {
    public final List<Tarjeta> tarjetaList;

    public TarjetaAdapter(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @NonNull
    @Override
    public TarjetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarjeta, parent, false);
        return new TarjetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarjetaViewHolder holder, int position) {
        Tarjeta tarjeta = tarjetaList.get(position);
        holder.nombreTextView.setText(tarjeta.getNombre());
        holder.numeroTextView.setText(String.valueOf(tarjeta.getNumeroTar()));
        holder.fechaCaducidadTextView.setText(tarjeta.getFechacad());
    }

    @Override
    public int getItemCount() {
        return tarjetaList.size();
    }

    public void clear() {
        tarjetaList.clear(); // Limpiar la lista de tarjetas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    public void addAll(List<Tarjeta> nuevasTarjetas) {
        tarjetaList.addAll(nuevasTarjetas); // Agregar nuevas tarjetas
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    public static class TarjetaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, numeroTextView, fechaCaducidadTextView;

        public TarjetaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            numeroTextView = itemView.findViewById(R.id.numeroTextView);
            fechaCaducidadTextView = itemView.findViewById(R.id.fechaCaducidadTextView);
        }
    }
}