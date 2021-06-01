package sensor

data class ParkingSample(
    val timeInHHMM: String,
    val isOccupied: Boolean,
    val parkingSpotID: String,
    val area: String)

