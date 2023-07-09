package com.antonio.appinterbank

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    private lateinit var usuario: Usuario
    private val handler = Handler()
    private val logoutRunnable = Runnable {
        // Redireccionar al usuario a la pantalla de inicio de sesión
        val intent = Intent(this@HomeActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Obtener el objeto Usuario del Intent
        usuario = intent.getParcelableExtra("usuario") ?: Usuario("", "", "", emptyList())

        setupRecyclerView()

        var isSaldoOculto = false

        val txtAdditionalInfo = findViewById<TextView>(R.id.txtAdditionalInfo)
        txtAdditionalInfo.setOnClickListener {
            isSaldoOculto = !isSaldoOculto
            if (isSaldoOculto) {
                // Ocultar el saldo
                txtAdditionalInfo.text = "Ver saldo"
                // Actualizar la lista de productos con montos ocultos
                val productosListOcultarSaldo = usuario.productos.map { producto ->
                    producto.copy(saldo = "*******")
                }
                updateRecyclerView(productosListOcultarSaldo)
            } else {
                // Mostrar el saldo
                txtAdditionalInfo.text = "Ocultar saldo"
                // Actualizar la lista de productos con montos visibles
                updateRecyclerView(usuario.productos)
            }
        }

        // Iniciar el temporizador de sesión
        startSessionTimer()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPokemons)
        val adapter = ProductosAdapter(usuario.productos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun updateRecyclerView(productosList: List<Producto>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPokemons)
        val adapter = recyclerView.adapter as? ProductosAdapter
        adapter?.updateProductos(productosList)
    }

    private fun startSessionTimer() {
        val intent = Intent(this@HomeActivity, SessionTimerService::class.java)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        // Detener el temporizador de sesión cuando la actividad se detiene
        handler.removeCallbacks(logoutRunnable)
    }
}
