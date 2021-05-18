package fr.isen.kenza.smartgreen

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import fr.isen.kenza.smartgreen.databinding.ActivityDetailBleBinding


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class DetailBleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBleBinding
    private lateinit var data: BluetoothDevice



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        data= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)?: error("missing ble device")

      //  binding.test1.text = data.uuids.toString()
        //binding.test2.text= data.address
    //    binding.test3.text = data.name


    }
    private fun BluetoothGatt.printGattTable() {
        if (services.isEmpty()) {
            Log.i("printGattTable", "No service and characteristic available, call discoverServices() first?")
            return
        }
        services.forEach { service ->
            val characteristicsTable = service.characteristics.joinToString(
                separator = "\n|--",
                prefix = "|--"
            ) { it.uuid.toString() }
            Log.i("printGattTable", "\nService ${service.uuid}\nCharacteristics:\n$characteristicsTable"
            )
        }
    }


}
