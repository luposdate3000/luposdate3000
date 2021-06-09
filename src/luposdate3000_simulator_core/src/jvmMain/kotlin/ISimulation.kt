package lupos.simulator_core

public interface ISimulation {

    public fun getCurrentClock(): Long
    public fun startSimulation()
    public fun endSimulation()
}
