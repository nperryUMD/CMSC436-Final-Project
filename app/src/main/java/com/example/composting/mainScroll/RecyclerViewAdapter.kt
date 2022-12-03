package com.example.composting.mainScroll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

lateinit var lineGraphView: GraphView

class RecyclerViewAdapter(context: Context, list: ArrayList<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_COMPOST_BIN = 1
        const val VIEW_ANALYTICS_BIN = 2
        const val VIEW_GAME_BIN = 4
    }

    private val context: Context = context
    var list: ArrayList<Data> = list
    private lateinit var  database : DatabaseReference
    private inner class ViewCompostViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var total: TextView = itemView.findViewById(R.id.totalCompostedText)
        var turn: TextView = itemView.findViewById(R.id.leftCompostItemText)
        var health: TextView = itemView.findViewById(R.id.rightCompostItemText)
        var btn: Button = itemView.findViewById(R.id.compostingCardBtn)
        fun bind(position: Int) {
            val userid = FirebaseAuth.getInstance().currentUser!!.uid
            database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid)

            if(list[position] is CompostCard){
                val  recyclerViewModel = list[position] as CompostCard
                database?.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        total.text = "Composted\n" + dataSnapshot.child("totalCompostEntries").getValue().toString()
                        turn.text = "Turn in\n"  + dataSnapshot.child("turnDays").getValue().toString()
                        health.text = "Health\n" +  dataSnapshot.child("compostHealth").getValue().toString()

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }

                })

                btn.setOnClickListener(){
                    itemView.findNavController().navigate(R.id.compostingDetails);
                }
            }
        }
    }

    private inner class ViewAnalyticsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            if(list[position] is AnalyticsCard){
                val recyclerViewModel = list[position] as AnalyticsCard
                // on below line we are initializing
                // our variable with their ids.
                lineGraphView = itemView.findViewById(R.id.idGraphView)

                // on below line we are adding data to our graph view.
                val series: LineGraphSeries<DataPoint> = LineGraphSeries(
                    arrayOf(
                        // on below line we are adding
                        // each point on our x and y axis.
                        DataPoint(1.0, 1.0),
                        DataPoint(5.0, 3.0),
                        DataPoint(2.0, 4.0),
                        DataPoint(3.0, 9.0),
                        DataPoint(4.0, 6.0),
                        DataPoint(5.0, 3.0),
                        DataPoint(6.0, 6.0),
                        DataPoint(7.0, 1.0),
                        DataPoint(8.0, 2.0)
                    )
                )

                // on below line adding animation
                lineGraphView.animate()

                // on below line we are setting scrollable
                // for point graph view
                lineGraphView.viewport.isScrollable = true

                // on below line we are setting scalable.
                lineGraphView.viewport.isScalable = true

                // on below line we are setting scalable y
                lineGraphView.viewport.setScalableY(true)

                // on below line we are setting scrollable y
                lineGraphView.viewport.setScrollableY(true)

                // on below line we are setting color for series.
//                    series.color = R.color.purple_200

                // on below line we are adding
                // data series to our graph view.
                lineGraphView.addSeries(series)
            }
        }
    }

    private inner class ViewGameViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var coins: TextView = itemView.findViewById(R.id.coinCountText)
        var trophies: TextView = itemView.findViewById(R.id.trophyCountText)
        var percentageText: TextView = itemView.findViewById(R.id.milestonePercentageText)
        var fractionText: TextView = itemView.findViewById(R.id.milestoneFractionText)
        var milestone: ProgressBar = itemView.findViewById(R.id.mileStoneProgressBar)
        var btn: Button = itemView.findViewById(R.id.gameCardBtn)
        fun bind(position: Int) {
            if(list[position] is GameCard) {
                val recyclerViewModel = list[position] as GameCard
                coins.text = "5"
                trophies.text = "10"
                milestone.progress = 50
                percentageText.text = "50%"
                fractionText.text = "10/20"
                btn.setOnClickListener(){
                    itemView.findNavController().navigate(R.id.gameMainScreen);
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_COMPOST_BIN) {
            ViewCompostViewHolder(
                LayoutInflater.from(context).inflate(R.layout.compost_bin, parent, false)
            )
        }else if (viewType == VIEW_ANALYTICS_BIN){
            ViewAnalyticsViewHolder(
                LayoutInflater.from(context).inflate(R.layout.analytics_bin, parent, false)
            )
        }else{
            ViewGameViewHolder(
                LayoutInflater.from(context).inflate(R.layout.game_bin, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === VIEW_COMPOST_BIN) {
            (holder as ViewCompostViewHolder).bind(position)
        }else if (list[position].viewType === VIEW_ANALYTICS_BIN){
            (holder as ViewAnalyticsViewHolder).bind(position)
        }else {
            (holder as ViewGameViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }


}