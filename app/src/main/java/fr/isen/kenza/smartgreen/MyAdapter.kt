package fr.isen.kenza.smartgreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val lightList: ArrayList<Light>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_light, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(lightList[position])
    }

    override fun getItemCount(): Int {
        return lightList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(light: Light) {
            val tvName = itemView.findViewById<TextView>(R.id.tvName)
            val tvValue = itemView.findViewById<TextView>(R.id.tvValue)
            tvName.text = light.key
            tvValue.text = light.value
        }
    }
}
class Light(val key: String, val value: String) {
}