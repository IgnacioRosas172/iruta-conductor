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

}