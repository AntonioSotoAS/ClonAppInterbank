package com.antonio.appinterbank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private Usuario usuario;
    private List<Producto> productosList;
    private Handler handler = new Handler();
    private Runnable logoutRunnable = new Runnable() {
        @Override
        public void run() {
            // Redireccionar al usuario a la pantalla de inicio de sesión
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private boolean isSaldoOculto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obtener el objeto Usuario del Intent
        usuario = getIntent().getParcelableExtra("usuario");
        if (usuario == null) {
            usuario = new Usuario("", "", "", new ArrayList<>());
        }

        setupRecyclerView();

        TextView txtAdditionalInfo = findViewById(R.id.txtAdditionalInfo);
        txtAdditionalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSaldoOculto = !isSaldoOculto;
                if (isSaldoOculto) {
                    // Ocultar el saldo
                    txtAdditionalInfo.setText("Ver saldo");
                } else {
                    // Mostrar el saldo
                    txtAdditionalInfo.setText("Ocultar saldo");
                }
                // Actualizar el RecyclerView
                updateRecyclerView();
            }
        });

        // Iniciar el temporizador de sesión
        startSessionTimer();

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Producto> nuevosProductos = new ArrayList<>();
                nuevosProductos.add(new Producto("4", R.mipmap.cuadrado_interbank, "Cuenta de Ahorros", "$400.00", "Cuenta Ahorro", "198111111118",
                        Arrays.asList(
                                new Movimiento("1", "Depósito", "2022-07-01", "S/ +656.50", "EGRESO"),
                                new Movimiento("2", "Retiro", "2022-07-02", "S/ -789.50", "INGRESO"),
                                new Movimiento("3", "Pago", "2022-07-03", "S/ +950.50", "INGRESO")
                        )
                ));

                nuevosProductos.add(new Producto("5", R.mipmap.cuadrado_interbank, "Cuenta de Credito", "$500.00", "Cuenta Corriente", "398657400879",
                        Arrays.asList(
                                new Movimiento("4", "Depósito", "2022-07-04", "S/ +1000.00", "EGRESO"),
                                new Movimiento("5", "Retiro", "2022-07-05", "S/ -500.00", "INGRESO"),
                                new Movimiento("6", "Pago", "2022-07-06", "S/ +750.00", "INGRESO")
                        )
                ));

                nuevosProductos.add(new Producto("6", R.mipmap.cuadrado_interbank, "Cuenta de Ahorros", "$600.00", "Cuenta Ahorro", "698800007880",
                        Arrays.asList(
                                new Movimiento("7", "Depósito", "2022-07-07", "S/ +800.00", "EGRESO"),
                                new Movimiento("8", "Retiro", "2022-07-08", "S/ -200.00", "INGRESO"),
                                new Movimiento("9", "Pago", "2022-07-09", "S/ +600.00", "INGRESO")
                        )
                ));

                // Agregar los nuevos productos a la lista de productos
                productosList.addAll(nuevosProductos);

                // Actualizar el RecyclerView con los nuevos productos
                updateRecyclerView();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPokemons);
        ProductosAdapter adapter = new ProductosAdapter(usuario.getProductos());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Guardar la referencia a la lista de productos en la variable
        productosList = usuario.getProductos();
    }

    private void updateRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPokemons);
        ProductosAdapter adapter = (ProductosAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setSaldoOculto(isSaldoOculto);
            adapter.notifyDataSetChanged();
        }
    }

    private void startSessionTimer() {
        Intent intent = new Intent(HomeActivity.this, SessionTimerService.class);
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Detener el temporizador de sesión cuando la actividad se detiene
        handler.removeCallbacks(logoutRunnable);
    }
}