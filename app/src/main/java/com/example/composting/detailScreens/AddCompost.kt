package com.example.composting.detailScreens

import AddCompostAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.MainActivity
import com.example.composting.databinding.AddCompostFragmentBinding
import com.example.composting.detailScreens.classes.CompostItems
import com.example.composting.detailScreens.classes.Datasource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class AddCompost : Fragment() {

    private lateinit var binding: AddCompostFragmentBinding
    private lateinit var recyclerView: RecyclerView

    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.title = "Add to your compost bin"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AddCompostFragmentBinding.inflate(inflater, container, false)

        val tabLayout : TabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Food"))
        tabLayout.addTab(tabLayout.newTab().setText("Vegetation"))
        tabLayout.addTab(tabLayout.newTab().setText("Live"))

        var selection : Int = 1

        fun rebuild() {
            val data : ArrayList<CompostItems> = Datasource().load()
            val adapter = AddCompostAdapter(this.requireContext(), data, selection)

            recyclerView = binding.compostListRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selection = when (tab?.text){
                    "Food" -> 1
                    "Vegetation" -> 2
                    else -> 3
                }
                rebuild()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        rebuild()

        // Return the root view.
        return binding.root
    }
}