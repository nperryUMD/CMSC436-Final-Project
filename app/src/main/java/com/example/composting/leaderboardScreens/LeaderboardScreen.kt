package com.example.composting.leaderboardScreens

import LeaderboardAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.databinding.LeaderboardFragmentBinding
import com.example.composting.detailScreens.classes.CompostItems
import com.example.composting.detailScreens.classes.Datasource

class LeaderboardScreen : Fragment() {

    private lateinit var binding: LeaderboardFragmentBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = LeaderboardFragmentBinding.inflate(inflater, container, false)

        val data : ArrayList<CompostItems> = Datasource().load()
        val adapter = LeaderboardAdapter(this.requireContext(), data)

        recyclerView = binding.leaderboardRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Return the root view.
        return binding.root
    }
}