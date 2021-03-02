abstract class Sensor(
    val name: String,
    var dataRateInSeconds: Int,
    var dataSink: Device,
    var device: Device
    ) : Entity()