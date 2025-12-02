package com.example.restaurante.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;
import com.example.restaurante.model.Mesa;
import com.example.restaurante.model.MesaStorage;
import com.example.restaurante.model.Plato;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    public static final String EXTRA_MESA_NUMERO = "mesa_numero";

    // referencia a la mesa q estoy editando para no perderla
    private Mesa mesa;
    // texto del total para ir viendo cuanto lleva pagando la mesa
    private MaterialTextView textTotal;
    // titulo q muestra cual mesa estoy abriendo
    private MaterialTextView textTitulo;
    // adaptador para mostrar los platos q van saliendo
    private PlatoAdapter platoAdapter;

    private final List<Plato> menu = Arrays.asList(
            new Plato("Ensalada verde", 8.50),
            new Plato("Crema de calabacín", 7.00),
            new Plato("Pasta al pesto", 11.50),
            new Plato("Hamburguesa veggie", 12.00),
            new Plato("Risotto de setas", 13.50),
            new Plato("Tarta de matcha", 5.50)
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        int numeroMesa = getIntent().getIntExtra(EXTRA_MESA_NUMERO, -1);
        mesa = MesaStorage.obtenerMesaPorNumero(numeroMesa);

        Toolbar toolbar = findViewById(R.id.toolbarMesa);
        toolbar.setNavigationOnClickListener(v -> finish());

        textTitulo = findViewById(R.id.textMesaTitulo);
        textTotal = findViewById(R.id.textTotalMesa);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlatos);
        MaterialButton buttonAdd = findViewById(R.id.buttonAdd);
        MaterialButton buttonRemove = findViewById(R.id.buttonRemove);

        if (mesa != null) {
            // pongo el titulo con el numero de mesa para tenerlo claro
            textTitulo.setText("Mesa " + mesa.getNumero());
            // preparo el adaptador con los platos existentes por si ya habia pedido algo
            platoAdapter = new PlatoAdapter(mesa.getPlatos());
            recyclerView.setAdapter(platoAdapter);
            actualizarTotal();
        }

        buttonAdd.setOnClickListener(v -> mostrarDialogoPlatos());
        buttonRemove.setOnClickListener(v -> eliminarPlato());
    }

    private void mostrarDialogoPlatos() {
        // armo la lista de nombres con precios para q se vea bonito en el dialogo
        CharSequence[] nombres = new CharSequence[menu.size()];
        for (int i = 0; i < menu.size(); i++) {
            Plato plato = menu.get(i);
            nombres[i] = plato.getNombre() + " - " + String.format("%.2f€", plato.getPrecio());
        }

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setItems(nombres, (dialog, which) -> agregarPlato(menu.get(which)))
                .show();
    }

    private void agregarPlato(Plato plato) {
        if (mesa == null) return;
        // intento agregar y si ya son 10 mejor aviso q no se puede mas
        boolean agregado = mesa.agregarPlato(plato);
        if (!agregado) {
            Toast.makeText(this, R.string.max_platos, Toast.LENGTH_SHORT).show();
            return;
        }
        platoAdapter.notifyItemInserted(mesa.getPlatos().size() - 1);
        actualizarTotal();
    }

    private void eliminarPlato() {
        if (mesa == null) return;
        // borro el ultimo plato para quitar lo mas reciente q pidieron
        boolean eliminado = mesa.eliminarUltimoPlato();
        if (!eliminado) {
            Toast.makeText(this, R.string.no_platos, Toast.LENGTH_SHORT).show();
            return;
        }
        platoAdapter.notifyItemRemoved(mesa.getPlatos().size());
        actualizarTotal();
    }

    private void actualizarTotal() {
        textTotal.setText(String.format("Total: %.2f€", mesa.calcularTotal()));
    }
}
