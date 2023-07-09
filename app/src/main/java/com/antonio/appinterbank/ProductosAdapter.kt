package com.antonio.appinterbank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(private val productosList: List<Producto>) : RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.antonio.appinterbank.R.layout.item_productos, parent, false)
        return ProductosViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val producto = productosList[position]
        holder.imgProducto.setImageResource(producto.imagen)
        holder.txtTipoProducto.text = producto.tipoProducto
        holder.txtMonto.text = producto.saldo
        holder.txtTipoCuenta.text = producto.tipoCuenta
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    inner class ProductosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(com.antonio.appinterbank.R.id.imgProducto)
        val txtTipoProducto: TextView = itemView.findViewById(com.antonio.appinterbank.R.id.txtTipoProducto)
        val txtMonto: TextView = itemView.findViewById(com.antonio.appinterbank.R.id.txtMonto)
        val txtTipoCuenta: TextView = itemView.findViewById(com.antonio.appinterbank.R.id.txtTipoCuenta)
    }
}

