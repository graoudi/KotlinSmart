package fr.isen.kenza.smartgreen.capteur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import fr.isen.kenza.smartgreen.databinding.ActivityHomeBinding
import fr.isen.kenza.smartgreen.databinding.ActivityNeedBinding

class SensorValue : AppCompatActivity() {
    private lateinit var binding: ActivityNeedBinding
    private var imagesList = mutableListOf<Int>()
//tableaux de valeur
    var humidite  = arrayOf("50%", "60%", "70%")
    var temperature  = arrayOf("15°C", "18°C", "20°C")
    var lumiere  = arrayOf("200Lx", "300Lx", "150Lx")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeedBinding.inflate(layoutInflater)

        setContentView(binding.root)

        for (i in (humidite)){
            binding.humText.text = i
        }

        for (i in (temperature)){
            binding.tempText.text = i
        }
        for (i in (lumiere)){
            binding.lightText.text = i
        }
        //postToList()

    }

/*
    private fun addToList( image: Int){

        imagesList.get(image)
        binding.humText.text = "50%"
    }
    private fun postToList() {

        addToList(R.drawable.hibiscus)
    }


 */
    }