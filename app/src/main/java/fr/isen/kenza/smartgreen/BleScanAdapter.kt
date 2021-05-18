package fr.isen.kenza.smartgreen

import android.bluetooth.le.ScanResult
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import fr.isen.kenza.smartgreen.databinding.CellBleDevicesBinding


class BleScanAdapter
    (private val listBle: MutableList<ScanResult>
     ,private val onClickListener:  (ScanResult) -> Unit
): RecyclerView.Adapter<BleScanAdapter.BleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleViewHolder {
        val binding = CellBleDevicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listBle.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: BleViewHolder, position: Int) {
        holder.textTitle.text = listBle[position].device.toString()

        holder.nameTitle.text = listBle[position].device.name?: "Unknown"
        //  holder.nameTitle.text = listBle[position].scanRecord?.deviceName.toString()
        //holder.numID.text = listBle[position].scanRecord?.advertiseFlags.toString()
        holder.numID.text = "${listBle[position].rssi} dBm"
        holder.layoutBle.setOnClickListener { onClickListener.invoke(listBle[position]) }

    }

    class BleViewHolder(bleBinding: CellBleDevicesBinding) : RecyclerView.ViewHolder(bleBinding.root){
        val textTitle: TextView = itemView.findViewById(R.id.adressDevice)
        val nameTitle: TextView = itemView.findViewById(R.id.nameDevice)
        val numID: TextView = itemView.findViewById(R.id.buttonNumber)
        val layoutBle = itemView.findViewById<View>(R.id.cellBluetooth)

    }

    fun addDevice(data: ScanResult) {
        if (!listBle.contains(data)) {
            listBle.add(data)

            //  listBle.add(name)
        }
    }



}