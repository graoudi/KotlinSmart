package fr.isen.kenza.smartgreen.capteur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.capteur.Humidity
import fr.isen.kenza.smartgreen.capteur.MyAdapter2
import fr.isen.kenza.smartgreen.databinding.ActivityHumidityBinding
import kotlin.collections.ArrayList


class HumidityActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityHumidityBinding
    private lateinit var sensorManager: SensorManager
    private var humSensor: Sensor? = null

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSensorChanged(event: SensorEvent?) {

        //recupere l'humidite

        if (event?.sensor?.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            getHumSensor(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHumidityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sensorManager = (getSystemService(Context.SENSOR_SERVICE) as SensorManager?)!!
        humSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getHumSensor(event: SensorEvent) {
        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//redirection vers l'adapter
        val temps = ArrayList<Humidity>()
        temps.add(Humidity("Humidity Reading ", event.values[0].toString()))
        binding.recyclerView2.adapter = MyAdapter2(temps)
    }



    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, humSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}