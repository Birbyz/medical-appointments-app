package com.example.medicalappointments.ui.users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.UsersAdapter
import com.example.medicalappointments.models.UserModel
import com.example.medicalappointments.networking.repository.AuthenticationRepository
import com.example.medicalappointments.networking.repository.UserRepository
import com.example.medicalappointments.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class UsersFragment: Fragment() {

    private val users = mutableListOf<UserModel>()
    private val adapter = UsersAdapter(users)

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

        getUsersFromServer()
    }

    private fun getUsersFromServer() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    UserRepository.getUsers(1)
                }
                users.clear()
                users.addAll(result.data)
                adapter.notifyItemChanged(0, users.size)
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