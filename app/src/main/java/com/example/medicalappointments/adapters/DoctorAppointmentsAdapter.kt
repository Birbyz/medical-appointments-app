package com.example.medicalappointments.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.PatientEntityModel
import com.example.medicalappointments.utils.extensions.getAge
import java.time.format.DateTimeFormatter

class DoctorAppointmentsAdapter : ListAdapter<AppointmentEntityModel, DoctorAppointmentsAdapter.ViewHolder>(DiffCallback()) {

    private var patients: List<PatientEntityModel> = emptyList()
    private var users: List<UserEntityModel> = emptyList()

    fun submitData(
        appointments: List<AppointmentEntityModel>,
        patients: List<PatientEntityModel>,
        users: List<UserEntityModel>
    ) {
        this.patients = patients
        this.users = users
        submitList(appointments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_appointment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById(R.id.imv_avatar)
        private val name: TextView = itemView.findViewById(R.id.tv_patient_name)
        private val age: TextView = itemView.findViewById(R.id.tv_patient_age)
        private val categoryText: TextView = itemView.findViewById(R.id.tv_category)
        private val datetime: TextView = itemView.findViewById(R.id.tv_datetime)

        @SuppressLint("SetTextI18n")
        fun bind(appointment: AppointmentEntityModel) {
            val patient = patients.find { it.id == appointment.patientId } ?: return
            val user = users.find { it.id == patient.userId } ?: return
            val category = Category.fromId(appointment.categoryId)

            name.text = "${user.firstName} ${user.lastName}"
            age.text = "Age: ${patient.birthdate.getAge()}"
            categoryText.text = category.getDisplayName(itemView.context)
            datetime.text = appointment.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))

            Glide.with(itemView.context)
                .load(user.avatar)
                .placeholder(R.drawable.ic_placeholder_avatar)
                .into(avatar)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AppointmentEntityModel>() {
        override fun areItemsTheSame(oldItem: AppointmentEntityModel, newItem: AppointmentEntityModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AppointmentEntityModel, newItem: AppointmentEntityModel): Boolean =
            oldItem == newItem
    }
}
