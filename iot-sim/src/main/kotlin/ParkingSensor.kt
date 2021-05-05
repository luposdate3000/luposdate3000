import java.nio.charset.StandardCharsets

class ParkingSensor(
    var device: Device,
    override var dataSinkAddress: Int
): Device.Sensor {

    class ParkingObservation(val isOccupied: Boolean)

    init {
        sensorCounter++
    }

    companion object {
        var dataRateInSeconds: Int = 30

        var sensorCounter = 0
            private set

        var observationCounter = 0
            private set

        fun resetCounter() {
            observationCounter = 0
            sensorCounter = 0
        }
    }

    override fun observe() {
        device.waitForObservationEnd(dataRateInSeconds.toLong())
    }

    override fun onObservationEnd() {
        val data = packSensorData()
        device.sendRoutedPackage(device.address, dataSinkAddress, data)
        observationCounter++
        observe()
    }

    private fun getParkingObservation()
        = ParkingObservation(RandomGenerator.random.nextBoolean())

    private fun packSensorData(): DatabaseAdapter.DatabasePackage {
        val header = object: Header {
            override val sourceAddress: Int
                get() = device.address
            override val destinationAddress: Int
                get() = dataSinkAddress
            override val participation: Boolean
                get() = false
            override val operate: Boolean
                get() = false
            override val participantsList: IntArray
                get() = intArrayOf()
        }
        val observation = getParkingObservation()
        val insertStatement = "Insert ${observation.isOccupied}"
        val payload = insertStatement.toByteArray(StandardCharsets.UTF_8)
        return DatabaseAdapter.DatabasePackage(header, payload)

    }

}