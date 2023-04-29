package com.psp.irutaconductor.models

import com.beust.klaxon.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private val klaxon = Klaxon()


data class Driver (
    val id: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val image: String? = null,
    val plateNumber: String? = null,
    val cooperativa: String? = null,
    val colorCar: String? = null,
    val brandCar: String ? = null,
) {

    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Driver>(json)
    }

}