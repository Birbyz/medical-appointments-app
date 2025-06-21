package com.example.medicalappointments.adapters

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
import com.example.medicalappointments.data.dao.PatientDAO
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.utils.extensions.getAge

//ListAdapter is a class which requires a param
// the param fun is used to keep only the original items and avoid duplicates
class PatientsAdapter: ListAdapter<Patient, PatientsAdapter.PatientViewHolder>(PatientDiffCallback()) {

    // used to pass an XML file to a view and returns a ViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)

        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PatientViewHolder,
        position: Int
    ) {
        getItem(position)?.let{ holder.bind(it) }
    }

    inner class PatientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById<ImageView>(R.id.imv_avatar)
        private val fullName: TextView = itemView.findViewById<TextView>(R.id.tv_full_name)
        private val ageBirthdate: TextView = itemView.findViewById<TextView>(R.id.tv_age_birthdate)
        private val email: TextView = itemView.findViewById<TextView>(R.id.tv_email)

        fun bind(model: Patient) {
            val fullNameValue = "${model.user.firstName} ${model.user.lastName}"

            val age = model.birthdate.getAge()
            val fullBirthDate = "$age (${model.birthdate})"

            fullName.text = fullNameValue
            ageBirthdate.text = fullBirthDate
            email.text = model.user.email

            Glide.with(avatar.context)
                .load(model.user.avatar)
                .into(avatar)
        }
    }
}

// function for patients' comparison - given as a param to ListAdapter()
private class PatientDiffCallback: DiffUtil.ItemCallback<Patient>() {

    override fun areItemsTheSame(
        oldItem: Patient,
        newItem: Patient
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Patient,
        newItem: Patient
    ): Boolean = oldItem == newItem
}