package lupos.simulator_iot.measure

internal class Measurement {

    // topology
    internal var numberOfDevices: Double = 0.0
    internal var numberOfSensorDevices: Double = 0.0
    internal var numberOfDatabaseDevices: Double = 0.0
    internal var numberOfQuerySenders: Double = 0.0
    internal var numberOfLinks: Double = 0.0

    // times
    internal var initializationDurationInSec: Double = 0.0
    internal var realSimulationDurationInSec: Double = 0.0
    internal var simulationDurationInSec: Double = 0.0
    internal var realStartUpTimeStampInISO: String = ""
    internal var shutDownTimeStampInISO: String = ""
    internal var realShutDownTimeStampInISO: String = ""

    // traffic
    internal var numberOfSentPackages: Double = 0.0
    internal var networkTrafficInKiloBytes: Double = 0.0
    internal var numberOfParkingSamplesMade: Double = 0.0
    internal var numberOfQueriesRequested: Double = 0.0
    internal var numberOfSentDatabasePackages: Double = 0.0
    internal var numberOfSentSamplePackages: Double = 0.0
    internal var numberOfSentDIOPackages: Double = 0.0
    internal var numberOfSentDAOPackages: Double = 0.0
    internal var numberOfForwardedPackages: Double = 0.0
}
