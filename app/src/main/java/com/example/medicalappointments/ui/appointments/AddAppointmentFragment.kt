package com.example.medicalappointments.ui.appointments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.medicalappointments.R
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.data.repositories.SpecialtyRepository
import com.example.medicalappointments.data.repositories.UserRepository
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddAppointmentFragment: Fragment() {
    private lateinit var specialtySpinner: Spinner
    private lateinit var doctorSpinner: Spinner

    private var specialties: List<Pair<Long, String>> = emptyList()

    private lateinit var categorySpinner: Spinner
    private var selectedCategory: Category? = null

    private var selectedSpecialtyId: Long? = null
    private var selectedDoctorId: Long? = null

    private lateinit var titleEditText: EditText
    private lateinit var dateTimeEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var submitButton: Button
    private var selectedDateTime: LocalDateTime? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_appointment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        specialtySpinner = view.findViewById<Spinner>(R.id.spinner_specialty)
        doctorSpinner = view.findViewById<Spinner>(R.id.spinner_doctor)
        categorySpinner = view.findViewById(R.id.spinner_category)

        titleEditText = view.findViewById(R.id.et_title)
        dateTimeEditText = view.findViewById(R.id.et_appointment_datetime)
        descriptionEditText = view.findViewById(R.id.et_appointment_description)
        submitButton = view.findViewById(R.id.btn_submit_appointment)

        loadSpecialties()
        loadCategories()

        dateTimeEditText.setOnClickListener {
            showDateTimePicker()
        }

        submitButton.setOnClickListener {
            submitAppointment()
        }
    }

    private fun loadSpecialties() {
        lifecycleScope.launch {
            specialties = withContext(Dispatchers.IO) {
                val all = SpecialtyRepository.getAll()
                Log.i("SPECIALTIES COUNT", "SPECIALTIES = ${all.size}")
                all.map { it.id to it.name }
            }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                specialties.map { it.second }
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            specialtySpinner.adapter = adapter

            specialtySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedSpecialtyId = specialties[position].first
                    loadDoctorsBySpecialty(selectedSpecialtyId!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedSpecialtyId = null
                }
            }
        }
    }

    private fun loadDoctorsBySpecialty(specialtyId: Long) {
        lifecycleScope.launch {
            val doctors = withContext(Dispatchers.IO) {
                val allDoctors = DoctorRepository.getAll()
                val filteredDoctors = allDoctors.filter { it.specialtyId == specialtyId }

                val users = UserRepository.getAll().associateBy { it.id }

                filteredDoctors.mapNotNull { doctor ->
                    users[doctor.userId]?.let { user ->
                        doctor.id to "${user.firstName} ${user.lastName}"
                    }
                }
            }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                doctors.map { it.second }
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            doctorSpinner.adapter = adapter

            doctorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDoctorId = doctors[position].first
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedDoctorId = null
                }
            }
        }
    }

    private fun loadCategories() {
        val categories = Category.entries.filter { it != Category.UNKNOWN }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories.map { it.getDisplayName(requireContext()) }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCategory = categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCategory = null
            }
        }
    }

    private fun submitAppointment() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        if (title.isEmpty()) {
            titleEditText.error = "Title is required"
            return
        }

        if (selectedDoctorId == null) {
            Toast.makeText(requireContext(), "Please select a doctor", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedCategory == null) {
            Toast.makeText(requireContext(), "Please select a category", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedDateTime == null) {
            Toast.makeText(requireContext(), "Please select a date and time", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val userId = SharedPrefsManager.getUserIdFromToken()
            if (userId == null) {
                Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val patient = withContext(Dispatchers.IO) {
                PatientRepository.getAll().find { it.userId == userId }
            }

            val patientId = patient?.id
            if (patientId == null) {
                Toast.makeText(requireContext(), "Patient not found", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val appointment = AppointmentEntityModel(
                title = title,
                description = description,
                patientId = patientId,
                doctorId = selectedDoctorId!!,
                categoryId = selectedCategory!!.id,
                date = selectedDateTime!!
            )

            withContext(Dispatchers.IO) {
                AppointmentRepository.insert(appointment)
            }

            Toast.makeText(requireContext(), "Appointment added", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun showDateTimePicker() {
        val now = LocalDateTime.now()
        val today = now.toLocalDate()
        val minTimeToday = now.toLocalTime().plusMinutes(30)

        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val pickedDate = LocalDate.of(year, month + 1, dayOfMonth)

                // select workdays -> Monday - Saturday
                if (pickedDate.dayOfWeek.value == 7) {
                    Toast.makeText(requireContext(), "Appointments are not available on Sundays", Toast.LENGTH_SHORT).show()
                    return@DatePickerDialog
                }

                val isToday = pickedDate == today
                val minHour = if (isToday && minTimeToday.hour >= 8) minTimeToday.hour else 8
                val minMinute = if (isToday && minTimeToday.minute >= 30) 30 else 0

                val timePicker = TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minuteOfHour ->
                        // rounded minutes - :00 sau :30
                        val minute = if (minuteOfHour < 30) 0 else 30
                        val pickedTime = LocalTime.of(hourOfDay, minute)

                        if (pickedTime.hour !in 8..19 || (pickedTime.hour == 20 && pickedTime.minute > 0)) {
                            Toast.makeText(requireContext(), "Please select a time between 08:00 and 20:00", Toast.LENGTH_SHORT).show()
                            return@TimePickerDialog
                        }

                        if (isToday && pickedTime.isBefore(minTimeToday.withSecond(0).withNano(0))) {
                            Toast.makeText(requireContext(), "Please select a time at least 30 minutes from now", Toast.LENGTH_SHORT).show()
                            return@TimePickerDialog
                        }

                        selectedDateTime = LocalDateTime.of(pickedDate, pickedTime)
                        val formatted = selectedDateTime!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        dateTimeEditText.setText(formatted)

                    },
                    minHour,
                    minMinute,
                    true
                )

                timePicker.show()
            },
            today.year,
            today.monthValue - 1,
            today.dayOfMonth
        )

        // Blochează datele din trecut
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
    }
}