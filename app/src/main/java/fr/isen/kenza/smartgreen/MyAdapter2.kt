package fr.isen.kenza.smartgreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter2(private val humList: ArrayList<Humidity>) : RecyclerView.Adapter<MyAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_humidity, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(humList[position])
    }

    override fun getItemCount(): Int {
        return humList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(hum: Humidity) {
            val Name = itemView.findViewById<TextView>(R.id.tvName2)
            val Value = itemView.findViewById<TextView>(R.id.tvValue2)
            Name.text = hum.key
            Value.text = hum.value.toString()
        }
    }
}
class Humidity(val key: String, val value: Float) {
}