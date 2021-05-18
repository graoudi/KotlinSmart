package fr.isen.kenza.smartgreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.R
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.installations.Utils
import fr.isen.kenza.smartgreen.databinding.ActivityHumidityBinding
import fr.isen.kenza.smartgreen.databinding.ActivityTemperatureBinding
import java.util.*
import kotlin.collections.ArrayList


class HumidityActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityHumidityBinding
    private lateinit var sensorManager: SensorManager
    private var humSensor: Sensor? = null

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSensorChanged(event: SensorEvent?) {





        if (event?.sensor?.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            getTemperatureSensor(event)
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
    fun getTemperatureSensor(event: SensorEvent) {
        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val temps = ArrayList<Temp>()
        temps.add(Temp("Humidity Reading ", event.values[0].toFloat()))
        binding.recyclerView2.adapter =  MyAdapter1(temps)
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