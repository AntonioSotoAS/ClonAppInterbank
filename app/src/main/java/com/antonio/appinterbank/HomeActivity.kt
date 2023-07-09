package com.antonio.appinterbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val productosList = listOf(
            Producto(
                "1",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros",
                "$500.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("1", "Depósito", "2022-07-01", "Transferencia"),
                    Movimiento("2", "Retiro", "2022-07-02", "Efectivo"),
                    Movimiento("3", "Pago", "2022-07-03", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "2",
                R.mipmap.cuadrado_interbank,
                "Tarjeta de Crédito",
                "$1000.00",
                "Tarjeta de Crédito",
                listOf(
                    Movimiento("4", "Compra", "2022-07-04", "Comercio Electrónico"),
                    Movimiento("5", "Pago", "2022-07-05", "Transferencia"),
                    Movimiento("6", "Cargo", "2022-07-06", "Comercio Local")
                )
            ),
            Producto(
                "3",
                R.mipmap.cuadrado_interbank,
                "Cuenta Corriente",
                "$2000.00",
                "Cuenta Corriente",
                listOf(
                    Movimiento("7", "Transferencia", "2022-07-07", "Transferencia"),
                    Movimiento("8", "Pago", "2022-07-08", "Tarjeta de Crédito"),
                    Movimiento("9", "Depósito", "2022-07-09", "Transferencia")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            ),
            Producto(
                "4",
                R.mipmap.cuadrado_interbank,
                "Cuenta de Ahorros USD",
                "$1000.00",
                "Cuenta Ahorro",
                listOf(
                    Movimiento("10", "Depósito", "2022-07-10", "Transferencia"),
                    Movimiento("11", "Retiro", "2022-07-11", "Efectivo"),
                    Movimiento("12", "Pago", "2022-07-12", "Tarjeta de Crédito")
                )
            )
        )






        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPokemons)
        val adapter = ProductosAdapter(productosList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }
}