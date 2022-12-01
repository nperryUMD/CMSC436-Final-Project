package com.example.composting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.databinding.DashboardFragmentBinding
import com.example.composting.mainScroll.*
import com.google.firebase.auth.FirebaseAuth

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use the provided ViewBinding class to inflate
        // the layout and then return the root view.
        val binding = DashboardFragmentBinding.inflate(inflater, container, false)

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            Toast.makeText(
                requireContext(),
                "You are now logged out!",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack(R.id.mainFragment, false)
        }

        val data : ArrayList<Data> = Datasource().load()
        val adapter = RecyclerViewAdapter(this.requireContext(), data)
        recyclerView = binding.mainMenuScroll
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Return the root view.
        return binding.root
    }
}
