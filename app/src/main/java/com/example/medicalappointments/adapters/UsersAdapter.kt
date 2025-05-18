package com.example.medicalappointments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicalappointments.R
import com.example.medicalappointments.models.UserModel

class UsersAdapter(
    val items: List<UserModel>
): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        items.getOrNull(position)?.let {
            item -> holder.bind(item)
        }
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById<ImageView>(R.id.imv_avatar)
        private val fullName: TextView = itemView.findViewById<TextView>(R.id.tv_full_name)
        private val email: TextView = itemView.findViewById<TextView>(R.id.tv_email)

        fun bind(model: UserModel) {
            val fullNameValue = "${model.firstName} ${model.lastName}"
            fullName.text = fullNameValue

            email.text = model.email

            Glide.with(avatar.context)
                .load(model.avatar)
                .into(avatar)
        }
    }
}