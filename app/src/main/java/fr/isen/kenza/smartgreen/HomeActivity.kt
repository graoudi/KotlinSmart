package fr.isen.kenza.smartgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.kenza.smartgreen.bluetooth.ScanActivity
import fr.isen.kenza.smartgreen.capteur.AutomaticActivity
import fr.isen.kenza.smartgreen.databinding.ActivityHomeBinding
import fr.isen.kenza.smartgreen.plante.PlantActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
//page accueil avec les trois boutons principaux pour la redirection vers les activites
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