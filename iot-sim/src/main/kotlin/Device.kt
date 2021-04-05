import com.javadocmd.simplelatlng.LatLng


class Device(
    val powerSupply: PowerSupply,
    val location: LatLng,
    val name: String,
    val application: Entity?,
    val sensors: MutableList<Sensor> = ArrayList()

)
{

    val networkCard = NetworkCard(this)

    fun broadCast() {
        //TODO GetLinks(), //hier muss eine Unterscheidung zwischen Sensoren und DODAG getroffen werden
        //TODO Setze eigenen Rang
        //TODO Wähle Elternknoten aus

    }
}