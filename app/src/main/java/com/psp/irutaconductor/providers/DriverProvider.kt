package com.psp.irutaconductor.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import com.psp.irutaconductor.models.Driver

class DriverProvider {

    val db = Firebase.firestore.collection("Drivers")

    fun crate(driver: Driver): Task<Void> {
        return db.document(driver.id!!).set(driver)
    }

    fun getDriver(idDriver: String): Task<DocumentSnapshot> {
        return db.document(idDriver).get()
    }

    fun actualizar(driver: Driver): Task<Void> {
        val map: MutableMap<String, Any> = HashMap()
        map["brandCar"] = driver?.brandCar!!
        map["colorCar"] = driver?.colorCar!!
        map["plateNumber"] = driver?.plateNumber!!
        map["cooperativa"] = driver?.cooperativa!!
        map["ruta"] = driver?.ruta!!

            return db.document(driver?.id!!).update(map)
    }

}