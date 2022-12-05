package com.example.composting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composting.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.title = "Composting Pals"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.register.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_registrationFragment)
        }
        return binding.root
    }
}