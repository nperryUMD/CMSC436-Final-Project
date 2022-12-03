package com.example.composting.detailScreens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.composting.MainActivity
import com.example.composting.R
import com.example.composting.databinding.CompostingDetailsFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CompostingDetails : Fragment() {
    private lateinit var  database : DatabaseReference
    private lateinit var binding: CompostingDetailsFragmentBinding


    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.title = "Your bin"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid)
        // Inflate the layout for this fragment
        binding = CompostingDetailsFragmentBinding.inflate(inflater, container, false)
        binding.btnTurnCompost.setOnClickListener{
            // Turn the compost
            Toast.makeText(
                context,
                "Compost has been turned!",
                Toast.LENGTH_LONG
            ).show()

            // update the database
        }
        binding.btnAddItems.setOnClickListener { container!!.findNavController().navigate(R.id.action_compostingDetails2_to_addCompost) }


        database?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // HEALTH CARD UPDATES
                val healthInt: Int = dataSnapshot.child("compostHealth").getValue().toString().toInt()
                if (healthInt > 5){
                    binding.healthText.text = "Bad"
                    binding.healthText.setBackgroundColor(Color.parseColor("#f0554a"))
                    binding.substanceSuggestionText.text = "Your compost health is carbon-rich"
                }else if (healthInt < -5){
                    binding.healthText.text = "Bad"
                    binding.healthText.setBackgroundColor(Color.parseColor("#f0554a"))
                    binding.substanceSuggestionText.text = "Your compost health is nitrogen-rich"
                }else{
                    binding.healthText.text = "Great"
                    binding.healthText.setBackgroundColor(Color.parseColor("#50f04a"))
                    binding.substanceSuggestionText.text = "Your compost health is well balanced"
                }

                // TURN CARD UPDATES
                val turnInt: Int = dataSnapshot.child("turnDays").getValue().toString().toInt()
                if (turnInt == 0){
                    binding.turnDaysText.text = "Turn today"
                    binding.turnDetailsDaysText.text = "You should turn your compost today"
                }else{
                    binding.turnDetailsDaysText.text = "Turn in $turnInt days"
                    binding.turnDetailsDaysText.text = "You should turn your compost in $turnInt days"
                }

                // TOTAL CARD UPDATES
                val totalInt: Int = dataSnapshot.child("totalCompostEntries").getValue().toString().toInt()
                val carbonInt: Int = 0 // dataSnapshot.child("totalCompostEntries").getValue().toString().toInt()
                val nitrogenInt: Int = 0 // dataSnapshot.child("totalCompostEntries").getValue().toString().toInt()
                val liveInt: Int = 0 // dataSnapshot.child("totalCompostEntries").getValue().toString().toInt()

                binding.totalStatsText.text = "$totalInt Items"
                binding.detailsStatsText.text = "You have $carbonInt carbon rich, $nitrogenInt nitrogen rich, $liveInt live animals"
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }

        })

        // Return the root view.
        return binding.root
    }
}