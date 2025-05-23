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
import com.example.medicalappointments.data.models.getDisplayName
import com.example.medicalappointments.models.DoctorModel

class DoctorsAdapter: ListAdapter<DoctorModel, DoctorsAdapter.DoctorViewHolder>(DoctorsDiffCallback()) {
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
        private val email = itemView.findViewById<TextView>(R.id.tv_email)
        private val specialty = itemView.findViewById<TextView>(R.id.tv_specialty)

        fun bind(model: DoctorModel) {
            val fullNameVal = "${model.user.firstName} ${model.user.lastName}"

            fullName.text = fullNameVal
            email.text = model.user.email
            yearsOfExperience.text = model.yearsOfExperience.toString()
            specialty.text = model.specialty.getDisplayName(itemView.context)

            Glide.with(avatar.context)
                .load(model.user.avatar)
                .into(avatar)
        }
    }
}

private class DoctorsDiffCallback: DiffUtil.ItemCallback<DoctorModel>(){
    override fun areItemsTheSame(
        oldItem: DoctorModel,
        newItem: DoctorModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DoctorModel,
        newItem: DoctorModel
    ): Boolean = oldItem == newItem
}