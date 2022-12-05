package com.example.composting.login

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composting.R
import com.example.composting.databinding.RegistrationFragmentBinding
import com.example.composting.mainScroll.UserCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class RegistrationFragment : Fragment(){
    private var validator = Validators()
    private lateinit var auth: FirebaseAuth
    private lateinit var  database : DatabaseReference
    internal lateinit var email: String
    /** Binding to XML layout */
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Use the provided ViewBinding class to inflate the layout.
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)

        auth = requireNotNull(FirebaseAuth.getInstance())

        binding.register.setOnClickListener { registerNewUser()
            }

        // Return the root view.
        return binding.root
    }

    private fun registerNewUser() {
        email = binding.email.text.toString()
        val password: String = binding.password.text.toString()

        if (!validator.validEmail(email)) {
            Snackbar.make(requireView(),
                getString(R.string.invalid_email), Snackbar.LENGTH_SHORT).show()

            return
        }

        if (!validator.validPassword(password)) {
            Snackbar.make(requireView(),
                getString(R.string.invalid_password),Snackbar.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    newUserData()
                    Snackbar.make(requireView(),
                        getString(R.string.register_success_string),Snackbar.LENGTH_SHORT).show()

                    findNavController().navigate(
                        R.id.action_registrationFragment_to_dashboardFragment
                    )
                } else {
                    Snackbar.make(requireView(),
                        getString(R.string.register_failed_string),Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    private fun newUserData(){
        val user = FirebaseAuth.getInstance().currentUser
        val userid = user!!.uid
        database = FirebaseDatabase.getInstance().getReference("Users")
        var currDate = LocalDate.now().toString()
        database.child(userid).setValue(UserCard(0,email,0,0,currDate,0,
       0,0,0,0,1.0,1.0,1.0,0,0,0) )

    }


}