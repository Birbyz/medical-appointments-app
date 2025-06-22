package com.example.medicalappointments.ui.doctors

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.DoctorsAdapter
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.SpecialtyRepository
import com.example.medicalappointments.data.repositories.UserRepository
import com.example.medicalappointments.extensions.toModel
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Specialty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoctorsFragment: Fragment() {
    private val adapter = DoctorsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_doctors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_doctors).apply {
            this.adapter = this@DoctorsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        //get
        loadDoctors()
    }

    private fun loadDoctors() {
        viewLifecycleOwner.lifecycleScope.launch {
            val doctors = withContext(Dispatchers.IO) { DoctorRepository.getAll() }
            val users = withContext(Dispatchers.IO) { UserRepository.getAll() }
            val specialties = withContext(Dispatchers.IO) { SpecialtyRepository.getAll() }
            val appointments = withContext(Dispatchers.IO) { AppointmentRepository.getAll() }

            val doctorModels = doctors.mapNotNull { doctor ->
                val user = users.find { it.id == doctor.userId } ?: return@mapNotNull null
                val specialty =
                    specialties.find { it.id == doctor.specialtyId } ?: return@mapNotNull null
                val totalAppointments = appointments.count { it.doctorId == doctor.id }


                Doctor(
                    id = doctor.id,
                    user = user.toModel(),
                    specialty = specialty.toModel(),
                    yearsOfExperience = doctor.yearsOfExperience,
                    totalAppointments = totalAppointments
                )
            }

            adapter.submitList(doctorModels)
        }
    }
}