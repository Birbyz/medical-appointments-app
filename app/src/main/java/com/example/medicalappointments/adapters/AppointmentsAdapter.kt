package com.example.medicalappointments.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

class AppointmentsAdapter : ListAdapter<Appointment, AppointmentsAdapter.AppointmentViewHolder>(AppointmentDiffCallback()) {
    var onDeleteClick: ((Appointment) -> Unit)? = null

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
        private val doctorName: TextView = itemView.findViewById<TextView>(R.id.tv_doctor_name)
        private val doctorSpecialty: TextView = itemView.findViewById<TextView>(R.id.tv_doctor_specialty)
        //appointment data
        private val description: TextView = itemView.findViewById<TextView>(R.id.tv_appointment_description)

        // delete appointment
        private val deleteButton: ImageButton = itemView.findViewById(R.id.btn_delete_appointment)

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

//            Doctor info
            val doctor = appointment.doctor
            doctorName.text = "${doctor.user.firstName} ${doctor.user.lastName}"
            doctorSpecialty.text = doctor.specialty.name

            // delete function -> show warning then execute
            deleteButton.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Cancel Appointment")
                    .setMessage("Are you sure you want to cancel this appointment?")
                    .setPositiveButton("Yes") { _, _ ->
                        onDeleteClick?.invoke(appointment)
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
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