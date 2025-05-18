package com.example.medicalappointments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicalappointments.R
import com.example.medicalappointments.models.UserModel

class UsersAdapter(
    var items: List<UserModel>
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

    fun updateList(newList: List<UserModel>) {
        // checks the differences between the 2 lists and stores them in a new list
        val diffResult = DiffUtil.calculateDiff(UsersDiffCallback(items, newList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
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

//    ITEMS COMPARISON
    inner class UsersDiffCallback(
        private val oldList: List<UserModel>,
        private val newList: List<UserModel>
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
}