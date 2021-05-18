package fr.isen.kenza.smartgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.kenza.smartgreen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonBle.setOnClickListener{
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }
        binding.buttonPlant.setOnClickListener{
            val intent = Intent(this, PlantActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAutom.setOnClickListener{
            val intent = Intent(this, AutomaticActivity::class.java)
            startActivity(intent)
        }

    }
}