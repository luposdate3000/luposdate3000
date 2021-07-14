package lupos.simulator_iot.measure

internal class Measurement {

    // topology
    internal var numberOfDevices = 0
    internal var numberOfSensorsDevices = 0
    internal var numberOfDatabasesDevices = 0
    internal var numberOfQuerySenders = 0
    internal var numberOfLinks = 0

    // times
    internal var initializationDurationInSec: Double = 0.0
    internal var realSimulationDurationInSec: Double = 0.0
    internal var simulationDurationInSec: Double = 0.0
    internal var realStartUpTimeStampInISO: String = ""
    internal var shutDownTimeStampInISO: String = ""
    internal var realShutDownTimeStampInISO: String = ""

    // traffic
    internal var numberOfSentPackages = 0
    internal var networkTrafficInBytes: Long = 0
    internal var numberOfParkingSamplesMade: Int = 0
    internal var numberOfQueriesRequested: Int = 0
    internal var numberOfSentDatabasePackages: Int = 0
    internal var numberOfSentSamplePackages: Int = 0
    internal var numberOfSentDIOPackages: Int = 0
    internal var numberOfSentDAOPackages: Int = 0
    internal var numberOfForwardedPackages: Int = 0


}
