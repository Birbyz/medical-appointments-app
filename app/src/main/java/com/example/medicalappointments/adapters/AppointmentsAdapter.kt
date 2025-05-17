package com.example.medicalappointments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicalappointments.R
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.CategoryType
import com.example.medicalappointments.models.FollowUpAppointment
import com.example.medicalappointments.models.RegularAppointment
import com.example.medicalappointments.models.SurgeryAppointment
import com.example.medicalappointments.models.VideoAppointment
import com.example.medicalappointments.utils.extensions.logErrorMessage

class AppointmentsAdapter(
    val items: List<Appointment>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    return the list size
    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].categoryType.id

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        "onCreateViewHolder".logErrorMessage()
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            // FOLLOW UP
            CategoryType.FOLLOW_UP.id -> {
                val view: View = inflater.inflate(R.layout.item_follow_up, parent, false)
                AppointmentViewHolder(view)
            }

            // REGULAR
            CategoryType.REGULAR.id -> {
                val view: View = inflater.inflate(R.layout.item_regular, parent, false)
                AppointmentViewHolder(view)
            }

            // SURGERY
            CategoryType.SURGERY.id -> {
                val view: View = inflater.inflate(R.layout.item_surgery, parent, false)
                AppointmentViewHolder(view)
            }

            // VIDEO
            CategoryType.VIDEO.id -> {
                val view: View = inflater.inflate(R.layout.item_video, parent, false)
                AppointmentViewHolder(view)
            }

            else -> {
                val view = inflater.inflate(R.layout.item_appointment, parent, false)
                return AppointmentViewHolder(view)
            }
        }
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_appointment, parent, false)
//
//        returns an instance of the AppointmentViewHolder, basically this method creates it
//        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        // getOrNull saves the program from chasing because of index out of bounds;
        // also, it returns null in case the array is null
        val item = items.getOrNull(position) ?: return

        // the holder is the one created in onCreateViewHolder function
        when(holder) {
            is AppointmentViewHolder -> holder.bind(item)
            is FollowUpAppointmentViewHolder -> (item as? FollowUpAppointment)?. let { holder.bind(it) }
            is RegularAppointmentViewHolder -> (item as? RegularAppointment)?. let { holder.bind(it) }
            is SurgeryAppointmentViewHolder -> (item as? SurgeryAppointment)?. let { holder.bind(it) }
            is VideoAppointmentViewHolder -> (item as? VideoAppointment)?. let { holder.bind(it) }
        }

        "onBindViewHolder; position = $position".logErrorMessage()
    }

    inner class FollowUpAppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(appointment: FollowUpAppointment) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_appointment_title).text = appointment.title
            view.findViewById<TextView>(R.id.tv_appointment_description).text = appointment.description

            // DISPLAY IMAGE
            val imageView = view.findViewById<ImageView>(R.id.imv_appointment_image)
            Glide.with(imageView.context)
                .load(appointment.imageUrl)
                .into(imageView)
        }
    }

    inner class RegularAppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(appointment: RegularAppointment) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_appointment_title).text = appointment.title
            view.findViewById<TextView>(R.id.tv_appointment_description).text = appointment.description
        }
    }

    inner class SurgeryAppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(appointment: SurgeryAppointment) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_appointment_title).text = appointment.title
            view.findViewById<TextView>(R.id.tv_appointment_description).text = appointment.description
        }
    }

    inner class VideoAppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(appointment: VideoAppointment) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_appointment_title).text = appointment.title
            view.findViewById<TextView>(R.id.tv_appointment_description).text = appointment.description
        }
    }

    inner class AppointmentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(appointment: Appointment) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_appointment_title).text = appointment.title
            view.findViewById<TextView>(R.id.tv_appointment_description).text = appointment.description
        }
    }
}