package com.example.medicalappointments.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.medicalappointments.R
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.managers.SharedPrefsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    // display appointments in HomePage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = SharedPrefsManager.getUserIdFromToken()
        Log.i("Login", "USER ID: $userId")

        lifecycleScope.launch {
            val isPatient = withContext(Dispatchers.IO) {
                PatientRepository.getAll().any { it.userId == userId }
            }

            if (isPatient) {
                showPatientUI(view)
            } else { showDoctorUI(view) }
        }

        view.findViewById<Button>(R.id.btn_logout).apply {
            visibility = View.VISIBLE
            setOnClickListener { logout() }
        }
    }

    fun goToPatients() {
        val action = HomeFragmentDirections.actionHomeFragmentToNavigationPatients()
        findNavController().navigate(action)
    }

    fun goToDoctors() {
        val action = HomeFragmentDirections.actionHomeFragmentToNavigationDoctors()
        findNavController().navigate(action)
    }

    fun goToAppointments() {
        val action = HomeFragmentDirections.actionHomeFragmentToNavigationAppointments()
        findNavController().navigate(action)
    }

    fun goToDoctorAppointments() {
        val action = HomeFragmentDirections.actionHomeFragmentToDoctorAppointmentsFragment()
        findNavController().navigate(action)
    }

    private fun logout() {
        SharedPrefsManager.clearAuthToken()

        Toast.makeText(requireContext(), "You have been logged out.", Toast.LENGTH_SHORT).show()

        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun showPatientUI(view: View) {
        view.findViewById<Button>(R.id.btn_go_to_doctors).apply {
            visibility = View.VISIBLE
            setOnClickListener { goToDoctors() }
        }

        view.findViewById<Button>(R.id.btn_go_to_appointments).apply {
            visibility = View.VISIBLE
            setOnClickListener { goToAppointments() }
        }
    }

    private fun showDoctorUI(view: View) {
        view.findViewById<Button>(R.id.btn_go_to_doctor_appointments).apply {
            visibility = View.VISIBLE
            setOnClickListener { goToDoctorAppointments() }
        }
        view.findViewById<Button>(R.id.btn_go_to_patients).apply {
            visibility = View.VISIBLE
            setOnClickListener { goToPatients() }
        }
    }
}