package com.example.medicalappointments.ui.users


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
import com.example.medicalappointments.adapters.UsersAdapter
import com.example.medicalappointments.data.models.DoctorEntityModel
import com.example.medicalappointments.data.models.SpecialtyEntityModel
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.PatientEntityModel
import com.example.medicalappointments.models.User
import com.example.medicalappointments.data.repositories.UserRepository as UserDataRepository
import com.example.medicalappointments.networking.repository.UserRepository
import com.example.medicalappointments.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.time.LocalDate

class UsersFragment: Fragment() {

    private val adapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_users).apply {
            this.adapter = this@UsersFragment.adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }
    }
}