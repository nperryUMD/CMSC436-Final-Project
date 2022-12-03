import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.R
import com.example.composting.detailScreens.classes.CompostItems
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class LeaderboardAdapter(context: Context, list: ArrayList<CompostItems>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var list: ArrayList<CompostItems> = list
    private lateinit var  database : DatabaseReference

    private inner class PersonViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.leftCompostItemText)
        var score: TextView = itemView.findViewById(R.id.rightCompostItemText)
        var box: MaterialCardView = itemView.findViewById(R.id.masterCompostItem)
        fun bind(position: Int) {
            val recyclerViewModel = list[position] as CompostItems
            text.text =  (position + 1).toString() + ". " + recyclerViewModel.name
            score.text = recyclerViewModel.health.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(context).inflate(R.layout.compost_items_fragment, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].category
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as LeaderboardAdapter.PersonViewHolder).bind(position)
    }
}