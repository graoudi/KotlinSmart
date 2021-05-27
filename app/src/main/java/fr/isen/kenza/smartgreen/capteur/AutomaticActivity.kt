package fr.isen.kenza.smartgreen.capteur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.kenza.smartgreen.databinding.ActivityAutomaticBinding

class AutomaticActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutomaticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //redirection vers les activités après l'appuie sur images
        binding = ActivityAutomaticBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.light.setOnClickListener {
            val intent = Intent(this, SensorActivity::class.java)
            startActivity(intent)
        }
        binding.temp.setOnClickListener {
            val intent = Intent(this, TemperatureActivity::class.java)
            startActivity(intent)
        }
        binding.humidity.setOnClickListener {
            val intent = Intent(this, HumidityActivity::class.java)
            startActivity(intent)
        }


    }
}