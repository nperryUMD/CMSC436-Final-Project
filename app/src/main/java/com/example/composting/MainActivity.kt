package com.example.composting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.composting.databinding.MainActivityBinding
import com.google.firebase.provider.FirebaseInitProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase APIs
        FirebaseInitProvider()

        // Use the provided ViewBinding class to inflate the
        // layout and set content view to the root view.
        setContentView(MainActivityBinding.inflate(layoutInflater).root)
    }
}
