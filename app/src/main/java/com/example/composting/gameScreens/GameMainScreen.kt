package com.example.composting.gameScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.composting.R
import com.example.composting.databinding.CompostingDetailsFragmentBinding
import com.example.composting.databinding.GameMainScreenFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GameMainScreen : Fragment() {

    private lateinit var binding: GameMainScreenFragmentBinding
    private lateinit var  database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GameMainScreenFragmentBinding.inflate(inflater, container, false)
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid)
        binding.btnBuyTrophies.setOnClickListener {

            database?.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var currCoins = dataSnapshot.child("coins").getValue().toString().toInt()
                    var currTrophies = dataSnapshot.child("trophies").getValue().toString().toInt()
                    if (currCoins >= 10) {
                        database.child("trophies").setValue(currTrophies + 1)
                        database.child("coins").setValue(currCoins-10)
                        Toast.makeText(
                            context,
                            "Congrats your trophy has been added!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough coins!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }

            })
        }



        binding.btnUpgradeCoins.setOnClickListener {
            database?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var currCoins = dataSnapshot.child("coins").getValue().toString().toInt()
                    if (currCoins >= 50) {
                        database.child("coinMultiplier").setValue(1.1)
                        database.child("coins").setValue(currCoins-50)
                        Toast.makeText(
                            context,
                            "Congrats your coin has been upgraded!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough coins!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }

        binding.btnUpgradeTrophies.setOnClickListener {
            database?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var currTrophies = dataSnapshot.child("trophies").getValue().toString().toInt()
                    if (currTrophies >= 10) {
                        database.child("trophyMultiplier").setValue(1.1)
                        database.child("trophies").setValue(currTrophies-10)
                        Toast.makeText(
                            context,
                            "Congrats your coin has been upgraded!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough coins!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }

        binding.btnUpgradeBin.setOnClickListener {
            database?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var currTrophies = dataSnapshot.child("trophies").getValue().toString().toInt()
                    if (currTrophies >= 25) {
                        database.child("coinMultiplier").setValue(1.1)
                        database.child("trophies").setValue(currTrophies-25)
                        database.child("trophyMultiplier").setValue(1.1)
                        Toast.makeText(
                            context,
                            "Congrats on your upgrade!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough coins!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }
        // Return the root view.
        return binding.root
    }
}