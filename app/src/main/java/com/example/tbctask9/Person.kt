package com.example.tbctask9

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Person(
    var name: String,
    var surname: String,
    val email: String,
    val id: String = UUID.randomUUID().toString()
) : Parcelable
{
    fun getFullName() = "$name $surname"
}

