package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.AppointmentsAdapter
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.CategoryRepository
import com.example.medicalappointments.models.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppointmentsFragment: Fragment() {
    private val adapter = AppointmentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_appointments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_insert_appointment)?.setOnClickListener{
            AppointmentRepository.insert(generateRandomAppointment())
        }

        view.findViewById<Button>(R.id.btn_get_categories)?.setOnClickListener{
            getCategoriesFromDatabase()
        }
    }

    fun generateRandomAppointment(): Appointment {
        return TODO("Provide the return value")
    }

    fun getCategoriesFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            val models = withContext(Dispatchers.IO) {
                val entities = AppointmentRepository.getAll()
            }

            adapter.submitList(models)
        }
    }

}