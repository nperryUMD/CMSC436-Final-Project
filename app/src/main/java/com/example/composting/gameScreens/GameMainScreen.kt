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

class GameMainScreen : Fragment() {

    private lateinit var binding: GameMainScreenFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GameMainScreenFragmentBinding.inflate(inflater, container, false)

        binding.btnBuyTrophies.setOnClickListener {
            //TODO
            // GET coins in account
            // if(coinsAmount > purchase amount)
            //      update coins
            //      update trophies
            //      Display success with TOAST
            // else
            //      Display fail with TOAST

            Toast.makeText(
                context,
                "Sorry, you do not have enough coins!",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.btnUpgradeCoins.setOnClickListener {
            Toast.makeText(
                context,
                "Sorry, you do not have enough coins!",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.btnUpgradeTrophies.setOnClickListener {
            Toast.makeText(
                context,
                "Sorry, you do not have enough trophies!",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.btnUpgradeBin.setOnClickListener {
            Toast.makeText(
                context,
                "Sorry, you do not have enough trophies!",
                Toast.LENGTH_LONG
            ).show()
        }
        // Return the root view.
        return binding.root
    }
}