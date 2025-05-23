package com.example.medicalappointments.adapters

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
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.utils.extensions.logErrorMessage
import kotlin.math.log

class AppointmentsAdapter: ListAdapter<Appointment, AppointmentsAdapter.AppointmentViewHolder>(
    AppointmentDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppointmentViewHolder {
        "onCreateViewHolder".logErrorMessage()
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_appointments, parent, false)

        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AppointmentViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AppointmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(appointment: Appointment) {
            // fills up the fields with the appointment's values

        }
    }
}

private class AppointmentDiffCallback: DiffUtil.ItemCallback<Appointment>() {
    override fun areItemsTheSame(
        oldItem: Appointment,
        newItem: Appointment
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Appointment,
        newItem: Appointment
    ): Boolean = oldItem == newItem

}