package com.example.medicalappointments.ui.users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.UsersAdapter
import com.example.medicalappointments.models.UserModel

class UsersFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UsersAdapter(
            listOf(UserModel(
                id = "0",
                firstName = "First Name",
                lastName = "Last Name",
                email = "email",
                avatar = "https://picsum.photos/200"
            ))
        )

        view.findViewById<RecyclerView>(R.id.rv_users).apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this.context)
        }
    }
}