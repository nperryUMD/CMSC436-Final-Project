package com.example.composting.detailScreens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.composting.MainActivity
import com.example.composting.R
import com.example.composting.databinding.CompostingDetailsFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.DAYS
import com.google.android.material.snackbar.Snackbar


class CompostingDetails : Fragment() {
    private lateinit var  database : DatabaseReference
    private lateinit var binding: CompostingDetailsFragmentBinding
    var currDate = LocalDate.now()


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
        database?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var numOfDays = dataSnapshot.child("turnDays").getValue().toString().toInt()
                binding.turnDaysText.text = "Turn in $numOfDays days"
                binding.turnDetailsDaysText.text = "You should turn your compost in $numOfDays days"
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        binding.btnTurnCompost.setOnClickListener{
            database?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    database.child("turnDate").setValue(currDate.toString())
                    database.child("turnDays").setValue(7)
                    binding.turnDaysText.text = "Turn in 7 days"
                    binding.turnDetailsDaysText.text =
                        "You should turn your compost in 7 days"

                    Snackbar.make(requireView(),
                        getString(R.string.compost_has_been_turned), Snackbar.LENGTH_SHORT).show()
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

        }

        binding.btnAddItems.setOnClickListener { container!!.findNavController().navigate(R.id.action_compostingDetails2_to_addCompost) }

        database?.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // HEALTH CARD UPDATES
                val healthInt: Int = dataSnapshot.child("compostHealth").getValue().toString().toInt()
                if (healthInt > 15){
                    binding.healthText.text = getString(R.string.Bad)
                    binding.healthText.setBackgroundColor(Color.parseColor("#f0554a"))
                    binding.substanceSuggestionText.text = getString(R.string.Your_compost_health_is_nitrogen_rich)
                }else if (healthInt < -15){
                    binding.healthText.text = "Bad"
                    binding.healthText.setBackgroundColor(Color.parseColor("#f0554a"))
                    binding.substanceSuggestionText.text = getString(R.string.Your_compost_health_is_carbon_rich)
                }else{
                    binding.healthText.text = "Great"
                    binding.healthText.setBackgroundColor(Color.parseColor("#50f04a"))
                    binding.substanceSuggestionText.text = getString(R.string.Your_compost_health_is_well_balanced)
                }

                // TURN CARD UPDATES
                    val format: DateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-d")
                     var turned =dataSnapshot.child("turnDate").getValue().toString()

                    val currDay: LocalDate = LocalDate.parse(currDate.toString(), format)
                     val turnedDate: LocalDate = LocalDate.parse(turned, format)
                    val dayNextTurn = 7 - (DAYS.between(turnedDate,currDay ).toInt())
                    val turnInt: Int = dayNextTurn.toInt()
                     database.child("turnDays").setValue(dayNextTurn)
                    if(dayNextTurn.toInt() == 0 && dataSnapshot.child("turnDays").getValue().toString().toInt() == 7){
                        database.child("turnDays").setValue(dayNextTurn)
                        val turnInt: Int = dataSnapshot.child("turnDays").getValue().toString().toInt()
                    }

                    if (turnInt == 0 ) {
                        binding.turnDaysText.text = getString(R.string.Turn_today)
                        binding.turnDetailsDaysText.text = getString(R.string.You_should_turn_your_compost_today)
                    } else {
                        binding.turnDaysText.text = "Turn in $turnInt days"
                        binding.turnDetailsDaysText.text =
                            "You should turn your compost in $turnInt days"
                        database.child("turnDays").setValue(dayNextTurn)
                }

                // TOTAL CARD UPDATES
                val totalInt: Int = dataSnapshot.child("totalCompostEntries").getValue().toString().toInt()
                val carbonInt: Int =  dataSnapshot.child("carbonTotal").getValue().toString().toInt()
                val nitrogenInt: Int =  dataSnapshot.child("nitrogenTotal").getValue().toString().toInt()
                val liveInt: Int =  dataSnapshot.child("liveTotal").getValue().toString().toInt()

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