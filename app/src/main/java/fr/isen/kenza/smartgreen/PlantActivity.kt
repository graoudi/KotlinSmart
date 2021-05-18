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
        addToList("Cactus",  "Arrosez uniquement votre cactée si le terreau est sec..",
            R.drawable.cactus)
        addToList("Violette","La violette africaine a besoin",
            R.drawable.violette1)
        addToList("Cyclamen", "Le cyclamen a besoin de fraîcheur pour rester beau ",
            R.drawable.rose)
        addToList("Orchidee", "La température adéquate pour les orchidées est comprise entre 20 et 25° C. Il faudra arroser l’orchidée environ 1 fois tous les 10 jours en période de floraison et tous les 20 jours environ quand l’orchidée est défleurie. ",
            R.drawable.orchidee)

        addToList("Basilic", "Choisissez un endroit lumineux et ensoleillé, le bord d'une fenêtre par exemple. Arrosez dès que le sol est sec.",
            R.drawable.basilic)
        addToList("Romarin", "Offrez-lui une exposition lumineuse et chaude. Abritez-le des vents froids. Installez-le plein sud ou sud-ouest. Le romarin est sobre. L'arrosage n'est utile qu'à la plantation et l'été qui suit. Il se contentera de la pluie les années suivantes.",
            R.drawable.romarin)
        addToList("Menthe", "se cultive de préférence seule et demande un substrat toujours humide sauf en hiver. Elle préfère une terre riche tout au long de l’année. Exposée à l’ombre ou en lumière tamisée, la menthe devient très résistante. Selon les espèces, elle est rustique jusqu’à -5 ou -15°C.",
            R.drawable.menthe)
        addToList("Ciboulette", "L’idéal est de surmonter la ciboulette d’une cloche en verre ou de la mettre dans une petite serre.\n" +
                "La ciboulette peut pousser en hiver si les températures restent supérieures à 10-12°.",
            R.drawable.ciboulette)
        addToList("Persil", "Arroser en cas de fortes chaleurs ou de sécheresse prolongée. Le persil redoute en effet les coups de chauds qui ont tendance à le faire jaunir.",
            R.drawable.persil)
        addToList("Hibiscus", "L'hibiscus a besoin de beaucoup de lumière, mais jamais de soleil direct qui risque de brûler la fleur. L'idéal est de la placer derrière une fenêtre bien exposée, mais avec un rideau occultant. Pour bien s'épanouir, l'hibiscus a besoin d'au moins 6 heures de soleil par jour.",
            R.drawable.hibiscus)
        addToList("Tulipe", "Les tulipes boivent beaucoup en absorbant l'eau par toute la tige mais n'aiment pas être noyées ! 10 cm d'eau suffisent largement. Pensez juste à ajuster tous les jours ou tous les 2 jours, mais avec modération.",
            R.drawable.tulipe)
        addToList("Yuca", "Laisser sécher le terreau en surface entre 2 arrosages.1 à 2 arrosages/semaine suffisent. Tous les 15 jours environ, vous pourrez lui apporter de l’engrais liquide.",
            R.drawable.yuca)
        addToList("Lavande", "De fait, elle est généralement assez facile à entretenir. Une fois plantée, elle se développera toute seule. Vous allez ainsi pouvoir profiter de toutes ses vertus en terme de décoration extérieure.",
            R.drawable.lavande)
        addToList("Jasmin", "Ce jasmin grimpant a besoin d’une lumière vive pour prospérer. À l’intérieur, placer la potée près d’une baie vitrée, mais éviter le soleil direct.",
            R.drawable.jasmin)
        addToList("Camelia", "jusqu'à -20°C pour certaines variétés en pleine terre, -5°C en pot, les camélias à fleurs simple sont souvent plus résistant au gel\n" +
                "Exposition, mi-ombre, orientation nord-ouest idéale, évitez les vents froids",
            R.drawable.camelia)
        addToList("Pivoine", "Les pivoines n’ont pas besoin d’un arrosage trop important. En revanche, en période de sécheresse, où les températures sont particulièrement hautes, il convient de leur apporter de l’eau pour que le sol soit humide.",
            R.drawable.pivoine)
    }
}