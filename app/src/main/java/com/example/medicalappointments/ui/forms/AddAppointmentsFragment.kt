package com.example.medicalappointments.ui.forms

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.medicalappointments.R
import java.text.SimpleDateFormat
import java.util.Locale

class AddAppointmentsFragment: Fragment() {
    private lateinit var titleField: EditText
    private lateinit var dateTimeField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var categoryField: Spinner
    private lateinit var doctorField: Spinner
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_appointment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleField = view.findViewById<EditText>(R.id.et_title)
        categoryField = view.findViewById<Spinner>(R.id.spinner_category)
        doctorField = view.findViewById<Spinner>(R.id.spinner_doctor)
        dateTimeField = view.findViewById<EditText>(R.id.et_appointment_datetime)
        descriptionField = view.findViewById<EditText>(R.id.et_appointment_description)
        submitButton = view.findViewById<Button>(R.id.btn_submit_appointment)

        setupDateTimePicker()
    }

    private fun setupDateTimePicker() {
        dateTimeField.setOnClickListener {
            val currentDateTime = Calendar.getInstance()

            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, day)

                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            // Round to nearest 30 minutes
                            var roundedMinute: Int
                            var adjustedHour = hour

                            when {
                                minute < 30 -> {
                                    roundedMinute = 30
                                }

                                else -> {
                                    roundedMinute = 0
                                    adjustedHour =
                                        (adjustedHour + 1) % 24 // %24 gives the HH a max value of 23
                                }
                            }

                            selectedDate.set(Calendar.HOUR_OF_DAY, adjustedHour)
                            selectedDate.set(Calendar.MINUTE, roundedMinute)

                            val formatted = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            dateTimeField.setText(formatted.format(selectedDate.time))
                        },
                        currentDateTime.get(Calendar.HOUR_OF_DAY),
                        currentDateTime.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                currentDateTime.get(Calendar.YEAR),
                currentDateTime.get(Calendar.MONTH),
                currentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}