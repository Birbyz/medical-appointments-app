package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.AppointmentsAdapter
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.CategoryRepository
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.data.repositories.SpecialtyRepository
import com.example.medicalappointments.data.repositories.UserRepository
import com.example.medicalappointments.extensions.toModel
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.Specialty
import com.example.medicalappointments.ui.doctors.DoctorsFragment
import com.example.medicalappointments.ui.patients.PatientsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class AppointmentsFragment: Fragment() {
    private val adapter = AppointmentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_appointments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.btn_go_to_add_appointment)?.setOnClickListener {
            goToAddAppointmentsForm()
        }

        view.findViewById<RecyclerView>(R.id.rv_appointments).apply {
            this.adapter = this@AppointmentsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        adapter.onDeleteClick = { appointment ->
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    AppointmentRepository.delete(appointment.id)
                }
                loadUserAppointments()
            }
        }

        loadUserAppointments()
    }

    private fun goToAddAppointmentsForm() {
        val action = AppointmentsFragmentDirections.actionAppointmentsFragmentToAddAppointmentFragment()
        findNavController().navigate(action)
    }

    private fun loadUserAppointments() {
        lifecycleScope.launch {
            val userId = SharedPrefsManager.getUserIdFromToken() ?: return@launch

            val patientEntity = withContext(Dispatchers.IO) {
                PatientRepository.getAll().find { it.userId == userId }
            } ?: return@launch

            val userEntity = withContext(Dispatchers.IO) {
                UserRepository.getAll().find { it.id == userId }
            } ?: return@launch

            val patient = Patient(
                id = patientEntity.id,
                user = userEntity.toModel(),
                birthdate = patientEntity.birthdate
            )

            val appointments = withContext(Dispatchers.IO) {
                AppointmentRepository.getAll().filter { it.patientId == patient.id }
            }

            val doctors = DoctorRepository.getAll()
            val doctorUsers = UserRepository.getAll()
            val specialties = SpecialtyRepository.getAll()

            val appointmentModels = appointments.mapNotNull { entity ->
                val doctorEntity = doctors.find { it.id == entity.doctorId } ?: return@mapNotNull null
                val doctorUser = doctorUsers.find { it.id == doctorEntity.userId } ?: return@mapNotNull null
                val specialty = specialties.find { it.id == doctorEntity.specialtyId } ?: return@mapNotNull null

                val doctor = Doctor(
                    id = doctorEntity.id,
                    user = doctorUser.toModel(),
                    specialty = Specialty(id = specialty.id, name = specialty.name),
                    yearsOfExperience = doctorEntity.yearsOfExperience
                )

                val category = Category.fromId(entity.categoryId)

                entity.toModel(patient, doctor, category)
            }

            adapter.submitList(appointmentModels)
        }
    }
}