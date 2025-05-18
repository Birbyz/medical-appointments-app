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
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.repositories.AppointmentRepository
import com.example.medicalappointments.data.repositories.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppointmentsFragment: Fragment() {
    val args: AppointmentsFragmentArgs by navArgs()

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
            getCategoriesWithAppointments()
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

        return AppointmentEntityModel(title = appointmentTitles.random(), categoryId = args.categoryId)
    }

    fun getCategoriesWithAppointments() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                CategoryRepository.getAllCategoriesWithAppointments()
            }

            view?.findViewById<TextView>(R.id.tv_result)?.text = result.toString()
        }
    }

}