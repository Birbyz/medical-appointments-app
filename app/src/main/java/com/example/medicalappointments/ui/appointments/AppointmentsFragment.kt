package com.example.medicalappointments.ui.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.AppointmentsAdapter
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppointmentsFragment: Fragment() {
    private lateinit var adapter: AppointmentsAdapter

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

        view.findViewById<ImageButton>(R.id.btn_go_to_add_appointment)?.setOnClickListener {
            goToAddAppointmentsForm()
        }
    }

    private fun getAppointmentsAndSetupAdapter(view: View) {
        lifecycleScope.launch {
            val appointments = withContext(Dispatchers.IO) {
                val appointmentEntities = AppointmentRepository.getAll()
                val users = UserRepository.getAll()
                val patients = PatientRepository.getAll()
                val doctors = DoctorRepository.getAll()
            }

//            adapter = AppointmentsAdapter(users, birthdates)
//            view.findViewById<RecyclerView>(R.id.rv_appointments).adapter = adapter
//            adapter.submitList(appointments)
        }
    }

    private fun goToAddAppointmentsForm() {
        val action = AppointmentsFragmentDirections.actionAppointmentsFragmentToAddAppointmentsFragment()
        findNavController().navigate(action)
    }

}