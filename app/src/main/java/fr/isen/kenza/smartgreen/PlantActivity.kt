package fr.isen.kenza.smartgreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.databinding.ActivityHomeBinding
import fr.isen.kenza.smartgreen.databinding.ActivityPlantBinding

class PlantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlantBinding
    lateinit var adapter: RecyclerView_Adapter
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlantrecyclerView.layoutManager = LinearLayoutManager(this)
        binding.PlantrecyclerView.adapter=  PlantAdapter(titlesList, descList, imagesList)

        postToList()


    }

    private fun addToList(title: String, description: String, image: Int){
        titlesList.add(title)
        descList.add(description)
        imagesList.add((image))


        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                    adapter.filter.filter(p0)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })



    }

    private fun postToList(){


            addToList("Rose", "Exposition : Lumière ambiante du soleil mais indirecte.",
                R.drawable.rose)

            addToList("Aloe Vera", "L’entretien de l’aloé vera doit être régulier mais modéré : pensez à arroser 1 fois par semaine environ, surtout lorsque le sol est sec en surface. ",
                R.drawable.aloevera)


          addToList("Anthurium", "La température d’une maison ou d’un appartement est idéal car elle doit être comprise en 18 et 21° toute l’année.",
              R.drawable.anthurium)


      addToList("Cactus",  "Arrosez uniquement votre cactée si le terreau est sec..",R.drawable.cactus)
        addToList("Violette","La violette africaine a besoin", R.drawable.violette1)

      addToList("Cyclamen", "Le cyclamen a besoin de fraîcheur pour rester beau ", R.drawable.rose)


     addToList("Orchidee", "La température adéquate pour les orchidées est comprise entre 20 et 25° C. Il faudra arroser l’orchidée environ 1 fois tous les 10 jours en période de floraison et tous les 20 jours environ quand l’orchidée est défleurie. ", R.drawable.orchidee)




}


}