package fr.isen.kenza.smartgreen.plante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.kenza.smartgreen.R
import fr.isen.kenza.smartgreen.RecyclerView_Adapter
import fr.isen.kenza.smartgreen.capteur.SensorValue
import fr.isen.kenza.smartgreen.databinding.ActivityPlantBinding

class PlantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlantBinding
    lateinit var adapter: RecyclerView_Adapter
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var detailHum = mutableListOf<String>()

    private var imagesList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlantrecyclerView.layoutManager = LinearLayoutManager(this)
        binding.PlantrecyclerView.adapter= PlantAdapter(titlesList, descList, imagesList) {
            val intent = Intent(this, SensorValue::class.java)
            startActivity(intent)
        }

        postToList()
/*
        binding.PlantrecyclerView.setOnClickListener{
            val intent = Intent(this, SensorValue::class.java)
            startActivity(intent)
        }
*/


    }

    //rempli la liste
    private fun addToList(title: String, description: String, image: Int){
        titlesList.add(title)
        descList.add(description)
        imagesList.add((image))
       // detailHum.add(detail)

//barre de recherche
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

    //ajoute titres + info + images

    private fun postToList(){

        addToList("Rose", "Exposition : Lumi??re ambiante du soleil mais indirecte.",
                R.drawable.rose)

        addToList("Aloe Vera", "L???entretien de l???alo?? vera doit ??tre r??gulier mais mod??r?? : pensez ?? arroser 1 fois par semaine environ, surtout lorsque le sol est sec en surface. ",
                R.drawable.aloevera)

        addToList("Anthurium", "La temp??rature d???une maison ou d???un appartement est id??al car elle doit ??tre comprise en 18 et 21?? toute l???ann??e.",
                R.drawable.anthurium)
        addToList("Cactus",  "Arrosez uniquement votre cact??e si le terreau est sec..",
                R.drawable.cactus)
        addToList("Violette","La violette africaine a besoin",
                R.drawable.violette1)
        addToList("Cyclamen", "Le cyclamen a besoin de fra??cheur pour rester beau ",
                R.drawable.rose)
        addToList("Orchidee", "La temp??rature ad??quate pour les orchid??es est comprise entre 20 et 25?? C. Il faudra arroser l???orchid??e environ 1 fois tous les 10 jours en p??riode de floraison et tous les 20 jours environ quand l???orchid??e est d??fleurie. ",
                R.drawable.orchidee)
        addToList("Basilic", "Choisissez un endroit lumineux et ensoleill??, le bord d'une fen??tre par exemple. Arrosez d??s que le sol est sec.",
                R.drawable.basilic)
        addToList("Romarin", "Offrez-lui une exposition lumineuse et chaude. Abritez-le des vents froids. Installez-le plein sud ou sud-ouest. Le romarin est sobre. L'arrosage n'est utile qu'?? la plantation et l'??t?? qui suit. Il se contentera de la pluie les ann??es suivantes.",
                R.drawable.romarin)
        addToList("Menthe", "se cultive de pr??f??rence seule et demande un substrat toujours humide sauf en hiver. Elle pr??f??re une terre riche tout au long de l???ann??e. Expos??e ?? l???ombre ou en lumi??re tamis??e, la menthe devient tr??s r??sistante. Selon les esp??ces, elle est rustique jusqu????? -5 ou -15??C.",
                R.drawable.menthe)
        addToList("Ciboulette", "L???id??al est de surmonter la ciboulette d???une cloche en verre ou de la mettre dans une petite serre.\n" +
                "La ciboulette peut pousser en hiver si les temp??ratures restent sup??rieures ?? 10-12??.",
                R.drawable.ciboulette)
        addToList("Persil", "Arroser en cas de fortes chaleurs ou de s??cheresse prolong??e. Le persil redoute en effet les coups de chauds qui ont tendance ?? le faire jaunir.",
                R.drawable.persil)
        addToList("Hibiscus", "L'hibiscus a besoin de beaucoup de lumi??re, mais jamais de soleil direct qui risque de br??ler la fleur. L'id??al est de la placer derri??re une fen??tre bien expos??e, mais avec un rideau occultant. Pour bien s'??panouir, l'hibiscus a besoin d'au moins 6 heures de soleil par jour.",
                R.drawable.hibiscus)
        addToList("Tulipe", "Les tulipes boivent beaucoup en absorbant l'eau par toute la tige mais n'aiment pas ??tre noy??es ! 10 cm d'eau suffisent largement. Pensez juste ?? ajuster tous les jours ou tous les 2 jours, mais avec mod??ration.",
                R.drawable.tulipe)
        addToList("Yuca", "Laisser s??cher le terreau en surface entre 2 arrosages.1 ?? 2 arrosages/semaine suffisent. Tous les 15 jours environ, vous pourrez lui apporter de l???engrais liquide.",
                R.drawable.yuca)
        addToList("Lavande", "De fait, elle est g??n??ralement assez facile ?? entretenir. Une fois plant??e, elle se d??veloppera toute seule. Vous allez ainsi pouvoir profiter de toutes ses vertus en terme de d??coration ext??rieure.",
                R.drawable.lavande)
        addToList("Jasmin", "Ce jasmin grimpant a besoin d???une lumi??re vive pour prosp??rer. ?? l???int??rieur, placer la pot??e pr??s d???une baie vitr??e, mais ??viter le soleil direct.",
                R.drawable.jasmin)
        addToList("Camelia", "jusqu'?? -20??C pour certaines vari??t??s en pleine terre, -5??C en pot, les cam??lias ?? fleurs simple sont souvent plus r??sistant au gel\n" +
                "Exposition, mi-ombre, orientation nord-ouest id??ale, ??vitez les vents froids",
                R.drawable.camelia)
        addToList("Pivoine", "Les pivoines n???ont pas besoin d???un arrosage trop important. En revanche, en p??riode de s??cheresse, o?? les temp??ratures sont particuli??rement hautes, il convient de leur apporter de l???eau pour que le sol soit humide.",
                R.drawable.pivoine)


    }


}