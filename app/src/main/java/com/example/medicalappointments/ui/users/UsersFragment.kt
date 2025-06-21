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

        //getUsersFromDatabase()
        getUsersFromServer()
    }

//    private fun getUsersFromDatabase() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            val result = withContext(Dispatchers.IO) {
//                UserDataRepository.getAll()
//            }
//
//            adapter.submitList(result)
//        }
//    }

    private fun insertInitialData() {
        // user dummy data
        val users = listOf(
            UserEntityModel(
                id = 1,
                email = "john.doe@example.com",
                firstName = "John",
                lastName = "Doe",
                avatar = "https://avatar.iran.liara.run/public"
            ),
            UserEntityModel(
                id = 2,
                email = "jane.smith@example.com",
                firstName = "Jane",
                lastName = "Smith",
                avatar = "https://avatar.iran.liara.run/public"
            ),
            UserEntityModel(
                id = 3,
                email = "bob.miller@example.com",
                firstName = "Bob",
                lastName = "Miller",
                avatar = "https://avatar.iran.liara.run/public"
            ),
            UserEntityModel(
                id = 4,
                email = "alice.wilson@example.com",
                firstName = "Alice",
                lastName = "Wilson",
                avatar = "https://avatar.iran.liara.run/public"
            ),
            UserEntityModel(
                id = 5,
                email = "michael.brown@example.com",
                firstName = "Michael",
                lastName = "Brown",
                avatar = "https://avatar.iran.liara.run/public"
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            ApplicationController.instance?.appDatabase?.userDAO?.insert(users)
        }
    }

    private fun getUsersFromServer() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    UserRepository.getUsers(1)
                }

                // NEEDS FIX
                adapter.submitList(result as List<User?>?)
            } catch (e: IOException) {
                ("Please check your internet connection").showToast(requireContext())
            } catch (e: HttpException) {
                ("Server error: ${e.code()}".showToast(requireContext()))
            } catch (e: Exception) {
                ("Unexpected error: ${e.localizedMessage}").showToast(requireContext())
            }
        }
    }
}