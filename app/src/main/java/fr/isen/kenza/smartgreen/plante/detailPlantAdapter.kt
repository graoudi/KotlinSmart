package fr.isen.kenza.smartgreen.plante


import android.app.AlertDialog
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.isen.kenza.smartgreen.R
import fr.isen.kenza.smartgreen.bluetooth.BLEService

//adapter pour expandable
class DetailBleAdapter(
        private val gatt: BluetoothGatt?,
        private val serviceList: MutableList<BLEService>,
        private val context: Context
) :
    ExpandableRecyclerViewAdapter<DetailBleAdapter.ServiceViewHolder, DetailBleAdapter.CharacteristicViewHolder>(
        serviceList
    ) {
    private var enabled : Boolean = false
    class ServiceViewHolder(itemView: View) : GroupViewHolder(itemView) {
//associe la layout au code
        val serviceName: TextView = itemView.findViewById(R.id.nomParent)
        val serviceUUID: TextView = itemView.findViewById(R.id.uuidParent)

        val arrow: ImageView = itemView.findViewById(R.id.arrrowButton)
        override fun expand() {
            super.expand()
            arrow.animate().rotation(-180f).setDuration(400L).start()
        }
    }

    class CharacteristicViewHolder(itemView: View) : ChildViewHolder(itemView) {
        val characteristicUUID: TextView = itemView.findViewById(R.id.UUIDText)
        val characteristicProperties: TextView = itemView.findViewById(R.id.propertiesText)
        val characteristicValue: TextView = itemView.findViewById(R.id.valueText)
        val characteristicNom : TextView = itemView.findViewById(R.id.specificities)

        val characteristicReadAction: Button = itemView.findViewById(R.id.buttonLecture)
        val characteristicWriteAction: Button = itemView.findViewById(R.id.buttonEcriture)
        val characteristicNotifyAction: Button = itemView.findViewById(R.id.buttonNotify)
    }
//cellule parent
    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder =
        ServiceViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.cell_parent, //correpond au parent, expandable de base
                        parent,
                        false
                )
        )
