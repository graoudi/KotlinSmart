package fr.isen.kenza.smartgreen



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter1(private val tempList: ArrayList<Temp>) : RecyclerView.Adapter<MyAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_temperature, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(tempList[position])
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(temp: Temp) {
            val Name = itemView.findViewById<TextView>(R.id.tvName1)
            val Value = itemView.findViewById<TextView>(R.id.tvValue1)
            Name.text = temp.key
            Value.text = temp.value.toString()
        }
    }
}
class Temp(val key: String, val value: Float) {
}