package com.example.medicalappointments.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.AppointmentsAdapter
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Pacient
import com.example.medicalappointments.models.SpecialtyType
import java.time.LocalDate
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    // display appointments in HomePage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycleView = view.findViewById<RecyclerView>(R.id.rv_items)

        val appointments = listOf(
            Appointment(
                title = "Eye Check-up",
                pacient = Pacient("Alice", "Popescu", 28, LocalDate.of(1997, 4, 12)),
                doctor = Doctor("Dr. Ion Ionescu", 15, SpecialtyType.OPHTHALMOLOGIST),
                date = LocalDateTime.of(2025, 5, 20, 10, 30),
                description = "Routine annual eye examination"
            ),
            Appointment(
                title = "General Consultation",
                pacient = Pacient("Mihai", "Stan", 35, LocalDate.of(1990, 9, 5)),
                doctor = Doctor("Dr. Elena Dobre", 10, SpecialtyType.GENERAL_PRACTITIONER),
                date = LocalDateTime.of(2025, 6, 1, 9, 0),
                description = "General health check and blood test request"
            ),
            Appointment(
                title = "Heart Health Evaluation",
                pacient = Pacient("Andreea", "Ilie", 60, LocalDate.of(1965, 11, 23)),
                doctor = Doctor("Dr. Sorin Marinescu", 20, SpecialtyType.CARDIOLOGIST),
                date = LocalDateTime.of(2025, 6, 10, 14, 45),
                description = "Check for arrhythmia symptoms"
            ),
            Appointment(
                title = "Skin Allergy Test",
                pacient = Pacient("Radu", "Tudor", 42, LocalDate.of(1983, 2, 17)),
                doctor = Doctor("Dr. Laura Grigore", 12, SpecialtyType.DERMATOLOGIST),
                date = LocalDateTime.of(2025, 5, 22, 11, 15),
                description = "Evaluate skin reaction to suspected allergens"
            ),
            Appointment(
                title = "Child Health Review",
                pacient = Pacient("Maria", "Enache", 7, LocalDate.of(2018, 8, 30)),
                doctor = Doctor("Dr. Ana Vasilescu", 8, SpecialtyType.PEDIATRICIAN),
                date = LocalDateTime.of(2025, 6, 5, 16, 0),
                description = "Routine pediatric check-up"
            )
        )

        val adapter = AppointmentsAdapter(appointments)

        val layoutManager = LinearLayoutManager(requireContext())

        // add all params in recycleView (rv)
        recycleView?.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

}