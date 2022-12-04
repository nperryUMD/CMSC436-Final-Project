package com.example.composting.leaderboardScreens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class Datasource {
    private lateinit var  database : DatabaseReference

    fun load() : MutableLiveData<ArrayList<Person>>{
        val ranking = MutableLiveData(ArrayList<Person>())
        val list = ArrayList<Person>()

        database = FirebaseDatabase.getInstance().reference.child("Users")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    val email = dataSnapshot1.child("email").value.toString()
                    val score = dataSnapshot1.child("coins").value.toString().toInt()
                    list.add(Person(email, score))
                }
                val sortedList = ArrayList(list.sortedWith(compareByDescending { it.score }))

                ranking.postValue(sortedList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }

        })

        return ranking
    }
}