//cellule enfant
    override fun onCreateChildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacteristicViewHolder =
        CharacteristicViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.cell_enfant, //correspond au cell enfant, info en depliant l'expandable
                        parent,
                        false
                )
        )

    //permet de lire/ecrire/notifier les valeurs
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onBindChildViewHolder(
            holder: CharacteristicViewHolder, flatPosition: Int, group: ExpandableGroup<*>,
            childIndex: Int
    ) {
        val characteristic: BluetoothGattCharacteristic = (group as BLEService).items[childIndex]
        val title = BleUuidAttribut.getBLEAttributeFromUUID(group.title).title
        val uuid = "UUID : ${characteristic.uuid}"
        holder.characteristicUUID.text = uuid
        holder.characteristicNom.text = title

        holder.characteristicReadAction.visibility = View.GONE
        holder.characteristicNotifyAction.visibility = View.GONE
        holder.characteristicWriteAction.visibility = View.GONE
        if (proprieties(characteristic.properties).contains("Lecture")) {
            holder.characteristicReadAction.visibility = View.VISIBLE
        }
        if (proprieties(characteristic.properties).contains("Ecriture")) {
            holder.characteristicWriteAction.visibility = View.VISIBLE
        }
        if (proprieties(characteristic.properties).contains("Notifier")) {
            holder.characteristicNotifyAction.visibility = View.VISIBLE
        }
//clique pour faire une action de type ecrite/lire/notifier
        holder.characteristicProperties.text = "propri??t??s : ${proprieties(characteristic.properties)}"

        //fenetre de dialogue pour ecrire une valeur
        holder.characteristicWriteAction.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            val editView = View.inflate(context, R.layout.popupecriture, null)
            alertDialog.setView(editView)
            alertDialog.setPositiveButton("Valider") { _, _ ->

                val popup: TextView = editView.findViewById(R.id.popup)
                val texte = popup.text.toString().toByteArray()

                characteristic.setValue(texte)

                val result1 = gatt?.writeCharacteristic(characteristic)
                Log.d("erreur : ", result1.toString())

                val result = gatt?.readCharacteristic(characteristic)
                Log.d("erreur : ", result.toString())
            }
            alertDialog.setNegativeButton("Annuler") { alertDialog, _ -> alertDialog.cancel() }
            alertDialog.create()
            alertDialog.show()
        }

        holder.characteristicReadAction.setOnClickListener {
            val result = gatt?.readCharacteristic(characteristic)

            Log.d("erreur : ", result.toString())
            Log.d("valeur de uid : ", characteristic.uuid.toString())
            if (characteristic.value != null) {

                val texteRecu = String(characteristic.value)
                holder.characteristicValue.text = "Valeur : ${ texteRecu}"
            }

        }

        holder.characteristicNotifyAction.setOnClickListener {
            if (!enabled) {
                enabled = true
                gatt?.setCharacteristicNotification(characteristic, true)

                if (characteristic.descriptors.size > 0) {

                    val descriptors = characteristic.descriptors
                    for (descriptor in descriptors) {

                        if (characteristic.properties and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0) {
                            descriptor.value =
                                if (enabled) BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
                        } else if (characteristic.properties and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0) {
                            descriptor.value =
                                if (enabled) BluetoothGattDescriptor.ENABLE_INDICATION_VALUE else BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
                        }
                        gatt?.writeDescriptor(descriptor)
                    }

                }
            } else {
                enabled = false
                gatt?.setCharacteristicNotification(characteristic, false)
            }
        }
        if (characteristic.uuid.toString() == BleUuidAttribut.getBLEAttributeFromUUID(characteristic.uuid.toString()).uuid && enabled) {
            if (characteristic.value == null)
                holder.characteristicValue.text = "Valeur : 0"
            else {
                holder.characteristicValue.text =
                    "Valeur : ${byteArrayToHexString(characteristic.value)}"
            }

        }


    }
    //format des valeurs
    private fun byteArrayToHexString(array: ByteArray): String {
        val result = StringBuilder(array.size * 2)
        for ( byte in array ) {
            val toAppend = String.format("%X", byte)
            result.append(toAppend).append("-")
        }
        result.setLength(result.length - 1)
        return result.toString()
    }

    override fun onBindGroupViewHolder(
            holder: ServiceViewHolder, flatPosition: Int,
            group: ExpandableGroup<*>
    ) {
        val title = BleUuidAttribut.getBLEAttributeFromUUID(group.title).title
        holder.serviceName.text = title
        holder.serviceUUID.text = group.title
    }


    private fun proprieties(property: Int): StringBuilder {
        val sb = StringBuilder()
        if (property and BluetoothGattCharacteristic.PROPERTY_WRITE != 0) {
            sb.append("Ecriture")
        }
        if (property and BluetoothGattCharacteristic.PROPERTY_READ != 0) {
            sb.append(" Lecture")
        }
        if (property and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0) {
            sb.append(" Notifier")
        }
        if (sb.isEmpty()) sb.append("Aucune")
        return sb
    }

    enum class BleUuidAttribut(val uuid: String, val title: String) {
        ACCES_GENERIQUE("00001800-0000-1000-8000-00805f9b34fb", "Acc??s g??n??rique"),
        ATTRIBUT_GENERIQUE("00001801-0000-1000-8000-00805f9b34fb", "Attribut g??n??rique"),
        SERVICE_SPECIFIQUE("466c1234-f593-11e8-8eb2-f2801f1b9fd1", "Service Sp??cifique "),
        SERVICE_SPE2("466c9abc-f593-11e8-8eb2-f2801f1b9fd1", "Service Sp??cifique "),
        UNKNOW_SERVICE("", "Inconnu");

        companion object {
            fun getBLEAttributeFromUUID(uuid: String) = values().firstOrNull {
                it.uuid == uuid
            } ?: UNKNOW_SERVICE
        }
    }
}
