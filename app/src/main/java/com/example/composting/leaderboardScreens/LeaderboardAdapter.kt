import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.R
import com.example.composting.leaderboardScreens.Person
import kotlin.collections.ArrayList

class LeaderboardAdapter(context: Context, list: MutableLiveData<ArrayList<Person>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var list: MutableLiveData<ArrayList<Person>> = list


    private inner class PersonViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.leftCompostItemText)
        var score: TextView = itemView.findViewById(R.id.rightCompostItemText)
        fun bind(position: Int) {
            val recyclerViewModel = list.value?.get(position)
            text.text =   "${position + 1}. ${recyclerViewModel?.email}"
            score.text = recyclerViewModel?.score.toString()
            text.textSize = 18.0F
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(context).inflate(R.layout.compost_items_fragment, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return list.value!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as LeaderboardAdapter.PersonViewHolder).bind(position)
    }
}