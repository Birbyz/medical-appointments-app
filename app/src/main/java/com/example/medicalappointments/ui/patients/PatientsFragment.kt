package com.example.medicalappointments.ui.patients

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
import com.example.medicalappointments.adapters.PatientsAdapter
import com.example.medicalappointments.data.repositories.PatientRepository
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate


class PatientsFragment: Fragment() {

    private val adapter = PatientsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_patients, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_patients).apply {
            this.adapter = this@PatientsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        // INSERT
        //insertDummyPatient()

        //GET PATIENTS FROM DB
        getPatientsFromDatabase()
    }

    private fun getPatientsFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                PatientRepository.getAllPatients()
            }

            adapter.submitList(result)
        }
    }

    private fun insertDummyPatient() {
        // dummy data
        val patients = listOf(
            Patient(
                user = UserModel(
                    id = "u001",
                    email = "john.doe@example.com",
                    firstName = "John",
                    lastName = "Doe",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                age = 23,
                birthdate = LocalDate.of(2001, 10, 25)
            ),
            Patient(
                user = UserModel(
                    id = "u002",
                    email = "jane.smith@example.com",
                    firstName = "Jane",
                    lastName = "Smith",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                age = 30,
                birthdate = LocalDate.of(1994, 5, 12)
            ),
            Patient(
                user = UserModel(
                    id = "u003",
                    email = "bob.miller@example.com",
                    firstName = "Bob",
                    lastName = "Miller",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                age = 40,
                birthdate = LocalDate.of(1984, 1, 3)
            ),
            Patient(
                user = UserModel(
                    id = "u004",
                    email = "alice.wilson@example.com",
                    firstName = "Alice",
                    lastName = "Wilson",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                age = 27,
                birthdate = LocalDate.of(1997, 8, 19)
            ),
            Patient(
                user = UserModel(
                    id = "u005",
                    email = "michael.brown@example.com",
                    firstName = "Michael",
                    lastName = "Brown",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                age = 35,
                birthdate = LocalDate.of(1989, 11, 7)
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            ApplicationController.instance?.appDatabase?.patientDAO?.insertPatients(patients)
        }
    }

}