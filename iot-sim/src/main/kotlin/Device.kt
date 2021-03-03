import com.javadocmd.simplelatlng.LatLng


data class Device(
    val powerSupply: PowerSupply,
    val location: LatLng,
    val name: String,
    val application: Entity?,
    val sensors: MutableList<Sensor> = ArrayList())
{

    val networkCard = NetworkCard(this)

}