package fr.isen.kenza.smartgreen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.databinding.ActivityTemperatureBinding


class TemperatureActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityTemperatureBinding
    private lateinit var sensorManager: SensorManager
    private var tempSensor: Sensor? = null
    private val mAbsoluteHumidityValue: TextView? = null
    private val mLastKnownRelativeHumidity = 0f

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSensorChanged(event: SensorEvent?) {





        if (event?.sensor?.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            getTemperatureSensor(event)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sensorManager = (getSystemService(Context.SENSOR_SERVICE) as SensorManager?)!!
        tempSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
     fun getTemperatureSensor(event: SensorEvent) {
        binding.recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val temps = ArrayList<Temp>()
        temps.add(Temp("Temperature Reading ", event.values[0].toFloat()))
        binding.recyclerView1.adapter =  MyAdapter1(temps)
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}