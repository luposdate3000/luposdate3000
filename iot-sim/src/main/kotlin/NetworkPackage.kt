class NetworkPackage(
    val sourceAddress: Int,
    val destinationAddress : Int,
    val data: Any) {

    class ParkingObservation(val isOccupied: Boolean)
    class ObservationEnd
    class DIO(val rank: Int)
    class DAO(val isPath: Boolean, val destinations: Set<Int>)
}