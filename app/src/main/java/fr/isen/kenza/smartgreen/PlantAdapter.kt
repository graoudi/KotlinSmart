package fr.isen.kenza.smartgreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.smartgreen.databinding.PlantadapterBinding

class PlantAdapter (private var titles: List<String>,
private var details : List<String>, private var images: List<Int>)
    :RecyclerView.Adapter<PlantAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlantadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    class ViewHolder(itemView: PlantadapterBinding) : RecyclerView.ViewHolder(itemView.root){
    val itemTitle: TextView = itemView.plantName
        val itemDetail: TextView= itemView.plantDetail
        val itemPicture: ImageView = itemView.catagoryImage
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemPicture.setImageResource(images[position])

    }
}