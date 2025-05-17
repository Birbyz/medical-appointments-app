package com.example.medicalappointments.ui.home

import Doctor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalappointments.R
import com.example.medicalappointments.adapters.CategoriesAdapter
import com.example.medicalappointments.data.models.CategoryEntityModel
import com.example.medicalappointments.data.repositories.CategoryRepository
import com.example.medicalappointments.models.CategoryType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    // display appointments in HomePage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_items)

        val items = listOf(
            CategoryType.FOLLOW_UP,
            CategoryType.VIDEO,
            CategoryType.SURGERY,
            CategoryType.REGULAR
        ).shuffled()

        val adapter = CategoriesAdapter(items) {
            direction -> addCategoryIntoDatabase(direction)
        } // {} for unit - lambda fun

        val layoutManager = LinearLayoutManager(requireContext())

        // add all params in recycleView (rv)
        recyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

    fun goToAppointments(id: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToAppointmentsFragment(id)

        findNavController().navigate(action)
    }

    fun addCategoryIntoDatabase(categoryType: CategoryType) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val entity = CategoryEntityModel(
                    id = categoryType.id.toLong(),
                    category = categoryType
                )
                CategoryRepository.insert(entity)
            }
            goToAppointments(categoryType.id.toLong())
        }
    }

}