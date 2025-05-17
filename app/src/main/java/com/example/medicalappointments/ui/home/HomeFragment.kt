package com.example.medicalappointments.ui.home

import Doctor
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
import com.example.medicalappointments.models.CategoryType
import com.example.medicalappointments.models.FollowUpAppointment
import com.example.medicalappointments.models.Pacient
import com.example.medicalappointments.models.RegularAppointment
import com.example.medicalappointments.models.SurgeryAppointment
import com.example.medicalappointments.models.VideoAppointment
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
            SurgeryAppointment(
                title = "Knee Replacement Surgery",
                pacient = Pacient("George", "Popa", 67, LocalDate.of(1958, 3, 10)),
                doctor = Doctor("Dr. Radu Iancu", 25, SpecialtyType.ORTHOPEDIST),
                date = LocalDateTime.of(2025, 6, 15, 8, 0),
                description = "Scheduled surgery for left knee replacement",
            ),
            VideoAppointment(
                title = "Online Therapy Session",
                pacient = Pacient("Ioana", "Lungu", 31, LocalDate.of(1993, 1, 25)),
                doctor = Doctor("Dr. Daria Neagu", 12, SpecialtyType.PSYCHIATRIST),
                date = LocalDateTime.of(2025, 6, 18, 17, 30),
                description = "Weekly video consultation for mental health support",
            ),
            RegularAppointment(
                title = "General Health Check-up",
                pacient = Pacient("Andrei", "Vasilescu", 45, LocalDate.of(1980, 7, 9)),
                doctor = Doctor("Dr. Mircea Iliescu", 18, SpecialtyType.GENERAL_PRACTITIONER),
                date = LocalDateTime.of(2025, 6, 12, 10, 0),
                description = "Routine annual health examination",
            ),
            FollowUpAppointment(
                title = "Post-Op Follow-up",
                pacient = Pacient("Cristina", "Barbu", 54, LocalDate.of(1971, 10, 3)),
                doctor = Doctor("Dr. Vlad Petrescu", 22, SpecialtyType.SURGEON),
                date = LocalDateTime.of(2025, 6, 20, 14, 0),
                description = "Follow-up appointment after abdominal surgery",
            ),
            SurgeryAppointment(
                title = "Knee Replacement Surgery",
                pacient = Pacient("George", "Popa", 67, LocalDate.of(1958, 3, 10)),
                doctor = Doctor("Dr. Radu Iancu", 25, SpecialtyType.ORTHOPEDIST),
                date = LocalDateTime.of(2025, 6, 15, 8, 0),
                description = "Scheduled surgery for left knee replacement",
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