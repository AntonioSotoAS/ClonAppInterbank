package com.antonio.appinterbank

import android.os.Parcel
import android.os.Parcelable

data class Producto(
    val id: String,
    val imagen: Int,
    val tipoProducto: String,
    val saldo: String,
    val tipoCuenta: String,
    val movimientos: List<Movimiento>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Movimiento)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(imagen)
        parcel.writeString(tipoProducto)
        parcel.writeString(saldo)
        parcel.writeString(tipoCuenta)
        parcel.writeTypedList(movimientos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}