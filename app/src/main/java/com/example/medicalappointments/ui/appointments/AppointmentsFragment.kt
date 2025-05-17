package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.medicalappointments.R
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.repositories.AppointmentRepository

class AppointmentsFragment: Fragment() {
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
    }

    fun generateRandomAppointment(): AppointmentEntityModel {
        val appointmentTitles = listOf(
            "General Consultation",
            "Post-Operative Follow-up",
            "Dermatology Check-up",
            "Left Knee Surgery",
            "Video Therapy Session",
            "Annual Cardiology Evaluation",
            "Routine Pediatric Control"
        )
        Log.e("CITY", appointmentTitles.random())

        return AppointmentEntityModel(title = appointmentTitles.random())
    }

}