package com.example.restaurante.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;
import com.example.restaurante.model.Plato;

import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {

    // la lista de platos q ya pidieron en esta mesa
    private final List<Plato> platos;

    public PlatoAdapter(List<Plato> platos) {
        this.platos = platos;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plato, parent, false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        Plato plato = platos.get(position);
        holder.textNombre.setText(plato.getNombre());
        holder.textPrecio.setText(String.format("%.2f€", plato.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    static class PlatoViewHolder extends RecyclerView.ViewHolder {
        final TextView textNombre;
        final TextView textPrecio;

        PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombrePlato);
            textPrecio = itemView.findViewById(R.id.textPrecioPlato);
        }
    }
}
