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
import com.example.medicalappointments.R
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.utils.extensions.getAge
import com.example.medicalappointments.utils.extensions.logErrorMessage

class AppointmentsAdapter(users: List<UserEntityModel>) : ListAdapter<Appointment, AppointmentsAdapter.AppointmentViewHolder>(AppointmentDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppointmentViewHolder {
        "onCreateViewHolder".logErrorMessage()
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)

        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AppointmentViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AppointmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //category data
        private val categoryIcon = itemView.findViewById<ImageView>(R.id.iv_category_icon)
        private val categoryName = itemView.findViewById<TextView>(R.id.tv_category_name)
        // appointment data
        private val title: TextView = itemView.findViewById<TextView>(R.id.tv_appointment_title)
        private val date: TextView = itemView.findViewById<TextView>(R.id.tv_appointment_date)
        // patient data
        private val patientName: TextView = itemView.findViewById<TextView>(R.id.tv_patient_name)
        private val patientBirthdate: TextView = itemView.findViewById<TextView>(R.id.tv_age_birthdate)
        //appointment data
        private val description: TextView = itemView.findViewById<TextView>(R.id.tv_appointment_description)


        @SuppressLint("SetTextI18n", "WeekBasedYear")
        fun bind(appointment: Appointment) {
            // fills up the fields with the appointment's values
            val context = itemView.context

//            Category Info
            categoryName.text = appointment.category.getDisplayName(context) // adapts the category name to the phone's language
            categoryIcon.setImageResource(appointment.category.iconRes)

//            Appointment info
            title.text = appointment.title
            description.text = appointment.description

            val formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM YYYY, HH:MM")
            date.text = appointment.date.format(formatter)

//            Patient info
            val patient = appointment.patient
            patientName.text = "${patient.user.firstName} ${patient.user.lastName}"

            val age = patient.birthdate.getAge()
            patientBirthdate.text = context.getString(R.string.label_age, age)
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