package com.example.medicalappointments.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.models.CategoryType
import com.example.medicalappointments.utils.extensions.logErrorMessage

class CategoriesAdapter(
    val items: List<CategoryType>,
    val onItemClick: (CategoryType) -> Unit
): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

//    return the list size
    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].id

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        "onCreateViewHolder".logErrorMessage()
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        // getOrNull saves the program from chasing because of index out of bounds;
        // also, it returns null in case the array is null
        val item = items.getOrNull(position) ?: return
        holder.bind(item)

        "onBindViewHolder; position = $position".logErrorMessage()
    }

    inner class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(category: CategoryType) {
            // fills up the fields with the appointment's values
            view.findViewById<TextView>(R.id.tv_category_name).text = view.context.getString(category.resourceId)

            view.setOnClickListener {
                onItemClick.invoke(category) // nav
            }
        }
    }


}