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
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.Specialty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class AppointmentsFragment: Fragment() {
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
    }

    private fun goToAddAppointmentsForm() {
        val action = AppointmentsFragmentDirections.actionAppointmentsFragmentToAddAppointmentFragment()
        findNavController().navigate(action)
    }
}