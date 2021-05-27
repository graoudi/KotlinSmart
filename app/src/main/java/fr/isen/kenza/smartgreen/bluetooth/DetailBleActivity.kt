package fr.isen.kenza.smartgreen.bluetooth

import android.bluetooth.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.plante.DetailBleAdapter
import fr.isen.kenza.smartgreen.databinding.ActivityDetailPlantBinding


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class DetailBleActivity : AppCompatActivity() {

    var statut: String = "status :  "
    var bluetoothGatt: BluetoothGatt? = null

    private lateinit var binding: ActivityDetailPlantBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//associe le bon layout a l'activite
        binding = ActivityDetailPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val device: BluetoothDevice? = intent.getParcelableExtra("ble_device")

        bluetoothGatt = device?.connectGatt(this, true, gattCallback)
        binding.nameDeviceBLE.text = device?.name ?: "Appareil Inconnu" //affiche le nom sinon inconnu
        binding.StatusBLE.text = "Status : en cours de connexion" //affiche l'etat du statut
//connexion au ble
        bluetoothGatt?.connect()
        connectToDevice(device)

//affiche  adresse mac
        binding.adressBleDetail.text = device?.address
    }
//connexion au device
    private fun connectToDevice (device: BluetoothDevice?) {
        bluetoothGatt = device?.connectGatt(this, false, gattCallback)
    }
//affiche etat de la connection
    private val gattCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    runOnUiThread {
                        statut = STATE_CONNECTED
                        binding.StatusBLE.text = "Status : " + statut
                    }
                    bluetoothGatt?.discoverServices()
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    runOnUiThread {
                        statut = STATE_DISCONNECTED
                        binding.StatusBLE.text = "Status : " + statut
                    }
                }
            }
        }

    //read la caracteristique
        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            runOnUiThread {
                binding.RecyclerBleScan.adapter?.notifyDataSetChanged()
            }
        }
//write caracteristique
        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            runOnUiThread {
                binding.RecyclerBleScan.adapter?.notifyDataSetChanged()
            }
        }
    //affiche les caracteristiques
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            runOnUiThread {
                binding.RecyclerBleScan.adapter?.notifyDataSetChanged()
            }
        }
//services decouverts avec affichages des donnees type uuid ect..
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            runOnUiThread {
                binding.RecyclerBleScan.adapter = DetailBleAdapter(
                        gatt,
                        gatt?.services?.map {
                            BLEService(it.uuid.toString(), it.characteristics)
                        }?.toMutableList() ?: arrayListOf(), this@DetailBleActivity
                )
                binding.RecyclerBleScan.layoutManager = LinearLayoutManager(this@DetailBleActivity)
            }
        }
    }

    companion object {
        private const val STATE_DISCONNECTED = "Déconnecté"
        private const val STATE_CONNECTED = "Connecté"
    }
}

