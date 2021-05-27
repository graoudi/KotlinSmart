package fr.isen.kenza.smartgreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.smartgreen.databinding.ActivityPlantBinding
import fr.isen.kenza.smartgreen.databinding.PlantadapterBinding
import java.util.*
import kotlin.collections.ArrayList

//adapter de la plante
class RecyclerView_Adapter(private var pList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>(), Filterable {

    var FilterList = ArrayList<String>()

    lateinit var mcontext: Context

    class CHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        FilterList = pList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder{
        val binding = PlantadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = FilterList[position]
    }

    class ViewHolder(itemView: PlantadapterBinding) : RecyclerView.ViewHolder(itemView.root){
        val itemTitle: TextView = itemView.plantName

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    FilterList = pList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in pList) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    FilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = FilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                FilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}