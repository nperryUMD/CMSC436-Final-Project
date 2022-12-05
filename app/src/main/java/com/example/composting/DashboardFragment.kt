package com.example.composting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.databinding.DashboardFragmentBinding
import com.example.composting.mainScroll.Data
import com.example.composting.mainScroll.Datasource
import com.example.composting.mainScroll.RecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = "Compost Bin"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use the provided ViewBinding class to inflate
        // the layout and then return the root view.
        val binding = DashboardFragmentBinding.inflate(inflater, container, false)
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid)

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            Snackbar.make(requireView(),
                getString(R.string.logged_out), Snackbar.LENGTH_SHORT).show()

            findNavController().popBackStack(R.id.mainFragment, false)
        }

       val data : ArrayList<Data> = Datasource().load()
        val adapter = RecyclerViewAdapter(this.requireContext(), data)


        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                binding.coinUpperText.text = "Coins: "  + dataSnapshot.child("coins").getValue().toString()
                binding.trophyUpperText.text = "Trophies: "  + dataSnapshot.child("trophies").getValue().toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
            }

        })

        recyclerView = binding.mainMenuScroll
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Return the root view.
        return binding.root
    }
}
