package com.example.medicalappointments.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medicalappointments.R
import com.example.medicalappointments.managers.SharedPrefsManager

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    // display appointments in HomePage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_go_to_users).setOnClickListener {
            goToUsers()
        }

        view.findViewById<Button>(R.id.btn_go_to_patients).setOnClickListener {
            goToPatients()
        }

        view.findViewById<Button>(R.id.btn_go_to_doctors).setOnClickListener {
            goToDoctors()
        }

        view.findViewById<Button>(R.id.btn_go_to_appointments).setOnClickListener {
            goToAppointments()
        }

        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            logout()
        }
    }

    fun goToUsers() {
        val action = HomeFragmentDirections.actionHomeFragmentToNavigationUsers()
        findNavController().navigate(action)
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

    private fun logout() {
        SharedPrefsManager.clearAuthToken()

        Toast.makeText(requireContext(), "You have been logged out.", Toast.LENGTH_SHORT).show()

        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}