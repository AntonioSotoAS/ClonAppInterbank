package com.antonio.appinterbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val usuario = Usuario(
            numeroTarjeta = "123456789",
            dni = "12345678",
            contra = "password",
            productos = listOf(
                Producto(
                    id = "1",
                    imagen = R.mipmap.cuadrado_interbank,
                    tipoProducto = "Cuenta de Ahorros",
                    saldo = "$500.00",
                    tipoCuenta = "Cuenta Ahorro",
                    numeroCuenta = "998878787877",
                    movimientos = listOf(
                        Movimiento("1", "Depósito", "2022-07-01", "S/ +656.50", "EGRESO"),
                        Movimiento("2", "Retiro", "2022-07-02", "S/ -789.50", "INGRESO"),
                        Movimiento("3", "Pago", "2022-07-03", "S/ +950.50", "INGRESO")
                    )
                ),
                // Agregar más productos aquí
            )
        )

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val edtNumeroTarjeta = findViewById<TextInputLayout>(R.id.edt_numero_tarjeta)
        val edtDni = findViewById<TextInputLayout>(R.id.edt_dni)
        val edtContra = findViewById<TextInputLayout>(R.id.edt_contra)



        // Autenticación del usuario
        btnLogin.setOnClickListener {
            val inputNumeroTarjeta = edtNumeroTarjeta.editText?.text.toString()
            val inputDni = edtDni.editText?.text.toString()
            val inputContra = edtContra.editText?.text.toString()


            if (inputNumeroTarjeta == usuario.numeroTarjeta && inputDni == usuario.dni && inputContra == usuario.contra) {
                // Autenticación exitosa, iniciar la siguiente actividad y pasar los datos del usuario
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            } else {
                // Autenticación fallida, mostrar un mensaje de error
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}