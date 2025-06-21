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
import com.example.medicalappointments.data.repositories.DoctorRepository
import com.example.medicalappointments.extensions.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoctorsFragment: Fragment() {
    private val adapter = DoctorsAdapter(
//        users = TODO(),
//        specialties = TODO()
    )

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
        //insertDummyDoctors()

        //get
        getDoctorsFromDatabase()
    }

    private fun getDoctorsFromDatabase() {
        viewLifecycleOwner.lifecycleScope.launch {
//            val models = withContext(Dispatchers.IO) {
//                val entities = DoctorRepository.getAll()
////                entities.map { it.toModel() }
//            }
//
//            adapter.submitList(models)
        }
    }
}