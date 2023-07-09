package com.antonio.appinterbank

import android.os.Parcel
import android.os.Parcelable

data class Usuario(
    val numeroTarjeta: String,
    val dni: String,
    val contra: String,
    val productos: List<Producto>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Producto)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numeroTarjeta)
        parcel.writeString(dni)
        parcel.writeString(contra)
        parcel.writeTypedList(productos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}