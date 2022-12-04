package com.example.composting.gameScreens

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.composting.MainActivity
import com.example.composting.databinding.GameMainScreenFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.math.RoundingMode
import android.util.Log
class GameMainScreen : Fragment() {

    private lateinit var binding: GameMainScreenFragmentBinding
    private lateinit var  database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.title = "Buy something"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GameMainScreenFragmentBinding.inflate(inflater, container, false)
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid)
        database?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var currNextCoinMultiplier = dataSnapshot.child("coinMultiplier").getValue().toString().toBigDecimal() + .1.toBigDecimal()
                var currNextTrophyMultiplier = dataSnapshot.child("trophyMultiplier").getValue().toString().toBigDecimal() + .1.toBigDecimal()
                var currNextMilestoneMultiplier = dataSnapshot.child("milestoneMultiplier").getValue().toString().toBigDecimal() + .1.toBigDecimal()
                binding.gameCoinMultiplyText.text = dataSnapshot.child("coinMultiplier").getValue().toString() +"x"
                binding.gameTrophyMultiplyText.text = dataSnapshot.child("trophyMultiplier").getValue().toString() +"x"
                binding.gameMilestoneMultiplyText.text = dataSnapshot.child("milestoneMultiplier").getValue().toString() +"x"
               binding.textView15.text = "25 Trophies = " + currNextMilestoneMultiplier +"x on all new items!"
                binding.gameTrophyUpgradeText.text =  "10 Trophies = " + currNextTrophyMultiplier + "x on all new Trophies"
               binding.gameCoinUpgradeText.text = "50 coins = " + currNextCoinMultiplier + "x on all new coins"
                binding.gameMilestoneAmountText.text = dataSnapshot.child("milestones").getValue().toString()
                binding.detailsTitleText.text = dataSnapshot.child("coins").getValue().toString()
                binding.gameTrophyAmountText.text = dataSnapshot.child("trophies").getValue().toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        binding.btnBuyTrophies.setOnClickListener {

            database?.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var currCoins = dataSnapshot.child("coins").getValue().toString().toInt()
                    var currTrophies = dataSnapshot.child("trophies").getValue().toString().toInt()
                    if (currCoins >= 10) {
                        database.child("trophies").setValue(currTrophies + 1)
                        database.child("coins").setValue(currCoins-10)
                        binding.gameTrophyAmountText.text = (currTrophies+1).toString()
                        binding.detailsTitleText.text = (currCoins-10).toString()
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
                    var currCoinMultiplier = dataSnapshot.child("coinMultiplier").getValue().toString().toDouble()
                    if (currCoins >= 50) {
                        var newMultiplier = (currCoinMultiplier.toBigDecimal() +.1.toBigDecimal()).toDouble()
                        var nextMultiplier = (currCoinMultiplier.toBigDecimal() +.2.toBigDecimal()).toString()
                        database.child("coinMultiplier").setValue(newMultiplier)
                        database.child("coins").setValue(currCoins-50)
                        binding.gameCoinMultiplyText.text = (currCoinMultiplier.toBigDecimal()+.1.toBigDecimal()).toString() + "x"
                        binding.gameCoinUpgradeText.text = "50 coins = " + nextMultiplier + "x on all new coins"
                        binding.detailsTitleText.text = (currCoins-50).toString()

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
                    var currTrophyMultiplier = dataSnapshot.child("trophyMultiplier").getValue().toString().toDouble()
                    if (currTrophies >= 10) {
                        var newMultiplier = (currTrophyMultiplier.toBigDecimal() +.1.toBigDecimal()).toDouble()
                        var nextMultiplier = (currTrophyMultiplier.toBigDecimal() +.2.toBigDecimal()).toString()
                        database.child("trophyMultiplier").setValue(newMultiplier)
                        database.child("trophies").setValue(currTrophies-10)
                        binding.gameTrophyMultiplyText.text = (currTrophyMultiplier.toBigDecimal()+.1.toBigDecimal()).toString() +"x"
                        binding.gameTrophyUpgradeText.text =  "10 Trophies = " + nextMultiplier  + "x on all new Trophies"
                        binding.gameTrophyAmountText.text = (currTrophies-10).toString()
                        Toast.makeText(
                            context,
                            "Congrats your trophy has been upgraded!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough trophies!",
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
                    var currMilestone = dataSnapshot.child("milestones").getValue().toString().toInt()
                    var currMilestoneMultiplier = dataSnapshot.child("milestoneMultiplier").getValue().toString().toDouble()
                    var currTrophies = dataSnapshot.child("trophies").getValue().toString().toInt()
                    if (currTrophies >= 25) {
                        var newMultiplier = (currMilestoneMultiplier.toBigDecimal() +.1.toBigDecimal()).toDouble()
                        var nextMultiplier = (currMilestoneMultiplier.toBigDecimal() +.2.toBigDecimal()).toString()
                        database.child("milestoneMultiplier").setValue(newMultiplier)
                        database.child("trophies").setValue(currTrophies-25)
                        binding.gameMilestoneMultiplyText.text = (currMilestoneMultiplier.toBigDecimal()+.1.toBigDecimal()).toString() +"x"
                        binding.textView15.text =  "25 Trophies = " + nextMultiplier  + "x on all new items!"
                        binding.gameMilestoneAmountText.text = (currMilestone).toString()
                        Toast.makeText(
                            context,
                            "Congrats your upgrades!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Sorry, you do not have enough trophies!",
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