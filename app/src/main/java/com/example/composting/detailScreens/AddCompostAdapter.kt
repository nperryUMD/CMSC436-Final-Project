import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.R
import com.example.composting.detailScreens.classes.CompostItems
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.android.material.snackbar.Snackbar

class AddCompostAdapter(context: Context, list: ArrayList<CompostItems>, selection: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selection : Int = selection
    private val context: Context = context
    var list: ArrayList<CompostItems> = list
    private lateinit var  database : DatabaseReference

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.leftCompostItemText)
        var health: TextView = itemView.findViewById(R.id.rightCompostItemText)
        var box: MaterialCardView = itemView.findViewById(R.id.masterCompostItem)
        fun bind(position: Int) {
            val recyclerViewModel = list[position] as CompostItems
            box.setOnClickListener {

                val userid = FirebaseAuth.getInstance().currentUser!!.uid
                database = FirebaseDatabase.getInstance().reference.child("Users").child(userid)

                database?.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        var healthVal =
                            dataSnapshot.child("compostHealth").getValue(Int::class.java)
                        var numOfEntries =
                            dataSnapshot.child("totalCompostEntries").getValue(Int::class.java)
                        var coins = dataSnapshot.child("coins").value.toString().toInt()
                        var trophies = dataSnapshot.child("trophies").value.toString().toInt()
                        var trophyMultiplier =
                            dataSnapshot.child("trophyMultiplier").value.toString().toDouble()
                        var coinMultiplier =
                            dataSnapshot.child("coinMultiplier").value.toString().toDouble()
                        var currMilestones =
                            dataSnapshot.child("milestones").value.toString().toInt()
                        var currMilestoneProgress =
                            dataSnapshot.child("milestoneProgress").value.toString().toInt()
                        var currMilestoneMultiplier =
                            dataSnapshot.child("milestoneMultiplier").value.toString().toDouble()

                        //update information when items added
                        database.child("compostHealth")
                            .setValue(healthVal.toString().toInt() + health.text.toString().toInt())
                        database.child("totalCompostEntries")
                            .setValue(numOfEntries.toString().toInt() + 1)

                        database.child("milestoneProgress").setValue((currMilestoneMultiplier).toInt() + currMilestoneProgress)

                        var newCoinValue = coins + (coinMultiplier).toInt()
                        if (newCoinValue >= 1000){
                            newCoinValue -= 1000
                            database.child("trophies").setValue(trophies + (trophyMultiplier).toInt())
                        }
                        //update coin values
                        database.child("coins").setValue(newCoinValue)

                        //update trophy values and milestone when reach milestone
                        if (dataSnapshot.child("milestoneProgress").value.toString()
                                .toInt() >= 100
                        ) {
                            database.child("milestones").setValue(currMilestones + 1)
                            database.child("milestoneProgress").setValue(0)
                            database.child("trophies").setValue(trophies + (10 * trophyMultiplier))

                        }
                        //update nitrogen, carbon and live totals
                        val currNitrogenVal = dataSnapshot.child("nitrogenTotal").value.toString().toInt()
                        val currCarbonVal = dataSnapshot.child("carbonTotal").value.toString().toInt()
                        val currLiveVal = dataSnapshot.child("liveTotal").value.toString().toInt()
                        if(health.text.toString().toInt() >0 ){
                            database.child("nitrogenTotal").setValue(currNitrogenVal+1)
                        }else if(health.text.toString().toInt()<0){
                            database.child("carbonTotal").setValue(currCarbonVal+1)
                        }else if (health.text.toString().toInt() == 0){
                            database.child("liveTotal").setValue(currLiveVal+1)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
                // Add health to compost

                var snackString = recyclerViewModel.name + " " + context.getString(R.string.compost_added)

                Snackbar.make(box,
                    snackString, Snackbar.LENGTH_SHORT).show()
            }
            text.text = recyclerViewModel.name
            health.text = recyclerViewModel.health.toString()
        }
    }

    private inner class Blank(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (selection == viewType){
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.compost_items_fragment, parent, false)
            )
        }
        return Blank(
            LayoutInflater.from(context).inflate(R.layout.blank_frag, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].category
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (selection == list[position].category){
            (holder as AddCompostAdapter.View1ViewHolder).bind(position)
        }else{
            (holder as AddCompostAdapter.Blank).bind(position)
        }
    }
}