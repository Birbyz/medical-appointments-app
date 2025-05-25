package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.AppointmentsAdapter
import com.example.medicalappointments.data.models.Doctor
import com.example.medicalappointments.data.models.SpecialtyType
import com.example.medicalappointments.data.models.toEntity
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import com.example.medicalappointments.data.models.toEntity
import com.example.medicalappointments.data.models.toModel

class AppointmentsFragment: Fragment() {
    private val adapter = AppointmentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_appointments, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_appointments).apply {
            this.adapter = this@AppointmentsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

//        view.findViewById<Button>(R.id.btn_insert_appointment)?.setOnClickListener{
//            AppointmentRepository.insert(generateRandomAppointment())
//        }
//
//        view.findViewById<Button>(R.id.btn_get_categories)?.setOnClickListener{
//            getCategoriesFromDatabase()
//        }


    }

    private fun insertDummyAppointments() {
        val dummyAppointments = listOf(
            Appointment(
                title = "Control post-operator",
                description = "Evaluare după intervenție chirurgicală",
                patient = Patient(
                    user = UserModel("p001", "ana@email.com", "Ana", "Popescu", ""),
                    birthdate = LocalDate.of(1996, 3, 14)
                ),
                doctor = Doctor(
                    user = UserModel("d001", "doc@email.com", "Andrei", "Georgescu", ""),
                    yearsOfExperience = 12,
                    specialty = SpecialtyType.SURGEON // ✅ corect
                ),
                date = LocalDateTime.now().plusDays(2),
                category = Category.FOLLOW_UP
            ),
            Appointment(
                title = "Consultație generală",
                description = "Dureri de cap și oboseală",
                patient = Patient(
                    user = UserModel("p002", "dan@email.com", "Dan", "Ionescu", ""),
                    birthdate = LocalDate.of(1989, 7, 3)
                ),
                doctor = Doctor(
                    user = UserModel("d002", "doc2@email.com", "Ioana", "Petrescu", ""),
                    yearsOfExperience = 8,
                    specialty = SpecialtyType.GENERAL_PRACTITIONER // ✅ corect
                ),
                date = LocalDateTime.now().plusDays(3),
                category = Category.REGULAR
            ),
            Appointment(
                title = "Consultație online",
                description = "Consultație video pentru simptome minore",
                patient = Patient(
                    user = UserModel("p003", "elena@email.com", "Elena", "Marin", ""),
                    birthdate = LocalDate.of(2001, 11, 27)
                ),
                doctor = Doctor(
                    user = UserModel("d003", "doc3@email.com", "Mihai", "Radu", ""),
                    yearsOfExperience = 6,
                    specialty = SpecialtyType.PSYCHIATRIST // ✅ sau altul potrivit pentru online
                ),
                date = LocalDateTime.now().plusDays(1),
                category = Category.VIDEO
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                dummyAppointments.forEach {
                    AppointmentRepository.insert(it.toEntity())
                }
            }
        }
    }

    private fun getAppointmentsFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            val models = withContext(Dispatchers.IO) {
                AppointmentRepository.getAll().map { it.toModel() }
            }
            adapter.submitList(models)
        }
    }

}