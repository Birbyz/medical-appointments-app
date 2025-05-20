package com.example.medicalappointments.ui.patients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.PatientsAdapter
import com.example.medicalappointments.data.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

}