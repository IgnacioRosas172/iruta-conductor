package com.psp.irutaconductor.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.psp.i_rutaconductor.providers.AuthProvider
import com.psp.irutaconductor.databinding.ActivityProfileBinding
import com.psp.irutaconductor.models.Driver
import com.psp.irutaconductor.providers.DriverProvider
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    val driverProvider = DriverProvider()
    val authProvider = AuthProvider()

    private var imageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        getDriver()
        binding.imageViewBack.setOnClickListener { finish() }
        binding.btnActualizar.setOnClickListener { actualizarInfo() }
    }

private fun actualizarInfo () {
    val marcaAutobus = binding.textFieldCarBrand.text.toString()
    val colorAutobus = binding.textFieldCarColor.text.toString()
    val placaAutobus = binding.textFieldCarPlate.text.toString()
    val cooperativaAutobus = binding.textFieldCooperativaP.text.toString()
    val rutaAutobus = binding.textFieldRutaP.text.toString()

    val driver = Driver(
        id = authProvider.getId(),
        brandCar = marcaAutobus,
        colorCar = colorAutobus,
        plateNumber = placaAutobus,
        cooperativa = cooperativaAutobus,
        ruta = rutaAutobus,
    )

    driverProvider.actualizar(driver).addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(this@ProfileActivity, "Datos actualizados correctamente", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this@ProfileActivity, "Datos no actualizados", Toast.LENGTH_LONG).show()
        }
    }

}


    private fun getDriver() {
        driverProvider.getDriver(authProvider.getId()).addOnSuccessListener { document ->
            if (document.exists()) {
                val driver = document.toObject(Driver::class.java)
                binding.textViewEmail.text = driver?.email
                binding.textFieldName.setText(driver?.name)
                binding.textFieldLastname.setText(driver?.lastname)
                binding.textFieldPhone.setText(driver?.phone)
                binding.textFieldCarBrand.setText(driver?.brandCar)
                binding.textFieldCarColor.setText(driver?.colorCar)
                binding.textFieldCarPlate.setText(driver?.plateNumber)
            }
        }
    }






}