package lupos.simulator_iot

public expect class IoTSimulation {
    public fun simulate(configFileName: String)
    public fun measureStarPerformance(withDummyDatabase: Boolean)
    public constructor()
}
