package com.example.composting.detailScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import com.example.composting.R
import com.example.composting.databinding.CompostingDetailsFragmentBinding
import com.example.composting.databinding.RegistrationFragmentBinding

class compostingDetails : Fragment() {

    private lateinit var binding: CompostingDetailsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = CompostingDetailsFragmentBinding.inflate(inflater, container, false)
        binding.detailsItemAddBtn.setOnClickListener { container!!.findNavController().navigate(R.id.action_compostingDetails_to_addCompost) }
        binding.compostingBackBtn.setOnClickListener { container!!.findNavController().navigate(R.id.action_compostingDetails_to_dashboardFragment) }


        // Return the root view.
        return binding.root
    }
}