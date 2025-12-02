package com.example.restaurante.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;
import com.example.restaurante.model.Mesa;
import com.example.restaurante.model.MesaStorage;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MesaAdapter.OnMesaClickListener {

    // adaptador listo para ir actualizando las mesas q voy tocando
    private MesaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // saco la lista de mesas para ver como van
        List<Mesa> mesas = MesaStorage.obtenerMesas();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTables);
        adapter = new MesaAdapter(mesas, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // refresco todo por si volvi y ya cambie algo en otra mesa
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMesaClick(Mesa mesa) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(TableActivity.EXTRA_MESA_NUMERO, mesa.getNumero());
        startActivity(intent);
    }
}
