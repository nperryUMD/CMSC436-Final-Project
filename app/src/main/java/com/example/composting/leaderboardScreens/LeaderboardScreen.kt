package com.example.composting.leaderboardScreens

import LeaderboardAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.MainActivity
import com.example.composting.databinding.LeaderboardFragmentBinding

class LeaderboardScreen : Fragment() {

    private lateinit var binding: LeaderboardFragmentBinding
    private lateinit var recyclerView: RecyclerView

    override fun onResume() {
        super.onResume()
        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.title = "Leaderboard"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = LeaderboardFragmentBinding.inflate(inflater, container, false)

        val data: MutableLiveData<ArrayList<Person>> = Datasource().load()
        data.observe(viewLifecycleOwner, Observer<ArrayList<Person>> {
            val adapter = LeaderboardAdapter(this.requireContext(), data)

            recyclerView = binding.leaderboardRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
            recyclerView.adapter = adapter
        })



        // Return the root view.
        return binding.root
    }
}