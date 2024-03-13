package com.example.conectandes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.net.Uri
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class CreateAlarm : AppCompatActivity() {
    private lateinit var editTextDate: EditText
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var textInputLayoutDescription: TextInputLayout

    companion object {
        const val MAP_REQUEST_CODE = 1
    }

    private lateinit var textViewUbicacion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)

        val spinner: Spinner = findViewById(R.id.spinnerOptions)
        val spinner_group: Spinner = findViewById(R.id.spinnerGroup)
        val spinner_member: Spinner = findViewById(R.id.spinnerMember)
        val opciones = resources.getStringArray(R.array.opciones_spinner)
        val optionsGroup = resources.getStringArray(R.array.opciones_spinner_group)
        val optionsMember = resources.getStringArray(R.array.opciones_spinner_member)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        val adapter_group = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsGroup)
        val adapter_member = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsMember)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        textViewUbicacion = findViewById(R.id.textViewUbicacion)

        // Especifica el diseño que se utilizará para mostrar las opciones en la lista desplegable
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter_member.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Establece el adaptador en el Spinner
        spinner.adapter = adapter
        spinner_group.adapter = adapter_group
        spinner_member.adapter = adapter_member

        // Establece el valor inicialmente seleccionado (por ejemplo, la primera opción)
        spinner.setSelection(0)
        spinner_group.setSelection(0)
        spinner_member.setSelection(0)

        editTextDate = findViewById(R.id.editTextDate)
        textInputLayout = findViewById(R.id.textInputLayout)
        textInputLayoutDescription = findViewById(R.id.textInputLayoutDescription)

        editTextDescription.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Cuando el EditText obtiene el foco, establece el hint del TextInputLayout como nulo.
                textInputLayoutDescription.hint = null
            } else {
                // Cuando el EditText pierde el foco, restablece el hint del TextInputLayout.
                textInputLayoutDescription.hint = getString(R.string.descripcion)
            }
        }

        val btnSaveAlarm=findViewById<Button>(R.id.buttonSave);
        val btnCancelAlarm=findViewById<Button>(R.id.buttonCancel);

        btnSaveAlarm.setOnClickListener {
            val intentMainActivity = Intent(this, MainActivity::class.java);
            startActivity(intentMainActivity);
        }

        btnCancelAlarm.setOnClickListener {
            val intentMainActivity = Intent(this, MainActivity::class.java);
            startActivity(intentMainActivity);
        }
    }


    fun showDatePickerDialog(view: View) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                editTextDate.setText(formattedDate)
            }
        }, year, month, day)

        datePickerDialog.show()
    }

    fun openGoogleMaps(view: View) {
        val geoUri = "geo:lat,lon?q=lat,lon(Label)"
        val gmmIntentUri: Uri = Uri.parse(geoUri)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(mapIntent, CreateAlarm.MAP_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CreateAlarm.MAP_REQUEST_CODE) {
            textViewUbicacion.text = "1,02020303 - 34,095867777"

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val locationInfo = data?.getStringExtra("key_location_info")
                    textViewUbicacion.text = locationInfo
                }
                Activity.RESULT_CANCELED -> {
                    textViewUbicacion.text = "No se pudo obtener ubicación"
                }
            }
        }
    }

}