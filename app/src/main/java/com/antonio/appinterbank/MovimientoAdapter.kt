package com.antonio.appinterbank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MovimientoAdapter(private val movimientosList: List<Movimiento>) : RecyclerView.Adapter<MovimientoAdapter.MovimientosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento, parent, false)
        return MovimientosViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovimientosViewHolder, position: Int) {
        val movimiento = movimientosList[position]
        holder.txtTipoMovimiento.text = movimiento.tipoMovimiento
        holder.txtFechaMovimiento.text = movimiento.fecha
        holder.txtMontoMovimiento.text = movimiento.montoMovimiento

        val colorResId = if (movimiento.tipo == "INGRESO") R.color.ingreso else R.color.egreso
        val color = ContextCompat.getColor(holder.itemView.context, colorResId)
        holder.txtMontoMovimiento.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return movimientosList.size
    }

    inner class MovimientosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTipoMovimiento: TextView = itemView.findViewById(R.id.txtTipoMovimiento)
        val txtFechaMovimiento: TextView = itemView.findViewById(R.id.txtFechaMovimiento)
        val txtMontoMovimiento: TextView = itemView.findViewById(R.id.txtMontoMovimiento)
    }
}

