import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composting.R
import com.example.composting.detailScreens.classes.CompostItems
import com.google.android.material.card.MaterialCardView

class AddCompostAdapter(context: Context, list: ArrayList<CompostItems>, selection: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selection : Int = selection
    private val context: Context = context
    var list: ArrayList<CompostItems> = list


    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.leftCompostItemText)
        var health: TextView = itemView.findViewById(R.id.rightCompostItemText)
        var box: MaterialCardView = itemView.findViewById(R.id.masterCompostItem)
        fun bind(position: Int) {
            val recyclerViewModel = list[position] as CompostItems
            box.setOnClickListener() {

                // TODO
                // Add health to compost

                var toastString = recyclerViewModel.name + " " + context.getString(R.string.compost_added)
                Toast.makeText(
                    context,
                    toastString,
                    Toast.LENGTH_LONG
                ).show()
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