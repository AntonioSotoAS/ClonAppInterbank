package com.antonio.appinterbank
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder

class SessionTimerService : Service() {
    private val handler = Handler()
    private val logoutRunnable = Runnable {
        // Redireccionar al usuario a la pantalla de inicio de sesión
        val intent = Intent(this@SessionTimerService, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        stopSelf() // Detener el servicio
    }

    override fun onCreate() {
        super.onCreate()
        // Iniciar el temporizador cuando se crea el servicio
        handler.postDelayed(logoutRunnable, 120000) // 2 minutos (120000 milisegundos)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // No hacer nada en este método, ya que el temporizador se inicia en onCreate()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener el temporizador y eliminar la tarea pendiente cuando se destruye el servicio
        handler.removeCallbacks(logoutRunnable)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
