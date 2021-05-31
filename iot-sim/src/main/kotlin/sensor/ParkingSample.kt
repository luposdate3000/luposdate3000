package sensor

class ParkingSample(
    val timeInHHMM: String,
    val isOccupied: Boolean,
    val parkingSpotID: String,
    val area: String) {

    fun toRDFString(): String {
        return ""
    }
}