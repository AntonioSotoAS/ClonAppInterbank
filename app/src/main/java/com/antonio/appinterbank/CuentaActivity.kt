package com.antonio.appinterbank

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CuentaActivity : AppCompatActivity() {
    private lateinit var usuario: Usuario
    private val handler = Handler()
    private val logoutRunnable = Runnable {
        // Redireccionar al usuario a la pantalla de inicio de sesión
        val intent = Intent(this@CuentaActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        usuario = intent.getParcelableExtra("usuario") ?: Usuario("", "", "", emptyList())

        val producto = intent.getParcelableExtra<Producto>("producto")

        val imageViewMovimiento = findViewById<ImageView>(R.id.imageViewMovimiento)
        val tvCuentaSoles = findViewById<TextView>(R.id.tvCuentaSoles)
        val tvMonto = findViewById<TextView>(R.id.tvMonto)
        val tvNumero = findViewById<TextView>(R.id.tvNumero)

        if (producto != null) {
            // Configura los valores en las vistas
            imageViewMovimiento.setImageResource(producto.imagen)
            tvCuentaSoles.text = producto.tipoProducto
            tvMonto.text = producto.saldo
            tvNumero.text = producto.numeroCuenta

            // Configura los movimientos
            val movimientosList = producto.movimientos

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerMovimientos)
            val adapter = MovimientoAdapter(movimientosList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        val btnCopiar = findViewById<Button>(R.id.btnCopiar)
        val tvNumeroCuenta = findViewById<TextView>(R.id.tvNumero)

        // Copiar número de cuenta
        btnCopiar.setOnClickListener {
            val numeroCuenta = tvNumeroCuenta.text.toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("NumeroCuenta", numeroCuenta)
            clipboard.setPrimaryClip(clip)

            Toast.makeText(this, "Número de cuenta copiado", Toast.LENGTH_SHORT).show()
        }

        // Compartir número de cuenta
        val btnCompartir = findViewById<Button>(R.id.btnCompartir)

        btnCompartir.setOnClickListener {
            val numeroCuenta = tvNumeroCuenta.text.toString()
            val compartirIntent = Intent(Intent.ACTION_SEND)
            compartirIntent.type = "text/plain"
            compartirIntent.putExtra(Intent.EXTRA_TEXT, "Mi número de cuenta es: $numeroCuenta")

            startActivity(Intent.createChooser(compartirIntent, "Compartir número de cuenta"))
        }

        // Iniciar el temporizador de sesión
        startSessionTimer()
    }

    private fun startSessionTimer() {
        val intent = Intent(this@CuentaActivity, SessionTimerService::class.java)
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        // Detener el temporizador de sesión cuando la actividad se detiene
        handler.removeCallbacks(logoutRunnable)
    }
}
