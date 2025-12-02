package com.example.restaurante.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;
import com.example.restaurante.model.Mesa;

import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> {

    public interface OnMesaClickListener {
        void onMesaClick(Mesa mesa);
    }

    // lista de mesas q voy mostrando en la pantalla principal
    private final List<Mesa> mesas;
    // callback para cuando toque una mesa y quiera entrar a verla
    private final OnMesaClickListener listener;

    public MesaAdapter(List<Mesa> mesas, OnMesaClickListener listener) {
        this.mesas = mesas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa, parent, false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesaViewHolder holder, int position) {
        Mesa mesa = mesas.get(position);
        holder.textMesaNombre.setText("Mesa " + mesa.getNumero());
        holder.textPlatos.setText("Platos: " + mesa.getPlatos().size());
        holder.textTotal.setText(String.format("Total: %.2f€", mesa.calcularTotal()));
        // dejo el click listo para saltar al detalle de la mesa q toque
        holder.itemView.setOnClickListener(v -> listener.onMesaClick(mesa));
    }

    @Override
    public int getItemCount() {
        return mesas.size();
    }

    public void actualizarMesa(int posicion) {
        // aviso al adaptador q esta mesa cambio para q se refresque
        notifyItemChanged(posicion);
    }

    static class MesaViewHolder extends RecyclerView.ViewHolder {
        final TextView textMesaNombre;
        final TextView textPlatos;
        final TextView textTotal;

        MesaViewHolder(@NonNull View itemView) {
            super(itemView);
            textMesaNombre = itemView.findViewById(R.id.textMesaNombre);
            textPlatos = itemView.findViewById(R.id.textPlatos);
            textTotal = itemView.findViewById(R.id.textTotal);
        }
    }
}
