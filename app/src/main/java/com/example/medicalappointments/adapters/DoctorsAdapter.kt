package com.example.medicalappointments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicalappointments.R
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.User

class DoctorsAdapter: ListAdapter<Doctor, DoctorsAdapter.DoctorViewHolder>(DoctorsDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)

        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DoctorViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class DoctorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById<ImageView>(R.id.imv_avatar)
        private val fullName: TextView = itemView.findViewById<TextView>(R.id.tv_full_name)
        private val yearsOfExperience = itemView.findViewById<TextView>(R.id.tv_years_of_experience)
        private val specialty = itemView.findViewById<TextView>(R.id.tv_specialty)
        private val totalAppointments = itemView.findViewById<TextView>(R.id.tv_total_appointments)

        @SuppressLint("SetTextI18n")
        fun bind(model: Doctor) {
            val user = model.user

            specialty.text = model.specialty.name
            fullName.text = "${user.firstName} ${user.lastName}"
            yearsOfExperience.text = "Experience: ${model.yearsOfExperience} years"
            totalAppointments.text = "Appointments: ${model.totalAppointments}"

            Glide.with(itemView.context)
                .load(user.avatar)
                .placeholder(R.drawable.ic_placeholder_avatar)
                .into(avatar)
        }
    }
}

private class DoctorsDiffCallback: DiffUtil.ItemCallback<Doctor>(){
    override fun areItemsTheSame(
        oldItem: Doctor,
        newItem: Doctor
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Doctor,
        newItem: Doctor
    ): Boolean = oldItem == newItem
}