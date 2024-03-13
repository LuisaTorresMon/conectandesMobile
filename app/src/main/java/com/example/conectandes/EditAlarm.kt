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
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat

class EditAlarm : AppCompatActivity() {
    private lateinit var editTextDate: EditText
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var textInputLayoutDescription: TextInputLayout
    private lateinit var editTextTime: EditText
    private lateinit var editTextHour: EditText
    private lateinit var editTextMinute: EditText
    private lateinit var editTextAM: EditText
    private lateinit var editTextPM: EditText

    companion object {
        const val MAP_REQUEST_CODE = 1
    }

    private lateinit var textViewUbicacion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alarm)

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
        //editTextTime = findViewById(R.id.editTextTime)


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
        spinner.setSelection(2)
        spinner_group.setSelection(1)
        spinner_member.setSelection(1)

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

        editTextHour = findViewById(R.id.editTextHour)
        editTextMinute = findViewById(R.id.editTextMinute)
        editTextAM = findViewById(R.id.editTextAM)
        editTextPM = findViewById(R.id.editTextPM)

        editTextHour.setOnClickListener {
            mostrarTimePicker(isHourPicker = true)
        }

        editTextMinute.setOnClickListener {
            mostrarTimePicker(isHourPicker = false)
        }

        /*editTextTime.setOnClickListener {
            mostrarTimePicker()
        }*/
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

    private fun mostrarTimePicker(isHourPicker: Boolean) {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Selecciona la hora")
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .build()

        picker.addOnPositiveButtonClickListener {
            val selectedHour = picker.hour
            val selectedMinute = picker.minute
            if (selectedHour >= 12) {
                editTextAM.setText("AM")
                editTextAM.setBackgroundColor(ContextCompat.getColor(this, R.color.inactive_text))

                editTextPM.setText("PM")
                editTextPM.setBackgroundColor(ContextCompat.getColor(this, R.color.active_buttons))
            } else {
                editTextAM.setText("AM")
                editTextAM.setBackgroundColor(ContextCompat.getColor(this, R.color.active_buttons))

                editTextPM.setText("PM")
                editTextPM.setBackgroundColor(ContextCompat.getColor(this, R.color.inactive_text))
            }

            val displayHour = if (selectedHour > 12) selectedHour % 12 else selectedHour

            if (isHourPicker) {
                editTextHour.setText(String.format("%02d", selectedHour))
                editTextMinute.setText(String.format("%02d", selectedMinute))
            } else {
                editTextHour.setText(String.format("%02d", selectedHour))
                editTextMinute.setText(String.format("%02d", selectedMinute))
            }
        }

        picker.show(supportFragmentManager, "tag")
    }

}