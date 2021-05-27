package fr.isen.kenza.smartgreen.bluetooth

import android.bluetooth.BluetoothGattCharacteristic
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class BLEService(title: String, items: List<BluetoothGattCharacteristic>) :
//recycler expensible
    ExpandableGroup<BluetoothGattCharacteristic>(title, items)

