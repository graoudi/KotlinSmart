package fr.isen.kenza.smartgreen.bluetooth

import android.bluetooth.le.ScanResult
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.smartgreen.R
import fr.isen.kenza.smartgreen.databinding.CellBleDevicesBinding


class BleScanAdapter
    (private val listBle: MutableList<ScanResult> //liste vides
     ,private val onClickListener:  (ScanResult) -> Unit
): RecyclerView.Adapter<BleScanAdapter.BleViewHolder>() {

//redirection vers le bon layouts
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleViewHolder {
        val binding = CellBleDevicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listBle.size //de la taille listBle
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: BleViewHolder, position: Int) {
        holder.textTitle.text = listBle[position].device.toString() //affiche adresse

        holder.nameTitle.text = listBle[position].device.name?: "Unknown" //affiche le nom sinon affiche inconnus

        holder.numID.text = "${listBle[position].rssi} dBm" //affiche rssi
        holder.layoutBle.setOnClickListener { onClickListener.invoke(listBle[position]) } //clique sur le layout

    }

    class BleViewHolder(bleBinding: CellBleDevicesBinding) : RecyclerView.ViewHolder(bleBinding.root){
        //associe les donnnes du layout au code
        val textTitle: TextView = itemView.findViewById(R.id.adressDevice)
        val nameTitle: TextView = itemView.findViewById(R.id.nameDevice)
        val numID: TextView = itemView.findViewById(R.id.buttonNumber)
        val layoutBle = itemView.findViewById<View>(R.id.cellBluetooth)

    }
//ajoute les données trouvé par scan result et rempli la liste
    fun addDevice(data: ScanResult) {
        if (!listBle.contains(data)) {
            listBle.add(data)

            //  listBle.add(name)
        }
    }



}