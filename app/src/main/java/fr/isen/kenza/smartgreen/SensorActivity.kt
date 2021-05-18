package fr.isen.kenza.smartgreen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.databinding.ActivityHomeBinding
import fr.isen.kenza.smartgreen.databinding.LightSensorBinding

class SensorActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: LightSensorBinding
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null



        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSensorChanged(event: SensorEvent?) {

        if(event!!.sensor.type == Sensor.TYPE_LIGHT){
            getLightSensor(event)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LightSensorBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sensorManager = (getSystemService(Context.SENSOR_SERVICE) as SensorManager?)!!
        lightSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getLightSensor(event: SensorEvent) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val lights = ArrayList<Light>()
        lights.add(Light("Light Sensor Reading ", event.values[0].toString()))
        binding.recyclerView.adapter =  MyAdapter(lights)
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}