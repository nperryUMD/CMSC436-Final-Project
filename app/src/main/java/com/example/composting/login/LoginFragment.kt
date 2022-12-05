package com.example.composting.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composting.R
import com.example.composting.databinding.LoginFragmentBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(){
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Use the provided ViewBinding class to inflate the layout.
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        firebaseAuth = requireNotNull(FirebaseAuth.getInstance())

        binding.login.setOnClickListener { loginUserAccount() }

        // Return the root view.
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    private fun loginUserAccount() {
        val email: String = binding.email.text.toString()
        val password: String = binding.password.text.toString()

        if (TextUtils.isEmpty(email)) {
            hideKeyboard(requireView())
            Snackbar.make(requireView(),
                getString(R.string.login_toast),Snackbar.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            hideKeyboard(requireView())
            Snackbar.make(requireView(),
                getString(R.string.password_toast),Snackbar.LENGTH_SHORT).show()

            return
        }

        binding.progressBar.visibility = View.VISIBLE

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Snackbar.make(requireView(),
                        getString(R.string.Login_successful),Snackbar.LENGTH_SHORT).show()


                    findNavController().navigate(
                        R.id.action_loginFragment_to_dashboardFragment
                    )
                } else {
                    hideKeyboard(requireView())
                    Snackbar.make(requireView(),
                        getString(R.string.Login_failed_Please_try_again_later),Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}