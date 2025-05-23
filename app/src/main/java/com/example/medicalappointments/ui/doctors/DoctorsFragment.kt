package com.example.medicalappointments.ui.doctors

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
import com.example.medicalappointments.adapters.DoctorsAdapter
import com.example.medicalappointments.data.models.SpecialtyType
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.models.DoctorModel
import com.example.medicalappointments.models.UserModel
import com.example.medicalappointments.models.toEntity
import com.example.medicalappointments.models.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoctorsFragment: Fragment() {
    private val adapter = DoctorsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_doctors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_doctors).apply {
            this.adapter = this@DoctorsFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        // insert
        insertDummyDoctors()

        //get
        getDoctorsFromDatabase()
    }

    private fun getDoctorsFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
            val models = withContext(Dispatchers.IO) {
                val entities = DoctorRepository.getAll()
                entities.map { it.toModel() }
            }

            adapter.submitList(models)
        }
    }

    private fun insertDummyDoctors() {
        // dummy data
        val dummyDoctors = listOf(
            DoctorModel(
                user = UserModel(
                    id = "u001",
                    email = "ana.popescu@med.ro",
                    firstName = "Ana",
                    lastName = "Popescu",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                yearsOfExperience = 10,
                specialty = SpecialtyType.CARDIOLOGIST,
            ),
            DoctorModel(
                user = UserModel(
                    id = "u002",
                    email = "mihai.ionescu@med.ro",
                    firstName = "Mihai",
                    lastName = "Ionescu",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                yearsOfExperience = 8,
                specialty = SpecialtyType.DERMATOLOGIST
            ),
            DoctorModel(
                user = UserModel(
                    id = "u003",
                    email = "elena.marinescu@med.ro",
                    firstName = "Elena",
                    lastName = "Marinescu",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                yearsOfExperience = 12,
                specialty = SpecialtyType.NEUROLOGIST
            ),
            DoctorModel(
                user = UserModel(
                    id = "u004",
                    email = "andrei.georgescu@med.ro",
                    firstName = "Andrei",
                    lastName = "Georgescu",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                yearsOfExperience = 5,
                specialty = SpecialtyType.SURGEON
            ),
            DoctorModel(
                user = UserModel(
                    id = "u005",
                    email = "ioana.dumitru@med.ro",
                    firstName = "Ioana",
                    lastName = "Dumitru",
                    avatar = "https://avatar.iran.liara.run/public"
                ),
                yearsOfExperience = 7,
                specialty = SpecialtyType.PEDIATRICIAN
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            ApplicationController.instance?.appDatabase?.doctorDAO?.insertDoctors(
                dummyDoctors.map { it.toEntity() }
            )
        }
    }
}