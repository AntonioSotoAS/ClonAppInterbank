package com.antonio.appinterbank

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(private var productosList: List<Producto>) : RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder>() {
    private var isSaldoOculto = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ProductosViewHolder(view)
    }

    fun setSaldoOculto(saldoOculto: Boolean) {
        isSaldoOculto = saldoOculto
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val producto = productosList[position]
        holder.imgProducto.setImageResource(producto.imagen)
        holder.txtTipoProducto.text = producto.tipoProducto
        if (isSaldoOculto) {
            holder.txtMonto.text = "*******"
        } else {
            holder.txtMonto.text = producto.saldo
        }
        holder.txtTipoCuenta.text = producto.tipoCuenta

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CuentaActivity::class.java)
            intent.putExtra("producto", producto)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    inner class ProductosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtTipoProducto: TextView = itemView.findViewById(R.id.txtTipoProducto)
        val txtMonto: TextView = itemView.findViewById(R.id.txtMonto)
        val txtTipoCuenta: TextView = itemView.findViewById(R.id.txtTipoCuenta)
    }

    fun updateProductos(newProductosList: List<Producto>) {
        productosList = newProductosList
        notifyDataSetChanged()
    }

    fun getProductosList(): List<Producto> {
        return productosList
    }
}
