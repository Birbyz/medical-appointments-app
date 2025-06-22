package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.DoctorAppointmentsAdapter
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.data.repositories.UserRepository
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.models.PatientEntityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoctorAppointmentsFragment: Fragment() {
    private val adapter = DoctorAppointmentsAdapter()

    private var originalAppointments: List<AppointmentEntityModel> = emptyList()
    private var patients: List<PatientEntityModel> = emptyList()
    private var users: List<UserEntityModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_doctor_appointments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("DEBUG_FRAGMENT", "DoctorAppointmentsFragment was loaded")

        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_doctor_appointments).apply {
            this.adapter = this@DoctorAppointmentsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        loadDoctorAppointments()
    }

    private fun loadDoctorAppointments() {
        lifecycleScope.launch {
            val userId = SharedPrefsManager.getUserIdFromToken() ?: return@launch

            val doctor = withContext(Dispatchers.IO) {
                DoctorRepository.getAll().find { it.userId == userId }
            } ?: return@launch

            originalAppointments = withContext(Dispatchers.IO) {
                AppointmentRepository.getAll().filter { it.doctorId == doctor.id }
            }

            patients = withContext(Dispatchers.IO) { PatientRepository.getAll() }
            users = withContext(Dispatchers.IO) { UserRepository.getAll() }

            Log.d("DoctorAppointments", "Loaded ${originalAppointments.size} appointments")
            adapter.submitData(originalAppointments, patients, users)
        }
    }
}