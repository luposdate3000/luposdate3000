class NetworkPackage(
    val sourceAddress: Int,
    val destinationAddress : Int,
    val data: Any) {

    class ParkingObservation(val isOccupied: Boolean)
    class ObservationEnd
}